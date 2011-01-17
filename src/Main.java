import graphics.Window;
import objects.UserController;
/**
 * Mainklassen startar upp spelet
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
            try{
                // Gör så att spelet inte går för snabbt
                Thread.sleep(10);
            } catch (InterruptedException ex) { }
        }
    }
}
