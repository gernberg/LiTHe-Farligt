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
    private double maxSpeed, weight, speed, angle, acceleration, torque, previousX, previousY, previousAngle;
    Velocity velocity;

    public double getPreviousAngle() {
        return previousAngle;
    }

    public void setPreviousAngle(double previousAngle) {
        this.previousAngle = previousAngle;
    }
    double engineCapacity;
    boolean usedByUser = false;

    public double getAcceleration() {
        return acceleration;
    }
//    public void setAcceleration(double acceleration){
//        setAcceleration((double) acceleration);
//    }
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }


    public void setAngle(double angle) {
        setPreviousAngle(getAngle());
        this.angle = angle;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getTorque() {
        return torque;
    }

//    public void setTorque(double torque) {
//        setTorque((double) torque);
//    }
    public void setTorque(double torque) {
        this.torque = torque;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Berätta om användaren använder det här objektet för tillfället.
     * Behövs kanske egentligen inte - men används av Debug.
     * @return
     */
    public boolean isUsedByUser() {
        return usedByUser;
    }
    /**
     * Bestämmer om objektet används av användaren
     * @param usedByUser
     */
    public void setUsedByUser(boolean usedByUser) {
        this.usedByUser = usedByUser;
    }
    /**
     * Kör init() och initierar sedan velocity.
     */
    public MoveableObject(){
        init();
        velocity = new Velocity(this);
    }
    /**
     * Hämtar vinkeln för objektet
     * @return
     */
    public double getAngle(){
        return angle;
    }
    /**
     * Ber objektet att uppdatera vad som hänt sen sist.
     */
    public void poll(){
        // TODO: Är det bra att göra såhär?
        // Känner att man kanske ska skippa kontrollen i setX, setY, setAngle?
        setPreviousX(getX());
        setPreviousY(getY());
        setPreviousAngle(getAngle());
        getNewX();
        getNewY();
        // Vi vill att alla objekt ska tappa hastighet gradvis
        velocity.killSpeed(acceleration/5);
    }
    /**
     * Accelererar objektet
     */
    public void accelerate(){
        velocity.increaseSpeed();
    }
    /**
     * Bromsar objektet
     */
    public void brake(){
        velocity.killSpeed();
    }
    /**
     * Saktar ner objektet
     */
    public void retardate(){
        velocity.decreaseSpeed();
    }
    /**
     * Svänger objektet åt vänster.
     */
    public void turnLeft(){
        velocity.turnLeft();
    }
    /**
     * Svänger objektet åt höger.
     */
    public void turnRight(){
        velocity.turnRight();
    }
    /**
     * Denna funktion är tänkt att hämta objektets nya X position
     * @return
     */
    public abstract double getNewX();
    /**
     * Denna funktion är tänkt att hämta objektets nya Y position
     * @return
     */
    public abstract double getNewY();
    /**
     * Hämtar boundingboxen för hur nära man behöver stå för att byta till ett
     * visst fordon.
     * TODO: Ge metoden ett bra namn...
     * @return
     */
    public Shape getEnteringRectangle(){
        return rotateRectangle(new Rectangle(getBoundingX()-10, getBoundingY()-10, getWidth()+20, getHeight()+20));
    }
    /**
     * Denna metod är till för att sätta standardvärden för objektet.
     */
    public abstract void init();

    public double getPreviousX() {
        return previousX;
    }

    private void setPreviousX(double previousX) {
        this.previousX = previousX;
    }

    public double getPreviousY() {
        return previousY;
    }

    private void setPreviousY(double previousY) {
        this.previousY = previousY;
    }
    public void setX(double x){
        setPreviousX(getX());
        super.setX(x);
    }
    public void setY(double y){
        setPreviousY(getY());
        super.setY(y);
    }
    public boolean hasMoved(){
        
        return (getX()!=getPreviousX() ||
                getY()!=getPreviousY() ||
                getAngle()!=getPreviousAngle());
    }

    public void setPreviousPosition() {
        setPosition((int)getPreviousX(), (int)getPreviousY());
    }

    public void setPreviousAngle() {
        angle = getPreviousAngle();
    }
}
