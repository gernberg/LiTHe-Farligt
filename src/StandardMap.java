
import java.util.HashSet;
import java.util.Set;
import objects.Building;
import objects.Water;
import objects.Entity;

/**
 * Standardkartan för spelet. Innehåller inga roliga saker.
 * @author gustav
 */
class StandardMap {

    private static Set<Entity> entities = new HashSet<Entity>();
    /**
     * Lägger ut massa vatten och hus.
     * @return
     */
    public static Set<Entity> getMapEntities() {
        // TODO: Snygga upp koden
        for (int i = 0; i < 5; i++) {
            entities.add(new Water(i * 500, -500) {

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
        for (int i = 0; i < 5; i++) {
            entities.add(new Water(-500, 500 * i));
        }

        for (int i = 0; i < 10; i++) {
            entities.add(new Building(1460, i * 146));
        }
        for (int i = 0; i < 10; i++) {
            entities.add(new Building(i * 146, 1460));
        }
        return entities;
    }
}
