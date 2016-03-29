package com.dagtech.redguard.redguardgame;

/**
 * Created by Lionel on 3/30/2015.
 */
import com.dagtech.redguard.framework.Screen;
import com.dagtech.redguard.framework.implementation.AndroidGame;

public class SampleGame extends AndroidGame {
    @Override
    public Screen getInitScreen() {
        return new LoadingScreen(this);
    }

}