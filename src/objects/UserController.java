/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author gustav
 */
public class UserController implements KeyListener{
    MoveableObject currentObject;
    public void keyTyped(KeyEvent e) { /* Denna hanterar vi inte */}
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
