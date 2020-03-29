/**
 *autor hezi yaffe 208424242.
 */
public class Line {
   private Point start;
   private Point end;
   /**
    *@param start is the starting point.
    *@param end is the ending point.
    */
   // constructors.
   public Line(Point start, Point end) {
   this.start = start;
   this.end = end;
   }
   /**
    *@param x1 is the x of the starting point.
    *@param y1 is the y of the starting point
    *@param x2 is the x of the ending point.
    *@param y2 is the y of the ending point
    */
   public Line(double x1, double y1, double x2, double y2) {
   this.start = new Point(x1, y1);
   this.end = new Point(x2, y2);
   }
   /**
    *@return length of the given line.
    */
   // Return the length of the line.
   public double length() {
   return (this.start).distance(this.end);
   }
   /**
    *@return the middle point of an given line.
    */
   // Returns the middle point of the line.
   public Point middle() {
   Point point1 = new Point((this.start.getX() + this.end.getX()) / 2,
   (this.start.getY() + this.end.getY()) / 2);
   return point1;
   }
   /**
    *@return the start point of the line.
    */
   public Point start() {
   return this.start;
   }
   /**
    *@return the end point of the line.
    */
   public Point end() {
   return this.end;
   }
   /**
    *@param other is another line.
    *@return true if the lines Intersected, otherwise false.
    */
   public boolean isIntersecting(Line other) {
       /* if (this.equals(other)) {
            return false;
        }
        */
        // Line AB represented as a1x + b1y = c1
        double a1 = this.end().getY() - this.start().getY(); //B.y - A.y;
        double b1 = this.start().getX() - this.end().getX(); //A.x - B.x;
        double c1 = a1 * (this.start().getX()) + b1 * (this.start().getY());
        // Line CD represented as a2x + b2y = c2
        double a2 = other.end().getY() - other.start().getY(); //D.y - C.y;
        double b2 = other.start().getX() - other.end().getX(); //C.x - D.x;
        double c2 = a1 * (other.start().getX()) + b1 * (other.start().getY());
        double determinant = a1 * b2 - a2 * b1;
        return determinant != 0;
    }
    /**
     *@param other is another line.
     *@return intersection Point of two given lines.
     */
    public Point intersectionWith(Line other) {
        // Line AB represented as a1x + b1y = c1
        //B.y - A.y;
        double a1 = this.end().getY() - this.start().getY();
        //A.x - B.x;
        double b1 = this.start().getX() - this.end().getX();
        double c1 = a1 * (this.start().getX()) + b1 * (this.start().getY());
        // Line CD represented as a2x + b2y = c2
        //D.y - C.y;
        double a2 = other.end().getY() - other.start().getY();
        //C.x - D.x;
        double b2 = other.start().getX() - other.end().getX();
        double c2 = a2 * (other.start().getX()) + b2 * (other.start().getY());
        double determinant = a1 * b2 - a2 * b1;
        double x = (b2 * c1 - b1 * c2) / determinant;
        double y = (a1 * c2 - a2 * c1) / determinant;
        return new Point(x, y);
    }
    /**
     *@param other is the intersection Point.
     *@return true if point in section, otherwise false.
     */
    public boolean isPointInSection(Point other) {
      /*if (this.start().distance(other) + other.distance(this.end()) - this.length() <= 0.00000000000005) {
          return true;
      } else {
          return false;
      }
      */
      return (this.start().distance(other) + other.distance(this.end()) - this.length() <= 0.00000000000005);
    }
    /**
     *@param other is another line.
     *@return true if the lines are equal, false otherwise.
     */
   public boolean equals(Line other) {
   return ((this.start.equals(other.start)) && (this.end.equals(other.end)));
   }
}