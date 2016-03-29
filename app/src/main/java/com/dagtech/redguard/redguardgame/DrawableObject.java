package com.dagtech.redguard.redguardgame;

import com.dagtech.redguard.framework.Graphics;
import com.dagtech.redguard.framework.Image;

/**
 * Created by Lionel on 12/29/2015.
 */
public class DrawableObject {

    private double centerX, centerY, xOffset, yOffset;
    private Tile currentTile;
    //Animation variables
    private int BMP_ROWS;
    private int BMP_COLUMNS;

    private int currentFrame = 0;
    private int width;
    private int height;

    private float animElapsedTime = 0;

    private Image currentAnim;

    public DrawableObject() {

    }

    public void update(float deltaTime){
        animElapsedTime += deltaTime;

        if (animElapsedTime > 20) {
            currentFrame = ++currentFrame % BMP_COLUMNS;
            animElapsedTime = 0;
        }
    }

    public void draw(Graphics g) {
        int srcX = currentFrame * width;
        int srcY = 0;
        g.drawImage(currentAnim,(int)(centerX + xOffset), (int)(centerY - yOffset), srcX, srcY, width, height);
    }

    public void setImage(Image image, int rows, int cols) {
        currentAnim = image;
        BMP_ROWS = rows;
        BMP_COLUMNS = cols;
        this.width = Assets.image.getWidth() / BMP_COLUMNS;
        this.height = Assets.image.getHeight() / BMP_ROWS;
    }

    public void setCurrentTile(Tile t){
        if (currentTile != null) {
            currentTile.removeObject(this);
        }
        this.currentTile = t;
        currentTile.addObject(this);
    }

    public Tile getCurrentTile(){
        return currentTile;
    }

    public int getX() {
        return currentTile.getX();
    }

    public int getY() {
        return currentTile.getY();
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterX(double x) {
        centerX = x;
    }

    public void setCenterY(double y) {
        centerY = y;
    }

    public double getXOffset() {
        return xOffset;
    }

    public double getYOffset() {
        return yOffset;
    }

    public void setXOffset(double x) {
         xOffset = x;
    }

    public void setYOffset(double y) {
        yOffset = y;
    }
}

