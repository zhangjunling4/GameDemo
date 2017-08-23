package com.seek.gamedemo.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by admin on 2017/8/23.
 */

public class SineCurveView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Canvas canvas;
    private Paint paint;
    private boolean flag;

    private Path path;
    private int x = 0, y = 0;

    public SineCurveView(Context context) {
        super(context);

        initView();
    }

    private void initView() {
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);

        paint = new Paint();
        paint.setColor(Color.BLACK);

        path = new Path();
        path.moveTo(0, 400);
        this.setKeepScreenOn(true);
    }

    public SineCurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SineCurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {
        while (flag){
            myDraw();
            x+=1;
            y = (int) (100*Math.sin(x * 2 * Math.PI / 180) + 400);

            path.lineTo(x, y);
        }
    }

    private void myDraw() {
        try {
            canvas = mHolder.lockCanvas();

            canvas.drawColor(Color.WHITE);
            canvas.drawPath(path, paint);
        }catch (Exception e){

        }finally {
            if (canvas != null){
                mHolder.unlockCanvasAndPost(canvas);
            }
        }

    }
}
