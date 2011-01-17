
import objects.Water;
import graphics.ImageObject;
import graphics.Window;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;
import objects.Building;
import objects.cars.StandardCar;
import objects.cars.Lada;
import objects.cars.Pimpmobile;
import objects.Destroyable;
import objects.MoveableObject;
import objects.Entity;
import objects.Pedestrian;
import objects.Person;
import objects.Road;
import objects.Stealable;
import objects.UserController;
import objects.UserInformation;

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

    public Coordinator(Window window, UserController userController) {
        addPerson();
        addCar();
        addCar();
        addCar();
        addCar();
        addCar();
        addCar();
        addPeople();
        foregroundObjects.add(new StandardCar(150, 1 + 0));
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
    public void addPerson() {
        mainCharacter = new Person() {

            @Override
            public void setImage() {
                setImage(new ImageObject("maincharacter.png"));
            }
        };
        foregroundObjects.add(mainCharacter);
    }

    public void addCar() {
        // TODO: Fult, borde göras snyggare
        addCar(50 + (int) Math.floor(Math.random() * window.getWORLD_WIDTH()), 50 + (int) Math.floor(Math.random() * window.getWORLD_HEIGHT()));
    }

    public void addPeople() {
        // TODO: Fult, borde göras snyggare
        for (int i = 0; i < 50; i++) {
            Pedestrian p = new Pedestrian((int) (Math.random() * window.getWORLD_WIDTH()), (int) (Math.random() * window.getWORLD_HEIGHT()));
            p.setAngle((double) (Math.random() * Math.PI * 2.0));
            p.setSpeed(p.getMaxSpeed());
            foregroundObjects.add(p);
        }
    }

    public void addCar(int x, int y) {
        foregroundObjects.add(new StandardCar(x, y));
    }

    public MoveableObject getPerson() {
        return mainCharacter;
    }

    /**
     * Byter vilket objekt vi befinner oss i, returnerar det objekt vi byter till.
     * @param currentObject
     * @return
     */
    public MoveableObject switchObject(MoveableObject currentObject) {
        MoveableObject tmpObject = currentObject;
        for (Entity object : foregroundObjects) {
            if (!currentObject.equals(mainCharacter)) {
                // Om man inte är en mainCharacter just nu - betyder det att vi alltid
                // ska "hoppa ut ur" fordonet.
                ((Stealable) currentObject).abandonAction();
                return mainCharacter;
            }
            if (object.isStealable() && !object.equals(currentObject)) {
                if (((Stealable) object).getEnteringRectangle().intersects(currentObject.getBoundingRectangle().getBounds())) {
                    ((Stealable) object).stealAction();
                    return (MoveableObject) object;
                }
            }
        }
        return tmpObject;
    }

    /**
     * 
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

        if (userController.shallWeSwitchObjects()) {
            double tmpX = userController.getCurrentObject().getX();
            double tmpY = userController.getCurrentObject().getY();
            double tmpAngle = userController.getCurrentObject().getAngle();
            userController.setCurrentObject(switchObject(userController.getCurrentObject()));
            // TODO: Detta borde inte ligga här - utan någonstans snyggare.
            // Typ i switchObject.
            if (!userController.getCurrentObject().equals(mainCharacter)) {
                foregroundObjects.remove(mainCharacter);
            } else {
                mainCharacter.init();
                mainCharacter.setX(tmpX);
                mainCharacter.setY(tmpY);
                mainCharacter.setAngle((double) tmpAngle);
                foregroundObjects.add(mainCharacter);
            }
        }
        updateWindow();
        timeout--;
        if (timeout <= 0) {
            text = null;
        }
        // Om spelaren dör
        if (((Destroyable) userController.getCurrentObject()).isDestroyed()) {
            gameOverAction();
            return false;
        }
        return true;
    }

    private void addScore(int score) {
        // Ignorera alla poänguppdateringar som är 0
        if(score==0)
            return;
        this.score += score;
        printText(String.valueOf(score), 10);
    }
    int timeout = 0;
    String text = null;

    /**
     * 
     * @param text Texten som skall skrivas ut på skärmen
     * @param timeout
     */
    private void printText(String text, int timeout) {
        this.text = text;
        this.timeout = timeout;
    }

    private void updateWindow() {
        window.strangex = userController.getCurrentObject().getIntX();
        window.strangey = userController.getCurrentObject().getIntY();
        window.draw(foregroundObjects, backgroundObjects, score, text);
    }

    private void gameOverAction() {
        if (Highscore.writeNewHighscore(score)) {
            printText("Ny highscore: " + score, 1);
        } else {
            printText((Highscore.getHighscore() - score) + " poäng från ny highscore", 1);
        }
        updateWindow();
    }
}
