/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

/**
 *
 * @author gustav
 */
public class Velocity {
    float speed;
    double angle;

    public void increaseSpeed() {
        speed++;
    }
    public void decreaseSpeed(){
        speed--;
    }
    public void increaseAngle(){

    }
    public void decreaseAngle(){
        
    }
    public int getNewX(int x) {
        return Math.round((float) Math.cos(angle) * speed);
    }

    public int getNewY(int y) {
        return Math.round((float) Math.sin(angle) * speed);
    }
}
