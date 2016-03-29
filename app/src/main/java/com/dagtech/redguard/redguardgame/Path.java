package com.dagtech.redguard.redguardgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Lionel on 11/30/2015.
 */
public class Path {
    private ArrayList<Tile> path;
    private int totalCost;

    public Path() {
        path = new ArrayList<Tile>();
    }

    public void addPoint(Tile t){
        path.add(t);
        totalCost += t.getCost();
    }

    public void removePoint(Tile t) {
        path.remove(t);
    }

    public ArrayList<Tile> getPath() {
        return path;
    }

    public Tile get(int i) {
        return path.get(i);
    }

    public Tile getLast() {
        return path.get(path.size() - 1);
    }

    public int size() {
        return path.size();
    }

    public int getTotalCost() {
        //ignore the first point because that's the starting point
        return totalCost - this.get(0).getCost();
    }

    public String dumpPath() {
        String p = new String();
        for (int i = 0; i < path.size(); i++) {
            p += " (" + Integer.toString(path.get(i).getX()) + "," + Integer.toString(path.get(i).getY()) + ")";
        }
        return p;
    }

    public String dumpPathCoord() {
        String p = new String();
        for (int i = 0; i < path.size(); i++) {
            p += " (" + Double.toString(path.get(i).getTileX()) + "," + Double.toString(path.get(i).getTileY()) + ")";
        }
        return p;
    }

    public void reset() {
        path.clear();
        path = new ArrayList<Tile>();
    }

    public void reverse() {
        Collections.reverse(path);
    }

}

