package objects;

import graphics.ImageObject;

/**
 * Lada är den sämsta bilen i spelet.
 * @author gustav
 */
public class CarLada extends Car{
    public CarLada(int x, int y){
        super(x, y);
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
