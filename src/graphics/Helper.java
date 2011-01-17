package graphics;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import objects.Entity;

/**
 * Klass som innehåller hjälpfunktioner för det grafiska gränssnittet
 * @author gustav
 */
public class Helper {
    public static Helper allHelpers = new Helper();
    private Helper(){}
    /**
     * Roterar en rektangel runt en viss x,y koordinat
     * @param r Rektangeln
     * @param angle Rotationsvinkeln
     * @param x
     * @param y
     * @return
     */
    public Shape rotateRectangle(Rectangle r, double angle, int x, int y) {
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(angle, x, y);
        return tfm.createTransformedShape(r);
    }
    /**
     * Roterar en rektangel kring en enhets rotationscenter.
     * @param r Rektangeln som skall roteras
     * @param e Entityn som har ett rotationscenter
     * @return
     */
    public Shape rotateRectangle(Rectangle r, Entity e) {
        return rotateRectangle(r, e.getAngle(), e.getIntX() + e.getRotationCenterX(), e.getIntY() + e.getRotationCenterY());
    }
}


