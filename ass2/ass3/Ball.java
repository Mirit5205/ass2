import biuoop.DrawSurface;

import java.awt.*;

/**
 *autor hezi yaffe 208424242.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private  java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
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
     *@param g is the current game.
     *this method add the ball to the game by
     *adding it to the sprit list.
     */
    public void addToGame (Game g) {
        this.setGameEnvironment(g.getEvniorment());
        if (g.getSpirtes().getSpritesList() == null) {
            g.getSpirtes().initializeSpiritList();
            g.getSpirtes().getSpritesList().add(this);
        } else {
            g.getSpirtes().getSpritesList().add(this);
        }
    }
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
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
    /**
     *@param surface is our DrawSurface.
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();
        int radius = this.getRadius();
        surface.setColor(this.getColor());
        surface.fillCircle(x, y, radius);
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

    //check if the ball collide, and change is center point accordingly
    public void moveOneStep(CollisionInfo collisionInfo) {
        Line trajectory = this.getTrajectoryLine();
        Line trajectory2;
        Point noCollideNewCenter = this.getVelocity().applyToPoint(this.getCenter());
        Point collideNewCenter;
        Velocity newVelocity;
        Ball tmpBall;
        CollisionInfo collisionInfo2;

        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();

        //if there is a hit
        if (isCollide(trajectory, noCollideNewCenter, collisionInfo.getCollisionPoint())) {
            //ball is'nt stuck inside block, but i get null after a while
            /*
            newVelocity = collisionInfo.getCollisionObject().
                    hit(collisionInfo, this.getVelocity());
            collideNewCenter = newVelocity.applyToPoint(this.getCenter());

            tmpBall = new Ball(collideNewCenter, 10, Color.black);
            tmpBall.setVelocity(newVelocity);
            trajectory2 = tmpBall.getTrajectoryLine();
            tmpBall.setGameEnvironment(this.gameEnvironment);
            collisionInfo2 = tmpBall.getGameEnvironment().getClosestCollision(trajectory2);
            //if the new center is inside a block
            if (Block.isBallCenterInColliadable(collisionInfo2.getCollisionObject(), collideNewCenter)) {
                this.setVelocity(newVelocity);
                this.setCenter(collisionInfo.getCollisionPoint());
            }
            */
            // the hit method in collidable interface
                this.setVelocity(collisionInfo.getCollisionObject().
                        hit(collisionInfo, this.getVelocity()));
            this.setCenter(this.getVelocity().applyToPoint(collisionInfo.getCollisionPoint()));
        } else {
            this.setCenter (noCollideNewCenter);
        }
    }

    /*if the distance between the new center and the trajectory start point is gretter than
    the distance between the coliision point and the trajectory start point
     */
    public boolean isCollide (Line trajectory, Point newCenter, Point CollisionPoint) {
        return trajectory.start().distance(CollisionPoint) -
                trajectory.start().distance(newCenter) < 0.000005;
    }
    /**
     *we get the ball trajectory line by apply ball velocity to ball center point
     *multiple times.
     *@return  the trajeectory line.
     */
    public Line getTrajectoryLine() {
        //the initial ball center is the start of the trajectory line
        Point oldCenter = this.getCenter();
        for (int i = 0; i <= 1000; i++) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        Line trajectory = new Line(oldCenter,this.getCenter());
        this.setCenter(oldCenter);
        return trajectory;
    }
    /**
     *@return  the game enviroment field.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    public void timePassed(){
        this.moveOneStep(this.gameEnvironment.getClosestCollision(this.getTrajectoryLine()));
    }
}