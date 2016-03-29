package com.dagtech.redguard.redguardgame;

/**
 * Created by Lionel on 12/15/2015.
 */
public class Zombie extends Enemy {

    public Zombie(String name, Path path) {
        super(name, path);
        super.setHealth(10);
        super.setDiffX(1);
        super.setDiffY(0.5);
    }
}
