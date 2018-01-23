package com.vsahin.moneycim.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vsahin.moneycim.R;
import com.vsahin.moneycim.View.MainActivity;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread  thread = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent Intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(Intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}
