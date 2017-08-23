package com.seek.gamedemo.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by admin on 2017/8/22.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder sfh;
    private Paint paint;
    private int textX = 10, textY = 10;
    private Thread th;
    private boolean flag;//1. 便于消亡线程； 2. 防止重复创建线程及程序异常
    private Canvas canvas;
    private int screenW, screenH;

    public MySurfaceView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);

        setFocusable(true);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenW = this.getWidth();
        screenH = this.getHeight();
        flag = true;

        th = new Thread(this);

        th.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 视图消亡时，响应此函数
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    private void myDraw() {
        try {
            canvas = sfh.lockCanvas();
            if (canvas != null){
                canvas.drawRGB(0,0,0);
                canvas.drawText("GAME", textX, textY, paint);
            }
        }catch (Exception e){

        }finally {
            if (canvas != null){
                sfh.unlockCanvasAndPost(canvas);
            }
        }
//        //刷屏的方式
//        /**
//         *  1. 每次绘图之前重新绘制一张画布
//         *  2. 每次绘图之前在画布上填充一种颜色
//         *  3. 每次绘图之前指定RGB来填充画布
//         *  4. 每次绘图之前绘制一张等同于屏幕大小的图片覆盖在画布之上
//         */
////        canvas.drawRect(0,0,this.getWidth(), this.getHeight(),paint);
//        canvas.drawColor(Color.BLACK);
////        canvas.drawRGB(0,0,0);
//        canvas.drawText("Game", textX, textY, paint);
//        sfh.unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textX = (int) event.getX();
        textY = (int) event.getY();
;
        return true;
    }

    @Override
    public void run() {
        while (flag){
            long start = System.currentTimeMillis();
            myDraw();
            logic();

            long end = System.currentTimeMillis();

            try {
                if ( end - start < 50){
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void logic() {

    }
}
