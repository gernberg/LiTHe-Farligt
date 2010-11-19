package graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener{
    private boolean[] keyStatus;
    public Panel(){
        keyStatus = new boolean[50];
        setIgnoreRepaint(true);
        addKeyListener(this);
        setFocusable(true);

    }

    public void keyTyped(KeyEvent ke) {
        return;
    }

    public void keyPressed(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = false;

    }
    public boolean isKeyPressed(int keyCode){
        return keyStatus[keyCode];
    }
    
}
