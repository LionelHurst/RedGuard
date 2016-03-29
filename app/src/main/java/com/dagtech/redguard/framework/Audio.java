package com.dagtech.redguard.framework;

/**
 * Created by Lionel on 3/30/2015.
 */
public interface Audio {
    public Music createMusic(String file);

    public Sound createSound(String file);
}
