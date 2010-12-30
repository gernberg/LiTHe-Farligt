/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.ImageObject;

/**
 *
 * @author gustav
 */
public class Car extends MoveableObject implements Stealable, Destroyable{

    public Car(int x, int y) {
        super();
        setPosition(x, y);
    }

    public void init() {
        setCenterX(35);
        setCenterY(25);
        setSpeed(0);
        setWidth(40);
        setHeight(25);
        setAcceleration(0.2);
        setMaxSpeed(10);
        setTorque(0.5);
        setImage();
        setPosition(150,200);
    }
    public int getBoundingY(){
        return super.getBoundingY() + 12;
    }

    @Override
    public void setImage() {
        setImage(new ImageObject("car.png"));
    }
    @Override
    public double getNewX(){
        setX(velocity.getNewX(getX()));
        return getX();
    }
    @Override
    public double getNewY(){
        setY(velocity.getNewY(getY()));
        return getY();
    }

    public void stealAction() {
        // Ger bilen en ny bild
        setImage(new ImageObject("car_stolen.png"));
    }

    public void abandonAction(){
        // Återställer till standardbilden
        setImage();
    }

    boolean destroyed = false;
    int health = 100;
    public void destroy(double angle, double speed){
        destroyed = true;
    }

    public int getScore() {
        return 1000;
    }

    public boolean isDestroyed() {
        return destroyed;
    }


}
