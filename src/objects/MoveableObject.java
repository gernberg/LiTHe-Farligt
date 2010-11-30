/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author gustav
 */
public abstract class MoveableObject extends Object{
    float maxSpeed;
    float weight;
    float speed, angle, acceleration, torque;
    Velocity velocity;
    float engineCapacity;
    boolean usedByUser = false;

    public boolean isUsedByUser() {
        return usedByUser;
    }

    public void setUsedByUser(boolean usedByUser) {
        this.usedByUser = usedByUser;
    }
    
    public MoveableObject(){
        setVelocity();
    }
    @Override
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }
    public float getAngle(){
        return velocity.getAngle();
    }
    public void poll(){
        getNewX();
        getNewY();
    }

    public void accelerate(){
        velocity.increaseSpeed();
    }
    public void brake(){
        velocity.killSpeed();
    }
    /**
     * TODO: Hitta på snyggare namn på funktionen?
     */
    public void retardate(){
        velocity.decreaseSpeed();
    }
    public void turnLeft(){
        velocity.turnLeft();
    }
    public void turnRight(){
        velocity.turnRight();
    }
    public void setVelocity(){
        velocity = new Velocity(speed, angle, acceleration, torque, maxSpeed);
    }
    public abstract float getNewX();
    public abstract float getNewY();

    public double distanceTo(Object object) {
        return  Math.sqrt(Math.pow(this.getX()-object.getX(), 2) +
                Math.pow(this.getX()-object.getX(), 2));
    }
    /**
     * Skapar en standard bounding - om vi antar att det mesta är rektanglar
     * med inte allt för knepiga bilder.
     * @return
     */
    public Shape getBoundingShape() {
        return new Rectangle(getBoundingX(), getBoundingY(), getWidth(), getHeight());
    }
    
}
