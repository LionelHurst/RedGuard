package com.dagtech.redguard.redguardgame;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lionel on 4/4/2015.
 */
public class Map {

    private ArrayList<ArrayList<Tile>> tilearray;

    private Path p1 = new Path();
    private Tile startingPoint, endingPoint;
    private int hOffset, vOffset, width, height;

    public Map() {

    }

    public void generateRandomMap(int maxWidth, int maxHeight) {
        this.width = maxWidth;
        this.height = maxHeight;

        tilearray = new ArrayList<ArrayList<Tile>>();
        int i = 0;
        int j = 0;
        hOffset = width * 32;
        vOffset = 50;

        for (i = 0; i < height; i++) {
            tilearray.add(new ArrayList<Tile>());
            for (j = 0; j < width; j++) {
                Tile t = new Tile(i, j, 5, hOffset, vOffset);
                tilearray.get(i).add(t);
            }
        }

        startingPoint = getTile(0, 0);
        endingPoint = getTile(width-1, height-1);

        i = 0;
        j = 0;

        for (; i < height/2; i++) {
            p1.addPoint(getTile(i, j));
        }
        for (; j < width-2; j++) {
            p1.addPoint(getTile(i, j));
        }
        for (; i < height; i++) {
            p1.addPoint(getTile(i, j));
        }
    }

    private void place3x3(int times) {
        Random rand = new Random();
        ArrayList<Point> dumpsters = new ArrayList<Point>();
        Point p = new Point();
        int x, y, i, j, size;

        p.x = rand.nextInt(width - 2);
        p.y = rand.nextInt(height -2);
        dumpsters.add(p);

        i = 0;

        while(i < times) {
            p = new Point(rand.nextInt(width - 2), rand.nextInt(height - 2));

            for (j = 0; j < dumpsters.size(); j ++){
                if ((p.x > dumpsters.get(j).x - 3 && p.x < dumpsters.get(j).x + 3) && (p.y > dumpsters.get(j).y - 3 && p.y < dumpsters.get(j).y + 3)) {
                    p = new Point(rand.nextInt(width - 2), rand.nextInt(height - 2));
                    j = 0;
                }
            }
            dumpsters.add(p);
            i++;
        }

        for (i = 0; i < dumpsters.size(); i++) {
            x = dumpsters.get(i).x;
            y = dumpsters.get(i).y;

            for (j = 0; j < 3; j++) {
                tilearray.get(x).get(y + j).setType(7);
                tilearray.get(x + 1).get(y + j).setType(7);
                tilearray.get(x + 2).get(y + j).setType(7);
            }
        }
    }

    public void update() {
        for (int i = 0; i < tilearray.size(); i++) {
            for (int j = 0; j < tilearray.get(i).size(); j++) {
                tilearray.get(i).get(j).update();
            }
        }

        hOffset += tilearray.get(0).get(0).getSpeedX();
        vOffset += tilearray.get(0).get(0).getSpeedY();

    }

    public void updateSpeed(double speedX, double speedY) {
        for (int i = 0; i < tilearray.size(); i++) {
            for (int j = 0; j < tilearray.get(i).size(); j++) {
                tilearray.get(i).get(j).setSpeedX(speedX);
                tilearray.get(i).get(j).setSpeedY(speedY);
            }
        }
    }

    public void moveMap(double x, double y) {
        hOffset += (int)x;
        vOffset += (int)y;
        for (int i = 0; i < tilearray.size(); i++) {
            for (int j = 0; j < tilearray.get(i).size(); j++) {
                tilearray.get(i).get(j).moveTileX((int) x);
                tilearray.get(i).get(j).moveTileY((int) y);
            }
        }

        //this.update();
    }

    public ArrayList<ArrayList<Tile>> getMap() {
        return tilearray;
    }

    public Path getPath1() {
        return p1;
    }

    public Tile getTile(int x, int y){
        return tilearray.get(x).get(y);
    }

    public int getHOffSet() {
        return hOffset;
    }

    public int getVOffSet() {
        return vOffset;
    }

    public int getCost(Tile t) {
        return tilearray.get(tilearray.lastIndexOf(t)).size();
    }

    public int getMapHeight() {
        return height;
    }

    public int getMapWidth() {
        return width;
    }

    public int getRowWidth(int i) {
        return tilearray.get(i).size();
    }

}
