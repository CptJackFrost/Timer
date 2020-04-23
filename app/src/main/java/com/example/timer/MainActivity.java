package com.example.timer;

import androidx.annotation.NonNull;
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
    private boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textViewTimer = findViewById(R.id.textViewTimer);
        if (savedInstanceState != null) {
            this.seconds = savedInstanceState.getInt("seconds");
            this.isRunning = savedInstanceState.getBoolean("isRunning");
            this.wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", this.seconds);
        outState.putBoolean("isRunning", this.isRunning);
        outState.putBoolean("wasRunning", this.wasRunning);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.wasRunning = this.isRunning;
        this.isRunning = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.isRunning = this.wasRunning;
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

    private void runTimer() {
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
