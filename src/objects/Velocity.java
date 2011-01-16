package objects;

/**
 * Velocity håller koll på alla viktiga aspekter hos hastigheten för ett fordon
 * såsom fart, riktning samt funktioner acceleration / retardation
 */
public class Velocity {
    private MoveableObject moveableObject;
    private double angle, speed;
    
    public Velocity(MoveableObject moveableObject) {
        this.moveableObject = moveableObject;
    }
    private double getSpeed(){
        return moveableObject.getSpeed();
    }
    private void setSpeed(double speed){
        moveableObject.setSpeed(speed);
    }
    private void modifySpeed(double modificator){
        setSpeed(getSpeed()+modificator);
    }
    private double getAcceleration(){
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
    private double getTorque(){
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
    
    
    public double getNewX(double x) {
        return (double)(x +  Math.cos(getAngle()) * getSpeed());
    }

    public double getNewY(double y) {
        return (double)(y + Math.sin(getAngle()) * getSpeed());
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
     * Dödar hastigheten, anropar killSpeed(double) med objektets acceleration.
     */
    public void killSpeed() {
        killSpeed(getAcceleration()*getMaxSpeed()/10);
    }
    /**
     * Dödar hastigheten (dvs. minskar / ökar så att den tillslut antar 0.
     * @param retardation hur snabbt vi ska döda hastigheten.
     */
    public void killSpeed(double retardation) {
        if(getSpeed()>0){
            modifySpeed(-retardation);
            if(getSpeed()<0){
                setSpeed(0);
            }
        }else if(getSpeed()<0){
            modifySpeed(retardation);
            if(getSpeed()>0){
                setSpeed(0);
            }
        }
    }
    private double getMaxSpeed(){
        return moveableObject.getMaxSpeed();
    }
    private double getMinSpeed() {
        return - getMaxSpeed();
    }

    private void modifyAngle(double i) {
        moveableObject.setAngle((double) moveableObject.getAngle()+i);;
    }
}