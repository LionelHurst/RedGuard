package com.dagtech.redguard.framework.implementation;

/**
 * Created by Lionel on 3/30/2015.
 */
import java.util.List;

import android.view.View.OnTouchListener;

import com.dagtech.redguard.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}
