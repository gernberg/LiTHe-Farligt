package objects;

import java.awt.Shape;

public interface Stealable {
    /**
     * Vad som ska hända med objektet när det stjäls, ex. ska ett larm gå?
     */
    public void stealAction();
    /**
     * Vad ska hända när man överger ett objekt efter att ha stulet det?
     */
    public void abandonAction();
    /**
     * Hämtar den rektangel som är området för "ingång" i objektet
     * @return
     */
    public abstract Shape getEnteringRectangle();
}