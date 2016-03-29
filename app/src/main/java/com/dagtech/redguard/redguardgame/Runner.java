
package com.dagtech.redguard.redguardgame;

/**
 * Created by Lionel on 12/29/2015.
 */
public class Runner extends Enemy {
    public Runner(String name, Path path) {
        super(name, path);
        super.setHealth(5);
        super.setDiffX(2);
        super.setDiffY(1);
    }
}
