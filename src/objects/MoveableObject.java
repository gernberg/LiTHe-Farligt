/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

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
    public MoveableObject(){
        init();
        velocity = new Velocity(speed, angle, acceleration, torque, maxSpeed);
    }
    public double getAngle(){
        return velocity.getAngle();
    }
    /**
     * Ber objektet att uppdatera vad som hänt sen sist.
     */
    public void poll(){
        getNewX();
        getNewY();
        // Vi vill att alla objekt ska tappa hastighet gradvis
        velocity.killSpeed(acceleration/10);
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
    public void turnLeft(){
        velocity.turnLeft();
    }
    public void turnRight(){
        velocity.turnRight();
    }
    public abstract float getNewX();
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
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(getAngle(), getIntX() + getRotationCenterX(), getIntY() + getRotationCenterY());
        return tfm.createTransformedShape(r);
    }

    public abstract void init();
}
