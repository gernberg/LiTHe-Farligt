import graphics.Window;
import objects.UserController;
/**
 * 
 * @author gustav
 */
public class Main {
    public static void main(String[] args){
        Main m = new Main();
        m.start();
    }
    public void start(){
        Window window = new Window();
        UserController userController = new UserController(window);
        Coordinator coordinator = new Coordinator(window, userController);

        while(coordinator.update())
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) { }
        }
    }
}
