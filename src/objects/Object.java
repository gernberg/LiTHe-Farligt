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
public abstract class Object {
    ImageObject image;
    float x,y;
    int centerX, centerY;
    public ImageObject getImage() {
        return image;
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
        return image.getWidth();
    }
    public int getHeight(){
        return image.getHeight();
    }
    public int getRotationCenterX(){
        return centerX;
    }
    public int getRotationCenterY(){
        return centerY;
    }
    /**
     * Förslagsvis så skapar vi olika funktioner för moveable och fixed.
     * För att fixed aldrig ska behöva tänka några bounds, medans moveable
     * kanske inte alltid får anta alla positioner.
     * @param x
     * @param y
     */
    public abstract void setPosition(int x, int y);
    /**
     * Fråga what's up.
     */
    public abstract void poll();
    public abstract float getAngle();
}
