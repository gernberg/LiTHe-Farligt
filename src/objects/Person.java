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
    float speed = 0;
    float acceleration = 1;
    float maxSpeed = (float) 1;
    float torque = (float) 0.5;
    float angle;
    @Override
    public void setImage() {
        setImage(new ImageObject("car.png"));
    }

    public Person(){
        setImage();
        setVelocity();
        setPosition(100,200);
    }

    @Override
    public void poll() {
        // Gör ingenting
    }

    @Override
    public void setVelocity() {
        velocity = new Velocity(speed, angle, acceleration, torque, maxSpeed);
    }


    @Override
    public int getX(){
        setX(velocity.getNewX(x));
        return x;
    }
    @Override
    public int getY(){
        setY(velocity.getNewY(y));
        return y;
    }
}
