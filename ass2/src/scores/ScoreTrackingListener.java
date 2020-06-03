package scores;

import gameelements.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * author hezi yaffe 208424242.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private Counter numOfRemainingBlocks;

    //private constants.
    private static final int BALL_HIT_SCORES = 5;

    /**
     * @param scoreCounter is the score's counter.
     * @param numOfBlocks is the block's counter.
     */
    //constructor
    public ScoreTrackingListener(Counter scoreCounter, Counter numOfBlocks) {
        this.currentScore = scoreCounter;
        this.numOfRemainingBlocks = numOfBlocks;
    }

    /**
     * increase score's counter in constant amount of points.
     * @param beingHit is the ball that being hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(BALL_HIT_SCORES);
    }
}
