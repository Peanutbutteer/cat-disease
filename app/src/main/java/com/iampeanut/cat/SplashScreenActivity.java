package com.iampeanut.cat;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    long time = 3000;
    long timer;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.getStartIntent(SplashScreenActivity.this));
                SplashScreenActivity.this.finish();
            }
        };


    }


    @Override
    protected void onResume() {
        super.onResume();
        timer = time;
        handler.postDelayed(runnable, timer);
        time = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = timer - (System.currentTimeMillis() - time);
    }
}
