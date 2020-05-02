import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * block class
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private int hitCounter;
    static private java.awt.Color color;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 50;



    /*public void DrawOn(DrawSurface d) {
        Rectangle r = this.getCollisionRectangle();
        Point upperLeft = r.getUpperLeft();
        double height = r.getHeight();
        double width = r.getWidth();
        d.fillRectangle((int)upperLeft.getX(), (int)upperLeft.getY(),
                (int)width, (int)height);
        d.setColor(this.getColor());
        this.getCollisionRectangle().drawOn(d);
    }

     */
    //constructors
    public Block(Rectangle r) {
        this.rectangle = r;
    }
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
    }
    public Block(double x, double y, double width, double height) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
    }
    public Block(double x, double y, double width, double height, int hitCounter) {
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.hitCounter = hitCounter;
    }
    public void setColor(Color c) {
        this.color = c;
        this.getCollisionRectangle().setColor(c);
    }
    public Color getColor() {
        return this.color;
    }

    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle() {
        return this.rectangle;

    }

    public void addToGame(Game g) {
        g.getEvniorment().addCollidable(this);
        g.getSpirtes().addSprite(this);
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
     * @param currentVelocity is the velocity of the ball
     * when the collision occur.
     * the method Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @return is the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     */
    public Velocity hit(CollisionInfo c, Velocity currentVelocity) {
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
            if (isCollisionPointOutOfTheYEdges(dy, collisionPoint)) {
                dy = -dy;
            }
        }//if the y value of the ball center is outside the GUI
         else if (isCollisionPointOutOfTheYEdges(dy, collisionPoint)) {
            dy = -dy;
            if (isCollisionPointOutOfTheXEdges(dx, collisionPoint)) {
                dx = -dx;
            }
        }
         //vertical collision
         else if (dx == 0) {
            dy = -dy;

        }
         //horizontal collision
        else if (dy == 0) {
            dx = -dx;
        }
        else if (dx > 0 && dy > 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
            } else {
                dx = -dx;
            }
        }
        else if (dx > 0 && dy < 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
            } else {
                dx = -dx;
            }

        }
        else if (dx < 0 && dy > 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
        } else {
                dx = -dx;
            }
        }
        // if (dx < 0 && dy < 0)
        else {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                dy = -dy;
            }
            else {
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
        //location of the hit counter
        int hitCounterXLocation = (int) (upperLeft.getX() + width / 2 - 2);
        int hitCounterYLocation = (int) (upperLeft.getY() + height / 2 + 2);
        String hitCounter = Integer.toString(this.hitCounter);
        //assign color variable the block color
        Color color = this.getCollisionRectangle().getColor();
        //draw the block edges in black
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
        //draw the block according to his color
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
        //draw the hit counter in white
        surface.setColor(Color.white);
        if (this.hitCounter < 1) {
            surface.drawText(hitCounterXLocation, hitCounterYLocation, "x", 10);
        } else {
            surface.drawText(hitCounterXLocation, hitCounterYLocation, hitCounter, 10);
        }
    }
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
     * @return is a list of blocks array, the game's blocks lines
     * that appear when we iniialize the game,
     * according the assignment instructions.
     */
    public static List<Block[]> createListOfBlocksArr() {
        List<Block[]> listOfBlocksArr = new ArrayList<Block[]>();
        listOfBlocksArr.add(createFirstLineOfBlocks());
        listOfBlocksArr.add(createSecondLineOfBlocks());
        listOfBlocksArr.add(createThirdLineOfBlocks());
        listOfBlocksArr.add(createFourthLineOfBlocks());
        listOfBlocksArr.add(createFifthLineOfBlocks());
        listOfBlocksArr.add(createSixthLineOfBlocks());
        return listOfBlocksArr;

    }
    /**
     * @return is blocks array, the game's first blocks line,
     * according the assignment instructions.
     */
    public static Block[] createFirstLineOfBlocks() {
        Block[] blockArr = new Block[12];
        Point upperLeft = new Point(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE - BLOCK_WIDTH, 60);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, BLOCK_WIDTH, BLOCK_HEIGHT, 2);
            upperLeftX -= BLOCK_WIDTH;
            blockArr[i].setColor(color.GRAY);
        }
        return blockArr;
    }
    /**
     * @return is blocks array, the game's second blocks line,
     * according the assignment instructions.
     */
    public static Block[] createSecondLineOfBlocks() {
        Block[] blockArr = new Block[11];
        Point upperLeft = new Point(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE - BLOCK_WIDTH, 80);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, BLOCK_WIDTH, BLOCK_HEIGHT, 1);
            upperLeftX -= BLOCK_WIDTH;
            blockArr[i].setColor(color.RED);
        }
        return blockArr;
    }
    /**
     * @return is blocks array, the game's third blocks line,
     * according the assignment instructions.
     */
    public static Block[] createThirdLineOfBlocks() {
        Block[] blockArr = new Block[10];
        Point upperLeft = new Point(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE - BLOCK_WIDTH, 100);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, BLOCK_WIDTH, BLOCK_HEIGHT, 1);
            upperLeftX -= BLOCK_WIDTH;
            blockArr[i].setColor(color.YELLOW);
        }
        return blockArr;
    }
    /**
     * @return is blocks array, the game's forth blocks line,
     * according the assignment instructions.
     */
    public static Block[] createFourthLineOfBlocks() {
        Block[] blockArr = new Block[9];
        Point upperLeft = new Point(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE - BLOCK_WIDTH, 120);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, BLOCK_WIDTH, BLOCK_HEIGHT, 1);
            upperLeftX -= BLOCK_WIDTH;
            blockArr[i].setColor(color.BLUE);
        }
        return blockArr;
    }
    /**
     * @return is blocks array, the game's fifth blocks line,
     * according the assignment instructions.
     */
    public static Block[] createFifthLineOfBlocks() {
        Block[] blockArr = new Block[8];
        Point upperLeft = new Point(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE - BLOCK_WIDTH, 140);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, BLOCK_WIDTH, BLOCK_HEIGHT, 1);
            upperLeftX -= BLOCK_WIDTH;
            blockArr[i].setColor(color.PINK);
        }
        return blockArr;
    }
    /**
     * @return is blocks array, the game's sixth blocks line,
     * according the assignment instructions.
     */
    public static Block[] createSixthLineOfBlocks() {
        Block[] blockArr = new Block[7];
        Point upperLeft = new Point(Game.GUI_WIDTH - Game.GUI_BLOCK_EDGE_SIZE - BLOCK_WIDTH, 160);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, BLOCK_WIDTH, BLOCK_HEIGHT, 1);
            upperLeftX -= BLOCK_WIDTH;
            blockArr[i].setColor(color.GREEN);
        }
        return blockArr;
    }
}

