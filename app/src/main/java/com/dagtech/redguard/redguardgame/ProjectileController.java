package com.dagtech.redguard.redguardgame;

import com.dagtech.redguard.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Lionel on 12/29/2015.
 */
public class ProjectileController {

    private ArrayList<WeaponProjectile> controller;

    public ProjectileController() {
        controller = new ArrayList<>();
    }

    public void addProjectile(WeaponProjectile p) {
        controller.add(p);
        controller.add(p);
    }

    public void update(float deltaTime) {
        for (int i = 0; i < controller.size(); i++) {
            if (controller.get(i).isDead()) {
                controller.remove(i);
            } else {
                controller.get(i).update();
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < controller.size(); i++) {
            controller.get(i).draw(g);
        }
    }

    public ArrayList<WeaponProjectile> getProjectiles() {
        return controller;
    }
}

