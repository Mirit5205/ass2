/**
 *autor hezi yaffe 208424242.
 */
public class Point {
   //x and y value of the point.
   private double x;
   private double y;
   /**
    *@param x is the x value of the new point.
    *@param y is the y value of the new point.
    */
   // constructor
   public Point(double x, double y) {
   this.x = x;
   this.y = y;
   }
     @Override
  public String toString() {
    return "point x=" + this.getX() + " point y=" + this.getY();
  }
   /**
    *@param other is a point variable.
    *@return is the distance between those points.
    */
   // distance -- return the distance of this point to the other point
   public double distance(Point other) {
   double beforeRoot = (this.x - other.x) * (this.x - other.x)
   + (this.y - other.y) * (this.y - other.y);
   double pointsDistance = Math.sqrt(beforeRoot);
   return pointsDistance;
   }
   /**
    *@param other is a point variable.
    *@return is boolean value, if the points
    *are equal it returns true, otherwise it return
    *false.
    */
   // equals -- return true is the points are equal, false otherwise
   public boolean equals(Point other) {
   return ((this.x == other.x) && (this.y == other.y));
   }
   /**
    *@return the x value of the point.
    */
   public double getX() {
   return this.x;
   }
   /**
    *@return the y value of the point.
    */
   public double getY() {
   return this.y;
   }
   //setters
   /**
    *@param y1 is the y value of the point.
    */
   public void setY(double y1) {
   this.y = y1;
   }
   /**
    *@param x1 is the x value of the point.
    */
   public void setX(double x1) {
   this.x = x1;
   }
}
