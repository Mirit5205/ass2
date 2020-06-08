package removers;

import application.GameLevel;
import gameelements.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * author hezi yaffe 208424242.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * @param g is the game.
     * @param balls is the counter of remaining balls.
     */
    //constructor
    public BallRemover(GameLevel g, Counter balls) {
        this.game = g;
        this.remainingBalls = balls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit is the object that being hit.
     * @param hitter is hitter ball.
     */
   public void hitEvent(Block beingHit, Ball hitter) {
       hitter.removeFromGame(this.game, beingHit);
       this.remainingBalls.decrease(1);
   }
}
