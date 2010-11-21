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
        setImage(new ImageObject("car.png"));
    }
    public Person(){
        centerX = 35;
        centerY = 20;
        speed = 0;
        acceleration = 1;
        maxSpeed = 2;
        torque = (float) 0.5;
        setImage();
        setVelocity();
        setPosition(100,200);
    }

    @Override
    public void poll() {
        // Gör ingenting
    }
    @Override
    public float getX(){
        setX(velocity.getNewX(x));
        return x;
    }
    @Override
    public float getY(){
        setY(velocity.getNewY(y));
        return y;
    }
}
