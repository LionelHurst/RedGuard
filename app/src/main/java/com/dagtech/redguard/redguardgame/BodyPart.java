package com.dagtech.redguard.redguardgame;

import android.util.Log;

import java.util.Random;

/**
 * Created by Lionel on 11/30/2015.
 */
public class BodyPart {

    private int health, accuracyModifier, hitPercentage, powerMultiplier;
    private String name;
    private boolean isDead = false;
    private Random rand = new Random();
    private int xOffset, yOffset;

    public BodyPart (int health, int powerMultiplier, int accuracyModifier, String name, double xOffset, double yOffset){
        this.health = health;
        this.accuracyModifier = accuracyModifier;
        this.powerMultiplier = powerMultiplier;
        this.name = name;
    }

    public Boolean hit(int power, int accuracy) {
        hitPercentage = rand.nextInt(100);

        Log.e("redguard", "Actual hit number: " + Integer.toString(hitPercentage));
        Log.e("redguard", "Percentage: " + (accuracy - accuracyModifier));

        if (hitPercentage < (accuracy - accuracyModifier)) {
            this.health -= power;
            if (health <= 0)
                isDead = true;

            return true;
        } else {
            return false;
        }
    }

    public double getXOffset() {
        return xOffset;
    }

    public double getYOffset() {
        return yOffset;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getAccuracyModifier() {
        return accuracyModifier;
    }

    public int getDamage(int power) {
        return power * powerMultiplier;
    }

    public int getHealth() {
        return health;
    }

    public String getName() { return name; }
}

