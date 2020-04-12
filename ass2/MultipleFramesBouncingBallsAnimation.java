import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

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
     static void createTwoSubBallsArrays(Ball[] originalBallsArray,
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
     static void checkIfBallIsInDomain(Ball[] balls, double startX,
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

     static void prepareYellowRectangle (Ball[] yellowRectangleBalls, double yellowRectangleStartX
            , double yellowRectangleStartY, int yellowRectangleWidth, int yellowRectangleHeight) {
        //check if every ball is inside the proper rectangle.
        checkIfBallIsInDomain(yellowRectangleBalls, yellowRectangleStartX
                , yellowRectangleStartY, yellowRectangleWidth, yellowRectangleHeight);
        //set every ball the proper x,y domains.
         MultipleBouncingBallsAnimation.setBallsDomains(yellowRectangleBalls, yellowRectangleStartX,
                yellowRectangleStartY, yellowRectangleWidth + yellowRectangleStartX
                , yellowRectangleHeight + yellowRectangleStartY);
        //arrange every sub balls array in ascending Order.
         MultipleBouncingBallsAnimation.ascendingOrder(yellowRectangleBalls);
        //set velocity to every ball.
         MultipleBouncingBallsAnimation.setVelocityToBallsArray(yellowRectangleBalls);
        
    }
     static void prepareGrayRectangle (Ball[] grayRectangleBalls, double grayRectangleStartX
            , double grayRectangleStartY, int grayRectangleWidth, int grayRectangleHeight) {
        //check if every ball is inside the proper rectangle.
        checkIfBallIsInDomain(grayRectangleBalls, grayRectangleStartX
                , grayRectangleStartY, grayRectangleWidth, grayRectangleHeight);
        //set every ball the proper x,y domains.
         MultipleBouncingBallsAnimation.setBallsDomains(grayRectangleBalls, grayRectangleStartX,
                grayRectangleStartY, grayRectangleWidth + grayRectangleStartX
                , grayRectangleHeight + grayRectangleStartY);
        //arrange every sub balls array in ascending Order.
         MultipleBouncingBallsAnimation.ascendingOrder(grayRectangleBalls);
        //set velocity to every ball.
         MultipleBouncingBallsAnimation.setVelocityToBallsArray(grayRectangleBalls);

    }
    static private void drawAnimation(int[] radiusArray, int grayRectangleStartX, int grayRectangleStartY,
                                      int grayRectangleWidth, int grayRectangleHeight, int yellowRectangleStartX,
                                     int yellowRectangleStartY, int yellowRectangleWidth, int yellowRectangleHeight) {
        int guiWidth = 600;
        int guiHeight = 600;
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", guiWidth, guiHeight);
        Ball[] ballsArray = new Ball[radiusArray.length ];
        //create the proper balls array.
        MultipleBouncingBallsAnimation.createBallsArray(ballsArray, guiWidth, guiHeight, radiusArray);
        Ball[] grayRectangleBalls = new Ball[ballsArray.length / 2];
        Ball[] yellowRectangleBalls = new Ball[ballsArray.length - grayRectangleBalls.length];
        //create two sub balls arrays, one for the yellow Rectangle, one for the gray.
        createTwoSubBallsArrays(ballsArray, grayRectangleBalls,
                yellowRectangleBalls, ballsArray.length);
        prepareYellowRectangle(yellowRectangleBalls, yellowRectangleStartX
                , yellowRectangleStartY, yellowRectangleWidth, yellowRectangleHeight);
        prepareGrayRectangle (grayRectangleBalls, grayRectangleStartX
                , grayRectangleStartY, grayRectangleWidth, grayRectangleHeight);
        Sleeper sleeper = new Sleeper();
        //show the balls animation.
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle( grayRectangleStartX, grayRectangleStartY,
                    grayRectangleWidth, grayRectangleHeight);
            d.setColor(Color.YELLOW);
            d.fillRectangle((int) yellowRectangleStartX, (int) yellowRectangleStartY,
                    yellowRectangleWidth, yellowRectangleHeight);
            for (int i = 0; i <  grayRectangleBalls.length; i++) {
                d.setColor(Color.BLUE);
                grayRectangleBalls[i].moveOneStep();
                grayRectangleBalls[i].drawOn(d);
            }
            for (int i = 0; i <  yellowRectangleBalls.length; i++) {
                d.setColor(Color.WHITE);
                yellowRectangleBalls[i].moveOneStep();
                yellowRectangleBalls[i].drawOn(d);
            }
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            gui.show(d);
        }
    }
    /**
     *@param args are the args we get from the cmd.
     */
    public static void main(String[] args) {
        int[] radiusArray = new int[args.length];
        //add cmd arguments to the radiusArray.
        MultipleBouncingBallsAnimation.convertArgumentsToInts(radiusArray, args);
        int grayRectangleWidth = 450;
        int grayRectangleHeight = 450;
        int yellowRectangleWidth = 150;
        int yellowRectangleHeight = 150;
        int grayRectangleStartX = 50;
        int grayRectangleStartY = 50;
        int yellowRectangleStartX = 450;
        int yellowRectangleStartY = 450;
        drawAnimation(radiusArray ,grayRectangleStartX, grayRectangleStartY, grayRectangleWidth,
                grayRectangleHeight, yellowRectangleStartX, yellowRectangleStartY,
                yellowRectangleWidth, yellowRectangleHeight );
    }
}
