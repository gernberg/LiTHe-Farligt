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
    static int WIDTH = 1024;
    static int HEIGHT = 768;
    int x,y;
    int i = 0;
    Color backgroundColor = Color.WHITE;
    BufferedImage buffer;
    Panel panel;
    public void initialize(){
        buffer = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        x=0;
        y=0;
    }
    public void drawBuffer(){
        Graphics2D b = buffer.createGraphics();
        b.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
        RenderingHints.VALUE_ANTIALIAS_ON);
        b.setColor(backgroundColor); // TODO: Byt ut mot bild.
        b.fillRect(0,0,WIDTH,HEIGHT);
        ImageObject image = new ImageObject();

        drawImage(image, b, 100, 200);
        if(panel.isKeyPressed(KeyEvent.VK_LEFT)){
            i--;
        }
        if(panel.isKeyPressed(KeyEvent.VK_RIGHT)){
            i++;
        }
        if(panel.isKeyPressed(KeyEvent.VK_UP)){
            x++;
        }
        if(panel.isKeyPressed(KeyEvent.VK_DOWN)){
            x--;
        }
        drawImage(image, b, x, 100, (Math.PI/32)*i);

        b.dispose();
    }
    public void drawImage(ImageObject image, Graphics2D b, int x, int y, double rotation){
        AffineTransform tfm = new AffineTransform();
        
        tfm.rotate(rotation, x+image.getImage().getWidth()/2, y+image.getImage().getHeight()/2);
        b.setTransform(tfm);
        b.drawImage(image.getImage(),x,y,this);
    }
    public void drawImage(ImageObject image, Graphics2D b, int x, int y){
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
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.drawImage(buffer,0,0,this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void update() {
        // TODO: Uppdatera saker som händer.
    }
}
