/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gustav
 */
public class Animation {
    ArrayList<ImageObject> images;
    int increment = 0;
    boolean playing = true;
    private int cycles, cyclesLeft, speed;
    
    public Animation(){
        this(new ArrayList<ImageObject>());
    }
    public Animation(ArrayList<ImageObject> images){
        this(images, 10);
    }
    public Animation(ArrayList<ImageObject> images, int speed){
        setImages(images);
        setSpeed(speed);
    }

    public ArrayList<ImageObject> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageObject> images) {
        this.images = images;
    }

    public void addImage(ImageObject image){
        images.add(image);
    }

    public ImageObject poll() {
        if(!isPlaying()){
            // Om vi inte spelar en animation just nu.
            return null;
        }
        increment++;
        if(increment>=(images.size()*speed)){
            increment = 0;
        }
        decreaseCycles();
        return images.get(increment/speed);
    }

    public boolean isPlaying() {
        return (cyclesLeft>0) && playing;
    }
    public void play(){
        play(-1);
    }
    public void play(int cycles){
        playing = true;
        setCycles(cycles);
    }
    public void stop(){
        playing = false;
    }

    private void decreaseCycles() {
        if(cycles==-1)
            cyclesLeft = 1;
        else
            cyclesLeft--;
    }

    private void setCycles(int cycles) {
        this.cycles = cycles;
        if(cycles==-1)
            this.cyclesLeft = 1;
        else
            this.cyclesLeft = cycles;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }
}
