package com.seek.gamedemo.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.seek.gamedemo.R;

public class ObjectAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = ObjectAnimatorActivity.class.getSimpleName();
    private ImageView fish;
    private ImageView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐去标题栏(应用程序的)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐去状态栏部分(电池等图标和一些修饰部分)
        this.getWindow().setFlags(WindowManager   .LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_object_animator);

        fish = (ImageView) findViewById(R.id.fish);
        start = (ImageView) findViewById(R.id.start);

        start.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                startPropertyAnimation();
                break;
        }
    }

    /**
     * 向右移动
     */
    private void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(fish, "translationX", 300);
        animator.setDuration(300);
        animator.start();
    }

    private void startPropertyAnimation() {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 500f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.3f, 1f);//缩放动画
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.3f, 1f);

        ObjectAnimator.ofPropertyValuesHolder(fish, pvh1, pvh2, pvh3).setDuration(1000).start();
    }

    private void startValueAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setTarget(fish);
        animator.setDuration(1000).start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                Log.i(TAG, "value:" + value);

            }
        });
    }
}
