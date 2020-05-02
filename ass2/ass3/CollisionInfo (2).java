public class CollisionInfo {
    private Point p;
    private Collidable c;

    //getters
    // return the point at which the collision occurs.
    public Point getCollisionPoint() {
        return this.p;
    }
    //return the collidable object involved in the collision.
    public Collidable getCollisionObject() {
        return this.c;
    }

    //setters
    //set the point at which the collision occurs.
    public void setCollisionPoint(Point collisionPoint) {
        this.p = collisionPoint;
    }

    // set the collidable object involved in the collision.
    public void setCollisionObject(Collidable c) {
        this.c = c;
    }
}
