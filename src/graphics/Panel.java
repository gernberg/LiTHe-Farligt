package graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
    private boolean[] keyStatus;
    public Panel(){
        keyStatus = new boolean[50];
        setIgnoreRepaint(true);
        setFocusable(true);
    }
}
