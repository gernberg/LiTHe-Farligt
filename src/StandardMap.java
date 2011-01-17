
import java.util.HashSet;
import java.util.Set;
import objects.Building;
import objects.Water;
import objects.Entity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gustav
 */
class StandardMap {
    private static Set<Entity> entities = new HashSet<Entity>();
    public static Set<Entity> getMapEntities(){
        for (int i = 0; i < 5; i++){
            entities.add(new Water(i*500, -500) {

                @Override
                public double getAngle() {
                    return Math.PI / 2;
                }
            });
        }
        entities.add(new Water(-500, -500) {

            @Override
            public double getAngle() {
                return -Math.PI / 2;
            }
        });

        for (int i = 0; i < 5; i++){
            entities.add(new Water(-500, 500*i));
        }
        
        for (int i = 0; i < 10; i++) {
            entities.add(new Building(100 + i * 146, 0));
        }
        return entities;
    }
}
