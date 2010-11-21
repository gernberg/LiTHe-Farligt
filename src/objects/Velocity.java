/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

/**
 *
 * @author gustav
 */
public class Velocity {
    float speed;
    float maxSpeed;
    float angle;
    float acceleration;
    float torque;

    public Velocity(float speed, float angle, float acceleration, float torque, float maxSpeed) {
        this.speed = speed;
        this.angle = angle;
        this.acceleration = acceleration;
        this.torque = torque;
        this.maxSpeed = maxSpeed;
    }

    public void increaseSpeed() {
        speed += acceleration;
        if(speed>maxSpeed)
            speed = maxSpeed;
    }
    public void decreaseSpeed(){
        speed -= acceleration;
    }
    public void increaseAngle(){
        angle += torque/10;
    }
    public void decreaseAngle(){
        angle -= torque/10;
    }

    public float getAngle() {
        return angle;
    }
    
    public int getNewX(int x) {
        return x + (Math.round((float) Math.cos(angle) * speed));
    }

    public int getNewY(int y) {
        return y + (Math.round((float) Math.sin(angle) * speed));
    }
    public void turnRight(){
        if(speed>0)
            increaseAngle();
        else
            decreaseAngle();
    }
    public void turnLeft(){
        if(speed>0)
            decreaseAngle();
        else
            increaseAngle();
    }
}
