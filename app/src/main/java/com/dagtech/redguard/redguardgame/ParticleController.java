package com.dagtech.redguard.redguardgame;

import com.dagtech.redguard.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Lionel on 12/14/2015.
 */
public class ParticleController {
    private ArrayList<Particle> controller;

    public ParticleController() {
        controller = new ArrayList<Particle>();
    }

    public void addParticle(Particle p) {
        controller.add(p);
        controller.add(p);
    }

    public void update(float deltaTime) {
        for (int i = 0; i < controller.size(); i++) {
            controller.get(i).update(deltaTime);
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < controller.size(); i++) {
            controller.get(i).draw(g);
        }
    }

    public ArrayList<Particle> getUnits() {
        return controller;
    }
}
