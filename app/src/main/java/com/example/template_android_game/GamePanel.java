package com.example.template_android_game;

import android.content.Context;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread game;

    public GamePanel(Context context){
        super(context);
        this.game = new GameThread(getHolder(), this);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    public GameThread getThread(){
        return this.game;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        game.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.game.setRunning(false);
    }
}
