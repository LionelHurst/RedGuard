package com.dagtech.redguard.redguardgame;

import com.dagtech.redguard.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Lionel on 12/6/2015.
 */
public class PopupManager {

    private ArrayList<Popup> list = new ArrayList<Popup>();

    public PopupManager() {

    }

    public void addPopup(Tile t, String message, double life) {
        list.add(new Popup(t, message, life));
    }

    public void update() {
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).isDead()) {
                list.remove(i);
                i--;
            }
        }

        for (int i = 0; i < list.size(); i++){
            list.get(i).update();
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < list.size(); i++){
            list.get(i).draw(g);
        }
    }
}
