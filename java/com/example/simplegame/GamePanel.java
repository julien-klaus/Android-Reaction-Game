package com.example.game;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    public GamePanel(Context context) {
        super(context);
        this.gameThread = new GameThread(getHolder(), this);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        try {
            this.gameThread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameThread getThread() {
        return this.gameThread;
    }
}
