package com.dagtech.redguard.redguardgame;

import android.graphics.Point;
import android.util.Log;

import com.dagtech.redguard.framework.Graphics;
import com.dagtech.redguard.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by Lionel on 11/30/2015.
 */
public class EnemyController {
    private ArrayList<Enemy> controller;
    private Path path;
    private Stream stream;

    public EnemyController(Path path) {
        controller = new ArrayList<>();
        this.path = path;
        stream = new Stream();
    }

    public void addUnit(Enemy g) {
        controller.add(g);
    }

    public void update(float deltaTime) {
        Log.e("REDGUARD", Integer.toString(controller.size()));
        stream.update(deltaTime);

        for (int i = 0; i < controller.size(); i++) {
            controller.get(i).update(deltaTime);
        }
    }

    public void setTarget(DefenceUnit d) {
        int i = 0;
        double shortestDistance;
        Point p1 = new Point((int)d.getCenterX(), (int)d.getCenterY());
        Point p2;
        for (; i < controller.size(); i++) {
            if (!controller.get(i).isDead()) {
                p2 = new Point((int) controller.get(i).getCenterX(), (int) controller.get(i).getCenterY());
                shortestDistance = Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
                if (d.getWeapon().getRange() > shortestDistance) {
                    d.setIsAttacking(true);
                    d.setTarget(controller.get(i));
                    i = controller.size();
                }
            }
        }

        if (i == controller.size()) {
            d.setIsAttacking(false);
        }
    }

    private class Stream {
        private ArrayList<Enemy> units;
        private ArrayList<Float> timer;

        private float counter = 0;
        private int index = 0;

        public Stream() {
            units = new ArrayList<>();
            timer = new ArrayList<>();
            units.add(new Zombie("Zombie", path));
            units.add(new Runner("Zombie", path));
            units.add(new Zombie("Zombie", path));
            units.add(new Zombie("Zombie", path));
            units.add(new Runner("Zombie", path));
            units.add(new Zombie("Zombie", path));
            units.add(new Zombie("Zombie", path));
            timer.add(new Float(400));
            timer.add(new Float(40));
            timer.add(new Float(40));
            timer.add(new Float(100));
            timer.add(new Float(200));
            timer.add(new Float(10));
            timer.add(new Float(10));
        }

        public void update(float deltaTime) {
            if (index < units.size()) {
                counter += deltaTime;
                if (counter >= timer.get(index)) {
                    controller.add(units.get(index));
                    counter = 0;
                    index++;
                }
            }
        }

        public boolean isCompleted(){
            if (index < units.size())
                return false;
            else
                return true;
        }
    }
}