package objects.cars;

import graphics.ImageObject;

/**
 * Lada är den sämsta bilen i spelet.
 * @author gustav
 */
public class Lada extends StandardCar{
    public Lada(int x, int y){
        super(x, y);
        setMaxSpeed(5);
        setHealth(10);
        brokenCar = new ImageObject("car_lada_broken.png");
    }
    @Override
    public void setDestroyed(){
        destroyAnimation.addImage(new ImageObject("car_lada_exploding.png"));
        destroyAnimation.addImage(new ImageObject("car_lada_exploding2.png"));
        destroyAnimation.setSpeed(100);
        destroyAnimation.play(1);
        destroyed = true;
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
