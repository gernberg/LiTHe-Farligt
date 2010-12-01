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
public class Person extends MoveableObject{
    @Override
    public void setImage() {
        setImage(new ImageObject("person.png"));
    }
    public Person(){
        width = 10;
        height = 20;
        centerX = 5;

        centerY = 10;
        speed = 1;
        acceleration = 100;
        maxSpeed = 2;
        torque = (float) 2;
        setImage();
        setVelocity();
        setPosition(100,200);
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
