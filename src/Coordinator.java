
import graphics.Window;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;
import objects.Car;
import objects.MoveableObject;
import objects.Object;
import objects.Person;
import objects.UserController;
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
        window.addUserInput(userController);
        userController.setCurrentObject(person);

        this.window = window;
        this.userController = userController;
    }
    public void addPerson(){
        person = new Person();
        person.setUsedByUser(true);
        objects.add(person);
    }
    public void addCar(){
        objects.add(new Car());
    }
      public MoveableObject getPerson() {
        return person;
    }
    public MoveableObject switchObject(MoveableObject currentObject) {
        MoveableObject tmpObject = currentObject;
        for (Object object : objects) {
            if(!currentObject.equals(person)){
                // Om man inte är en person just nu  - betyder det att vi alltid
                // Ska "hoppa ut ur" fordonet.
                return person;
            }
            if(!object.equals(currentObject)){
                if(object.getEnteringRectangle().intersects(currentObject.getBoundingRectangle().getBounds())){
                    return (MoveableObject) object;
                }
            }
        }
        return tmpObject;
    }

    public void update() {
        for (Object object : objects) {
            object.poll();
        }
        userController.poll();
        if(userController.shallWeSwitchObjects()){
            float tmpX = userController.getCurrentObject().getX();
            float tmpY = userController.getCurrentObject().getY();
            userController.setCurrentObject(switchObject(userController.getCurrentObject()));
            if(!userController.getCurrentObject().equals(person)){
                // Om vi inte blev en person, då ska det hända saker.
                objects.remove(person);
            }else{
                person.init();
                person.setX(tmpX);
                person.setY(tmpY);
                objects.add(person);
            }
        }
        window.draw(objects);
    }
}
