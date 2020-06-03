package removers;

import application.Game;
import gameelements.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 * author hezi yaffe 208424242.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * @param g is the game.
     * @param removedBlocks is the counter of remaining blocks.
     */
    public BlockRemover(Game g, Counter removedBlocks) {
        this.game = g;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit is the object that being hit.
     * @param hitter is hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }

}
