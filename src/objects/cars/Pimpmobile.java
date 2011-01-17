/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects.cars;

import graphics.ImageObject;
import objects.MoveableObject;

/**
 *
 * @author gustav
 */
public class Pimpmobile extends StandardCar{
    public Pimpmobile(int x, int y){
        super(x, y);
        setAcceleration(0.7);
        setMaxSpeed(10);
        setHealth(500);
    }
    @Override
    public void setImage(){
        super.setImage(new ImageObject("car_pimp.png"));
    }
    public void stealAction(){
        super.stealAction();
        setImage(new ImageObject("car_pimp_stolen.png"));
    }
    /**
     * Att köra in i pimpbilen ger en dubbelt med poäng.
     * @param mo
     * @return
     */
    @Override
    public int getScore(MoveableObject mo) {
        return super.getScore(mo)*2;
    }

    @Override
    public void setDestroyed(){
        destroyAnimation.addImage(new ImageObject("car_pimp_exploding.png"));
        destroyAnimation.addImage(new ImageObject("car_pimp_exploding2.png"));
        destroyAnimation.addImage(new ImageObject("car_pimp_exploding3.png"));
        destroyAnimation.setSpeed(5);
        destroyAnimation.play(10);
        destroyed = true;
    }

}
