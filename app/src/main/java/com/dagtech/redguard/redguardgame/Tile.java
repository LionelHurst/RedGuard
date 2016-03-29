package com.dagtech.redguard.redguardgame;

import com.dagtech.redguard.framework.Graphics;
import com.dagtech.redguard.framework.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lionel on 4/4/2015.
 */
public class Tile {

    private int type, tileW, tileH, x, y, cost, hOffset, vOffset;
    private double speedX, speedY, tileX, tileY;
    public Image tileImage;
    private ArrayList<DrawableObject> objects;
    public boolean height, isPassable;
    private Random rand;

    public Tile(int x, int y, int type, int hOffset, int vOffset){
        objects = new ArrayList();
        this.x = x;
        this.y = y;
        tileW = 64;
        tileH = 32;
        tileX = ((y * tileW/2) - (x * tileW/2)) + hOffset;
        tileY = ((x * tileH/2) + (y * tileH/2)) + vOffset;
        this.hOffset = hOffset;
        this.vOffset = vOffset;
        speedX = 0;
        speedY = 0;
        rand = new Random();
        this.type = type;
        setType(type);
        height = false;
    }

    public int getCost() {
        return cost;
    }

    public void update() {
        tileX = (((y * tileW/2) - (x * tileW/2)) + hOffset) + speedX;
        tileY = (((x * tileH/2) + (y * tileH/2)) + vOffset) + speedY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getTileX() {
        return tileX;
    }

    public double getTileY() {
        return tileY;
    }

    public int getType() {
        return type;
    }

    public void addObject(DrawableObject d) {
        objects.add(d);
    }

    public void removeObject(DrawableObject d) {
        objects.remove(d);
    }

    public void drawObjects(Graphics g) {
        for (int i = 0; i < objects.size(); i++){
            objects.get(i).draw(g);
        }
    }

    public void setType(int i) {
        tileImage = Assets.tile;
        /*if (i == 5) {
            int randInt = rand.nextInt(3) + 1;
            if (randInt == 1) {
                tileImage = Assets.tile;
            } else if (randInt == 2) {
                //tileImage = com.dagtech.redguard.StartingClass.tileGrass2;
            } else if (randInt == 3) {
                //tileImage = Startingclass.tileGrass3;
            }

            this.tileY -= 50;
            cost = 1;
        } else if (i ==7 ) {
            //tileImage = com.dagtech.redguard.StartingClass.tileblue;
            cost = 2;
        } else if (i == 8) {
            //tileImage = com.dagtech.redguard.StartingClass.tileblock;
            tileY -= 16;
        }*/
        type = i;
    }

    public void setTileX(int tileX){
        this.tileX = tileX;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public void moveTileX(int tileX) {
        hOffset += tileX;
    }

    public void moveTileY(int tileY) {
        vOffset += tileY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }

    public boolean isPassable() {
        return !height;
    }

    public boolean hasHeight() {
        return height;
    }

}
