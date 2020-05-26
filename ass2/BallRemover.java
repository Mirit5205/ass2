public class BallRemover implements HitListener  {
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game g, Counter balls) {
        this.game = g;
        this.remainingBalls = balls;
    }

    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
   public void hitEvent(Block beingHit, Ball hitter) {
       hitter.removeFromGame(this.game, beingHit);
       this.remainingBalls.decrease(1);
       System.out.println(remainingBalls.getValue());
   }
}
