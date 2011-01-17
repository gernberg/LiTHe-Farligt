package objects.cars;

import graphics.Animation;
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
    double health = 1000;
    boolean destroyed = false;
    Animation destroyAnimation = new Animation();
    ImageObject brokenCar = new ImageObject("broken-car.png");

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    public StandardCar(){
        super();
    }
    public StandardCar(int x, int y) {
        super();
        setPosition(x, y);
    }
    public void init() {
        setRotationCenterX(35);
        setRotationCenterY(25);
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
     * Att köra på bilen ger en olika poäng beroende på hur stor skada man orsakar
     * @param mo
     * @return
     */
    public int getScore(MoveableObject mo){
        return (int) mo.getDamageRate();
    }
    public void setDestroyed(){
        destroyAnimation.addImage(new ImageObject("exploding-car.png"));
        destroyAnimation.addImage(new ImageObject("exploding-car2.png"));
        destroyAnimation.play(10);
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
        if(isDestroyed()){
            if(destroyAnimation.isPlaying()){
                return destroyAnimation.poll();
            }else{
                return brokenCar;
            }
        }
        return super.getImage();
    }

    @Override
    public collisionType getCollisionType() {
        return collisionType.SOLID;
    }

}
