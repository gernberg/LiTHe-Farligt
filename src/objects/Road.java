/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.ImageObject;

/**
 * En bit väg
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

    @Override
    public collisionType getCollisionType() {
        return collisionType.NON_SOLID;
    }
    
}
