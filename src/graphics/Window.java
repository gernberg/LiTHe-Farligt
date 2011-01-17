package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import javax.swing.JFrame;
import java.util.Set;
import javax.swing.JLabel;
import objects.MoveableObject;
import objects.UserController;
import objects.Entity;

public class Window extends JFrame {
    private static int WINDOW_WIDTH = 600;
    private static int WINDOW_HEIGHT = 600;
    private static int WORLD_WIDTH = 1280;
    private static int WORLD_HEIGHT = 1280;
    public int strangey, strangex;

    double i = 0;
    Color backgroundColor = Color.GRAY;
    BufferedImage buffer;
    Graphics2D b, bg2;
    Panel panel;

    boolean isBackgroundDrawed = false;
    /**
     * Ritar ut all grafik
     * @param objects De objekt som skall synas på skärmen
     */
    public void draw(Set<Entity> backgroundObjects, Set<Entity> foregroundObjects, long points, String text) {
        b = buffer.createGraphics();
        
        // Gör så att allt blir härligt smooth
        b.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        b.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        b.setColor(backgroundColor); // TODO: Byt ut mot bild.
        b.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);


        
        if(!isBackgroundDrawed){
            for (Entity foregroundObject : foregroundObjects) {
                drawObject(foregroundObject, b);
            }
            //isBackgroundDrawed = true;
        }
        for (Entity backgroundObject : backgroundObjects) {
                drawObject(backgroundObject, b);
        }
        drawScore(points);
        drawTextMessage(text);
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
    public void drawObject(Entity o, Graphics2D b){
        drawImage(o.getImage(), o.getIntX(), o.getIntY(), o.getAngle(), o.getRotationCenterX(), o.getRotationCenterY(), b);
    }
    private int getRelativeX(int x){
        return x-strangex+getWINDOW_WIDTH()/2;
    }
    private int getRelativeY(int y){
        return y-strangey+getWINDOW_HEIGHT()/2;
    }
    /**
     * Ritar en bild, med rotation
     * @param image Bilden som skall ritas ut
     * @param x x-position
     * @param y y-position
     * @param rotation Rotering (mätt i radianer)
     * @param rotationCenterX x-position för rotation, relativt bildens x position
     * @param rotationCenterY y-position för rotation, relativt bildens y position
     * @param b Graphics2D, buffern som skapas
     */
    public void drawImage(ImageObject image, int x, int y, double rotation, int rotationCenterX, int rotationCenterY, Graphics2D b) {
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(rotation, x + rotationCenterX-strangex+getWINDOW_WIDTH()/2, y + rotationCenterY-strangey+getWINDOW_HEIGHT()/2);
        b.setTransform(tfm);
//        System.out.println(x + "|" + strangex);
//        System.out.println(y + "|" + strangey);
        b.drawImage(image.getImage(), x-strangex+getWINDOW_WIDTH()/2, y-strangey+getWINDOW_HEIGHT()/2, this);
        tfm.rotate(0, 0, 0);
    }
    /**
     * Ritar en bild, helt utan rotation.
     * @param image
     * @param x
     * @param y
     */
    public void drawImage(ImageObject image, Graphics2D b, int x, int y) {
        drawImage(image, x, y, 0, 0, 0, b);
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

    public static int getWORLD_HEIGHT() {
        return WORLD_HEIGHT;
    }
    public static int getWORLD_WIDTH() {
        return WORLD_WIDTH;
    }
    public static int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

    public static int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }
    /**
     * Ritar ut ett meddelande på skärmen strax ovanför användaren.
     * @param textMessage
     */
    public void drawTextMessage(String textMessage) {
        if(textMessage==null)
            return;
        AffineTransform tfm = new AffineTransform();
        tfm.setToScale(3, 3);
        b.setTransform(tfm);
        b.setColor(Color.BLACK);
        b.drawChars(textMessage.toCharArray(), 0, textMessage.length(), 100-textMessage.length()*3, 100);
        tfm.setToScale(2.985, 2.985);
        b.setTransform(tfm);
        b.setColor(Color.WHITE);
        b.drawChars(textMessage.toCharArray(), 0, textMessage.length(), 100-textMessage.length()*3, 100);
        b.dispose();
    }

    private void drawScore(long points) {
        b.setTransform(new AffineTransform());
        b.setColor(Color.BLACK);
        b.fillRect(0, 0, getWINDOW_WIDTH(), 50);
        b.setColor(Color.WHITE);
        b.drawLine(0, 50, getWINDOW_WIDTH(), 50);
        b.setColor(Color.GREEN);
        b.drawChars(("Poäng: " + String.valueOf(points)).toCharArray(), 0, ("Poäng: " + String.valueOf(points)).length(), getWINDOW_WIDTH()/2-20, 45);
    }
  
}