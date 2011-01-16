import objects.Cop;
import objects.Water;
import graphics.ImageObject;
import graphics.Window;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import objects.Building;
import objects.Car;
import objects.Destroyable;
import objects.MoveableObject;
import objects.Object;
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
    Set<Object> foregroundObjects = new HashSet<Object>();
    Set<Object> backgroundObjects = new HashSet<Object>();
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
        foregroundObjects.add(new Cop(400, 400, new UserInformation(userController)));
        foregroundObjects.add(new Car(150, 150));
        backgroundObjects.add(new Water(-500,-500));
        backgroundObjects.add(new Water(-500,0));
        backgroundObjects.add(new Water(-500,500));
        backgroundObjects.add(new Water(-500,1000));
        backgroundObjects.add(new Road(0,-500));
        backgroundObjects.add(new Road(0,-200));
        backgroundObjects.add(new Road(0,100));
        backgroundObjects.add(new Road(0,400));
        for(int i = 0; i< 10; i++){
            backgroundObjects.add(new Building(100+i*146,0));
        }
        window.addUserInput(userController);
        userController.setCurrentObject(mainCharacter);

        this.window = window;
        this.userController = userController;
    }
    /**
     * Lägger till huvudkaraktären
     */
    public void addPerson(){
        mainCharacter = new Person(){
            @Override
            public void setImage() {
                setImage(new ImageObject("maincharacter.png"));
            }
        };
        foregroundObjects.add(mainCharacter);
    }
    public void addCar(){
        // TODO: Fult, borde göras snyggare
        addCar(50+(int)Math.floor(Math.random()*window.getWORLD_WIDTH()), 50+(int)Math.floor(Math.random()*window.getWORLD_HEIGHT()));
    }
    public void addPeople(){
        // TODO: Fult, borde göras snyggare
        for(int i = 0; i<50; i++){
            Person p = new Person((int)(Math.random()*window.getWORLD_WIDTH()),(int)(Math.random()*window.getWORLD_HEIGHT()));
            p.setAngle((double) (Math.random()*Math.PI*2.0));
            p.setSpeed(p.getMaxSpeed());
            foregroundObjects.add(p);
        }
    }
    public void addCar(int x, int y){
        foregroundObjects.add(new Car(x, y));
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
        for (Object object : foregroundObjects) {
            if(!currentObject.equals(mainCharacter)){
                // Om man inte är en mainCharacter just nu - betyder det att vi alltid
                // ska "hoppa ut ur" fordonet.
                ((Stealable) currentObject).abandonAction();
                return mainCharacter;
            }
            if(object.isStealable() && !object.equals(currentObject)){
                if(((Stealable) object).getEnteringRectangle().intersects(currentObject.getBoundingRectangle().getBounds())){
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
        Set<Object> foregroundObjectsToRemove = new HashSet<Object>();

        // TODO: Snygga upp de här raderna
        for (Object object : foregroundObjects) {
            if(object instanceof MoveableObject){
                MoveableObject moveableObject = (MoveableObject) object;
                moveableObject.poll();
                if(moveableObject.hasMoved()){
                    Set<Object> objects = new HashSet<Object>();
                    objects.addAll(foregroundObjects);
                    objects.addAll(backgroundObjects);
                    for(Object object2 : objects){
                        i++;
                        if(!object2.equals(moveableObject) && moveableObject.getBoundingRectangle().intersects(object2.getBoundingRectangle().getBounds2D())){
                            // Om vi "typ" kolliderat - kolla noggrannare
                            boolean collision = false;
                            for(Rectangle collisionRectangle : moveableObject.getBoundingRectangles()){
                                if(object2.getBoundingRectangle().intersects(collisionRectangle)){
                                    collision = true;
                                    break;
                                }
                            }
                            for(Rectangle collisionRectangle : object2.getBoundingRectangles()){
                                if(moveableObject.getBoundingRectangle().intersects(collisionRectangle)){
                                    collision = true;
                                    break;
                                }
                            }
                            if(collision){
                                if(object2.isDestroyable()){
                                    int score = ((Destroyable) object2).destroy(moveableObject.getAngle(), moveableObject.getDamageRate());
                            
                                    foregroundObjectsToRemove.add(object2);
                                    // Ge bara poäng om det är "en själv" som kolliderar
                                    if(moveableObject.equals(userController.getCurrentObject())){
                                        addScore(score);
                                    }
                                }else{
                                    moveableObject.setPreviousPosition();
                                    moveableObject.setPreviousAngle();
                                    double newSpeed = moveableObject.getSpeed();
                                    if(newSpeed>1){
                                        newSpeed--;
                                    }
                                    else if(newSpeed<1){
                                        newSpeed++;
                                    }
                                    moveableObject.setSpeed(-newSpeed);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(((Destroyable) userController.getCurrentObject()).isDestroyed()){
            // Döda spelet
            System.out.println("Game over.");
            return false;
        }
        foregroundObjects.removeAll(foregroundObjectsToRemove);
        backgroundObjects.addAll(foregroundObjectsToRemove);
        userController.poll();
        
        if(userController.shallWeSwitchObjects()){
            double tmpX = userController.getCurrentObject().getX();
            double tmpY = userController.getCurrentObject().getY();
            double tmpAngle = userController.getCurrentObject().getAngle();
            userController.setCurrentObject(switchObject(userController.getCurrentObject()));
            // TODO: Detta borde inte ligga här - utan någonstans snyggare.
            // Typ i switchObject.
            if(!userController.getCurrentObject().equals(mainCharacter)){
                foregroundObjects.remove(mainCharacter);
            }else{
                mainCharacter.init();
                mainCharacter.setX(tmpX);
                mainCharacter.setY(tmpY);
                mainCharacter.setAngle((double) tmpAngle);
                foregroundObjects.add(mainCharacter);
            }
        }
        window.strangex = userController.getCurrentObject().getIntX();
        window.strangey = userController.getCurrentObject().getIntY();
        window.draw(foregroundObjects, backgroundObjects, score);
        return true;
    }

    private void addScore(int score) {
        this.score += score;
    }
}
