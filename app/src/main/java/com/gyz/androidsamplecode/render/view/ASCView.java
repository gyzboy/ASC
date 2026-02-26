package com.gyz.androidsamplecode.render.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class ASCView extends View {

   private static final String TAG = "ASCView:View";

   public ASCView(Context context) {
      super(context);
   }

   public ASCView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   public ASCView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   // 会多次调用，已知在ViewRootImpl中，可能会测量两次，与父容器也有关
   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//调用时机
//      1.初始布局：
//      当视图第一次被添加到视图层次结构中时，系统需要测量和布局所有视图。这会触发performMeasure()，以确定视图的大小。
//      2.调用requestLayout()：
//      当requestLayout()被调用时，系统会标记视图树需要重新测量和布局。这通常发生在视图的大小、位置或内容发生变化时。
//      例如，调用setLayoutParams()修改视图的布局参数会触发requestLayout()。
//      3.父视图请求重新布局：
//      如果父视图调用了requestLayout()，则其所有子视图也需要重新测量和布局。这会导致performMeasure()被调用。
//      4.屏幕旋转或配置变化：
//      当设备旋转或其他配置（如语言、字体大小）发生变化时，系统会重新测量和布局视图树，触发performMeasure()。
//      5.视图树的可见性变化：
//      在某些情况下，视图的可见性变化（例如从GONE变为VISIBLE）可能会导致视图需要重新测量。
//      6.动态内容变化：
//      某些视图（如TextView）的内容变化可能需要重新测量，以适应新内容的大小。
      Log.d(TAG,"onMeasure");
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//      onMeasure()需要处理三种测量模式：
//      MeasureSpec.EXACTLY--- MATCH_PARENT or 精确数字
//      MeasureSpec.AT_MOST--- WRAP_CONTENT
//      MeasureSpec.UNSPECIFIED。
//      setMeasuredDimension()方法设置视图的测量宽度和高度

      // 如何优化：
      // 1.使用固定尺寸
      // 2.使用invalidate代替requestLayout
      // 3.使用ViewStub懒加载View
   }

   // 当调用requestLayout()时，系统会重新测量和布局视图树，这会导致onLayout()在需要时被调用
   // 如果父视图的布局发生变化，可能会导致子视图的onLayout()被调用，因为子视图的位置和大小可能需要调整
   @Override
   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      Log.d(TAG,"onLayout");
      super.onLayout(changed, left, top, right, bottom);
   }

   @Override
   protected void onAttachedToWindow() {
      Log.d(TAG,"onAttachedToWindow");
      super.onAttachedToWindow();
   }

   @Override
   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      Log.d(TAG,"onSizeChanged");
      super.onSizeChanged(w, h, oldw, oldh);
   }

   @Override
   protected void onFinishInflate() {
      Log.d(TAG,"onFinishInflate");
      super.onFinishInflate();
   }

   @Override
   protected void onWindowVisibilityChanged(int visibility) {
      Log.d(TAG,"onWindowVisibilityChanged:"+visibility);
      super.onWindowVisibilityChanged(visibility);
   }

   @Override
   protected void onAnimationStart() {
      Log.d(TAG,"onAnimationStart");
      super.onAnimationStart();
   }

   @Override
   protected void onAnimationEnd() {
      Log.d(TAG,"onAnimationEnd");
      super.onAnimationEnd();
   }

   @Override
   protected void onDetachedFromWindow() {
      Log.d(TAG,"onDetachedFromWindow");
      super.onDetachedFromWindow();
   }

   @Override
   protected void onDraw(@NonNull Canvas canvas) {
      Log.d(TAG,"onDraw");
      super.onDraw(canvas);
   }

//   直接调用setVisibility()：当通过代码直接调用setVisibility(View.VISIBLE)、setVisibility(View.INVISIBLE)或setVisibility(View.GONE)时，会触发onVisibilityChanged()。
//   父视图可见性变化：如果父视图的可见性发生变化，子视图的onVisibilityChanged()也可能被调用，因为子视图的实际可见性取决于父视图的可见性。
//   窗口焦点变化：当窗口获得或失去焦点时，视图的可见性可能会被重新评估，从而导致onVisibilityChanged()被调用
//   当Activity的生命周期状态发生变化（如启动、暂停、恢复等）时，视图的可见性可能会触发变化。
   @Override
   protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
      Log.d(TAG,"onVisibilityChanged:"+visibility);
      super.onVisibilityChanged(changedView, visibility);
   }

   @Override
   protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
      Log.d(TAG,"onFocusChanged");
      super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
   }

   @Override
   public void invalidate() {
      Log.d(TAG,"invalidate");
      super.invalidate();
   }

   @Override
   public void requestLayout() {
      Log.d(TAG,"requestLayout");
      super.requestLayout();
   }
}
