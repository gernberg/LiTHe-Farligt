package graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener{
    public Panel(){
        setIgnoreRepaint(true);
        addKeyListener(this);
        setFocusable(true);

    }

    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
