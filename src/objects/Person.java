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
public class Person extends MoveableObject implements Destroyable{
    private boolean destroyed;
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
        setSpeed(0);
        setAcceleration(100);
        setMaxSpeed(2);
        setTorque(2);
        setImage();
        setPosition(100,200);
    }
    @Override
    public double getNewX(){
        setX(getVelocity().getNewX(getX()));
        return getX();
    }
    @Override
    public double getNewY(){
        setY(getVelocity().getNewY(getY()));
        return getY();
    }

    public void destroy(double angle, double damage) {
        if(damage<=0)
            return;
        setAngle(angle);
        setPreviousAngle(angle);
        setImage(new ImageObject("dodperson.png"));
        setWidth(0);
        setHeight(0);
        setDestroyed();
    }
    /**
     * Man får alltid 100 poäng för att köra över människor.
     * @param mo
     * @return
     */
    public int getScore(MoveableObject mo){
        if(mo.getDamageRate()>0)
            return 100;
        return 0;
    }
    protected int animation_incr = 0;
    @Override
    public void poll() {
        if(!isDestroyed()){
            super.poll();
        }
    }
    public boolean isDestroyed() {
        return destroyed;
    }
    public void setDestroyed() {
        this.destroyed = true;
    }

    /**
     * Människor gör väldigt sällan skada genom att bara gå in i saker.
     * @return
     */
    @Override
    public double getDamageRate() {
        return 0;
    }

    @Override
    public collisionType getCollisionType() {
        return collisionType.FLOAT;
    }



}
