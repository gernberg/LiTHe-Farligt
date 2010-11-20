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
    int maxSpeed;
    float weight;
    Velocity velocity = new Velocity();
    float engineCapacity;
    
    @Override
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public int getX(){
        return velocity.getNewX(x);
    }
    @Override
    public int getY(){
        return velocity.getNewY(y);
    }

    public void accelerate(){
        velocity.increaseSpeed();
    }

}
