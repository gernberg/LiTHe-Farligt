
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import objects.Entity;
import objects.MoveableObject;
import objects.collisionType;

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
            return true;
        }
        if(areBoundingRectanglesColliding(object2, object1)){
            return true;
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
    /**
     * Bestämmer vad som ska hända med två objekt som kolliderar.
     * Returnerar eventuella objekt som dör.
     * @param a
     * @param b
     * @return Eventuellt förlorande objekt.
     */
    public Set<Entity> decideCollisionOutcome(MoveableObject a, Entity b){
        Set<Entity> killedEntities = new HashSet<Entity>();
        if(b.getCollisionType()==collisionType.FIXED){
            a.brake();
            a.setSpeed(-a.getSpeed());
            
        }
        return killedEntities;
    }
}
