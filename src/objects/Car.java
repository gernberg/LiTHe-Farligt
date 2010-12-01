/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.ImageObject;

/**
 *
 * @author gustav
 */
public class Car extends MoveableObject{

    public void init() {
        centerX = 35;
        centerY = 25;
        speed = 0;
        width = 45;
        height = 25;
        acceleration = (float) 0.2;
        maxSpeed = 50;
        torque = (float) 0.5;
        setImage();
        setPosition(150,200);
    }
    public int getBoundingY(){
        return super.getBoundingY() + 12;
    }

    @Override
    public void setImage() {
        setImage(new ImageObject("car.png"));
    }
    @Override
    public float getNewX(){
        setX(velocity.getNewX(x));
        return getX();
    }
    @Override
    public float getNewY(){
        setY(velocity.getNewY(y));
        return getY();
    }

}
