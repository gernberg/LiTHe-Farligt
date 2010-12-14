
import graphics.ImageObject;
import graphics.Window;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import objects.Car;
import objects.MoveableObject;
import objects.Object;
import objects.Person;
import objects.Stealable;
import objects.UserController;
import org.omg.CORBA.Current;
/**
 * Den här klassen fungerar som koordinator mellan Window och UserController.
 * @author gustav
 */
public class Coordinator {
    Set<Object> objects = new HashSet<Object>();
    MoveableObject person;
    Window window;
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
        window.addUserInput(userController);
        userController.setCurrentObject(person);

        this.window = window;
        this.userController = userController;
    }
    public void addPerson(){
        person = new Person(){

            @Override
            public void setImage() {
                setImage(new ImageObject("maincharacter.png"));
            }
        };
        person.setUsedByUser(true);
        objects.add(person);
    }
    public void addCar(){
        // TODO: Fult, borde göras snyggare
        addCar(50+(int)Math.floor(Math.random()*window.getWORLD_WIDTH()), 50+(int)Math.floor(Math.random()*window.getWORLD_HEIGHT()));
    }
    public void addPeople(){
        // TODO: Fult, borde göras snyggare
        for(int i = 0; i<50; i++){
            Person p = new Person((int)(Math.random()*window.getWORLD_WIDTH()),(int)(Math.random()*window.getWORLD_HEIGHT()));
            p.setAngle((float) (Math.random()*Math.PI*2.0));
            p.setSpeed(p.getMaxSpeed());
            objects.add(p);
        }
    }
    public void addCar(int x, int y){
        objects.add(new Car(x, y));
    }
    public MoveableObject getPerson() {
        return person;
    }
    public MoveableObject switchObject(MoveableObject currentObject) {
        MoveableObject tmpObject = currentObject;
        for (Object object : objects) {
            if(!currentObject.equals(person)){
                // Om man inte är en person just nu  - betyder det att vi alltid
                // ska "hoppa ut ur" fordonet.
                return person;
            }
            if(object.isStealable() && !object.equals(currentObject)){
                if(object.getEnteringRectangle().intersects(currentObject.getBoundingRectangle().getBounds())){
                    return (MoveableObject) object;
                }
            }
        }
        return tmpObject;
    }

    public void update() {
        int i = 0;
        for (Object object : objects) {
            if(object instanceof MoveableObject){
                MoveableObject moveableObject = (MoveableObject) object;
                moveableObject.poll();
                if(userController.getCurrentObject()==moveableObject){
                    System.out.println(moveableObject.getAngle() + "|" + moveableObject.getPreviousAngle());
                }
                if(moveableObject.hasMoved()){
                    for(Object object2 : objects){
                        i++;
                        if(!object2.equals(moveableObject) && moveableObject.getBoundingRectangle().intersects(object2.getBoundingRectangle().getBounds2D())){
                            // Om vi "typ" kolliderat - kolla noggrannare
                            boolean collision = false;
                            for(Rectangle collisionRectangle : moveableObject.getBoundingPoints()){
                                if(object2.getBoundingRectangle().intersects(collisionRectangle)){
                                    collision = true;
                                    break;
                                }
                            }
                            for(Rectangle collisionRectangle : object2.getBoundingPoints()){
                                if(moveableObject.getBoundingRectangle().intersects(collisionRectangle)){
                                    collision = true;
                                    break;
                                }
                            }
                            if(collision){
                                moveableObject.setPreviousPosition();
                                moveableObject.setPreviousAngle();
                                float newSpeed = moveableObject.getSpeed();
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
        System.out.println(i + "Kontroller");
        userController.poll();
        if(userController.shallWeSwitchObjects()){
            float tmpX = userController.getCurrentObject().getX();
            float tmpY = userController.getCurrentObject().getY();
            double tmpAngle = userController.getCurrentObject().getAngle();
            userController.setCurrentObject(switchObject(userController.getCurrentObject()));
            // TODO: Detta borde inte ligga här - utan någonstans snyggare.
            // Typ i switchObject.
            if(!userController.getCurrentObject().equals(person)){
                objects.remove(person);
            }else{
                person.init();
                person.setX(tmpX);
                person.setY(tmpY);
                person.setAngle((float) tmpAngle);
                objects.add(person);
            }
        }
        window.strangex = userController.getCurrentObject().getIntX();
        window.strangey = userController.getCurrentObject().getIntY();
        window.draw(objects);
    }
}
