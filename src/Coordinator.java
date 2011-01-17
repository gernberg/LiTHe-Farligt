import graphics.ImageObject;
import graphics.Window;
import java.util.HashSet;
import java.util.Set;
import objects.cars.StandardCar;
import objects.cars.Lada;
import objects.cars.Pimpmobile;
import objects.Destroyable;
import objects.MoveableObject;
import objects.Entity;
import objects.Pedestrian;
import objects.Person;
import objects.Stealable;
import objects.UserController;

/**
 * Den här klassen fungerar som koordinator mellan Window och UserController.
 * @author gustav
 */
public class Coordinator {

    /**
     * Uppdelning av objekt i två olika Set (foreground och background)
     * för att förenkla uppritningen.
     */
    Set<Entity> foregroundObjects = new HashSet<Entity>();
    Set<Entity> backgroundObjects = new HashSet<Entity>();
    MoveableObject mainCharacter;
    Window window;
    long score = 0;
    UserController userController;
    int timeout = 0;
    String text = null;

    public Coordinator(Window window, UserController userController) {
        addMainCharacter();
        addRandomCar();
        addRandomCar();
        addRandomCar();
        addRandomCar();
        addRandomCar();
        addRandomCar();
        addPedestrians();
        // Lägger ut några fixerade objekt
        foregroundObjects.add(new StandardCar(150, 100));
        foregroundObjects.add(new Lada(150, 200));
        foregroundObjects.add(new Pimpmobile(150, 300));
        backgroundObjects.addAll(StandardMap.getMapEntities());
        window.addUserInput(userController);
        userController.setCurrentObject(mainCharacter);

        this.window = window;
        this.userController = userController;
    }

    /**
     * Lägger till huvudkaraktären
     */
    public void addMainCharacter() {
        mainCharacter = new Person() {

            @Override
            public void setImage() {
                setImage(new ImageObject("maincharacter.png"));
            }
        };
        foregroundObjects.add(mainCharacter);
    }
    /**
     * Slumpar ut en bil på kartan
     */
    public void addRandomCar() {
        // TODO: Fult, borde göras snyggare
        addCar(50 + (int) Math.floor(Math.random() * window.getWORLD_WIDTH()), 50 + (int) Math.floor(Math.random() * window.getWORLD_HEIGHT()));
    }
    /**
     * Slumpar ut lite människor
     */
    public void addPedestrians() {
        for (int i = 0; i < 50; i++) {
            Pedestrian p = new Pedestrian((int) (Math.random() * window.getWORLD_WIDTH()), (int) (Math.random() * window.getWORLD_HEIGHT()));
            p.setAngle((double) (Math.random() * Math.PI * 2.0));
            p.setSpeed(p.getMaxSpeed());
            foregroundObjects.add(p);
        }
    }
    /**
     * Lägger ut en bil på en specifik x,y koordinat
     * @param x
     * @param y
     */
    public void addCar(int x, int y) {
        foregroundObjects.add(new StandardCar(x, y));
    }
    /**
     * Hämtar huvudkaraktären
     * @return
     */
    public MoveableObject getMainCharacter() {
        return mainCharacter;
    }

    /**
     * Provar att byta det objekt som man ko
     * @param currentObject
     * @return
     */
    public MoveableObject switchCurrentObject() {
        MoveableObject currentObject = userController.getCurrentObject();
        // Om man inte är en mainCharacter just nu - betyder det att vi alltid
        // ska "hoppa ut ur" fordonet.
        if (!currentObject.equals(mainCharacter)) {
            ((Stealable) currentObject).abandonAction();
            double tmpX = userController.getCurrentObject().getX();
            double tmpY = userController.getCurrentObject().getY();
            double tmpAngle = userController.getCurrentObject().getAngle();
            // Gör så att mainCharacter vet var han ska hamna
            mainCharacter.init();
            mainCharacter.setX(tmpX-10); // Offset på 10px för utseendets skull
            mainCharacter.setY(tmpY-10);
            mainCharacter.setAngle((double) tmpAngle);
            return mainCharacter;
        }
        for (Entity object : foregroundObjects) {
            // Om objektet vi undersöker är stjälbart och inte "sigsjälv"
            if (object.isStealable() && !object.equals(currentObject)) {
                if (((Stealable) object).getEnteringRectangle().intersects(currentObject.getBoundingRectangle().getBounds())) {
                    // Initiera stealAction hos objektet
                    ((Stealable) object).stealAction();
                    return (MoveableObject) object;
                }
            }
        }
        return currentObject;
    }

