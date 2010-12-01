package objects;

/**
 * Velocity håller koll på alla viktiga aspekter hos hastigheten för ett fordon
 * såsom fart, riktning samt funktioner acceleration / retardation
 */
public class Velocity {
    float speed;
    float maxSpeed;
    double angle;
    float acceleration;
    float torque;
    /**
     * @param speed Utgångshastighet
     * @param angle Riktingen för hastigheten
     * @param acceleration
     * @param torque Vridningsvinkeln (bestämmer hur snabbt ett objekt svänger)
     * @param maxSpeed
     */
    public Velocity(float speed, double angle, float acceleration, float torque, float maxSpeed) {
        this.speed = speed;
        this.angle = angle;
        this.acceleration = acceleration;
        this.torque = torque;
        this.maxSpeed = maxSpeed;
    }
    /**
     * Ökar hastigheten, med hänsyn till accelerationen
     */
    public void increaseSpeed() {
        speed += acceleration;
        if(speed>maxSpeed)
            speed = maxSpeed;
    }
    /**
     * Minskar
     */
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

    public double getAngle() {
        return angle;
    }
    
    public float getNewX(float x) {
        return (float)(x +  Math.cos(angle) * speed);
    }

    public float getNewY(float y) {
        return (float)(y + Math.sin(angle) * speed);
    }
    public void turnRight(){
        if(speed>=0)
            increaseAngle();
        else if(speed<0)
            decreaseAngle();
    }
    public void turnLeft(){
        if(speed>=0)
            decreaseAngle();
        else if(speed<0)
            increaseAngle();
    }

    public void killSpeed() {
        killSpeed(acceleration);
    }
    public void killSpeed(float acceleration) {
        if(speed>0){
            speed -= maxSpeed*acceleration/25;
            if(speed<0){
                speed = 0;
            }
        }else if(speed<0){
            speed += maxSpeed*acceleration/25;
            if(speed>0){
                speed = 0;
            }
        }
    }
}
