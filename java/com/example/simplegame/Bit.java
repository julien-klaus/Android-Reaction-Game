package com.example.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Bit {

    public Bitmap bit;
    private GamePanel game;
    private Rect rect;

    private static final int BIT_WIDTH = 200;
    private static final int BIT_HEIGHT = 100;

    private int x, y;

    public Bit(GamePanel gamePanel){
        this.game = gamePanel;
        this.bit = BitmapFactory.decodeResource(
                this.game.getResources(),
                R.drawable.bit);
        Random random = new Random();
        this.y = random.nextInt(game.getHeight()-this.BIT_HEIGHT);
        this.x = random.nextInt(game.getWidth()-this.BIT_WIDTH);
        this.rect = new Rect(this.x, this.y,
                this.x+BIT_WIDTH, this.y+ Bit.BIT_HEIGHT);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.bit, null, this.rect, null);
    }

    public Rect getRect() {
        return this.rect;
    }
}
