
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import objects.Destroyable;
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
    /**
     * Kontrollerar om två objekt kolliderar
     * @param object1
     * @param object2
     * @return
     */
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
    /**
     * Kontrollerar om boundingRectangles från object1 kolliderar med object2
     * @param object1
     * @param object2
     * @return
     */
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
        Set<Entity> destroyedEntities = new HashSet<Entity>();
        double damageRate = a.getDamageRate();
        if(b.getCollisionType()==collisionType.FIXED){
            // Skadar det objekt som krockar - dock bara hälften mot vad den skadar
            // andra.
            ((Destroyable) a).destroy(a.getAngle(), damageRate/2);
            // Ändrar så att a byter riktning och tappar hastighet vid ev. kollision
            a.setSpeed(-a.getSpeed());
            a.brake();
            a.setPreviousPosition();
            a.setPreviousAngle();
        }
        if(b.isDestroyable()){
            ((Destroyable) b).destroy(a.getAngle(), damageRate);
            if(((Destroyable) b).isDestroyed())
                destroyedEntities.add(b);
        }
        return destroyedEntities;
    }
    /**
     * Beräknar hur många poäng man får för att kollidera med ett objekt
     * @param moveableObject
     * @param entity
     * @return
     */
    public int calculateScore(MoveableObject moveableObject, Entity entity) {
        if(!entity.isDestroyable())
            return 0;
        return entity.getScore(moveableObject);
    }
}
