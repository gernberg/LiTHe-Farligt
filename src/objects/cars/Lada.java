package objects.cars;

import graphics.ImageObject;

/**
 * Lada är den sämsta bilen i spelet.
 * @author gustav
 */
public class Lada extends Car{
    public Lada(int x, int y){
        super(x, y);
        setMaxSpeed(5);
    }
    @Override
    public void setImage(){
        super.setImage(new ImageObject("car_lada.png"));
    }
    public void stealAction(){
        super.stealAction();
        setImage(new ImageObject("car_lada_stolen.png"));
    }
}