    /**
     * Denna funktion är den som styr i princip hela spelet.
     * Den håller koll på kollisioner, förflyttningar och skärmuppdateringar.
     * @return
     */
    public boolean update() {
        int i = 0;
        // Samlar object från foregroundObjects som inte ska vara kvar där utan
        // flyttas till backgroundObjects
        Set<Entity> foregroundObjectsToRemove = new HashSet<Entity>();

        // TODO: Snygga upp de här raderna
        for (Entity object : foregroundObjects) {
            if (object instanceof MoveableObject) {
                MoveableObject moveableObject = (MoveableObject) object;
                moveableObject.poll();
                if (moveableObject.hasMoved()) {
                    Set<Entity> objects = new HashSet<Entity>();
                    objects.addAll(foregroundObjects);
                    objects.addAll(backgroundObjects);
                    for (Entity object2 : objects) {
                        i++;
                        if (!object2.equals(moveableObject) && CollisionHelper.ch.isColliding(object2, moveableObject)) {
                            if (moveableObject.equals(userController.getCurrentObject())) {
                                addScore(CollisionHelper.ch.calculateScore(moveableObject, object2));
                            }
                            // Lägger till eventuellt trasiga objekt i kön för
                            // borttagning
                            foregroundObjectsToRemove.addAll(
                                    CollisionHelper.ch.decideCollisionOutcome(moveableObject, object2)
                                    );
                        }
                    }
                }
            }
        }
        foregroundObjects.removeAll(foregroundObjectsToRemove);
        backgroundObjects.addAll(foregroundObjectsToRemove);
        userController.poll();

        if (userController.shallWeSwitchObjects()){
            MoveableObject tmpMoveableObject = switchCurrentObject();
            // Kontrollera om det verkligen blev något byte
            if(!userController.getCurrentObject().equals(tmpMoveableObject)){
                userController.setCurrentObject(tmpMoveableObject);
                if(userController.getCurrentObject().equals(mainCharacter)){
                    foregroundObjects.add(mainCharacter);
                }else{
                    foregroundObjects.remove(mainCharacter);
                }
            }
        }
        updateWindow();
        if (timeout <= 0) {
            text = null;
        }else{
            timeout--;
        }
        // Om spelaren dör
        if (((Destroyable) userController.getCurrentObject()).isDestroyed()) {
            gameOverAction();
            return false;
        }
        return true;
    }
    /**
     * Lägger till poäng samt skriver ut den på skärmen
     * @param score
     */
    private void addScore(int score) {
        // Ignorera alla poänguppdateringar som är 0
        if(score==0)
            return;
        this.score += score;
        // Skriv ut till skärmen
        printText(String.valueOf(score), 10);
    }
    /**
     * 
     * @param text Texten som skall skrivas ut på skärmen
     * @param timeout
     */
    private void printText(String text, int timeout) {
        this.text = text;
        this.timeout = timeout;
    }
    /**
     * Uppdaterar skärmen
     */
    private void updateWindow() {
        window.offsetX = userController.getCurrentObject().getIntX();
        window.offsetY = userController.getCurrentObject().getIntY();
        window.draw(foregroundObjects, backgroundObjects, score, text);
    }
    /**
     * Denna körs när det blir "game over"
     * Sparar ev. highscore och skriver ut på skärmen
     */
    private void gameOverAction() {
        if (Highscore.writeNewHighscore(score)) {
            printText("Ny highscore: " + score, 1);
        } else {
            printText((Highscore.getHighscore() - score) + " poäng från ny highscore", 1);
        }
        updateWindow();
    }
}
