package com.example.template_android_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameThread extends Thread {
    /*
    Class for the game mechanic.
    In running the action happens.
     */
    public final static int SLEEP = 50;
    public int GAME_WIDTH;
    public int GAME_HIGHT;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;

    private Bitmap background;
    private Bit bit;

    private boolean running = true;

    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        background = BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.background);
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public Bit getBit (){
        return this.bit;
    }

    public void check_clicked(Point rect){
        if (this.bit.getRect() != null) {
            Log.i("debug", rect.toString());
            Log.i("debug", this.bit.getRect().toString());
            Log.i("debug", ""+this.bit.getRect().contains(rect.x, rect.y));
            if (this.bit.getRect().contains(rect.x, rect.y)) {
                this.bit.click();
            }
        }
    }

    @Override
    public void run(){
        this.running = true;
        Random random = new Random();
        int time = random.nextInt(9000) + 1000;
        Log.i("debug", String.format("time to appear %d ms", time));
        Canvas canvas = this.surfaceHolder.lockCanvas();
        this.GAME_WIDTH = canvas.getWidth();
        this.GAME_HIGHT = canvas.getHeight();
        surfaceHolder.unlockCanvasAndPost(canvas);
        this.bit = new Bit(this.gamePanel);
        int latenz = 0;
        while (this.running){
            canvas = this.surfaceHolder.lockCanvas();
            if (canvas != null){
                Rect backgroundRect = new Rect(0,0,this.GAME_WIDTH, this.GAME_HIGHT);
                canvas.drawBitmap(this.background, null, backgroundRect, null);
                if (time <= 0){
                    this.bit.draw(canvas);
                    latenz += SLEEP;
                }else{
                    Log.i("debug", String.format("time to appear %d ms", time));
                    time -= SLEEP;
                }
                if (this.bit.is_clicked()){
                    this.setRunning(false);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            try{
                sleep(SLEEP);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            if (this.running == false){
                Log.i("debug", "reached");
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setTextSize(50);
                canvas = surfaceHolder.lockCanvas();
                canvas.drawText(String.format("Clicked after %d ms.", latenz), 50, 100, paint);
                this.surfaceHolder.unlockCanvasAndPost(canvas);
                try{
                    this.join();
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    this.stop();
                }
            }
        }
    }

}
