package com.dagtech.redguard.redguardgame;

/**
 * Created by Lionel on 12/9/2015.
 */
public class Weapon {

    private double range;
    private int power, rateOfFire, accuracy, clipSize, reloadSpeed, currentAmmo, currentReload;
    private Boolean isReloading = false;

    public Weapon(double range, int rateOfFire, int power, int accuracy, int clipSize, int reloadSpeed){
        this.range = range;
        this.rateOfFire = rateOfFire;
        this.power = power;
        this.accuracy = accuracy;
        this.clipSize = clipSize;
        this.currentAmmo = clipSize;
        this.reloadSpeed = reloadSpeed;
        currentReload = 0;
    }

    public void fire() {
        currentAmmo--;
        if (currentAmmo <= 0)
            isReloading = true;
    }

    public void reload() {
        currentReload++;
        if (currentReload == reloadSpeed) {
            isReloading = false;
            currentAmmo = clipSize;
            currentReload = 0;
        }
    }

    public Boolean getIsReloading() {
        return isReloading;
    }

    public String getReloadingTime() {
        return Integer.toString(currentReload);
    }

    public int getRateOfFire() {
        return rateOfFire;
    }

    public double getRange(){
        return range;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }
}
