package objects;
public interface Stealable {
    /**
     * Vad som ska hända med objektet när det stjäls, ex. ska ett larm gå?
     */
    public void stealAction();
    /**
     * Vad ska hända när man överger ett objekt efter att ha stulet det?
     */
    public void abandonAction();
}