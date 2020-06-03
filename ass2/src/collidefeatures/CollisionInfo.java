package collidefeatures;

import geometryprimitives.Point;
import interfaces.Collidable;

/**
 * author hezi yaffe 208424242.
 */
public class CollisionInfo {
    private Point p;
    private Collidable c;

    //getters
    /**
     * @return return the point at which the collision occurs.
     */
    public Point getCollisionPoint() {
        return this.p;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable getCollisionObject() {
        return this.c;
    }

    //setters
    /**
     * set the point at which the collision occurs.
     * @param collisionPoint is the collision point.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.p = collisionPoint;
    }

    /**
     * set the collidable object involved in the collision.
     * @param collidable is the collidable object.
     */
    public void setCollisionObject(Collidable collidable) {
        this.c = collidable;
    }
}
