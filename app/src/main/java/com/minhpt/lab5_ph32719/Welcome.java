package com.minhpt.lab5_ph32719;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        imageView.startAnimation(bounce);
        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        textView.startAnimation(fade_in);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome.this, DangNhap.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}