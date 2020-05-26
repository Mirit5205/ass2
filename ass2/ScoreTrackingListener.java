public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private Counter numOfRemainingBlocks;

    //private constants.
    private static final int BALL_HIT_SCORES = 5;

    public ScoreTrackingListener(Counter scoreCounter, Counter numOfBlocks) {
        this.currentScore = scoreCounter;
        this.numOfRemainingBlocks = numOfBlocks;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(BALL_HIT_SCORES );
    }
}
