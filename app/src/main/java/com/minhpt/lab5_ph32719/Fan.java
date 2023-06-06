package com.minhpt.lab5_ph32719;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fan extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);

        imageView = findViewById(R.id.fan);
        Button btn_fast = findViewById(R.id.fast);
        Button btn_medium = findViewById(R.id.medium);
        Button btn_slow = findViewById(R.id.slow);
        Button btn_off = findViewById(R.id.off);

        Animation fast = new AnimationUtils().loadAnimation(this, R.anim.fast);
        Animation medium = new AnimationUtils().loadAnimation(this, R.anim.medium);
        Animation slow = new AnimationUtils().loadAnimation(this, R.anim.slow);
        Animation off = new AnimationUtils().loadAnimation(this, R.anim.off);

        btn_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFan(500);
//                imageView.startAnimation(fast);
            }
        });
        btn_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFan(900);
//                imageView.startAnimation(medium);
            }
        });
        btn_slow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFan(1500);
//                imageView.startAnimation(slow);
            }
        });
        btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopFan();
//                imageView.startAnimation(off);
            }
        });
    }

    private void startFan(long time) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(360)
                        .withEndAction(this)
                        .setDuration(time)
                        .setInterpolator(new LinearInterpolator())
                        .start();
            }
        };
        imageView.animate().rotationBy(360)
                .withEndAction(run)
                .setDuration(time)
                .setInterpolator(new LinearInterpolator())
                .start();
    }

    private void stopFan() {
        imageView.animate().cancel();
    }
}
