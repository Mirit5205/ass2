import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private Color color;

    public Paddle(Block b, Color c) {
        this.block = b;
        this.color = c;
        this.getCollisionRectangle().setColor(c);
    }
    public void moveLeft() {
        Point newUpperLeft = this.getCollisionRectangle().getUpperLeft();
        if (isPaddleCrossLeftEdge(newUpperLeft)){
            return;
        }
        newUpperLeft.setX(newUpperLeft.getX() - 10);
            this.block.setBlockLocation(newUpperLeft);
    }
    public void moveRight() {
        Point newUpperLeft = this.getCollisionRectangle().getUpperLeft();
        if (isPaddleCrossRightEdge(newUpperLeft))  {
            return;
        }
        newUpperLeft.setX(newUpperLeft.getX() + 10);
        this.block.setBlockLocation(newUpperLeft);
    }

    public boolean isPaddleCrossRightEdge(Point p) {
        return p.getX() + this.getCollisionRectangle().getWidth() > 570;
    }
    public boolean isPaddleCrossLeftEdge(Point p) {
        return p.getX() < 30;
    }
    // Sprite
    public void timePassed() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            //newUpperLeft.setX(newUpperLeft.getX() - 10);
            //this.block.setBlockLocation(newUpperLeft);
            this.moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            //newUpperLeft.setX(newUpperLeft.getX() + 10);
            //this.block.setBlockLocation(newUpperLeft);
            this.moveRight();
        }
    }
    public void drawOn(DrawSurface d) {
        this.getCollisionRectangle().drawOn(d);

    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        this.keyboard = g.getGui().getKeyboardSensor();
        g.getEvniorment().addCollidable(this);
        g.getSpirtes().addSprite(this);

    }
    //change the ball velocity according to where it hit the paddle
    public Velocity hit(CollisionInfo c, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        Point collisionPoint = c.getCollisionPoint();
        int region = paddleRegionHit(c.getCollisionPoint());
        /*
        double angel = Math.atan2(collisionPoint.getY(), collisionPoint.getX());
        if (Math.sin(angel) == 0) {
            angel += 0.1;
        }
        double speed = currentVelocity.getDx() / Math.sin(angel);
         */
        switch (region) {
            case 1:
                newVelocity = Velocity.fromAngleAndSpeed(300, 10 );
                break;

            case 2:
                newVelocity = Velocity.fromAngleAndSpeed(330, 10 );
                break;
            case 3:
                newVelocity = Velocity.fromAngleAndSpeed(0, 10 );
                break;
            case 4:
                newVelocity = Velocity.fromAngleAndSpeed(30, 10 );
                break;
            case 5:
                newVelocity = Velocity.fromAngleAndSpeed(60, 10 );
                break;
            default:
                break;
        }

        //return this.block.hit(c,currentVelocity, center);
        return newVelocity;
    }

    public int paddleRegionHit(Point collisionPoint) {
        double paddleWith = this.getCollisionRectangle().getWidth();
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        double upperLeftX = upperLeft.getX();
        double regionSize = paddleWith / 5;
        if (collisionPoint.getX() <= upperLeftX + regionSize) {
            return 1;
        }
        else if (collisionPoint.getX() <= upperLeftX + 2 * regionSize) {
            return 2;
        }
        else if (collisionPoint.getX() <= upperLeftX + 3 * regionSize) {
            return 3;
        }
        else if (collisionPoint.getX() <= upperLeftX + 4 * regionSize) {
            return 4;
        }
        // if (collisionPoint.getX() <= upperLeftX + 5 * regionSize)
        else {
            return 5;
        }
    }
}