package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * ImageObject är till för att underlätta hanteringen av bilder.
 */
public class ImageObject extends JPanel{
    private BufferedImage image;
    /**
     * För att instansiera ett ImageObject måste vi veta vilken bildfil vi skall använda
     * @param imagefile
     */
    public ImageObject(String imagefile){
        setImage(imagefile);
    }

    public BufferedImage getImage() {
        return image;
    }

    void setImage(String string) {
        try{
            image = ImageIO.read(new File("src/images/"+string));
        }catch(Exception e){
            // TODO: Se till att vi gör något smart om ex. filen inte finns.
            // Förslagsvis ladda en bild med ett kryss, eller döda programmet.
            System.out.println(e);
        }
    }
    
}
