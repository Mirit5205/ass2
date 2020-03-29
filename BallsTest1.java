import biuoop.GUI;
import biuoop.DrawSurface;

/**
  *author hezi yaffe 208424242.
  */
public class BallsTest1 {
    /**
     * @param args is an array of arguments we get from the cmd.
     */
   public static void main(String[] args) {
      GUI gui = new GUI("Balls Test 1", 400, 400);
      DrawSurface d = gui.getDrawSurface();
      Ball b1 = new Ball(100, 100, 30, java.awt.Color.RED);
      Ball b2 = new Ball(100, 150, 10, java.awt.Color.BLUE);
      Ball b3 = new Ball(80, 249, 50, java.awt.Color.GREEN);

      b1.drawOn(d);
      b2.drawOn(d);
      b3.drawOn(d);

      gui.show(d);
   }
}