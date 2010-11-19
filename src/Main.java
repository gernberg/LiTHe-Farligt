
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
            try
            {
                window.update();
                window.drawBuffer();
                window.drawScreen();
                Thread.sleep(15);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
