package objects;

import graphics.Helper;
import graphics.ImageObject;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity (tidigare Object, omdöpt pga. förvirring) är grundklassen för alla
 * saker som finns med i spelet.
 * @author gustav
 */
public abstract class Entity {
    private ImageObject image;
    private double x,y;
    private int rotationCenterX, rotationCenterY, height, width, weight;
    public ImageObject getImage() {
        return image;
    }
    /**
     * Hämtar x värdet. Kan med fördel skuggas av subklasser
     * @return
     */
    public int getBoundingY(){
        return getIntY();
    }
    /**
     *
     * Hämtar y värdet. Kan med fördel skuggas av subklasser
     * @return
     */
    public int getBoundingX(){
        return getIntX();
    }
    /**
     * Kör setImage(), inte så mkt mera just nu.
     */
    public Entity() {
        setImage();
    }
    /**
     * Denna funktion sätter bilden, ska köras vid init.
     */
    public abstract void setImage();
    public void setImage(ImageObject image){
        this.image = image;
    }
    public double getX() {
        return x;
    }
    public int getIntX(){
        return (int) Math.round(getX());
    }
    public int getIntY(){
        return (int) Math.round(getY());
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getRotationCenterX(){
        return rotationCenterX;
    }
    public int getRotationCenterY(){
        return rotationCenterY;
    }
    /**
     * Sätter objektets position,
     * Använder setX() och setY()
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }
    public abstract double getAngle();
    /**
     * Körs varje "runda" för att kolla om något nytt hänt
     */
    public abstract void poll();
    
    /**
     * Hämtar de små rektanglar som ligger i varje hörna av ett objekt.
     * 4st 1x1 rektanglar.
     * Behövs för att intersect bara fungerar mellan rektanglar - där max en får vara roterad
     * @return
     */
    public Set<Rectangle> getBoundingRectangles(){
        Set<Rectangle> pointSet = new HashSet<Rectangle>();
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX(), getBoundingY(), 1,1)).getBounds());
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX()+getWidth(), getBoundingY(), 1, 1)).getBounds());
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX(), getBoundingY()+getHeight(), 1, 1)).getBounds());
        pointSet.add(rotateRectangle(new Rectangle(getBoundingX()+getWidth(), getBoundingY()+getHeight(), 1, 1)).getBounds());
        return pointSet;
    }
    /**
     * Kollar om objektet är stjälbart
     * @return
     */
    public boolean isStealable() {
        return (this instanceof Stealable);
    }
    /**
     * Berätta hur många poäng användaren får för sina bravader.
     * Som standard får man inga poäng.
     * @param mo
     * @return
     */
    public int getScore(MoveableObject mo){
        return 0;
    }

    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    /**
     * Roterar en rektangel
     * TODO: Ligger den här i rätt fil verkligen, eller ska vi skapa en klass
     * för den här typen av "hjälpfunktioner"?
     * @param r Rektangeln som skall roteras.
     * @return
     */
    public Shape rotateRectangle(Rectangle r){
        return Helper.allHelpers.rotateRectangle(r, this);
    }
    /**
     * Hämtar boundingboxen för objektet (och ser till att den är roterad precis
     * som objektets bild)
     * @return
     */
    public Shape getBoundingRectangle() {
        return rotateRectangle(new Rectangle(getBoundingX(), getBoundingY(), getWidth(), getHeight()));
    }
    public void setRotationCenterX(int x){
        rotationCenterX = x;
    }
    public void setRotationCenterY(int y){
        rotationCenterY = y;
    }
    /**
     * Kollar om objektet är förstörbart, och isåfall - helt.
     * @return
     */
    public boolean isDestroyable(){
        if(this instanceof Destroyable){
            return !(((Destroyable) this).isDestroyed());
        }
        return false;
    }
    public abstract collisionType getCollisionType();
}