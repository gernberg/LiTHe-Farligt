
import java.awt.Rectangle;
import objects.Entity;

/**
 * 
 * @author gustav
 */
public class CollisionHelper {
    public static CollisionHelper ch = new CollisionHelper();
    private CollisionHelper(){

    }
    public boolean isColliding(Entity object1, Entity object2) {
        // Gör en snabbkoll om objekten kolliderar med sina "stora" rektanglar
        if(!object2.getBoundingRectangle().intersects(object1.getBoundingRectangle().getBounds2D())){
            return false;
        }
        // Omm deras större rektanglar kolliderar är det intressant att undersöka
        // deras "bounding rectangles" som är mer exakta.
        if(areBoundingRectanglesColliding(object1, object2)){

        }
        if(areBoundingRectanglesColliding(object2, object1)){

        }
        for(Rectangle collisionRectangle : object2.getBoundingRectangles()){
            if(object1.getBoundingRectangle().intersects(collisionRectangle)){
                return true;
            }
        }
        return false;
    }
    
    public boolean areBoundingRectanglesColliding(Entity object1, Entity object2) {
        for(Rectangle collisionRectangle : object1.getBoundingRectangles()){
            if(object2.getBoundingRectangle().intersects(collisionRectangle)){
                return true;
            }
        }
        return false;
    }
}
