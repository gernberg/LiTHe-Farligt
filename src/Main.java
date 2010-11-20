
import graphics.Window;
import java.awt.event.KeyListener;
import objects.UserController;

public class Main {
    public static void main(String[] args){
        Main m = new Main();
        m.start();
    }
    public void start(){
        KeyListener keyListener = new UserController();
        Window window = new Window(keyListener);
        window.initialize();
        while(true)
        {
            window.update();
            window.draw();
        }
    }
}
