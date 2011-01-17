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

}
