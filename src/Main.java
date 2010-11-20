
import graphics.Window;
import objects.UserController;

public class Main {
    public static void main(String[] args){
        Main m = new Main();
        m.start();
    }
    public void start(){
        UserController userInput = new UserController();
        Window window = new Window(userInput);
        window.initialize();
        while(true)
        {
            window.update();
            window.draw();
            //window.drawBuffer();
            //window.drawScreen();
        }
    }
}
