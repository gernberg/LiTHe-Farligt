/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

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


    public void accelerate(){
        velocity.increaseSpeed();
    }
    public void brake(){
        velocity.decreaseSpeed();
    }
    public void turnLeft(){
        velocity.turnLeft();
    }
    public void turnRight(){
        velocity.turnRight();
    }
    public abstract void setVelocity();

}
