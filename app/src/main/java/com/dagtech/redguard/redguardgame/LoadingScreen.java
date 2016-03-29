package com.dagtech.redguard.redguardgame; /**
 * Created by Lionel on 3/30/2015.
 */
import com.dagtech.redguard.framework.Game;
import com.dagtech.redguard.framework.Graphics;
import com.dagtech.redguard.framework.Screen;
import com.dagtech.redguard.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        Assets.tile = g.newImage("tile.png", ImageFormat.RGB565);
        Assets.tilePath = g.newImage("tilePath.png", ImageFormat.RGB565);
        Assets.image = g.newImage("image.png", ImageFormat.RGB565);
        Assets.defence = g.newImage("zombie.png", ImageFormat.RGB565);
        Assets.zombie1 = g.newImage("zombie1.png", ImageFormat.RGB565);
        Assets.zombie2 = g.newImage("zombie2.png", ImageFormat.RGB565);
        Assets.zombie3 = g.newImage("zombie3.png", ImageFormat.RGB565);
        Assets.zombie4 = g.newImage("zombie4.png", ImageFormat.RGB565);
        Assets.zombie5 = g.newImage("zombie5.png", ImageFormat.RGB565);
        Assets.zombie6 = g.newImage("zombie6.png", ImageFormat.RGB565);
        Assets.zombie7 = g.newImage("zombie7.png", ImageFormat.RGB565);

        Assets.popup = new PopupManager();
        Assets.particleController = new ParticleController();

        Assets.projectileController = new ProjectileController();

        game.setScreen(new MainMenuScreen(game));

    }


    @Override
    public void paint(float deltaTime) {


    }


    @Override
    public void pause() {


    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }
}