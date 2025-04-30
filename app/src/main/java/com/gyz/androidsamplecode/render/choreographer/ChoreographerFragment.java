package com.gyz.androidsamplecode.render.choreographer;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.Choreographer;
import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;

import java.util.HashMap;

public class ChoreographerFragment extends BaseFragment {

    private long previousFrameTimeNanos = 0;
    private long previousFrameTimeNanos2 = 0;
    private Choreographer choreographer;
    private View animatedView;
    private float position = 0f;
    private long lastFrameTimeNanos = 0;

    private final Choreographer.FrameCallback performanceCallback = new Choreographer.FrameCallback() {
        @Override
        public void doFrame(long frameTimeNanos) {
            if (previousFrameTimeNanos != 0) {
                long timeDiffNanos = frameTimeNanos - previousFrameTimeNanos;
                double timeDiff = timeDiffNanos / 1_000_000.0;
                sb.append("帧间隔:").append(timeDiff).append("ms").append("\n");
            }
            previousFrameTimeNanos = frameTimeNanos;
            choreographer.postFrameCallback(this);
        }
    };

    private final Choreographer.FrameCallback animationCallback = new Choreographer.FrameCallback() {
        @Override
        public void doFrame(long frameTimeNanos) {
            if (lastFrameTimeNanos != 0) {
                long elapsedTimeNanos = frameTimeNanos - lastFrameTimeNanos;
                float elapsedTimeMillis = elapsedTimeNanos / 1_000_000f;

                position += elapsedTimeMillis;

                // Apply the new position to the view
                animatedView.setTranslationX(position);
                // Stop the animation
                if (position > 200.f) {
                    choreographer.removeFrameCallback(this);
                    return;
                }
            }
            lastFrameTimeNanos = frameTimeNanos;
            choreographer.postFrameCallback(this);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        choreographer = Choreographer.getInstance();
        opts.add("工作流程");
        opts.add("性能监测开始");
        opts.add("性能监测结束");
        opts.add("精确动画帧控制");
        opts.add("模拟耗时检测");
    }

    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        switch (index) {
            case 0:
                sb.setLength(0);
                sb.append("1、初始化:\n" +
                        "- ThreadLocal 新建对象\n" +
                        "- 设置处理消息的 Handler\n" +
                        "- ");
                break;
            case 1:
                choreographer.postFrameCallback(performanceCallback);
                break;
            case 2:
                choreographer.removeFrameCallback(performanceCallback);
                content.setText(sb.toString());
                break;
            case 3:
                animatedView = v;
                choreographer.postFrameCallback(animationCallback);
                break;
            case 4:
                LogMonitor.getInstance().startMonitor();
                choreographer.postFrameCallback(performanceCallback);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10000; i++) {
                            try {
                                Thread.sleep(50);
                                System.out.println("in sleeping");
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        choreographer.removeFrameCallback(performanceCallback);
        choreographer.removeFrameCallback(animationCallback);
    }

    public static class LogMonitor {
        private static final String TAG = "LogMonitor";
        private HandlerThread mLogThread = new HandlerThread("log");
        private static LogMonitor sInstance = new LogMonitor();
        private static Handler mIoHandler;
        private static final long TIME_BLOCK = 50L;
        private LogMonitor() {
            mLogThread.start();
            mIoHandler = new Handler(mLogThread.getLooper());
        }

        private static Runnable mLogRunnable = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
                for (StackTraceElement s : stackTrace) {
                    sb.append(s.toString() + "\n");
                }
                System.out.println(TAG + " | " + sb.toString());
                mIoHandler.postDelayed(mLogRunnable, TIME_BLOCK);
            }
        };

        public static LogMonitor getInstance() {
            return sInstance;
        }
//        public boolean isMonitor() {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                return mIoHandler.hasCallbacks(mLogRunnable);
//            }
//            return false;
//        }
        public void startMonitor() {
            mIoHandler.postDelayed(mLogRunnable, TIME_BLOCK);
        }
        public void removeMonitor() {
            mIoHandler.removeCallbacks(mLogRunnable);
        }
    }
}
