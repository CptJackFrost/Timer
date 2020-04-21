package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTimer;

    private int seconds = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textViewTimer = findViewById(R.id.textViewTimer);
        runTimer();
    }

    public void startTimer(View view) {
        this.isRunning = true;
    }

    public void pauseTimer(View view) {
        this.isRunning = false;
    }

    public void resetTimer(View view) {
        this.isRunning = false;
        seconds = 0;
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String time = String.format("%d:%02d:%02d", hours, minutes, sec);
                textViewTimer.setText(time);

                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }
}
