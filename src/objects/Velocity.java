package objects;

/**
 * Velocity håller koll på alla viktiga aspekter hos hastigheten för ett fordon
 * såsom fart, riktning samt funktioner acceleration / retardation
 */
public class Velocity {
    private MoveableObject moveableObject;
    
    public Velocity(MoveableObject moveableObject) {
        this.moveableObject = moveableObject;
    }
    private float getSpeed(){
        return moveableObject.getSpeed();
    }
    private void setSpeed(float speed){
        moveableObject.setSpeed(speed);
    }
    private void modifySpeed(float modificator){
        setSpeed(getSpeed()+modificator);
    }
    private float getAcceleration(){
        return moveableObject.getAcceleration();
    }
    /**
     * Ökar hastigheten, med hänsyn till accelerationen
     */
    public void increaseSpeed() {
        modifySpeed(getAcceleration());
        if(getSpeed()>moveableObject.getMaxSpeed()){
            setSpeed(moveableObject.getMaxSpeed());
        }
    }
    /**
     * Minskar
     */
    public void decreaseSpeed(){
        modifySpeed(-getAcceleration());
        if(getSpeed()<getMinSpeed()){
            setSpeed(getMinSpeed());
        }
    }
    private float getTorque(){
        return moveableObject.getTorque();
    }
    public void increaseAngle(){
        modifyAngle(getTorque()/10);
    }
    public void decreaseAngle(){
        modifyAngle(-getTorque()/10);
    }

    public double getAngle() {
        return moveableObject.getAngle();
    }
    
    
    public float getNewX(float x) {
        return (float)(x +  Math.cos(getAngle()) * getSpeed());
    }

    public float getNewY(float y) {
        return (float)(y + Math.sin(getAngle()) * getSpeed());
    }
    public void turnRight(){
        if(getSpeed()>0)
            increaseAngle();
        else if(getSpeed()<0)
            decreaseAngle();
    }
    public void turnLeft(){
        if(getSpeed()>0)
            decreaseAngle();
        else if(getSpeed()<0)
            increaseAngle();
    }
    /**
     * Dödar hastigheten, anropar killSpeed(float) med objektets acceleration.
     */
    public void killSpeed() {
        killSpeed(getAcceleration());
    }
    /**
     * Dödar hastigheten (dvs. minskar / ökar så att den tillslut antar 0.
     * @param acceleration hur snabbt vi ska döda hastigheten.
     */
    public void killSpeed(float acceleration) {
        if(getSpeed()>0){
            // TODO: Är detta verkligen en uppgift i killSpeed, eller ska den ske
            // någonannanstans?
            modifySpeed(-getMaxSpeed()*acceleration/25);
            if(getSpeed()<0){
                setSpeed(0);
            }
        }else if(getSpeed()<0){
            modifySpeed(getMaxSpeed()*acceleration/25);
            if(getSpeed()>0){
                setSpeed(0);
            }
        }
    }
    private float getMaxSpeed(){
        return moveableObject.getMaxSpeed();
    }
    private float getMinSpeed() {
        return - getMaxSpeed();
    }

    private void modifyAngle(float i) {
        moveableObject.setAngle((float) moveableObject.getAngle()+i);;
    }
}