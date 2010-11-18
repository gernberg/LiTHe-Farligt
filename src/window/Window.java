package window;

import javax.swing.JFrame;

public class Window extends JFrame {
    static int WIDTH = 1024;
    static int HEIGHT = 768;
    public Window() {
        add(new Board());
        setTitle("Skeleton");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Kul att det inte är default
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
