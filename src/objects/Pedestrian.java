/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

/**
 *
 * @author gustav
 */
public class Pedestrian extends Person{

    public Pedestrian(int x, int y) {
        super(x, y);
        setSpeed(getMaxSpeed()/2);
        setAcceleration(0);
    }

    
}
