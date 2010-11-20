
import graphics.Window;

public class Main {
    public static void main(String[] args){
        Main m = new Main();
        m.start();
    }
    public void start(){
        Window window = new Window();
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
