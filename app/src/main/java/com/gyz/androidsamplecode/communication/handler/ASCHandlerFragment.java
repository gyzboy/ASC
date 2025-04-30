package com.gyz.androidsamplecode.communication.handler;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.support.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ASCHandlerFragment extends BaseFragment {
    private static int barrier;


    // 异步消息handler
    private static final Handler asyncHandler = Handler.createAsync(Looper.getMainLooper(), msg -> {
        sb.append("这是异步消息\n");
        if (msg.obj != null) {
            TextView tv = (TextView) msg.obj;
            tv.setText(sb.toString());
        }
        removeSyncBarrier();
        return false;
    });

    private static final Handler myHandle = new MyHandle(Looper.getMainLooper(), msg -> {
        if (msg.obj != null) {
            TextView tv = (TextView) msg.obj;
            if (msg.arg1 == -1) {
                sb.append("这是插入到头部的信息");
            }
            sb.append("\n");
            tv.setText(sb.toString());
        }
        // false继续向下传递
        return true;
    });

    @Override
    public void handleClick(View v) {
        sb.setLength(0);
        // 从以sPool为头节点的链表中取出头部Message,将sPool执行下一条Message
        Message message = Message.obtain();
        switch ((int) v.getTag()) {
            case 0:
                message.what = 1;
                message.obj = content;
                sb.append("sendEmptyMessage只包含what信息\n");
                content.setText(sb.toString());
                myHandle.sendEmptyMessage(1);
                break;
            case 1:
                message.what = 1;
                message.obj = content;
                sb.append("sendMessage可以包含除what外信息\n");
                myHandle.sendMessage(message);
                break;
            case 2:
                Message msg = Message.obtain(myHandle, () -> {
                    sb.append("message callback为第一优先级，会拦截后续callback\n");
                    sb.append("handler callback为第二优先级 返回false继续向下传递\n");
                    sb.append("自定义handler callback为第三优先级\n");
                    content.setText(sb.toString());
                });
                msg.what = 2;
                msg.obj = content;
                myHandle.sendMessage(msg);
                break;
            case 3:
                Message delayMsg = Message.obtain(myHandle, () -> {
                    sb.append("这是延迟 message，并非在 Delay 的时刻立即执行，执行时刻受唤醒误差和线程任务阻塞的影响必然晚于 Delay 时刻\n");
                    content.setText(sb.toString());
                });
                myHandle.sendMessageDelayed(delayMsg, 1000);
                message.obj = content;
                myHandle.sendMessage(message);
                break;
            case 4:
                for (int k = 0; k < 5; k++) {
                    Message mm = Message.obtain(myHandle, () -> sb.append("这是普通消息\n"));
                    myHandle.sendMessage(mm);
                }
                message.obj = content;
                message.arg1 = -1;
                sb.append("- 非延时 Message 并非立即执行，只是放入 MessageQueue 等待调度而已，执行时刻不确定这也就是为什么插到头部的message大概率在普通message之前执行\n\n");
                myHandle.sendMessageAtFrontOfQueue(message);
                break;
            case 5:
                sb.append("建立同步屏障必须早于需要屏蔽的同步 Message，否则无效\n");
                postSyncBarrier();
                for (int k = 0; k < 5; k++) {
                    Message mm = Message.obtain(myHandle, () -> sb.append("这是同步信息\n"));
                    mm.obj = content;
                    mm.arg1 = k;
                    myHandle.sendMessage(mm);
                }
                Message asyncMsg = Message.obtain(myHandle, () -> {
                    sb.append("这是异步信息\n");
                    content.setText(sb.toString());
                    // 同步屏障使用完之后记得移除，否则后续的 Message 永远阻塞
                    removeSyncBarrier();
                });
                asyncMsg.setAsynchronous(true);
                asyncMsg.obj = content;
                asyncMsg.arg1 = -2;
                myHandle.sendMessage(asyncMsg);
                break;
            case 6:
                postSyncBarrier();
                for (int k = 0; k < 5; k++) {
                    Message mm = Message.obtain();
                    mm.obj = content;
                    mm.arg1 = k;
                    myHandle.sendMessage(mm);
                }
                message.obj = content;
                asyncHandler.sendMessage(message);
                break;
            case 7:
                myHandle.getLooper().getQueue().addIdleHandler(() -> {
                    sb.append("idleHandler invoke\n");
                    content.setText(sb.toString());
                    // 返回true会每次空闲的时候执行
                    return false;
                });
                for (int k = 0; k < 5; k++) {
                    Message mm = Message.obtain();
                    mm.obj = content;
                    mm.arg1 = k;
                    myHandle.sendMessage(mm);
                }
                for (int k = 0; k < 5; k++) {
                    Message mm = Message.obtain();
                    mm.obj = content;
                    mm.arg1 = k;
                    mm.arg2 = 100;
                    myHandle.sendMessageDelayed(mm, 1000);
                }
                break;
        }
    }

    private static class MyHandle extends Handler {

        public MyHandle(@NonNull Looper looper, @Nullable Callback callback) {
            super(looper, callback);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.obj != null) {
                TextView tv = (TextView) msg.obj;
                tv.setText(sb.toString());
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("发送empty信息");
        opts.add("发送普通信息");
        opts.add("检测事件处理优先级");
        opts.add("发送延迟消息");
        opts.add("发送消息至队列头部");
        opts.add("设置消息类型为异步消息");
        opts.add("通过异步Handler发送异步消息");
        opts.add("发送IdleHandler");
    }


    private static void postSyncBarrier() {
        try {
            @SuppressLint("DiscouragedPrivateApi")
            MessageQueue queue = myHandle.getLooper().getQueue();
            Method postSyncBarrierMethod = MessageQueue.class.getDeclaredMethod("postSyncBarrier");
            postSyncBarrierMethod.setAccessible(true);
            barrier = (int) postSyncBarrierMethod.invoke(queue, new Object());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }

    private static void removeSyncBarrier() {
        try {
            @SuppressLint("DiscouragedPrivateApi")
            MessageQueue queue = myHandle.getLooper().getQueue();
            Method removeSyncBarrierMethod = MessageQueue.class.getDeclaredMethod("removeSyncBarrier", int.class);
            removeSyncBarrierMethod.setAccessible(true);
            removeSyncBarrierMethod.invoke(queue, barrier);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }
}
