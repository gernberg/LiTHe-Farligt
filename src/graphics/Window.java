package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import objects.Car;
import objects.MoveableObject;
import objects.UserController;
import objects.Object;
import objects.Person;

public class Window extends JFrame {

    static int WINDOW_WIDTH = 1024;
    static int WINDOW_HEIGHT = 768;
    double x, y;
    int xpos, ypos;
    double i = 0;
    Color backgroundColor = Color.LIGHT_GRAY;
    BufferedImage buffer;
    Panel panel;
    Set<MoveableObject> objects = new HashSet<MoveableObject>();
    MoveableObject person;
    public void initialize() {
        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
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
        b.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        for (MoveableObject object : objects) {
            object.poll();
            drawImage(object, b);
        }
        b.dispose();
    }
    public void drawImage(Object o, Graphics2D b){
        drawImage(o.getImage(), b, o.getIntX(), o.getIntY(), o.getAngle(), o.getRotationCenterX(), o.getRotationCenterY());
        drawDebug(o, b);
    }
    public void drawDebug(Object o, Graphics2D b){
        AffineTransform tfm = new AffineTransform();
        MoveableObject m = (MoveableObject) o;
        b.setTransform(tfm);
        b.setColor(Color.white);
        b.drawOval(o.getRotationCenterX() + o.getIntX() - 10,o.getRotationCenterY() + o.getIntY() - 10, 20, 20);
        b.setColor(Color.green);
        b.drawOval(o.getRotationCenterX() + o.getIntX() - 1,o.getRotationCenterY() + o.getIntY() - 1, 2, 2);
    }
    public void drawImage(ImageObject image, Graphics2D b, int x, int y, double rotation, int rotationCenterX, int rotationCenterY) {
        AffineTransform tfm = new AffineTransform();
        tfm.rotate(rotation, x + rotationCenterX, y + rotationCenterY);
        b.setTransform(tfm);
        b.drawImage(image.getImage(), x, y, this);
        tfm.rotate(0, 0, 0);
    }

    public void drawImage(ImageObject image, Graphics2D b, int x, int y) {
        drawImage(image, b, x, y, 0, 0, 0);
    }

    public Window() {
        panel = new Panel();
        add(panel);
        setTitle("GTA - LiTHe Farligt");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Kul att det inte är default
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        createBufferStrategy(2);
        
        addPerson();
        addCar();
    }

    public void drawScreen() {
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void update() {
        // TODO: Uppdatera saker som händer.

    }

    public void addUserInput(UserController UserController) {
        panel.addKeyListener(UserController);
    }
    public void addPerson(){
        person = new Person();
        person.setUsedByUser(true);
        objects.add(person);
    }
    public void addCar(){
        objects.add(new Car());
    }
    public MoveableObject getPerson() {
        return person;
    }

    public MoveableObject switchObject(MoveableObject currentObject) {
        for (MoveableObject object : objects) {
            if(!object.equals(currentObject)){
                if(currentObject.distanceTo(object)<100){
                    currentObject.setUsedByUser(false);
                    object.setUsedByUser(true);
                    return object;
                }
            }
        }
        return currentObject;
    }
}
