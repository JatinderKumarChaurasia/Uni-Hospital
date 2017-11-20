package com.example.jatinderkumar.spla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView txt1 =(TextView)  findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        txt1.setAnimation(animation);
        Thread thread = new Thread()
        {
            public void run()
            {
                try {
                    sleep(5000);
                    Intent intent = new Intent(splash.this,Login.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
