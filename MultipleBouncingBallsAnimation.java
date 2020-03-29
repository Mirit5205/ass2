import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;

/**
  *author hezi yaffe 208424242.
  */
public class MultipleBouncingBallsAnimation  {
    /**
     * @param balls is an array of balls.
     * the function using bubble sorting to arrange
     * the balls in ascending Order.
     */
    public static void ascendingOrder(Ball[] balls) {
        for (int j = 0; j < balls.length; j++) {
            for (int i = 0; i < (balls.length - 1); i++) {
                if (balls[i].getRadius() >= balls[i + 1].getRadius()) {
                    Ball tmp = balls[i];
                    balls[i] = balls[i + 1];
                    balls[i + 1] = tmp;
                }
            }
        }
}
    /**
     * @param ballsArray is an array of balls.
     * @param width is the GUI width.
     * @param height is the GUI height.
     * @param radiusArray is an array of balls radiuses.
     * the function create array of balls in random locations
     * on the gui.
     */
    public static void createBallsArray(Ball[] ballsArray, int width, int height, int[] radiusArray) {
    for (int i = 0; i < ballsArray.length; i++) {
        int r = radiusArray[i];
        Random rand = new Random();
        double x = rand.nextInt(width);
        double y = rand.nextInt(height);
        if (x < r) {
            x = r;
        }
        if (x + r > width) {
        x = width - r;
        }
        if (y < r) {
            y = r;
        }
        if (y + r > height) {
            y = height - r;
        }
        ballsArray[i] = new Ball(x, y, r , java.awt.Color.BLACK);
    }
    }
    /**
     * @param ballsArray is an array of balls.
     * the function set random velocity to every ball,
     * such that larger balls are slower then smalls.
     */
    public static void setVelocityToBallsArray(Ball[] ballsArray) {
    Random rand = new Random();
    int dx = rand.nextInt(10) + 1;
    int dy = rand.nextInt(10) + 1;
    for (int i = ballsArray.length - 1; i >= 0; i--) {
    if (ballsArray[i].getRadius() >= 50) {
        ballsArray[i].setVelocity(1, 1);
        }
        ballsArray[i].setVelocity(dx, dy);
        dx++;
        dy++;
    }
    }
    /**
     * @param ballsArray is an array of balls.
     * @param gui is the GUI.
     * the function draw the balls on the GUI surface.
     */
    public static void showBallsArrayAnimation(Ball[] ballsArray, GUI gui) {
    biuoop.Sleeper sleeper = new biuoop.Sleeper();
    while (true) {
    DrawSurface d = gui.getDrawSurface();
    for (int i = 0; i < ballsArray.length; i++) {
        ballsArray[i].moveOneStep();
        ballsArray[i].drawOn(d);
    }
        sleeper.sleepFor(50);  // wait for 50 milliseconds.
        gui.show(d);
    }
    }
    /**
     * @param radiusArray is an array of balls radiuses.
     * @param args is an array of arguments we get from the cmd.
     * the function convert those arguments from strings to ints.
     */
    public static void convertArgumentsToInts(int[] radiusArray, String[] args) {
    for (int i = 0; i < radiusArray.length; i++) {
    radiusArray[i] = Integer.parseInt(args[i]);
    }
    }
    /**
     * @param ballsArray is an array of balls.
     * @param startX is the least value of x on the GUI.
     * @param startY is the least value of y on the GUI.
     * @param width is the width value of the GUI.
     * @param heigth is the heigth value of the GUI.
    */
    public static void setBallsDomains(Ball[] ballsArray, double startX,
    double startY, double width, double heigth) {
    for (int i = 0; i < ballsArray.length; i++) {
    ballsArray[i].setXLowerDomain(startX);
    ballsArray[i].setYLowerDomain(startY);
    ballsArray[i].setXUpperDomain(width);
    ballsArray[i].setYUpperDomain(heigth);
    }
    }
    /**
     * @param args is an array of arguments we get from the cmd.
     */
    public static void main(String[] args) {
    Ball[] ballsArray = new Ball[args.length];
    int[] radiusArray = new int[ballsArray.length];
    int width = 200;
    int heigth = 200;
    int startX = 0;
    int startY = 0;
    GUI gui = new GUI("title", width, heigth);
    //MultipleBouncingBallsAnimation example = new MultipleBouncingBallsAnimation();
    convertArgumentsToInts(radiusArray, args);
    createBallsArray(ballsArray, width, heigth, radiusArray);
    setBallsDomains(ballsArray, startX, startY, width, heigth);
    ascendingOrder(ballsArray);
    setVelocityToBallsArray(ballsArray);
    showBallsArrayAnimation(ballsArray, gui);
    }
}
