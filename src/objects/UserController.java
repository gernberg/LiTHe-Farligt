package objects;

import graphics.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class UserController implements KeyListener{
    MoveableObject currentObject;
    Window window;
    // Ett set som innehåller de tangenter som är nertryckta för tillfället.
    Set<Integer> activeKeys = new HashSet<Integer>();
    
    boolean isEnterReleased = false;

    public UserController(Window window) {
        this.window = window;
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
        if(e.getKeyChar() == 'd'){
            window.switchDebug();
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
