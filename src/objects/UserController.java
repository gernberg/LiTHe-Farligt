/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gustav
 */
public class UserController implements KeyListener{
    MoveableObject currentObject;
    Window window;
    // Ett set som innehåller de tangenter som är nertryckta för tillfället.
    Set<Integer> activeKeys = new HashSet<Integer>();

    public UserController(Window window) {
        this.window = window;
        currentObject = getPerson();
    }
    public MoveableObject getPerson(){
        return window.getPerson();
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
        activeKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        activeKeys.remove(e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            switchObject();
        }
    }
    public boolean isKeyPressed(int keyCode){
        return activeKeys.contains(keyCode);
    }

    public void poll() {
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

    private void switchObject() {
        currentObject = window.switchObject(currentObject);
    }


}
