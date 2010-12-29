/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.Helpers;
import graphics.ImageObject;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gustav
 */
public abstract class Object {
    private ImageObject image;
    private double x,y;
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
    public double getX() {
        return x;
    }
    public int getIntX(){
        return (int) Math.round(getX());
    }
    public int getIntY(){
        return (int) Math.round(getY());
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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
    public abstract void poll();
    public abstract double getAngle();
    

    public Set<Rectangle> getBoundingPoints(){
        Set<Rectangle> pointSet = new HashSet<Rectangle>();
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX(), getBoundingY(), 1,1)).getBounds());
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX()+getWidth(), getBoundingY(), 1, 1)).getBounds());
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX(), getBoundingY()+getHeight(), 1, 1)).getBounds());
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX()+getWidth(), getBoundingY()+getHeight(), 1, 1)).getBounds());
        return pointSet;
    }
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
     * Hämtar boundingboxen för objektet (och ser till att den är roterad precis
     * som objektets bild)
     * @return
     */
    public Shape getBoundingRectangle() {
        return rotateRectangle(new Rectangle(getBoundingX(), getBoundingY(), getWidth(), getHeight()));
    }
    public boolean isDestroyable(){
        if(this instanceof Destroyable){
            return !(((Destroyable) this).isDestroyed());
        }
        return false;
    }
}
