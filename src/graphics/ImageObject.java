/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author gustav
 */
public class ImageObject extends JPanel{
    private BufferedImage image;
    public ImageObject() {
        try{
            image = ImageIO.read(new File("src/images/car.png"));
            
        }catch(Exception e){
            // TODO: Se till att vi gör något smart om ex. filen inte finns.
            // Förslagsvis ladda en bild med ett kryss, eller döda programmet.
            System.out.println(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
    
    
}
