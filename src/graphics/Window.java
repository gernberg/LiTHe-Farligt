package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Window extends JFrame {
    static int WIDTH = 1024;
    static int HEIGHT = 768;
    Color backgroundColor = Color.BLACK;
    BufferedImage buffer;
    public void initialize(){
        buffer = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
    }
    public void drawBuffer(){
        Graphics2D b = buffer.createGraphics();
        b.setColor(backgroundColor); // TODO: Byt ut mot bild.
        b.fillRect(0,0,WIDTH,HEIGHT);
        b.dispose();
    }
    public Window() {
        add(new Panel());
        setTitle("GTA - LiTHe Farligt");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Kul att det inte är default
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        createBufferStrategy(2);
    }

    public void drawScreen() {
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.drawImage(buffer,0,0,this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void update() {
        // TODO: Uppdatera saker som händer.
    }
}
