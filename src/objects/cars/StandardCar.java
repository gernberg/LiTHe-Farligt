package objects.cars;

import graphics.ImageObject;
import objects.Destroyable;
import objects.MoveableObject;
import objects.Stealable;
import objects.collisionType;

/**
 * StandardCar är grundklassen för alla bilar
 * @author gustav
 */
public class StandardCar extends MoveableObject implements Stealable, Destroyable{
    private double health = 1000;
    boolean destroyed = false;
    boolean burning = false;
    int burning_incr = 0;
    public StandardCar(){
        super();
    }
    public StandardCar(int x, int y) {
        super();
        setPosition(x, y);
    }
    public void init() {
        setCenterX(35);
        setCenterY(25);
        setSpeed(0);
        setWidth(40);
        setHeight(25);
        setAcceleration(0.2);
        setMaxSpeed(10);
        setTorque(0.5);
        setImage();
        setPosition(150,200);
        
    }
    public int getBoundingY(){
        return super.getBoundingY() + 12;
    }

    @Override
    public void setImage() {
        setImage(new ImageObject("car.png"));
    }
    @Override
    public double getNewX(){
        setX(getVelocity().getNewX(getX()));
        return getX();
    }
    @Override
    public double getNewY(){
        setY(getVelocity().getNewY(getY()));
        return getY();
    }

    public void stealAction() {
        // Ger bilen en ny bild
        setImage(new ImageObject("car_stolen.png"));
    }

    public void abandonAction(){
        // Återställer till standardbilden
        setImage();
    }

    
    public void destroy(double angle, double damage){
        health -= damage;
        if(health<0){
            setDestroyed();
        }
    }
    /**
     * Att köra på en bil ger en olika poäng beroende på hur stor skada man orsakar
     * @param mo
     * @return
     */
    public int getScore(MoveableObject mo){
        return (int) mo.getDamageRate();
    }
    public void setDestroyed(){
        burning = true;
        setImage(new ImageObject("exploding-car.png"));
        destroyed = true;
    }
    public void poll(){
        super.poll();
    }

    public int getScore() {
        return 1000;
    }
    /**
     * Alla bilar är stjälbara ända tills de är trasiga.
     * @return
     */
    @Override
    public boolean isStealable() {
        return !isDestroyed();
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public double getDamageRate() {
        return getSpeed()*getSpeed()*10;
    }

    public ImageObject getImage(){
        if(burning){
            burning_incr++;
        }
        if(burning && isDestroyed()){
           if(burning_incr%10>5){
            setImage(new ImageObject("exploding-car2.png"));
           }
           else{
            setImage(new ImageObject("exploding-car.png"));
           }
           if(burning_incr>100){
                setImage(new ImageObject("broken-car.png"));
                burning = false;
           }
        }
        return super.getImage();
    }

    @Override
    public collisionType getCollisionType() {
        return collisionType.FIXED;
    }

}