import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 *author hezi yaffe 208424242.
 */
public class AbstractArtDrawing {
    /**
     * @return random line on the GUI.
     */
    public Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        double startX = rand.nextInt(300); // get integer in range 0-400
        double startY = rand.nextInt(300); // get integer in range 0-400
        double endX = rand.nextInt(300); // get integer in range 0-400
        double endY = rand.nextInt(300); // get integer in range 0-400
        Line line1 = new Line(startX, startY, endX, endY);
        return line1;
    }
    /**
     * @param l is the line we want to draw.
     * @param d is the surface we gonna draw on.
     */
    void drawLine(Line l, DrawSurface d) {
        double startX = l.start().getX();
        double startY = l.start().getY();
        double endX = l.end().getX();
        double endY = l.end().getY();
        d.drawLine((int) startX, (int) startY, (int) endX, (int) endY);
    }
  /*
   this function find the instrctions points of the lines,
   and also find every line middle point.
  */
    /**
     * the function draw a random circle on the GUI.
     */
    public void drawMiddleAndInstractionsPoints() {
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] linesArray = new Line[10];
        for (int i = 0; i < 10; ++i) {
            d.setColor(Color.BLACK);
            linesArray[i] =  generateRandomLine();
            drawLine(linesArray[i], d);
            Point middle = linesArray[i].middle();
            double middlePointX = middle.getX();
            double middlePointY = middle.getY();
            d.setColor(Color.BLUE);
            d.fillCircle((int) middlePointX, (int) middlePointY, 3);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) {
                    continue;
                } else {
                    d.setColor(Color.RED);
                    if (linesArray[i].intersectionWith(linesArray[j]) == null) {
                        continue;
                    } else {
                        Point intersectionPoint = linesArray[i].intersectionWith(linesArray[j]);
                        //just added
                        if (linesArray[i].isPointInSection(intersectionPoint) &&
                                linesArray[j].isPointInSection(intersectionPoint)) {
                            d.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), 3);
                        }
                    }
                }
            }
        }
        gui.show(d);
    }
    /**
     * @param args is an array of arguments from the cmd.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawMiddleAndInstractionsPoints();
    }
}

