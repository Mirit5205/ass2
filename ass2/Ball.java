import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * author hezi yaffe 208424242.
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
    public void addToGame(Game g) {
        this.setGameEnvironment(g.getEvniorment());
        if (g.getSpirtes().getSprites() == null) {
            g.getSpirtes().initializeSpiritList();
            g.getSpirtes().getSprites().add(this);
        } else {
            g.getSpirtes().getSprites().add(this);
        }
    }

    /**
     * update game enviorment filed.
     * @param g is the game enviorment.
     */
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
     * @return ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * draw the ball on the given DrawSurface.
     *@param surface is our DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();
        int radius = this.getRadius();
        surface.setColor(this.getColor());
        surface.fillCircle(x, y, radius);
    }

    //setters

    /**
     * update the center filed.
     *@param center1 is the center point of the ball.
     */
    public void setCenter(Point center1) {
        this.center = center1;
    }

    /**
     * update the  x value of the center point.
     *@param x is the x value of the ball center point.
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * update the  y value of the center point.
     *@param y is the y value of the ball center point.
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * update the ball color.
     *@param color1 is the color of the ball.
     */
    public void setColor(java.awt.Color color1) {
        this.color = color1;
    }

    /**
     * set the ball radius.
     *@param radius is the ball radius.
     */
    public void setRadius(int radius) {
        this.r = radius;
    }

    /**
     * update the ball velocity.
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
     * move the ball on the surface.
     * check if balls next step is in the domain,
     * if not change direction of the.
     */
    public void moveOneStep() {
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(this.getTrajectoryLine());
        Line trajectory = this.getTrajectoryLine();
        Point noCollideNewCenter = this.getVelocity().applyToPoint(this.getCenter());
        Point collideNewCenter;
        Velocity newVelocity;
        Point tmpCenter = new Point(this.getCenter().getX(), this.getCenter().getY());

        //check if the ball collide, and change is center point accordingly
        if (isCollide(trajectory, noCollideNewCenter, collisionInfo.getCollisionPoint())) {
            //ball is'nt stuck inside block, but i get null after a while
            newVelocity = collisionInfo.getCollisionObject().
                    hit(collisionInfo, this.getVelocity(), this);
            collideNewCenter = newVelocity.applyToPoint(tmpCenter);
            //if the new center is inside a block
            if (isBallInBlock(collideNewCenter)) {
                this.setVelocity(makeSureBallNotStuckInCollidable());
            } else {
                // the hit method in collidable interface
                this.setVelocity(newVelocity);
            }
            Point newCenter = this.getVelocity().applyToPoint(collisionInfo.getCollisionPoint());
            remainBallInsideTheGui(newCenter);
            this.setCenter(newCenter);
        } else {
            remainBallInsideTheGui(noCollideNewCenter);
            this.setCenter(noCollideNewCenter);
        }
    }

    /**
     * make sure that the ball stay in the GUI.
     * @param ballCenter is ball's current location
     */
    public void remainBallInsideTheGui(Point ballCenter) {
        if (ballCenter.getX() >= Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE) {
            ballCenter.setX(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE);
        }
        if (ballCenter.getX() <= Game.GUI_UPPER_LEFT_X + Game.GUI_BLOCK_EDGE_SIZE) {
            ballCenter.setX(Game.GUI_UPPER_LEFT_X + Game.GUI_BLOCK_EDGE_SIZE);
        }
        if (ballCenter.getY() <= Game.GUI_UPPER_LEFT_Y + Game.GUI_BLOCK_EDGE_SIZE) {
            ballCenter.setY(Game.GUI_UPPER_LEFT_Y + Game.GUI_BLOCK_EDGE_SIZE);
        }
        if (ballCenter.getY() >= Game.GUI_HEIGHT - Game.GUI_BLOCK_EDGE_SIZE) {
            ballCenter.setY(Game.GUI_HEIGHT - Game.GUI_BLOCK_EDGE_SIZE);
        }
    }

    /**
     * make sure that the ball dont stuck inside block.
     * @return new velocity of the ball.
     */
    public Velocity makeSureBallNotStuckInCollidable() {
        Point tmpBallCenter = new Point(this.center.getX(), this.center.getY());
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();
        // three options of directions the ball can follow
        Velocity v1 = new Velocity(dx, -dy);
        Velocity v2 = new Velocity(-dx, dy);
        Velocity v3 = new Velocity(-dx, -dy);
        // ball center location with every one of the directions
        Point ballCenterWithV1 = new Point(tmpBallCenter.getX() + dx, tmpBallCenter.getY() - dy);
        Point ballCenterWithV2 = new Point(tmpBallCenter.getX() - dx, tmpBallCenter.getY() + dy);
        Point ballCenterWithV3 = new Point(tmpBallCenter.getX() - dx, tmpBallCenter.getY() - dy);

        //check in with state the ball dont stuck in block
        if (!isBallInBlock(ballCenterWithV1)) {
            return v1;
        } else if (!isBallInBlock(ballCenterWithV2)) {
            return v2;
        } else {
            return v3;
        }

    }

    /**
     * if the distance between the new center and the trajectory start point is grater than
     * the distance between the collision point and the trajectory start point.
     * @param trajectory is ball trajectory.
     * @param newCenter is the ball center.
     * @param collisionPoint is the point where the trajectory line and the
     * collidable collide.
     * @return true if the ball and the block collide.
     */
    public boolean isCollide(Line trajectory, Point newCenter, Point collisionPoint) {
        return trajectory.start().distance(collisionPoint)
                - trajectory.start().distance(newCenter) < Rectangle.EPSILON;
    }

    /**
     *we get the ball trajectory line by apply ball velocity to ball center point
     *multiple times.
     *@return  the trajeectory line.
     */
    public Line getTrajectoryLine() {
        //the initial ball center is the start of the trajectory line
        Point oldCenter = this.getCenter();
        Point tmpCenter = new Point(this.center.getX(), this.center.getY());
        for (int i = 0; i <= 1000; i++) {
            tmpCenter = this.getVelocity().applyToPoint(tmpCenter);
        }
        Line trajectory = new Line(oldCenter, tmpCenter);
        return trajectory;
    }

    /**
     *@return  the game enviroment field.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * notify the ball that time round is done and
     * activate the method moveOneStep again.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * check if ball is inside one of the objects in the collidable list.
     * @param ballCenter is the ball current center.
     * @return true if the ball center is in a collidable.
     */
    public boolean isBallInBlock(Point ballCenter) {
        List<Collidable> collidableList = gameEnvironment.getCollidablesList();
        for (Collidable c : collidableList) {
           Point p = c.getCollisionRectangle().getUpperLeft();
           double width = c.getCollisionRectangle().getWidth();
           double height = c.getCollisionRectangle().getHeight();
            if (isBallCenterInsideBlock(p, ballCenter, width, height)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checking if the ball center is inside the collidable
     * by calculate collidable zone and check if the ball center is
     * in this zone.
     * @param p is the collidable upper left point.
     * @param ballCenter is the ball center.
     * @param width is the collidable width.
     * @param height is the collidable height.
     * @return true if the ball is inside the collidable zone.
     */
    public boolean isBallCenterInsideBlock(Point p, Point ballCenter, double width, double height) {
        /*
        * calculate the ball center "Forbidden zone" by adding collidable width to
        * his upper left point x value and do the same with y point value and
        * the his height.
        * if ball center is inside the Forbidden zone return true.
        */
       return ((ballCenter.getX() >= p.getX())
               && (ballCenter.getX() <= p.getX() + width))
               && (ballCenter.getY() >= p.getY() && ballCenter.getY()
               <= p.getY() + height);
    }

    public void removeFromGameEnviorment(Block b) {
        List<Collidable> originalCollidables = this.getGameEnvironment().getCollidablesList();
        List<Collidable> duplicateOfOriginalCollidables = new ArrayList<Collidable>(originalCollidables);
        duplicateOfOriginalCollidables.remove(b);
        GameEnvironment newEnviorment = new GameEnvironment();
        newEnviorment.setCollidableList(duplicateOfOriginalCollidables);
        this.setGameEnvironment(newEnviorment);
    }

    public void removeFromGame(Game g, Block beingHit) {
        g.removeSprite(this);
        this.removeFromGameEnviorment(beingHit);
    }
}