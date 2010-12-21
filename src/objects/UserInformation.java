/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

/**
 *
 * @author gustav
 */
public class UserInformation {
    private UserController uc;
    public UserInformation(UserController uc) {
        this.uc = uc;
    }
    public double getPlayerX(){
        return uc.getCurrentObject().getX();
    }
    public double getPlayerY(){
        return uc.getCurrentObject().getY();
    }
    public double getPlayerAngle(){
        return uc.getCurrentObject().getAngle();
    }

}
