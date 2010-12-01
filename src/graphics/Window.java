package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.Set;
import objects.MoveableObject;
import objects.UserController;
import objects.Object;

public class Window extends JFrame {

    static int WINDOW_WIDTH = 1024;
    static int WINDOW_HEIGHT = 768;
    double i = 0;
    Color backgroundColor = Color.LIGHT_GRAY;
    BufferedImage buffer;
    Graphics2D b;
    Panel panel;
    private boolean debug = false;
    /**
     * Växlar debugläget
     */
    public void switchDebug(){
        debug = !debug;
    }
    /**
     * Ritar ut all grafik
     * @param objects De objekt som skall synas på skärmen
     */
    public void draw(Set<Object> objects) {
        // TODO: Ska vi snygga till koden?
        b = buffer.createGraphics();
        // Gör så att allt blir härligt smooth
        b.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        b.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        b.setColor(backgroundColor); // TODO: Byt ut mot bild.
        b.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        for (Object object : objects) {
            drawObject(object);
        }
        b.dispose();
        // Hämtad från drawScreen 
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    /**
     * Ritar ut ett objekt, anropar drawImage
     * @param o
     */
    public void drawObject(Object o){
        drawImage(o.getImage(), o.getIntX(), o.getIntY(), o.getAngle(), o.getRotationCenterX(), o.getRotationCenterY());
        if(debug){
            drawDebugData((MoveableObject) o);
        }
    }
    /**
     * Ritar ut debugdata för ett MoveableObject.
     * @param o
     */
    public void drawDebugData(MoveableObject o){
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(o.getAngle(), o.getIntX()+o.getRotationCenterX(), o.getIntY()+o.getRotationCenterY());
        b.setTransform(tfm);
        if(o.isUsedByUser()){
            b.setColor(Color.GREEN);
            b.drawOval(o.getRotationCenterX() + o.getIntX() - 10,o.getRotationCenterY() + o.getIntY() - 10, 20, 20);
        }
        b.setColor(Color.WHITE);
        b.drawOval(o.getRotationCenterX() + o.getIntX() - 1,o.getRotationCenterY() + o.getIntY() - 1, 2, 2);
        b.setColor(Color.RED);
        b.draw(o.getBoundingRectangle());
    }
    /**
     * Ritar en bild, med rotation
     * @param image Bilden som skall ritas ut
     * @param x x-position
     * @param y y-position
     * @param rotation Rotering (mätt i radianer)
     * @param rotationCenterX x-position för rotation, relativt bildens x position
     * @param rotationCenterY y-position för rotation, relativt bildens y position
     */
    public void drawImage(ImageObject image, int x, int y, double rotation, int rotationCenterX, int rotationCenterY) {
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(rotation, x + rotationCenterX, y + rotationCenterY);
        b.setTransform(tfm);
        b.drawImage(image.getImage(), x, y, this);
        tfm.rotate(0, 0, 0);
    }
    /**
     * Ritar en bild, helt utan rotation.
     * @param image
     * @param x
     * @param y
     */
    public void drawImage(ImageObject image, int x, int y) {
        drawImage(image, x, y, 0, 0, 0);
    }
    /**
     * Skapar alla viktiga saker
     */
    public Window() {
        panel = new Panel();
        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        add(panel);
        setTitle("GTA - LiTHe Farligt");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Kul att det inte är default
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        createBufferStrategy(2);
    }
    

    public void addUserInput(UserController UserController) {
        panel.addKeyListener(UserController);
    }

  
  
}
