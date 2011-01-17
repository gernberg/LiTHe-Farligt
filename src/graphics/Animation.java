package graphics;

import java.util.ArrayList;

/**
 * Denna klass hjälper till med animationer.
 * @author gustav
 */
public class Animation {

    ArrayList<ImageObject> images;
    int increment = 0;
    boolean playing = true;
    private int cycles, cyclesLeft, speed;

    public Animation() {
        this(new ArrayList<ImageObject>());
    }

    /**
     * Bestäm bilder för animationen
     * @param images
     */
    public Animation(ArrayList<ImageObject> images) {
        this(images, 10);
    }

    /**
     * Bestäm bilder och hastighet för animationen
     * @param images
     * @param speed
     */
    public Animation(ArrayList<ImageObject> images, int speed) {
        setImages(images);
        setSpeed(speed);
    }

    public ArrayList<ImageObject> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageObject> images) {
        this.images = images;
    }

    public void addImage(ImageObject image) {
        images.add(image);
    }

    /**
     * Ser vilken bild som är aktuell just nu.
     * @return en bild, eller null om animationen inte körs
     */
    public ImageObject poll() {
        if (!isPlaying()) {
            // Om vi inte spelar en animation just nu.
            return null;
        }
        increment++;
        if (increment >= (images.size() * speed)) {
            increment = 0;
        }
        decreaseCycles();
        return images.get(increment / speed);
    }
    /**
     * Kontrollerar om animationen skall spela.
     * @return
     */
    public boolean isPlaying() {
        return (cyclesLeft > 0) && playing;
    }

    public void play() {
        play(-1);
    }

    public void play(int cycles) {
        playing = true;
        setCycles(cycles);
    }

    public void stop() {
        playing = false;
    }
    /**
     * Minskar antal cykler som är kvar att spela upp
     */
    private void decreaseCycles() {
        if (cycles == -1) {
            // Specialfallet, när den ska loopa i all oändlighet
            cyclesLeft = 1;
        } else {
            cyclesLeft--;
        }
    }

    private void setCycles(int cycles) {
        this.cycles = cycles;
        if (cycles == -1) {
            // Specialfallet, när den ska loopa i all oändlighet
            this.cyclesLeft = 1;
        } else {
            this.cyclesLeft = cycles;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
