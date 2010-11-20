
import graphics.Window;
import java.awt.event.KeyEvent;
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
           
            //window.drawBuffer();
            //window.drawScreen();
        }
    }
}
