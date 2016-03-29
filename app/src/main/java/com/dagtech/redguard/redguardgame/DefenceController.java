package com.dagtech.redguard.redguardgame;

import com.dagtech.redguard.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Lionel on 12/14/2015.
 */
public class DefenceController {
    private ArrayList<DefenceUnit> controller;

    public DefenceController() {
        controller = new ArrayList<DefenceUnit>();
    }

    public void addUnit(DefenceUnit d) {
        controller.add(d);
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

    public ArrayList<DefenceUnit> getUnits() {
        return controller;
    }
}
