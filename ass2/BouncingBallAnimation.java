import biuoop.GUI;
import biuoop.DrawSurface;

/**
  *author hezi yaffe 208424242.
  */
public class BouncingBallAnimation  {
    /**
     * @param args is an array of arguments from the cmd.
     */
    public static void main(String[] args) {
      int startX = 0;
      int startY = 0;
      int width = 200;
      int heigth = 200;
      GUI gui = new GUI("title", width, heigth);
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK);
      ball.setVelocity(2, 2);
      ball.setXLowerDomain(startX);
      ball.setYLowerDomain(startY);
      ball.setXUpperDomain(width);
      ball.setYUpperDomain(heigth);
      while (true) {
         ball.moveOneStep();
         DrawSurface d = gui.getDrawSurface();
         ball.drawOn(d);
         gui.show(d);
         // wait for 50 milliseconds.
         sleeper.sleepFor(50);
      }
    }
}