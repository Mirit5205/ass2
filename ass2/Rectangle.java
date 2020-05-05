import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * author hezi yaffe 208424242.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;
    private List<Line> edges;
    public static final double EPSILON = 0.000000005;


    // Create a new rectangle with location and width/height.

    /**
     * rectangle constructor.
     * @param upperLeftP is the upper left point of the rectangle.
     * @param w the width of the rectangle.
     * @param h the height of the rectangle.
     */
    public Rectangle(Point upperLeftP, double w, double h) {
        this.upperLeft = upperLeftP;
        this.width = w;
        this.height = h;

    }
    /**
     * set rectangle color.
     * @param c the color we want to set.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * geter of rectangle color filed.
     * @return rectangle color.
     */
    public Color getColor() {
       return this.color;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line given line.
     * @return intersection points with the specified line and
     * the given rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsWithLine = new ArrayList<Point>();
        List<Line> rectangleEdges = this.rectangleEdgesAsLinesList();
        for (Line i : rectangleEdges) {
            if (line.isIntersecting(i)) {
                Point intersectionPoint = line.intersectionWith(i);
                if (line.isPointInSection(intersectionPoint) && i.isPointInSection(intersectionPoint)) {
                            intersectionPointsWithLine.add(intersectionPoint);
                        }
                    }
                }
        removeDuplicatesFromList(intersectionPointsWithLine);
        return intersectionPointsWithLine;
    }

    /**
     * drawing rectangle on the GUI.
     * @param surface is the draw surface.
     */
    public void drawOn(DrawSurface surface) {
        Point p = this.getUpperLeft();
        double h = this.getHeight();
        double w = this.getWidth();
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) p.getX(), (int) p.getY(),
                (int) w, (int) h);
        surface.setColor(this.color);
        surface.fillRectangle((int) p.getX(), (int) p.getY(),
                (int) w, (int) h);
    }

    /**
     * check if there is two equals intersection points using
     * two for loops, if there is any - remove one of them.
     * @param list is list on intersections points.
     */
    public void removeDuplicatesFromList(java.util.List<Point> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(i);
                }
            }
        }
    }

    /**
     * represent rectangle edges as 4 single lines.
     * @return list of the 4 edges of the rectangle.
     */
    public java.util.List<Line> rectangleEdgesAsLinesList() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line upperEdge = new Line(this.upperLeft, upperRight);
        Line bottomEdge = new Line(bottomRight, bottomLeft);
        Line leftEdge = new Line(this.upperLeft, bottomLeft);
        Line rightEdge = new Line(upperRight, bottomRight);
        List<Line> rectangleEdges = new ArrayList<Line>();
        rectangleEdges.add(upperEdge);
        rectangleEdges.add(bottomEdge);
        rectangleEdges.add(leftEdge);
        rectangleEdges.add(rightEdge);
        return rectangleEdges;
    }

    /**
     * checking if given point is on one of the rectangle edges.
     * @param p is the given point.
     * @param rectangleEdges list of rectangle edges as 4 lines.
     * @return true if given point is on one of the rectangle edges.
     */
    public boolean isPointOnRectangleEdges(Point p, List<Line> rectangleEdges) {
        for (Line l : rectangleEdges) {
            if (l.isPointInSection(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set rectangle edges as line list and update
     * the rectangle edges filed.
     */
    public void setRectangleEdgesAsLinesList() {
        this.edges = rectangleEdgesAsLinesList();
    }

    /**
     * checing if given point is on one of the two rectangle's vertical edges.
     * @param p is given point.
     * @return true if it does.
     */
    public boolean isPointOnVerticalEdge(Point p) {
        Double xValueBitBigger = p.getX() + EPSILON; // to make the point righter
        Point pointBitRighter = new Point(xValueBitBigger, p.getY());
        Double xValueBitSmaller = p.getX() - EPSILON; // to make the point righter
        Point pointBitlefter = new Point(xValueBitSmaller, p.getY());
        return ((isPointOnRectangleEdges(pointBitRighter, this.edges))
                || (isPointOnRectangleEdges(pointBitlefter, this.edges)));
    }
    /**
     * checing if given point is on one of the two rectangle's horizontal edges.
     * @param p is given point.
     * @return true if it does.
     */
    public boolean isPointOnHorizontalEdge(Point p) {
        Double yValueBitBigger = p.getX() + EPSILON; //to make the point lower
        Point pointBitLower = new Point(yValueBitBigger, p.getY());
        Double yValueBitSmaller = p.getX() - EPSILON; //to make the point lower
        Point pointBitUpper = new Point(yValueBitSmaller, p.getY());
        return ((isPointOnRectangleEdges(pointBitLower, this.edges))
                || (isPointOnRectangleEdges(pointBitUpper, this.edges)));
    }

    // Return the width and height of the rectangle

    /**
     * geter of the rectangle width filed.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;

    }

    /**
     * geter of the rectangle height filed.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * geter of the rectangle upper left point filed.
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * update the rectangle upper left point.
     * @param p is given point.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}

