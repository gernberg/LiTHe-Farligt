
import graphics.Window;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.UserController;

public class Main {
    public static void main(String[] args){
        Main m = new Main();
        m.start();
    }
    public void start(){
        Window window = new Window();
        UserController userController = new UserController(window);
        Coordinator coordinator = new Coordinator(window, userController);
        while(true)
        {
            coordinator.update();
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
