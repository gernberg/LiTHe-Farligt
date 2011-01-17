package objects;

import graphics.ImageObject;

/**
 * En byggnad.
 * @author gustav
 */
public class Building extends Entity{

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

    @Override
    public collisionType getCollisionType() {
        return collisionType.SOLID;
    }
    
}
