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
        float minSpeed = -maxSpeed;
        if(speed<minSpeed){
            speed = minSpeed;
        }
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
    
    public float getNewX(float x) {
        System.out.println(x + " " + angle);
        return (float)(x +  Math.cos(angle) * speed);
    }

    public float getNewY(float y) {
        return (float)(y + Math.sin(angle) * speed);
    }
    public void turnRight(){
        if(speed>0)
            increaseAngle();
        else if(speed<0)
            decreaseAngle();
    }
    public void turnLeft(){
        if(speed>0)
            decreaseAngle();
        else if(speed<0)
            increaseAngle();
    }
}
