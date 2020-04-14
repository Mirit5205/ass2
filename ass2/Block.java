import biuoop.DrawSurface;

import java.awt.*;

public class Block implements Collidable {
    private Rectangle rectangle;
    private int hitCounter;

    public Block(Rectangle r) {
        this.rectangle = r;
    }

    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle() {
        return this.rectangle;

    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity southEastHit(CollisionInfo c, Velocity currentVelocity, Point newCenter) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point collisionPoint = c.getCollisionPoint();
        Collidable collidable = c.getCollisionObject();
        Rectangle r = collidable.getCollisionRectangle();
        Velocity newVelocity;
        if (newCenter.getX() >= collisionPoint.getX() || newCenter.getX() <=
                r.getUpperLeft().getX() + r.getWidth()) {
            dx = -dx;
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }

    public Velocity southWestHit(CollisionInfo c, Velocity currentVelocity, Point newCenter) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point collisionPoint = c.getCollisionPoint();
        Collidable collidable = c.getCollisionObject();
        Rectangle r = collidable.getCollisionRectangle();
        Velocity newVelocity;
        if (newCenter.getX() >= collisionPoint.getX() || newCenter.getX() <=
                r.getUpperLeft().getX() + r.getWidth()) {
            dx = -dx;
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }

    public Velocity northEastHit(CollisionInfo c, Velocity currentVelocity, Point newCenter) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point collisionPoint = c.getCollisionPoint();
        Collidable collidable = c.getCollisionObject();
        Rectangle r = collidable.getCollisionRectangle();
        Velocity newVelocity;
        if (newCenter.getY() >= collisionPoint.getY() || newCenter.getY() <=
                r.getUpperLeft().getY() + r.getHeight()) {
            dy = -dy;
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }

    public Velocity northWestHit(CollisionInfo c, Velocity currentVelocity, Point newCenter) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point collisionPoint = c.getCollisionPoint();
        Collidable collidable = c.getCollisionObject();
        Rectangle r = collidable.getCollisionRectangle();
        Velocity newVelocity;
        if (newCenter.getY() >= collisionPoint.getY() || newCenter.getY() <=
                r.getUpperLeft().getY() + r.getHeight()) {
            dy = -dy;
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }

    public Velocity hit(CollisionInfo c, Velocity currentVelocity, Point newCenter) {
        this.hitCounter++;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point collisionPoint = c.getCollisionPoint();
        Collidable collidable = c.getCollisionObject();
        Rectangle r = collidable.getCollisionRectangle();
        Velocity newVelocity;

        if (newCenter.getX() >= 580 || newCenter.getX() <= 20) {
            dx = -dx;
        }
        if (newCenter.getY() >= 580 || newCenter.getY() <= 20) {
            dy = -dy;
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;

    }
        /*in order the ball remain in the Gui
                if (newCenter.getX() >= collisionPoint.getX() || newCenter.getX() <=
                        r.getUpperLeft().getX() + r.getWidth()) {
                    dx = -dx;
                }
                if (newCenter.getY()  >= collisionPoint.getY() || newCenter.getY()  <=
                        r.getUpperLeft().getY() + r.getHeight()) {
                    dy = -dy;
                }

*/
        //the second one
        /*
            if (newCenter.getX()  >= collisionPoint.getX() || newCenter.getX()  <=
                    r.getUpperLeft().getX() + r.getWidth()) {
                dx = -dx;
                newVelocity = new Velocity(dx, dy);
                return newVelocity;
            }
            if (newCenter.getY()  >= collisionPoint.getY() || newCenter.getY()  <=
                    r.getUpperLeft().getY() + r.getHeight()) {
                dy = -dy;
                newVelocity = new Velocity(dx, dy);
                return newVelocity;
        }
         */
            //the first one
        /*
        if (currentVelocity.getDx() >= 0) {
            if (newCenter.getX()  >= collisionPoint.getX()) {
                dx = -dx;
            }
        } else {
            if (newCenter.getX()  <= r.getUpperLeft().getX() + r.getWidth()) {
                dx = -dx;
            }
        }
         if (currentVelocity.getDy() >= 0) {
            if (newCenter.getY()  >= collisionPoint.getY()) {
                dy = -dy;
            }
        } else {
            if (newCenter.getY()  <= r.getUpperLeft().getY() + r.getHeight()) {
                dy = -dy;
            }
        }
         */
        //newVelocity = new Velocity(dx, dy);

        //return newVelocity;
    //}

    public void drawOn(DrawSurface surface) {
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        double height = this.getCollisionRectangle().getHeight();
        double width = this.getCollisionRectangle().getWidth();
        surface.fillRectangle((int)upperLeft.getX(), (int)upperLeft.getY(),
                (int)width, (int)height);
        surface.setColor(Color.BLACK);
    }
}
