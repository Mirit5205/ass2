package gameelements;

import collidefeatures.CollisionInfo;
import geometryprimitives.Line;
import geometryprimitives.Point;
import interfaces.Collidable;
import java.util.ArrayList;
import java.util.List;

/**
 * author hezi yaffe 208424242.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * @param c is the collidable we want to add.
     * add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     *
     * @param trajectory is the line that made when we
     * Assume an object moving from line.start() to line.end().
     * @return null If this object will not collide with any of the collidables
     * in this collection. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInfo = new CollisionInfo();
        Point p = null;
        Collidable collidable = null;
        //initialize p and collidable;
        for (Collidable c : collidables) {
            List<Point> interSectionPoints = c.getCollisionRectangle().intersectionPoints(trajectory);
            if (interSectionPoints.size() != 0) {
                p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                collidable = c;
                break;
            }
        }
        //there is no collide
        if (collidable == null) {
            System.out.println("collidable is null");
            return null;
        }

        // get the closest collidable object and the closest intersection point.
            for (Collidable c : collidables) {
                //if there is collide
                if (isCollide(trajectory, c)) {
                    List<Point> interSectionPoints = c.getCollisionRectangle().intersectionPoints(trajectory);
                    if (interSectionPoints.size() != 0) {
                    //if the new point is closer to the start of line
                    if (isPointIsCloser(trajectory, c, p, interSectionPoints)) {
                        p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                        collidable = c;
                    }
                    }
                }
            }
        collisionInfo.setCollisionPoint(p);
        collisionInfo.setCollisionObject(collidable);
        return collisionInfo;
    }

    /**
     * @param trajectory is the ball trajectory line.
     * @param c is the Collidable.
     * @param p is the ball center location if there is no collision.
     * @param intersectionPoints is list of intersection points between
     * ball trajectory line and the collision rectangle.
     * @return true if the distance between the ball start point
     * and the collision point is smaller than the distance between
     * ball start point and the ball center location if there is no collision.
     */
    public boolean isPointIsCloser(Line trajectory, Collidable c, Point p, List<Point> intersectionPoints) {
        return trajectory.start().distance(trajectory.
                closestIntersectionToStartOfLine(c.getCollisionRectangle()))
                <= trajectory.start().distance(p);
    }

    /**
     * @param trajectory is the ball trajectory line.
     * @param c is the colidable we want to check.
     * @return true if there is at least one intersection point
     * between the ball's line and the collision rectangle.
     */
    public boolean isCollide(Line trajectory, Collidable c) {
        return c.getCollisionRectangle().intersectionPoints(trajectory) != null;
    }

    /**
     * @return collidables filed.
     */
    public List<Collidable> getCollidablesList() {
        return this.collidables;
    }

    /**
     * set collidable list.
     * @param c is the list of the collidable we want to set
     * into the game enviorment.
     */
    public void setCollidableList(List<Collidable> c) {
        this.collidables = c;
    }
}
