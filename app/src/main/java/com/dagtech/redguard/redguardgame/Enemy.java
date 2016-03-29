package com.dagtech.redguard.redguardgame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.dagtech.redguard.framework.Graphics;
import com.dagtech.redguard.framework.Image;

/**
 * Created by Lionel on 11/30/2015.
 */
public class Enemy extends DrawableObject{

    private int power, health;
    private double diffX, diffY, disX, disY, speedX, speedY;

    protected boolean moving = false;
    private boolean alive = true;
    private Path path;
    private int pathPoint = 0;
    private Base target;

    private String name;

    //body parts declaration
    private BodyPart legs;
    private BodyPart arms;
    private BodyPart head;
    private BodyPart torso;

    private Paint paint = new Paint();

    public Enemy(String name, Path path) {

        super();
        super.setImage(Assets.image, 4, 3);
        super.setXOffset(8);
        super.setYOffset(7);

        this.name = name;
        this.path = path;

        legs =  new BodyPart(2, 1, 20, "Legs", 0, 0);
        arms =  new BodyPart(2, 1, 20, "Arms", 0, 0);
        head =  new BodyPart(1, 2, 40, "Head", 0, 0);
        torso =  new BodyPart(health, 1, 0, "Torso", 0, 0);

        paint.setColor(Color.GREEN);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);

        super.setCurrentTile(path.get(pathPoint));
        if (pathPoint + 1 != path.size() && alive) {

            //if moving east
            if ((path.get(pathPoint + 1).getX() >= path.get(pathPoint).getX()) && (path.get(pathPoint + 1).getY() == path.get(pathPoint).getY())) {
                speedX = -diffX;
                speedY = diffY;
            }

            //if moving west
            if ((path.get(pathPoint + 1).getX() <= path.get(pathPoint).getX()) && (path.get(pathPoint + 1).getY() == path.get(pathPoint).getY())) {
                speedX = diffX;
                speedY = -diffY;
            }

            //if moving north
            if ((path.get(pathPoint + 1).getX() == path.get(pathPoint).getX()) && (path.get(pathPoint + 1).getY() <= path.get(pathPoint).getY())) {
                speedX = -diffX;
                speedY = -diffY;
            }

            //if moving south
            if ((path.get(pathPoint + 1).getX() == path.get(pathPoint).getX()) && (path.get(pathPoint + 1).getY() >= path.get(pathPoint).getY())) {
                speedX = diffX;
                speedY = diffY;
            }

            disX += speedX;
            disY += speedY;

            super.setCenterX((super.getCurrentTile().getTileX() + disX) + super.getXOffset());
            super.setCenterY((super.getCurrentTile().getTileY() + disY) - super.getYOffset());

            //if unit has reached the net tile, remove the last point from the path
            if ((super.getCenterX() - super.getXOffset()) == path.get(pathPoint + 1).getTileX() && super.getCenterY() + super.getYOffset() == path.get(pathPoint + 1).getTileY()) {
                pathPoint++;
                this.setCurrentTile(path.get(pathPoint));
                disX = 0;
                disY = 0;
            }
        } else {
            super.setCenterX(super.getCurrentTile().getTileX() + super.getXOffset());
            super.setCenterY(super.getCurrentTile().getTileY() - super.getYOffset());

        }

    }

    /*public void draw(Graphics g){
        int srcX = currentFrame * width;
        int srcY = 0;
        g.drawImage(currentAnim,(int)centerX, (int)centerY, srcX, srcY, width, height);
    }*/

    public void hit(int power, int accuracy, BodyPart bp){
        if (bp.isDead()) {
            if (torso.hit(power, accuracy)) {
                Assets.popup.addPopup(super.getCurrentTile(), "HIT " + torso.getName() + " " + Integer.toString(bp.getDamage(power)) + " of damage", 30);
                health -= bp.getDamage(power);
            }

        } else {
            if (bp.hit(power, accuracy)) {
                Assets.popup.addPopup(super.getCurrentTile(), "HIT " + bp.getName() + " " + Integer.toString(bp.getDamage(power)) + " of damage", 30);
                health -= bp.getDamage(power);

                if (legs.getHealth() <= 0) {
                    diffX /= 4;
                    diffY /= 4;
                }
            } else {
                Assets.popup.addPopup(super.getCurrentTile(), "MISS!", 30);
            }
        }

        if (health <= 0) {
            die();
        }
    }

    public void die() {
        alive = false;
        super.setXOffset(super.getXOffset() + disX);
        super.setYOffset(super.getYOffset() - disY);
    }

    public void attack() {

    }

    public boolean isDead() {
        return !alive;
    }

    public double getDiffX() {
        return diffX;
    }

    public void setDiffX(double diffX) {
        this.diffX = diffX;
    }

    public double getDiffY() {
        return diffY;
    }

    public void setDiffY(double diffY) {
        this.diffY = diffY;
    }

    public boolean calculateHit(int accuracy, BodyPart bp) {
        return true;
    }

    public Path getPath() {
        return path;
    }

    public double getTileX() {
        return super.getCurrentTile().getTileX();
    }

    public double getTileY() {
        return super.getCurrentTile().getTileY();
    }

    public boolean isMoving() {
        return moving;
    }

    public int getPower() {
        return power;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getCenterX() {
        return super.getCenterX();
    }

    public double getCenterY() {
        return super.getCenterY();
    }

    public void setPower(int power){
        this.power = power;
    }

    public void setSpeedX(int speedX){
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public BodyPart getTorso() {
        return torso;
    }

    public BodyPart getHead() {
        return head;
    }

    public BodyPart getArms() {
        return arms;
    }

    public BodyPart getLegs() {
        return legs;
    }

}

