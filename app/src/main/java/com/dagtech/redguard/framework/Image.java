package com.dagtech.redguard.framework;

/**
 * Created by Lionel on 3/30/2015.
 */

import com.dagtech.redguard.framework.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
