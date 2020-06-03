package sprites;

import application.Game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidefeatures.CollisionInfo;
import collidefeatures.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;

/**
 * author hezi yaffe 208424242.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private Color color;
    public static final int PADDLE_STEP = 10;


    /**
     * paddle constructor.
     * @param b is the block that will use as a paddle.
     * @param c paddle color.
     */
    public Paddle(Block b, Color c) {
        this.block = b;
        this.color = c;
        this.getCollisionRectangle().setColor(c);
    }

    /**
     * moving the paddle left across the GUI.
     */
    public void moveLeft() {
        Point newUpperLeft = this.getCollisionRectangle().getUpperLeft();
        if (isPaddleCrossLeftEdge(newUpperLeft)) {
            return;
        }
        newUpperLeft.setX(newUpperLeft.getX() - PADDLE_STEP);
            this.block.setBlockLocation(newUpperLeft);
    }
    /**
     * moving the paddle right across the GUI.
     */
    public void moveRight() {
        Point newUpperLeft = this.getCollisionRectangle().getUpperLeft();
        if (isPaddleCrossRightEdge(newUpperLeft))  {
            return;
        }
        newUpperLeft.setX(newUpperLeft.getX() + PADDLE_STEP);
        this.block.setBlockLocation(newUpperLeft);
    }

    /**
     * checking if the paddle is outside the right edge of the game.
     * @param p the paddle block's upper left point.
     * @return true if it outside the right edge of the game.
     */
    public boolean isPaddleCrossRightEdge(Point p) {
        return p.getX() + this.getCollisionRectangle().getWidth()
                > Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE - PADDLE_STEP;
    }
    /**
     * checking if the paddle is outside the left edge of the game.
     * @param p the paddle block's upper left point.
     * @return true if it outside the left edge of the game.
     */
    public boolean isPaddleCrossLeftEdge(Point p) {
        return p.getX() < Game.GUI_UPPER_LEFT_X + Game.GUI_BLOCK_EDGE_SIZE + PADDLE_STEP;
    }
    // Sprite

    /**
     * notify that time round is over and moving the paddle across the
     * game accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * drawing paddle on the surface.
     * @param d is the drawing surface.
     */
    public void drawOn(DrawSurface d) {
        this.getCollisionRectangle().drawOn(d);

    }

    // Collidable
    /**
     * get paddle collision rectangle.
     * @return paddle collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * Add this paddle to the game.
     * @param g is the game.
     */
    public void addToGame(Game g) {
        this.keyboard = g.getGui().getKeyboardSensor();
        g.getEvniorment().addCollidable(this);
        g.getSpirtes().addSprite(this);

    }
    /**
     * change the ball velocity according to where it hit the paddle.
     * @param hitter is the ball that hit the block.
     * @param c is the collison info.
     * @param currentVelocity is the ball velocity while the collision occur.
     * @return the new ball velocity according the instructions.
     */
    public Velocity hit(CollisionInfo c, Velocity currentVelocity, Ball hitter) {

        Velocity newVelocity = currentVelocity;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        int region = paddleRegionHit(c.getCollisionPoint());
        double speed = Math.sqrt(dx * dx + dy * dy);

        switch (region) {
            case 1:
                newVelocity = Velocity.fromAngleAndSpeed(300, speed);
                break;

            case 2:
                newVelocity = Velocity.fromAngleAndSpeed(330, speed);
                break;
            case 3:
                newVelocity = Velocity.fromAngleAndSpeed(0, speed);
                break;
            case 4:
                newVelocity = Velocity.fromAngleAndSpeed(30, speed);
                break;
            case 5:
                newVelocity = Velocity.fromAngleAndSpeed(60, speed);
                break;
            default:
                break;
        }
        return newVelocity;
    }

    /**
     * checking in which area of the paddle collision occur.
     * @param collisionPoint is th point where the collision occur.
     * @return the paddle zone (integer from 1 to 5).
     */
    public int paddleRegionHit(Point collisionPoint) {
        double paddleWith = this.getCollisionRectangle().getWidth();
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        double upperLeftX = upperLeft.getX();
        double regionSize = paddleWith / 5;
        if (collisionPoint.getX() <= upperLeftX + regionSize) {
            return 1;
        } else if (collisionPoint.getX() <= upperLeftX + 2 * regionSize) {
            return 2;
        } else if (collisionPoint.getX() <= upperLeftX + 3 * regionSize) {
            return 3;
        } else if (collisionPoint.getX() <= upperLeftX + 4 * regionSize) {
            return 4;
        } else { // if (collisionPoint.getX() <= upperLeftX + 5 * regionSize)
            return 5;
        }
    }
}