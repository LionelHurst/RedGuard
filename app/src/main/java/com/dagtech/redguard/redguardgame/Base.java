package com.dagtech.redguard.redguardgame;

import com.dagtech.redguard.framework.Graphics;

/**
 * Created by Lionel on 12/15/2015.
 */
public class Base extends DrawableObject{

    private int health;
    private Tile currentTile;

    public Base() {

    }

    public void hit(int power) {
        health -= power;
    }

    public void update() {

    }

    public void draw(Graphics g) {

    }
}
