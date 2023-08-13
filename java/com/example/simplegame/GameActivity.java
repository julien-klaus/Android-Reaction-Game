package com.example.game;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

public class GameActivity extends ComponentActivity implements View.OnTouchListener {

    private GamePanel gamePanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.gamePanel = new GamePanel(this);
        this.gamePanel.setOnTouchListener(this);
        setContentView(this.gamePanel);


    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Point p = new Point((int)event.getX(), (int)event.getY());
            int latency = this.gamePanel.getThread().
                    checkClickedReturnLatency(p);
            if (latency == 1){
                this.gamePanel.getThread().stopRunning();
                Intent i = new Intent(this.gamePanel.getContext(),
                        MainActivity.class);
                i.putExtra("latency",
                        this.gamePanel.getThread().getLatency());
                startActivity(i);
            }
        }
        return true;
    }
}
