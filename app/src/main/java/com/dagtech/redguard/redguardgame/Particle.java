package com.dagtech.redguard.redguardgame;

import android.graphics.Color;
import android.graphics.Paint;

import com.dagtech.redguard.framework.Graphics;

import java.util.Random;

/**
 * Created by Lionel on 12/14/2015.
 */
public class Particle {
    private Tile currentTile;
    private double x, y, xOffset, yOffset, disX, disY, speedX, speedY;
    private int duration = 10;
    private Random rand = new Random();

    private Paint paint = new Paint();

    public Particle(Tile t, double xOffset, double yOffset) {
        currentTile = t;
        paint.setColor(Color.GREEN);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        speedX = rand.nextDouble() + 0.5;
        speedY = rand.nextDouble()/2;
    }

    public void update(float deltaTime){
        if (duration > 0) {
            duration--;
            disX += speedX;
            disY += speedY;
        }
        x = currentTile.getTileX() - disX;
        y = currentTile.getTileY() - disY;
    }

    public void draw(Graphics g){
        g.drawCircle((float)(x + xOffset), (float)(y - yOffset), 2, paint);
    }
}
