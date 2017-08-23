package com.seek.gamedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.seek.gamedemo.activity.BitMapMovieActivity;
import com.seek.gamedemo.activity.SineCurveActivity;
import com.seek.gamedemo.activity.SurfaceViewDemoActivity;
import com.seek.gamedemo.activity.ViewDemoActivity;
import com.seek.gamedemo.surface.BitmapMovieSurfaceView;
import com.seek.gamedemo.surface.MySurfaceView;
import com.seek.gamedemo.surface.SineCurveView;
import com.seek.gamedemo.view.MyView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //隐去标题栏(应用程序的)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐去状态栏部分(电池等图标和一些修饰部分)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //设置显示View实例
//        setContentView(new BitmapMovieSurfaceView(this));

        findViewById(R.id.tv_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewDemoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_surfaceview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SurfaceViewDemoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_bitmap_movie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BitMapMovieActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_sine_curve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SineCurveActivity.class);
                startActivity(intent);
            }
        });
    }
}
