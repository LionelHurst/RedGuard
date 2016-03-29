package com.dagtech.redguard.redguardgame;

import android.graphics.Color;
import android.graphics.Paint;

import com.dagtech.redguard.framework.Graphics;

/**
 * Created by Lionel on 12/6/2015.
 */
public class Popup {

    private Tile t;

    private double x, y, timer, life, disX, disY;
    private double speedY = 2;

    private boolean dead = false;

    private String message;

    private Paint p;

    public Popup(Tile t, String message, double life) {
        p = new Paint();
        p.setColor(Color.BLACK);
        timer = 0;
        this.life = life;
        this.t = t;
        this.message = message;
    }

    public void update() {
        timer++;
        if (timer < life) {
            x = t.getTileX();
            disY += speedY;
            y = t.getTileY() - disY;
        } else {
            dead = true;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void draw(Graphics g) {
        g.drawString(message, (int)x, (int)y, p);
    }
}
