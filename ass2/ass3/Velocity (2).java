/**
 *autor hezi yaffe 208424242.
 *Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     *@param dx is the change in position on the x axe.
     *@param dy is the change in position on the y axe.
     *its a constructor
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     *@param angle is the angel of the speed vector.
     *@param speed is the length of the speed vector.
     *@return the velocity calculate with angel and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double toRadians = Math.toRadians(angle);
        double dx = speed * Math.sin(toRadians);
        double dy = speed * -Math.cos(toRadians);
        return new Velocity(dx, dy);
    }

    /**
     *@return the change in position on the x axe.
     */
    // accessors
    public double getDx() {
        return this.dx;
    }
    /**
     *@return the change in position on the y axe.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     *@param p is the point we going to change.
     *@return the new point after the change in the position.
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }
}