package com.dagtech.redguard.redguardgame;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.dagtech.redguard.framework.Graphics;

import java.util.ArrayList;

/**
 * Created by Lionel on 12/3/2015.
 */
public class DefenceUnit extends DrawableObject{
    private int attackTimer;
    private boolean isAttacking;
    private Enemy target;
    private Weapon weapon;
    private Point p1, p2;
    private Paint paint = new Paint();
    private BodyPart bp;
    private String bpTarget;
    private Paint rangePaint = new Paint();
    //private ArrayList<WeaponProjectile> projectiles;

    public DefenceUnit(Tile t, Weapon w, String bpTarget) {
        super.setImage(Assets.defence, 1, 1);
        super.setCurrentTile(t);
        super.setXOffset(7);
        super.setYOffset(9);
        weapon = w;
        attackTimer = weapon.getRateOfFire();
        p1 = new Point();
        p2 = new Point();
        paint.setColor(Color.BLUE);
        this.bpTarget = bpTarget;
        rangePaint.setColor(Color.GREEN);
        rangePaint.setAlpha(10);
        //projectiles = new ArrayList<>();
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        /*for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isDead()) {
                projectiles.remove(i);
            } else {
                projectiles.get(i).update();
            }
        }*/

        p1.x = (int) super.getCenterX();
        p1.y = (int) super.getCenterY();
        if (target == null) {
            p2.x = p1.x;
            p2.y = p1.y;
        }

        super.setCenterX(super.getCurrentTile().getTileX() + super.getXOffset());
        super.setCenterY(super.getCurrentTile().getTileY() - super.getYOffset());

        if (!weapon.getIsReloading()) {
            if (isAttacking) {
                attackTimer++;
            } else {
                attackTimer = 0;
            }

            if (attackTimer >= weapon.getRateOfFire()) {
                p2.x = (int) target.getCenterX();
                p2.y = (int) target.getCenterY();

                Assets.projectileController.addProjectile(new WeaponProjectile(super.getCurrentTile(), p2, attackTimer/2));
                //projectiles.add(new WeaponProjectile(super.getCurrentTile(),p2, attackTimer/2));
                attack();
                Assets.particleController.addParticle(new Particle(target.getCurrentTile(), target.getXOffset(), target.getYOffset()));
                attackTimer = 0;
            }
        } else {
            weapon.reload();
        }
    }

    public void attack() {
        target.hit(weapon.getPower(), weapon.getAccuracy(), bp);
        weapon.fire();
    }

    public void setPreferredTarget(){
        if (target != null) {
            if (bpTarget == "torso")
                bp = target.getTorso();
            else if (bpTarget == "arms")
                bp = target.getArms();
            else if (bpTarget == "legs")
                bp = target.getLegs();
            else if (bpTarget == "head")
                bp = target.getHead();
        }
    }

    public double getCenterX() {
        return super.getCenterX();
    }

    public void setTarget(Enemy g) {
        target = g;
        setPreferredTarget();
    }

    public double getCenterY() {
        return super.getCenterY();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setIsAttacking(boolean b) {
        isAttacking = b;
    }

    public boolean getIsAttacking() {
        return isAttacking;
    }

    /*public void draw(Graphics g) {
        g.drawImage(Assets.defence, (int)centerX, (int)centerY);
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).draw(g);
        }
        if (weapon.getIsReloading()) {
            g.drawString("reloading " + weapon.getReloadingTime(), (int)centerX-10, (int)centerY-4, paint);
        }
        //g.drawCircle((float)centerX, (float)centerY, (float)weapon.getRange(), rangePaint);
    }*/
}
