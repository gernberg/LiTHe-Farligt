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
public class Person extends MoveableObject{
    
    @Override
    public void setImage() {
        setImage(new ImageObject("person.png"));
    }

}
