import biuoop.DrawSurface;
import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;
    private List<Line> rectangleEdges;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

    }
    //set color
    public void setColor (Color c) {
        this.color = c;
    }
    //get color
    public Color getColor () {
       return this.color;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsWithLine = new ArrayList<Point>();
        List<Line> rectangleEdges = this.RectangleEdgesAsLinesList();
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

    public void drawOn(DrawSurface surface) {
        Point upperLeft = this.getUpperLeft();
        double height = this.getHeight();
        double width = this.getWidth();
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int)upperLeft.getX(), (int)upperLeft.getY(),
                (int)width, (int)height);
        surface.setColor(this.color);
        surface.fillRectangle((int)upperLeft.getX(), (int)upperLeft.getY(),
                (int)width, (int)height);
    }
    /*
    check if there is two equals intersection points using
    two for loops, if there is any - remove one of them.
     */
    public void removeDuplicatesFromList (java.util.List<Point> list) {
        int counter = 0;
        if (list.size() <= 1) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if(list.get(i).equals(list.get(j))) {
                    list.remove(i);
                }
            }
        }
    }

    public java.util.List<Line> RectangleEdgesAsLinesList() {
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
    public boolean isPointOnRectangleEdges (Point p, List<Line> rectangleEdges ) {
        for (Line l : rectangleEdges) {
            if (l.isPointInSection(p)) {
                return true;
            }
        }
        return false;
    }

    //set rectangle edges as line list
    public void setRectangleEdgesAsLinesList() {
        this.rectangleEdges = RectangleEdgesAsLinesList();
    }

    public boolean isPointOnVerticalEdge (Point p) {
        Double xValueBitBigger = p.getX() + 0.00000000000001; // to make the point righter
        Point pointBitRighter = new Point (xValueBitBigger,p.getY());
        Double xValueBitSmaller = p.getX() - 0.00000000000001; // to make the point righter
        Point pointBitlefter = new Point (xValueBitSmaller,p.getY());
        return ((isPointOnRectangleEdges(pointBitRighter, this.rectangleEdges)) ||
                (isPointOnRectangleEdges(pointBitlefter, this.rectangleEdges)));
    }
    public boolean isPointOnHorizontalEdge (Point p) {
        Double yValueBitBigger = p.getX() + 0.00000000000001; //to make the point lower
        Point pointBitLower = new Point (yValueBitBigger,p.getY());
        Double yValueBitSmaller = p.getX() - 0.00000000000001; //to make the point lower
        Point pointBitUpper = new Point (yValueBitBigger,p.getY());
        return ((isPointOnRectangleEdges(pointBitLower, this.rectangleEdges)) ||
                (isPointOnRectangleEdges(pointBitUpper, this.rectangleEdges)));
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;

    }
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}

