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
        Coordinator coordinator = new Coordinator(new Window(), new UserController());

        while(coordinator.update())
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) { }
        }
    }
}
