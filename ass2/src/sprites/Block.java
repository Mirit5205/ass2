package sprites;

import application.Game;
import biuoop.DrawSurface;
import collidefeatures.CollisionInfo;
import collidefeatures.Velocity;
import gameelements.GameEnvironment;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * author hezi yaffe 208424242.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private int hitCounter;
    private static java.awt.Color color;
    private List<HitListener> hitListeners;
    //constants
    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_Y_START_POINT = 80;

    //constructors
    /**
     * @param r is block rectangle.
     */
    public Block(Rectangle r) {
        this.rectangle = r;
    }

    /**
     * @param upperLeft is rectangle upper left point.
     * @param width is rectangle width.
     * @param height is rectangle height.
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
    }

    /**
     * @param x is rectangle upper left point x value.
     * @param y is rectangle upper left point y value.
     * @param width is rectangle width.
     * @param height is rectangle height.
     */
    public Block(double x, double y, double width, double height) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
    }

    /**
     *
     * @param x is rectangle upper left point x value.
     * @param y is rectangle upper left point y value.
     * @param width is rectangle width.
     * @param height is rectangle height.
     * @param hitCounter is block hit counter.
     */
    public Block(double x, double y, double width, double height, int hitCounter) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.hitCounter = hitCounter;
    }

    //setters
    /**
     * update block color filed.
     * @param c is the block color.
     */
    public void setColor(Color c) {
        this.color = c;
        this.getCollisionRectangle().setColor(c);
    }

    //getters

    /**
     * @return block's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;

    }

    /**
     * add block to the game by adding him to the relevant lists.
     * @param g is the game.
     */
    public void addToGame(Game g) {
        g.getEvniorment().addCollidable(this);
        g.getSpirtes().addSprite(this);
    }

    /**
     * remove block from current game.
     * @param game is the current game.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * @param dx is the ball dx velocity.
     * @param collisionPoint is the Collision point
     * @return true if the next step of the ball is outside the GUI (axe X).
     */
    public boolean isCollisionPointOutOfTheXEdges(double dx , Point collisionPoint) {
      return (collisionPoint.getX() >= Game.GUI_WIDTH - dx
              || collisionPoint.getX() <= Game.GUI_BLOCK_EDGE_SIZE - dx);
    }

    /**
     * @param dy is the ball dy velocity.
     * @param collisionPoint is the Collision point
     * @return true if the next step of the ball is outside the GUI (axe Y).
     */
    public boolean isCollisionPointOutOfTheYEdges(double dy, Point collisionPoint) {
        return (collisionPoint.getY() >= Game.GUI_HEIGHT - dy
                || collisionPoint.getY() <= Game.GUI_BLOCK_EDGE_SIZE - dy);
    }

    /**
     * @param c is the collision info.
     * @param currentVelocity is the velocity of the ball.
     * @param hitter is the ball that hit the block.
     * when the collision occur.
     * the method Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @return is the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     */
    public Velocity hit(CollisionInfo c, Velocity currentVelocity, Ball hitter) {
        this.notifyHit(hitter);
        this.hitCounter--;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point collisionPoint = c.getCollisionPoint();
        Collidable collidable = c.getCollisionObject();
        Rectangle r = collidable.getCollisionRectangle();
        r.setRectangleEdgesAsLinesList();
        Velocity newVelocity = currentVelocity;
        //if the x value of the ball center is outside the GUI
        if (isCollisionPointOutOfTheXEdges(dx, collisionPoint)) {
            dx = -dx;
            //if the y value of the ball center is outside the GUI
            if (isCollisionPointOutOfTheYEdges(dy, collisionPoint)) {
                dy = -dy;
            }
        } else if (isCollisionPointOutOfTheYEdges(dy, collisionPoint)) {
            //y value of the ball center is outside the GUI
            dy = -dy;
            //if the x value of the ball center is outside the GUI
            if (isCollisionPointOutOfTheXEdges(dx, collisionPoint)) {
                dx = -dx;
            }
        } else if (dx == 0) { //vertical collision
            dy = -dy;

        } else if (dy == 0) { //horizontal collision
            dx = -dx;
        } else if (dx > 0 && dy > 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
            } else {
                dx = -dx;
            }
        } else if (dx > 0 && dy < 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
            } else {
                dx = -dx;
            }
        } else if (dx < 0 && dy > 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
            } else {
                dx = -dx;
            }
        } else { // if (dx < 0 && dy < 0)
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
            } else {
                dx = -dx;
            }
        }
        newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }

    /**
     * @param p is the upper left point of the clock.
     * the method set the bolck location to start in the
     * upper left point.
     */
    public void setBlockLocation(Point p) {
        this.rectangle.setUpperLeft(p);
    }

    /**
     * @param surface is the GUI surface we want to draw on.
     * the method draw bolck on the surface
     */
    public void drawOn(DrawSurface surface) {
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        double height = this.getCollisionRectangle().getHeight();
        double width = this.getCollisionRectangle().getWidth();
        //assign color variable the block color
        Color c = this.getCollisionRectangle().getColor();
        //draw the block edges in black
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
        //draw the block according to his color
        surface.setColor(c);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
    }

    /**
     * notify the block that time round is end,
     * currently not doing anything.
     */
    public void timePassed() {
        ;
    }

    /**
     * @param listOfBlocksArr is a list of blocks array we want to add the
     * collidable list.
     * @param enviorment is the game enviorment.
     * the method add all the bolcks in the list to game's collidable list.
     */
    public static void addListOfBlocksArrToCollidableList(List<Block[]> listOfBlocksArr,
                                                          GameEnvironment enviorment) {
        for (Block[] b : listOfBlocksArr) {
            for (int i = 0; i < b.length; i++) {
                enviorment.addCollidable(b[i]);
            }
        }
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl is the element we want to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl is the element we want to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * initialize hit listeners list.
     */
    public void initializeHitListenersList() {
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Notify all listeners about a hit event.
     * @param hitter is the ball that perform the hit event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * creating score block.
     * @return score block at the top of the GUI
     * according to instructions.
     */
    public static Block createScoreBlock() {
        Block scoreBlock;
        //upper left point of the GUI
        Point upperLeft = new Point(Game.GUI_UPPER_LEFT_X, Game.GUI_UPPER_LEFT_Y);
        scoreBlock = new Block(new Rectangle(upperLeft, Game.GUI_WIDTH, Game.GUI_BLOCK_EDGE_SIZE + 10));
        scoreBlock.setColor(Color.white);
        return scoreBlock;
    }

    /**
     * @return is a list of blocks array, the game's blocks lines
     * that appear when we iniialize the game,
     * according the assignment instructions.
     */
    public static List<Block[]> createGameBlocks() {
        List<Block[]> blocksArrList = new ArrayList<>();
        //array of colors, color for every line of blocks
        Color[] colorArr = {color.GRAY, color.GREEN, color.RED, color.PINK, color.YELLOW, color.BLUE};
        //blocks lines size
        Block[][] arrayOfBlockArr = {new Block[12], new Block[11], new Block[10],
                new Block[9], new Block[8], new Block[7]};
        //x location of the first block in every line
        Point upperLeft = new Point(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE
                - BLOCK_WIDTH, BLOCK_Y_START_POINT);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        //initial blocks array
        for (int i = 0; i < arrayOfBlockArr.length; i++) {
            for (int j = 0; j < arrayOfBlockArr[i].length; j++) {
                arrayOfBlockArr[i][j] = new Block(upperLeftX, upperLeftY, BLOCK_WIDTH, BLOCK_HEIGHT);
                arrayOfBlockArr[i][j].setColor(colorArr[i]);
                //gap between blocks
                upperLeftX -= BLOCK_WIDTH;
            }
            //initial x location of the first block in every line
            // (we modify it in the inner loop)
            upperLeftX = upperLeft.getX();
            //initial y location of the first block in every line
            upperLeftY = upperLeftY + BLOCK_HEIGHT;
            blocksArrList.add(arrayOfBlockArr[i]);
        }
        return blocksArrList;
    }

}

