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
public class Person extends MoveableObject{

    public Person(int x, int y) {
        super();
        setPosition(x, y);
    }

    public Person() {
        super();
    }
    @Override
    public void setImage() {
        setImage(new ImageObject("person.png"));
    }
    public void init(){
        setWidth(10);
        setHeight(20);
        setCenterX(5);
        setCenterY(10);
        setSpeed(1);
        setAcceleration(100);
        setMaxSpeed(30);
        setTorque(2);
        setImage();
        setPosition(100,200);
    }
    @Override
    public float getNewX(){
        setX(velocity.getNewX(getX()));
        return getX();
    }
    @Override
    public float getNewY(){
        setY(velocity.getNewY(getY()));
        return getY();
    }

}
