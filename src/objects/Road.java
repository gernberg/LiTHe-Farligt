/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.ImageObject;
import java.awt.Shape;

/**
 *
 * @author gustav
 */
public class Road extends Entity{

    public Road(int x, int y){
        setPosition(x, y);
    }
    @Override
    public void setImage() {
        setImage(new ImageObject("road.png"));
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
