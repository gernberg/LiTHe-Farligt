package objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class UserController implements KeyListener{
    // Det objekt som användaren kontrollerar för tillfället.
    MoveableObject currentObject;
    // Ett set som innehåller de tangenter som är nertryckta för tillfäl;let.
    Set<Integer> activeKeys = new HashSet<Integer>();
    
    boolean isEnterReleased = false;

    public UserController() {
        this.currentObject = null;
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
        activeKeys.add(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) {
        activeKeys.remove(e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            isEnterReleased = true;
        }
    }
    public boolean isKeyPressed(int keyCode){
        return activeKeys.contains(keyCode);
    }

    public void poll() {
        if(currentObject==null){
            // Om vi inte styr någonting för tillfället - ex. om man är död.
            return;
        }
        if(isKeyPressed(KeyEvent.VK_UP)){
            currentObject.accelerate();
        }
        if(isKeyPressed(KeyEvent.VK_DOWN)){
            currentObject.retardate();
        }
        if(isKeyPressed(KeyEvent.VK_LEFT)){
            currentObject.turnLeft();
        }
        if(isKeyPressed(KeyEvent.VK_RIGHT)){
            currentObject.turnRight();
        }
        if(isKeyPressed(KeyEvent.VK_SPACE)){
            currentObject.brake();
        }
    }


    public boolean shallWeSwitchObjects() {
       if(isEnterReleased){
           isEnterReleased = false;
           return true;
       }
       return false;
    }

    public MoveableObject getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(MoveableObject currentObject) {
        this.currentObject = currentObject;
    }


}
