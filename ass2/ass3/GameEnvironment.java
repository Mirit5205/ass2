import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInfo = new CollisionInfo();
        Point p = null;
        Collidable collidable = null;
        //initialize p and collidable;
        for (Collidable c : collidables) {
            List<Point> interSectionPoints = c.getCollisionRectangle().intersectionPoints(trajectory);
            if (interSectionPoints.size() != 0) {
                p = trajectory.closestIntersectionToStartOfLine(interSectionPoints);
                collidable = c;
                break;
            }
        }
        //there is no collide
        if ( collidable == null) {
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
                        p = trajectory.closestIntersectionToStartOfLine(interSectionPoints);
                        collidable = c;
                    }
                    }
                }
            }
        collisionInfo.setCollisionPoint(p);
        collisionInfo.setCollisionObject(collidable);
        return collisionInfo;
    }

    public boolean isPointIsCloser (Line trajectory, Collidable c, Point p, List<Point> intersectionPoints) {
        return trajectory.start().distance(trajectory.
                closestIntersectionToStartOfLine(intersectionPoints)) <=
                trajectory.start().distance(p);
    }

    public boolean isCollide(Line trajectory, Collidable c) {
        return c.getCollisionRectangle().intersectionPoints(trajectory) != null;
    }

    public List<Collidable> getCollidablesList() {
        return this.collidables;
    }

}
