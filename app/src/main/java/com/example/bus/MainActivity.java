package com.example.bus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(android.R.id.content).postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splashIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(splashIntent);
                finish();


            }
        }, 3000); // Delay for 3000 milliseconds (3 seconds)
    }
}