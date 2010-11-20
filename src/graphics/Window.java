package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;

public class Window extends JFrame {

    static int WIDTH = 1600;
    static int HEIGHT = 1024;
    double x, y;
    int xpos, ypos;
    double i = 0;
    Color backgroundColor = Color.DARK_GRAY;
    BufferedImage buffer;
    Panel panel;

    public void initialize() {
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        x = 0;
        y = 0;
        xpos = 50;
        ypos = 50;
    }
    public void draw(){
        drawBuffer();
        drawScreen();
    }
    public void drawBuffer() {
        Graphics2D b = buffer.createGraphics();
        // Gör så att allt blir härligt smooth
        b.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        b.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        b.setColor(backgroundColor); // TODO: Byt ut mot bild.
        b.fillRect(0, 0, WIDTH, HEIGHT);

        ImageObject image = new ImageObject();

        drawImage(image, b, 100, 200);
        if (panel.isKeyPressed(KeyEvent.VK_LEFT)) {
            if(y!=0)
                i--;
        }
        if (panel.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if(y!=0)
                i++;
        }
        if (panel.isKeyPressed(KeyEvent.VK_UP)) {
            y += 2;
        }
        if (panel.isKeyPressed(KeyEvent.VK_DOWN)) {
            y--;
        }
        if (panel.isKeyPressed(KeyEvent.VK_SPACE)) {
            if(y>0){
                y -= y/20;
                if(y<0){
                    y=0;
                }
            }
        }
        if (y > 50) {
            y = 50;
        }
        if (y < -20) {
            y = -20;
        }
        if (y>0){
            y-=0.5;
        }
        if (y<0){
            y+=0.5;
        }
        double angle = (Math.PI / 256) * i;
        xpos = (int) Math.round(xpos + y * Math.cos(angle));
        ypos = (int) Math.round(ypos + y * Math.sin(angle));
        drawImage(image, b, xpos / 5, ypos / 5, angle);

        b.dispose();
    }

    public void drawImage(ImageObject image, Graphics2D b, int x, int y, double rotation) {
        AffineTransform tfm = new AffineTransform();

        tfm.rotate(rotation, x + image.getCenterWidth(), y + image.getCenterHeight());
        b.setTransform(tfm);
        b.drawImage(image.getImage(), x, y, this);
    }

    public void drawImage(ImageObject image, Graphics2D b, int x, int y) {
        drawImage(image, b, x, y, 0);
    }

    public Window() {
        panel = new Panel();
        add(panel);
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
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        g.dispose();
    }

    public void update() {
        // TODO: Uppdatera saker som händer.
    }
}
