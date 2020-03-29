import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 *author hezi yaffe 208424242.
 */
public class MultipleFramesBouncingBallsAnimation  {
    /**
     *@param originalBallsArray is the array we goona seperate to
     * two sub arrays.
     *@param firstBallsArray is the first sub array.
     *@param secondBallsArray is the second sub array.
     *@param size is the size of the original Array.
     */
    public void createTwoSubBallesArrays(Ball[] originalBallsArray,
                                         Ball[] firstBallsArray, Ball[] secondBallsArray , int size) {
        for (int i = 0; i < (originalBallsArray.length / 2); i++) {
            firstBallsArray[i] = originalBallsArray [i];
            secondBallsArray[i] = originalBallsArray[size - 1];
            size--;
        }
    }
    /**
     *@param balls is the balls array.
     *@param startX is the least value of x
     *that the ball center can have.
     *@param startY is the least value of y
     *that the ball center can have.
     *@param width is the width of the rectangle.
     *@param heigth is the heigth of the rectangle.
     */
    public void checkIfBallIsInDomain(Ball[] balls, double startX,
                                      double startY, int width, int heigth) {
        for (int i = 0; i < balls.length; i++) {
            int r = balls[i].getRadius();
            if (balls[i].getX() - r < startX) {
                balls[i].setX(startX + r);
            }
            if (balls[i].getX() - r > startX + width) {
                balls[i].setX(startX + width - r);
            }
            if (balls[i].getY() - r < startY) {
                balls[i].setY(startY + r);
            }
            if (balls[i].getY() - r > startY + heigth) {
                balls[i].setY(startY + heigth - r);
            }
        }

    }
    /**
     *@param args are the args we get from the cmd.
     */
    public static void main(String[] args) {
        Ball[] ballsArray = new Ball[args.length ];
        int[] radiusArray = new int[ballsArray.length];
        Ball[] grayRectangleBalls = new Ball[args.length / 2];
        Ball[] yellowRectangleBalls = new Ball[args.length - grayRectangleBalls.length];
        MultipleFramesBouncingBallsAnimation example = new  MultipleFramesBouncingBallsAnimation();
        MultipleBouncingBallsAnimation example1 = new MultipleBouncingBallsAnimation();
        int guiWidth = 600;
        int guiHeigth = 600;
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", guiWidth, guiHeigth);
        int grayRectangleWidth = 450;
        int grayRectangleHeigh = 450;
        int yellowRectangleWidth = 150;
        int yellowRectangleHeigh = 150;
        double grayRectanglStartX = 50;
        double grayRectanglStartY = 50;
        double yellowRectangleStartX = 450;
        double yellowRectangleStartY = 450;
        //add cmd arguments to the radiusArray.
        example1.convertArgumentsToInts(radiusArray, args);
        //create the proper balls array.
        example1.createBallsArray(ballsArray, guiWidth, guiHeigth, radiusArray);
        //create two sub balls arrays, one for the yellow Rectangle,
        // and for the gray.
        example.createTwoSubBallesArrays(ballsArray, grayRectangleBalls,
                yellowRectangleBalls, ballsArray.length);
        //check if every ball is inside the proper rectange.
        example.checkIfBallIsInDomain(grayRectangleBalls, grayRectanglStartX,
                grayRectanglStartY, grayRectangleWidth, grayRectangleHeigh);
        example.checkIfBallIsInDomain(yellowRectangleBalls, yellowRectangleStartX
                , yellowRectangleStartY, yellowRectangleWidth, yellowRectangleHeigh);
        //set every ball the proper x,y domains.
        example1.setBallsDomains(grayRectangleBalls, grayRectanglStartX,
                grayRectanglStartY, grayRectangleWidth + grayRectanglStartX,
                grayRectangleHeigh + grayRectanglStartY);
        example1.setBallsDomains(yellowRectangleBalls, yellowRectangleStartX,
                yellowRectangleStartY, yellowRectangleWidth + yellowRectangleStartX
                , yellowRectangleHeigh + yellowRectangleStartY);
        //arrange every sub balls array in ascending Order.
        example1.ascendingOrder(grayRectangleBalls);
        example1.ascendingOrder(yellowRectangleBalls);
        //set velocity to every ball.
        example1.setVelocityToBallsArray(grayRectangleBalls);
        example1.setVelocityToBallsArray(yellowRectangleBalls);
        //show the balls animation.
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle((int) grayRectanglStartX, (int) grayRectanglStartY,
                    grayRectangleWidth, grayRectangleHeigh);
            d.setColor(Color.YELLOW);
            d.fillRectangle((int) yellowRectangleStartX, (int) yellowRectangleStartY,
                    yellowRectangleWidth, yellowRectangleHeigh);
            for (int i = 0; i <  grayRectangleBalls.length; i++) {
                d.setColor(Color.BLACK);
                grayRectangleBalls[i].moveOneStep();
                grayRectangleBalls[i].drawOn(d);
            }
            for (int i = 0; i <  yellowRectangleBalls.length; i++) {
                d.setColor(Color.BLACK);
                yellowRectangleBalls[i].moveOneStep();
                yellowRectangleBalls[i].drawOn(d);
            }
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            gui.show(d);
        }
    }
}
