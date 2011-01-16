package objects;

import graphics.ImageObject;
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gustav
 */
public class Cop extends Person{
    UserInformation ui;
    public Cop(int x, int y, UserInformation ui) {
        this.ui = ui;
        setPosition(x, y);
        setMaxSpeed(1.8);
    }

    @Override
    public double getNewX() {
        return super.getNewX();
    }

    @Override
    public double getNewY() {
        return super.getNewY();
    }
    @Override
    public double getAngle() {
        if(isDestroyed()){
            return getPreviousAngle();
        }
        return Math.atan2((ui.getPlayerY()-getY()),(ui.getPlayerX()-getX()));
    }
    public double getSpeed(){
        return getMaxSpeed();
    }


    @Override
    public void setImage() {
        setImage(new ImageObject("cop.png"));
    }

    @Override
    public int destroy(double angle, double damage) {
        System.out.println("Död polis");
        if(damage>2){
            return 0;
        }
        super.destroy(angle, damage);
        setImage(new ImageObject("dodcop.png"));
        return 1000;
    }
}
