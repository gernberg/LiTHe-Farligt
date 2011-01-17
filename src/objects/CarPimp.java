/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import graphics.ImageObject;

/**
 *
 * @author gustav
 */
public class CarPimp extends Car{
    public CarPimp(int x, int y){
        super(x, y);
    }
    @Override
    public void setImage(){
        super.setImage(new ImageObject("car_pimp.png"));
    }
    public void stealAction(){
        super.stealAction();
        setImage(new ImageObject("car_pimp_stolen.png"));
    }

}
