
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
        window.addUserInput(userController);
        window.initialize();
        while(true)
        {
            userController.poll();
            window.update();
            window.draw();
            try {
                Thread.sleep(15);
                //window.drawScreen();
                //window.drawScreen();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
