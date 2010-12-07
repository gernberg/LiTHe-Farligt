/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.Helpers;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 *
 * @author gustav
 */
public abstract class MoveableObject extends Object{
    // TODO: Detta kanske bara ska finnas i velocity.
    // Eller ska man skippa velocity helt?
    // Eller ska man skicka hela objektet till velocity så får velocity använda
    // getterpolls i Object?
    private float maxSpeed, weight, speed, angle, acceleration, torque, previousX, previousY, previousAngle;
    Velocity velocity;

    public float getPreviousAngle() {
        return previousAngle;
    }

    public void setPreviousAngle(float previousAngle) {
        this.previousAngle = previousAngle;
    }
    float engineCapacity;
    boolean usedByUser = false;

    public float getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(double acceleration){
        setAcceleration((float) acceleration);
    }
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }


    public void setAngle(float angle) {
        setPreviousAngle(angle);
        this.angle = angle;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(double torque) {
        setTorque((float) torque);
    }
    public void setTorque(float torque) {
        this.torque = torque;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
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
    public abstract float getNewX();
    /**
     * Denna funktion är tänkt att hämta objektets nya Y position
     * @return
     */
    public abstract float getNewY();
    /**
     * Hämtar boundingboxen för objektet (och ser till att den är roterad precis
     * som objektets bild)
     * @return
     */
    public Shape getBoundingRectangle() {
        return rotateRectangle(new Rectangle(getBoundingX(), getBoundingY(), getWidth(), getHeight()));
    }
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
     * Roterar en rektangel
     * TODO: Ligger den här i rätt fil verkligen, eller ska vi skapa en klass
     * för den här typen av "hjälpfunktioner"?
     * @param r Rektangeln som skall roteras.
     * @return
     */
    public Shape rotateRectangle(Rectangle r){
        return Helpers.allHelpers.rotateRectangle(r, this);
    }
    /**
     * Denna metod är till för att sätta standardvärden för objektet.
     */
    public abstract void init();

    public float getPreviousX() {
        return previousX;
    }

    private void setPreviousX(float previousX) {
        this.previousX = previousX;
    }

    public float getPreviousY() {
        return previousY;
    }

    private void setPreviousY(float previousY) {
        this.previousY = previousY;
    }
    public void setX(float x){
        setPreviousX(getX());
        super.setX(x);
    }
    public void setY(float y){
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
        setAngle(getPreviousAngle());
    }
}
