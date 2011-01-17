package objects;

import graphics.ImageObject;

/**
 * Vatten med staket
 * @author gustav
 */
public class Water extends Entity{
    public Water(int x, int y) {
        setPosition(x, y);
        setWidth(500);
        setHeight(500);
        setRotationCenterX(250);
        setRotationCenterY(250);
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
    public double getAngle() {
        return 0;
    }

    @Override
    public collisionType getCollisionType() {
        return collisionType.SOLID;
    }


}