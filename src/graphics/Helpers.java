/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import objects.MoveableObject;

/**
 *
 * @author gustav
 */
public class Helpers {
    public static Helpers allHelpers = new Helpers();
    private Helpers(){}
    public Shape rotateRectangle(Rectangle r, double angle, int x, int y) {
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(angle, x, y);
        return tfm.createTransformedShape(r);
    }

    public Shape rotateRectangle(Rectangle r, MoveableObject mo) {
        return rotateRectangle(r, mo.getAngle(), mo.getIntX() + mo.getRotationCenterX(), mo.getIntY() + mo.getRotationCenterY());
    }

}

