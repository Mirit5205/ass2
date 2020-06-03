package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * author hezi yaffe 208424242.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit is the ball that being hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
