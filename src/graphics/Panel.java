package graphics;

import javax.swing.JPanel;
/**
 * JPanel för det grafiska,
 * @author gustav
 */
public class Panel extends JPanel{
    public Panel(){
        // Information om varför IgnoreRepaint står här är hämtat från:
        // http://www.cokeandcode.com/info/tut2d.html
        setIgnoreRepaint(true);
        setFocusable(true);
    }
}
