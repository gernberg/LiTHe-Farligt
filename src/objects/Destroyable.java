/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

/**
 *
 * @author gustav
 */
public interface Destroyable {
    /**
     * Kollision med något, skicka med vinkel och hastighet så skall det
     * bestämmas vad utgången blir.
     * @param angle
     * @param speed
     */
    public void destroy(double angle, double speed);
    /**
     * Hur många poäng ska man få när man lyckats förstöra / krocka med objektet?
     * @return
     */
    public int getScore();
    /**
     * Berätta om objektet redan är förstört.
     * @return
     */
    public boolean isDestroyed();
}
