package objects;

import graphics.ImageObject;
import java.awt.Shape;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gustav
 */
public class Water extends Object{
    public Water(int x, int y) {
        setPosition(x, y);
        setWidth(500);
        setHeight(500);
    }

    @Override
    public void setImage() {
        setImage(new ImageObject("vatten.png"));
    }

    @Override
    public void poll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getAngle() {
        return 0;
    }

    @Override
    public Shape getEnteringRectangle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
