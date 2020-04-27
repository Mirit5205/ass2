import biuoop.GUI;
import biuoop.DrawSurface;

/**
 *author hezi yaffe 208424242.
 */

public class BouncingBallAnimation  {
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",200,200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        //ball.setXLowerDomain(0);
       // ball.setYLowerDomain(0);
        //ball.setXUpperDomain(200);
       // ball.setYUpperDomain(200);
        while (true) {
            //ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
    /**
     * @param args is an array of arguments from the cmd.
     */
    public static void main(String[] args) {
        int[] argsArr = new int[args.length];
        MultipleBouncingBallsAnimation.convertArgumentsToInts(argsArr, args);
        drawAnimation(new Point(argsArr[0], argsArr[1]), argsArr[2], argsArr[3]);


    }
}