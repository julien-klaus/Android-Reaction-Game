package com.example.template_android_game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.gamePanel = new GamePanel(this);
        this.gamePanel.setOnTouchListener(this);
        setContentView(this.gamePanel);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Point r = new Point((int)motionEvent.getX(), (int)motionEvent.getY());
        Log.i("debug", "click");
        this.gamePanel.getThread().check_clicked(r);
        return false;
    }
}
