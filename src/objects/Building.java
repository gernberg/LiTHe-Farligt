/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.ImageObject;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author gustav
 */
public class Building extends Object{

    public Building(int x, int y) {
        setPosition(x, y);
        setWidth(146);
        setHeight(144);
        
    }
    
    @Override
    public void setImage() {
        super.setImage(new ImageObject("hus3.png"));
    }

    @Override
    public void poll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getAngle() {
        return 0;
    }
    
}
