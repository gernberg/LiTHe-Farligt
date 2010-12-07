/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.ImageObject;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author gustav
 */
public abstract class Object {
    private ImageObject image;
    private float x,y;
    private int centerX, centerY, height, width;
    public ImageObject getImage() {
        return image;
    }
    /**
     * Hämtar x värdet. Kan med fördel skuggas av subklasser
     * @return
     */
    public int getBoundingY(){
        return getIntY();
    }
    /**
     *
     * Hämtar y värdet. Kan med fördel skuggas av subklasser
     * @return
     */
    public int getBoundingX(){
        return getIntX();
    }
    /**
     * Kör setImage(), inte så mkt mera just nu.
     */
    public Object() {
        setImage();
    }
    /**
     * Denna funktion sätter bilden, ska köras vid init.
     */
    public abstract void setImage();
    public void setImage(ImageObject image){
        this.image = image;
    }
    public float getX() {
        return x;
    }
    public int getIntX(){
        return Math.round(getX());
    }
    public int getIntY(){
        return Math.round(getY());
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getRotationCenterX(){
        return centerX;
    }
    public int getRotationCenterY(){
        return centerY;
    }
    /**
     * Sätter objektets position
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }
    /**
     * Fråga what's up.
     */
    public abstract void poll();
    public abstract double getAngle();
    /**
     * Returnerar en Shape som är boundingBoxen - till för bla. kollisionshantering.
     * @return
     */
    public abstract Shape getBoundingCircle();

    public abstract Shape getEnteringRectangle();
    /**
     * Kollar om objektet är stjälbart
     * @return
     */
    public boolean isStealable() {
        return this instanceof Stealable;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
}
