package com.seek.gamedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.seek.gamedemo.surface.BitmapMovieSurfaceView;
import com.seek.gamedemo.surface.MySurfaceView;
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

        //设置显示View实例
        setContentView(new BitmapMovieSurfaceView(this));
    }
}
