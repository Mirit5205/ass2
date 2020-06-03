package interfaces;

import collidefeatures.CollisionInfo;
import collidefeatures.Velocity;
import geometryprimitives.Rectangle;
import sprites.Ball;

/**
 * author hezi yaffe 208424242.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param c is the collision info.
     * @param currentVelocity is the ball current velocity.
     * @param hitter is the ball that hit the block.
     * @return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
      Velocity hit(CollisionInfo c, Velocity currentVelocity, Ball hitter);
}
