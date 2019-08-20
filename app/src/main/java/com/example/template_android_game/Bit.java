package com.example.template_android_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bit{

    public Bitmap bit;
    private GamePanel game;

    private Rect rect;

    private static final int BIT_WIDTH = 200;
    private static final int BIT_HEIGHT = 100;

    private int x,y;

    private boolean clicked;

    public Bit(GamePanel game){
        this.game = game;
        this.bit = BitmapFactory.decodeResource(this.game.getResources(), R.drawable.bit);
        Log.i("debug", "Game width"+game.getWidth());
        Log.i("debug", "Game height"+game.getHeight());
        Random random = new Random();
        this.y = random.nextInt(game.getHeight()-this.BIT_HEIGHT);
        this.x = random.nextInt(game.getWidth()-this.BIT_WIDTH);
        this.rect = new Rect(this.x, this.y, this.x + BIT_WIDTH, this.y + Bit.BIT_HEIGHT);
        this.clicked = false;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.bit, null, this.rect, null);
    }

    public Rect getRect(){
        return this.rect;
    }

    public boolean is_clicked(){
        return this.clicked == true;
    }

    public void click(){
        this.clicked = true;
    }
}
