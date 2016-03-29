package com.dagtech.redguard.redguardgame; /**
 * Created by Lionel on 3/30/2015.
 */
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.dagtech.redguard.framework.Game;
import com.dagtech.redguard.framework.Graphics;
import com.dagtech.redguard.framework.Screen;
import com.dagtech.redguard.framework.Input.TouchEvent;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    // Variable Setup
    // You would create game objects here.

    int livesLeft = 1;
    Paint paint;
    private Map map;
    private ArrayList<Point> touchDrag = new ArrayList();
    private EnemyController enemyController;
    private DefenceController defenceController;

    public GameScreen(Game game) {
        super(game);

        // Initialize game objects here
        map = new Map();
        map.generateRandomMap(10, 10);

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        enemyController = new EnemyController(map.getPath1());

        defenceController = new DefenceController();

        //defenceUnit1 = new DefenceUnit(map.getTile(3, 3), new Weapon(80, 15, 1, 60, 20, 200), "legs");
        //defenceUnit2 = new DefenceUnit(map.getTile(4, 9), new Weapon(150, 40, 5, 100, 4, 200), "head");
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        //if (touchEvents.size() > 0)
        state = GameState.Running;

    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

        //This is identical to the update() method from our Unit 2/3 game.


        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN) {
                touchDrag.add(new Point(event.x, event.y));
            }

            if (event.type == TouchEvent.TOUCH_DRAGGED) {
                touchDrag.add(new Point(event.x, event.y));
                if (touchDrag.size() > 1) {
                    if (len == 2) {
                        map.moveMap(touchDrag.get(touchDrag.size() - 1).x - touchDrag.get(touchDrag.size() - 2).x, touchDrag.get(touchDrag.size() - 1).y - touchDrag.get(touchDrag.size() - 2).y);
                        touchDrag.remove(0);
                    }
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if(getTile(event.x, event.y) != null) {
                    defenceController.addUnit(new DefenceUnit(getTile(event.x, event.y), new Weapon(80, 15, 1, 60, 20, 200), "legs"));
                }
            }

        }

        // 2. Check miscellaneous events like death:

        if (livesLeft == 0) {
            state = GameState.GameOver;
        }


        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();

        map.update();
        enemyController.update(deltaTime);

        //Sets the targets for each defence unit
        for (int i = 0; i < defenceController.getUnits().size(); i++) {
            enemyController.setTarget(defenceController.getUnits().get(i));
        }

        defenceController.update(deltaTime);
        Assets.popup.update();
        Assets.particleController.update(deltaTime);
        Assets.projectileController.update(deltaTime);
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 300 && event.x < 980 && event.y > 100
                        && event.y < 500) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1920, 1080, Color.WHITE);
        // First draw the game elements.
        paintTiles();
        paintPath();
        drawObjects();
        //defenceController.draw(g);
        Assets.popup.draw(g);
        Assets.particleController.draw(g);
        // Example:
        // g.drawImage(com.dagtech.redguard.Assets.background, 0, 0);
        // g.drawImage(com.dagtech.redguard.Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void paintTiles(){
        Graphics g = game.getGraphics();
        ArrayList<ArrayList<Tile>> m = map.getMap();
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.get(i).size(); j++) {
                Tile t = map.getTile(i, j);
                g.drawImage(t.getTileImage(), (int) t.getTileX(), (int) t.getTileY());
            }
        }
    }

    private void drawObjects(){
        Graphics g = game.getGraphics();
        ArrayList<ArrayList<Tile>> m = map.getMap();
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.get(i).size(); j++) {
                Tile t = map.getTile(i, j);
                t.drawObjects(g);
                //g.drawImage(t.getTileImage(), (int) t.getTileX(), (int) t.getTileY());
            }
        }
    }

    private void paintPath(){
        Graphics g = game.getGraphics();
        Path p1 = map.getPath1();
        //g.drawString(Integer.toString(p1.size()), (int)p1.get(0).getTileX(), (int)p1.get(0).getTileY(), text);

        for (int i = 0; i < p1.size(); i++) {
            //Tile t = map.getTile(p1.get(i).getX(), p1.get(i).getY());
            g.drawImage(Assets.tilePath, (int) p1.get(i).getTileX(), (int) p1.get(i).getTileY());
            //Log.e("redguard", "Tile " + Integer.toString(i) + " - " + Double.toString(p1.get(i).getTileX()) + "," + Double.toString(p1.get(i).getTileY()));
        }

    }

    private Tile getTile(int x, int y) {
        x -= map.getHOffSet();
        y -= map.getVOffSet();
        double tileW = 64;
        double tileH = 32;

        int hitx = (int)(((double)x / (double) tileW) + ((double) y / (double) tileH) - 0.5);
        int hity = (int)(((double)y / (double) tileH) - ((double) x / (double) tileW) + 0.5);

        if ((hitx >= 0) && (hity >= 0) && (hitx <= map.getMapWidth() - 1) && (hity <= map.getMapHeight() - 1)) {
            Log.e("Redguard", Integer.toString(hitx) + " " + Integer.toString(hity));
            return map.getTile(hity, hitx);
        } else {
            return null;
        }
    }


    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();


    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 640, 300, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        if (state == GameState.Running)
            pause();
        else
            resume();
    }
}