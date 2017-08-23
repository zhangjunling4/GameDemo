package com.seek.gamedemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2017/8/22.
 */

public class MyView extends View {
    private int textX = 20, textY = 20;

    public MyView(Context context) {
        super(context);
        setFocusable(true);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
    }


    /**
     * 重写父类绘图函数
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText("Game", textX, textY, paint);

        super.onDraw(canvas);
    }

    /**
     * 重写按键按下的事件函数
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        invalidate();
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 重写按键抬起事件函数
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //判断用户按下的键值是否为方向键的“上下左右”键
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP){
            //“上”按键被电击，应该让文本的Y坐标变小
            textY -= 2;

        }else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
            //“下”按键被电击，应该让文本的Y坐标变大
            textY += 2;
        }else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            //“左”按键被电击，应该让文本的X坐标变小
            textX -= 2;
        }else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            //“右”按键被电击，应该让文本的X坐标变大
            textX += 2;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * 重写触屏事件函数
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        //玩家手指点击屏幕的动作
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//            textX = x;
//            textY = y;
//        }else if (event.getAction() == MotionEvent.ACTION_UP){
//            textX = x;
//            textY = y;
//        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
//            textX = x;
//            textY = y;
//        }

        textX = (int) event.getX();
        textY = (int) event.getY();

        //重绘画布
        invalidate();
        return true;
    }
}
