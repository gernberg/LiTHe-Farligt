package objects;

/**
 * Interface för alla saker som är förstörbara
 * @author gustav
 */
public interface Destroyable {
    /**
     * Kollision med något, skicka med vinkel och skada så skall det
     * bestämmas vad utgången blir.
     * Returnerar hur mycket poäng användaren får för sina bravader.
     * @param angle
     * @param damage
     * @return score
     */
    public void destroy(double angle, double damage);
    /**
     * Berätta om objektet redan är förstört.
     * @return
     */
    public boolean isDestroyed();
}
