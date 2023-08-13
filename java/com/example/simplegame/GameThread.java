package com.example.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.Random;

public class GameThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private Bitmap background;

    private int GAME_WIDTH;
    private int GAME_HEIGHT;

    private Bit bit;

    private boolean running = false;
    private int latency = 0;
    private static final int SLEEP = 25;

    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.background = BitmapFactory.decodeResource(
                gamePanel.getResources(),
                R.drawable.background);
    }

    @Override
    public void run() {
        this.running = true;
        Random r = new Random();
        int time = r.nextInt(2000) + 1000;
        this.bit = new Bit(this.gamePanel);
        while (this.running) {
            Canvas canvas = this.surfaceHolder.lockCanvas();

            this.GAME_HEIGHT = canvas.getHeight();
            this.GAME_WIDTH = canvas.getWidth();
            Rect backgroundRect = new Rect(0,0,
                    this.GAME_WIDTH,this.GAME_HEIGHT);
            canvas.drawBitmap(this.background, null,
                    backgroundRect, null);

            if (time > 0) {
                time -= SLEEP;
            } else {
                this.bit.draw(canvas);
                this.latency += SLEEP;
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
            try {
                sleep(SLEEP);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (! this.running){
                try {
                    this.join();
                } catch(Exception e){
                    e.printStackTrace();
                } finally {
                    Thread.currentThread().interrupt();
                }
            }
        }
        try {
            this.join();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Thread.currentThread().interrupt();
        }
    }

    public int getLatency(){
        return this.latency;
    }

    public void stopRunning(){
        this.running = false;
    }

    public int checkClickedReturnLatency(Point p) {
        if (this.bit.getRect() != null){
            if (this.bit.getRect().contains(p.x, p.y)){
                return 1;
            }
        }
        return -1;
    }
}
