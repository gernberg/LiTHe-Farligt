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
    int x,y;

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
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    /**
     * Förslagsvis så skapar vi olika funktioner för moveable och fixed.
     * För att fixed aldrig ska behöva tänka några bounds, medans moveable
     * kanske inte alltid får anta alla positioner.
     * @param x
     * @param y
     */
    public abstract void setPosition(int x, int y);
    
}
