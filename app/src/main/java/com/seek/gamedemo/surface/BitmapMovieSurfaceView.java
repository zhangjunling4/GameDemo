package com.seek.gamedemo.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.seek.gamedemo.R;


/**
 * Created by admin on 2017/8/22.
 */

public class BitmapMovieSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private Bitmap bmp;
    private int bmpX, bmpY;

    private int textX = 10, textY = 10;
    private Thread th;
    private boolean flag;//1. 便于消亡线程； 2. 防止重复创建线程及程序异常

    private SurfaceHolder sfh;
    private Canvas canvas;
    private int screenW, screenH;
    private Paint paint;
    private Bitmap[] fishBmp = new Bitmap[10];
    private int currentFrame;

    public BitmapMovieSurfaceView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();

        for (int i=0; i<fishBmp.length; i++){
            fishBmp[i] = BitmapFactory.decodeResource(this.getResources(), R.drawable.fish0 + i);
        }

        setFocusable(true);
    }

    public BitmapMovieSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapMovieSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.water);
        bmpX = -bmp.getWidth() + this.getWidth();
        bmpY = this.getHeight() - bmp.getHeight();
        screenW = this.getWidth();
        screenH = this.getHeight();
        flag = true;

        th = new Thread(this);

        th.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    private void myDraw() {
        try {
            canvas = sfh.lockCanvas();
            if (canvas != null){
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bmp, bmpX, bmpY, paint);
                canvas.drawBitmap(fishBmp[currentFrame], 0, 0, paint);
            }
        }catch (Exception e){

        }finally {
            if (canvas != null){
                sfh.unlockCanvasAndPost(canvas);
            }
        }
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
        bmpX += 5;

        currentFrame ++;
        if (currentFrame >= fishBmp.length){
            currentFrame = 0;
        }
    }
}
