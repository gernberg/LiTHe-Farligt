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
        return Math.atan2((ui.getPlayerY()-getY()),(ui.getPlayerX()-getX()));
    }
    public double getSpeed(){
        return getMaxSpeed();
    }

    @Override
    public int getScore() {
        return 2000;
    }

    @Override
    public void setImage() {
        setImage(new ImageObject("cop.png"));
    }
    
}
