package com.dagtech.redguard.redguardgame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.dagtech.redguard.framework.Graphics;

/**
 * Created by Lionel on 12/12/2015.
 */
public class WeaponProjectile extends DrawableObject {
    private double lenX, lenY;
    private int duration, counter;
    private Paint paint = new Paint();

    public WeaponProjectile(Tile t, Point p, int duration) {
        super.setCurrentTile(t);
        lenX = t.getTileX() - (double) p.x;
        lenY = t.getTileY() - (double) p.y;
        paint.setColor(Color.DKGRAY);
        this.duration = duration;
        counter = duration;
    }

    public void update() {
        counter--;
    }

    public void draw(Graphics g) {
        paint.setAlpha((255 / duration) * counter);
        g.drawLine((int) super.getCurrentTile().getTileX() + 32, (int) super.getCurrentTile().getTileY(), (int) (super.getCurrentTile().getTileX() - lenX) + 32, (int) ((int) super.getCurrentTile().getTileY() - lenY), paint);
    }

    public Boolean isDead() {
        if (counter <= 0) {
            return true;
        } else {
            return false;
        }
    }
}