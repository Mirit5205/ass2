import biuoop.DrawSurface;
/**
 *autor hezi yaffe 208424242.
 */
public class Ball {
   private Point center;
   private int r;
   private  java.awt.Color color;
   private Velocity velocity;
   //the limits of the x,y value of the center point.
   private double xLowerDomain;
   private double yLowerDomain;
   private double xUpperDomain;
   private double yUpperDomain;

   /**
    *@param center is the center point of the ball.
    *@param r is the radius.
    *@param color is the color.
    */
   // constructor
   public Ball(Point center, int r, java.awt.Color color) {
       this.center = center;
       this.r = r;
       this.color = color;
   }
   /**
    *@param x is the x value of the center point of the ball.
    *@param y is the y value of the center point of the ball.
    *@param r is the radius.
    *@param color is the color.
    */
   public Ball(double x, double y, int r, java.awt.Color color) {
       Point theCenter = new Point(x, y);
       this.center = theCenter;
       this.r = r;
       this.color = color;
   }

   /**
    *@return center point of the ball.
    */
   // accessors
   public Point getCenter() {
       return this.center;
   }
   /**
    *@return x value of the center point.
    */
   public int getX() {
       return (int) this.center.getX();
   }
   /**
    *@return y value of the center point.
    */
   public int getY() {
        return (int) this.center.getY();
   }
   /**
    *@return radius of the ball.
    */
   public int getRadius() {
        return this.r;
   }
   /**
    *@return area value of the ball.
    */
   public int getSize() {
       return (int) (this.getRadius() * this.getRadius() * Math.PI);
   }
   /**
    *@return color of the ball.
    */
   public java.awt.Color getColor() {
       return this.color;
   }
   /**
    *@return the least x value that the center can have. .
    */
   public double getXLowerDomain() {
        return this.xLowerDomain;
   }
   /**
    *@return the least y value that the center can have. .
    */
   public double getYLowerDomain() {
        return this.yLowerDomain;
   }
   /**
    *@return the most x value that the center can have. .
    */
   public double getXUpperDomain() {
        return this.xUpperDomain;
   }
   /**
    *@return the most y value that the center can have. .
    */
   public double getYUpperDomain() {
        return this.yUpperDomain;
   }

   /**
    *@param surface is our DrawSurface.
    */
   // draw the ball on the given DrawSurface
   public void drawOn(DrawSurface surface) {
       int x = this.getX();
       int y = this.getY();
       int radius = this.getRadius();
       surface.fillCircle(x, y, radius);
       surface.setColor(this.getColor());
   }
   /**
    *@param x is the most x value that the center can have..
    */
   // setters
   public void setXUpperDomain(double x) {
       this.xUpperDomain = x;
   }
   /**
    *@param y is the most y value that the center can have..
    */
   public void setYUpperDomain(double y) {
       this.yUpperDomain = y;
   }
   /**
    *@param x is the least x value that the center can have..
    */
   public void setXLowerDomain(double x) {
       this.xLowerDomain = x;
   }
      /**
    *@param y is the least x value that the center can have..
    */
   public void setYLowerDomain(double y) {
       this.yLowerDomain = y;
   }
   /**
    *@param center1 is the center point of the ball.
    */
   public void setCenter(Point center1) {
       this.center = center1;
   }
   /**
    *@param x is the x value of the ball center point.
    */
   public void setX(double x) {
       this.center.setX(x);
   }
    /**
    *@param y is the y value of the ball center point.
    */
   public void setY(double y) {
       this.center.setY(y);
   }
    /**
    *@param color1 is the color of the ball.
    */
   public void setColor(java.awt.Color color1) {
       this.color = color1;
   }
    /**
    *@param radius is the ball radius.
    */
   public void setRadius(int radius) {
       this.r = radius;
   }
    /**
    *@param v is the ball speed.
    */
   public void setVelocity(Velocity v) {
       this.velocity = v;
   }
    /**
    *@param dx the change in position on the `x` axe.
    *@param dy the change in position on the `y` axe.
    */
   public void setVelocity(double dx, double dy) {
       Velocity v = new Velocity(dx, dy);
       this.velocity = v;
   }
    /**
    *@return is the ball speed.
    */
   public Velocity getVelocity() {
       return this.velocity;
   }
   /*
    check if balls next step is in the domain,
    if not change direction of the.
   */
   /**
    *move the ball on the surface.
    */
   public void moveOneStep() {
       Point newCenter = this.getVelocity().applyToPoint(this.center);
       double dx = this.getVelocity().getDx();
       double dy = this.getVelocity().getDy();
       if (this.getVelocity().getDx() >= 0) {
           if (newCenter.getX() + this.getRadius() >= this.xUpperDomain) {
               dx = -dx;
           }
       } else {
           if (newCenter.getX() - this.getRadius() <= this.xLowerDomain) {
               dx = -dx;
           }
       }
       if (this.getVelocity().getDy() >= 0) {
           if (newCenter.getY() + this.getRadius() >= this.yUpperDomain) {
               dy = -dy;
           }
       } else {
           if (newCenter.getY() - this.getRadius() <= this.yLowerDomain) {
               dy = -dy;
           }
       }
       this.setVelocity(dx, dy);
       this.center = this.getVelocity().applyToPoint(this.center);
   }
}