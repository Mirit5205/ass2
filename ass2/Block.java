import biuoop.DrawSurface;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private int hitCounter;
    static private java.awt.Color color;

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
        this.rectangle = new Rectangle(new Point(x,y), width, height);
    }
    public void setColor (Color c) {
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
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    public Velocity hit(CollisionInfo c, Velocity currentVelocity, Point newCenter) {
        this.hitCounter++;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point collisionPoint = c.getCollisionPoint();
        Collidable collidable = c.getCollisionObject();
        Rectangle r = collidable.getCollisionRectangle();
        r.setRectangleEdgesAsLinesList();
        Velocity newVelocity = currentVelocity;
        //if the x value of the ball center is outside the GUI
        if (newCenter.getX() >= 580 || newCenter.getX() <= 20) {
            dx = -dx;
            newVelocity = new Velocity(dx, dy);
        }
        //if the y value of the ball center is outside the GUI
        else if (newCenter.getY() >= 580 || newCenter.getY() <= 20) {
            dy = -dy;
            newVelocity = new Velocity(dx, dy);
        }
         else if (dx == 0) {
            newVelocity = new Velocity(dx, -dy);

        }
        else if (dy == 0) {
            newVelocity = new Velocity(-dx, dy);

        }
        else if (dx > 0 && dy > 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                newVelocity = new Velocity(dx, -dy);
            } else if (r.isPointOnHorizontalEdge(collisionPoint)){
                newVelocity = new Velocity(-dx, dy);
            }
            else {
                newVelocity = new Velocity(dx, -dy);
            }

        }
        else if (dx > 0 && dy < 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                newVelocity = new Velocity(dx, -dy);
            } else {
                newVelocity = new Velocity(-dx, dy);
            }

        }
        else if (dx < 0 && dy > 0) {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                newVelocity = new Velocity(dx, -dy);
        } else {
            newVelocity = new Velocity(-dx, dy);
            }
        }
        // if (dx < 0 && dy < 0)
        else {
            if (r.isPointOnVerticalEdge(collisionPoint)) {
                newVelocity = new Velocity(dx, -dy);
            } else {
                newVelocity = new Velocity(-dx, dy);
            }
        }
        /*
        //if the x value of the ball center is outside the GUI
        if (newCenter.getX() >= 580 || newCenter.getX() <= 20) {
            dx = -dx;
            newVelocity = new Velocity(dx, dy);
        }

        //if the y value of the ball center is outside the GUI
        else if (newCenter.getY() >= 580 || newCenter.getY() <= 20) {
            dy = -dy;
            newVelocity = new Velocity(dx, dy);
        }
        else if (dx > 0 && dy > 0) {
            newVelocity = new Velocity(dx, -dy);

        }
        else if (dx > 0 && dy < 0) {
            newVelocity = new Velocity(-dx, dy);
        }
        else if (dx < 0 && dy > 0) {
            newVelocity = new Velocity(dx, -dy);
        }
        else if (dx < 0 && dy < 0) {
            newVelocity = new Velocity(-dx, dy);
        }
        */
        /*
        /////////the first Code!!!!!!!!!!!!/////
        //if the x value of the ball center is outside the GUI
        if (newCenter.getX() >= 580 || newCenter.getX() <= 20) {
            dx = -dx;
            newVelocity = new Velocity(dx, dy);
            return newVelocity;
        }

        //if the y value of the ball center is outside the GUI
        if (newCenter.getY() >= 580 || newCenter.getY() <= 20) {
            dy = -dy;
            newVelocity = new Velocity(dx, dy);
            return newVelocity;
        }
        // give the ball direction according the collision point
        if (center.getX() < collisionPoint.getX() && center.getY() < collisionPoint.getY()) {
            newVelocity = new Velocity(-dx, dy);
            return newVelocity;
        }
        if (center.getX() >= collisionPoint.getX() && center.getY() >= collisionPoint.getY()) {
            newVelocity = new Velocity(-dx, dy);
            return newVelocity;
        }
        if (center.getX() < collisionPoint.getX() && center.getY() > collisionPoint.getY()) {
            newVelocity = new Velocity(dx, -dy);
            return newVelocity;
        }
        if (center.getX() >= collisionPoint.getX() && center.getY() <= collisionPoint.getY()) {
            newVelocity = new Velocity(dx, -dy);
            return newVelocity;
        }
        return newVelocity;
         */
        return newVelocity;
    }

    public  void setBlockLocation(Point p) {
        this.rectangle.setUpperLeft(p);
    }
    public void drawOn(DrawSurface surface) {
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        double height = this.getCollisionRectangle().getHeight();
        double width = this.getCollisionRectangle().getWidth();
        Color color = this.getCollisionRectangle().getColor();
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int)upperLeft.getX(), (int)upperLeft.getY(),
                (int)width, (int)height);
        surface.setColor(color);
        surface.fillRectangle((int)upperLeft.getX(), (int)upperLeft.getY(),
                (int)width, (int)height);
    }
    public void timePassed(){
        ;
    }

    public static void addListOfBlocksArrToCollidableList(List<Block[]> listOfBlocksArr,
                                                          GameEnvironment enviorment ) {
        for (Block[] b : listOfBlocksArr) {
            for (int i = 0; i < b.length; i++) {
                enviorment.addCollidable(b[i]);
            }
        }
    }

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
    public static Block[] createFirstLineOfBlocks() {
        Block[] blockArr = new Block[12];
        Point upperLeft = new Point(540, 60);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, 40, 10);
            upperLeftX -= 40;
            blockArr[i].setColor(color.GRAY);
        }
        return blockArr;
    }
    public static Block[] createSecondLineOfBlocks() {
        Block[] blockArr = new Block[11];
        Point upperLeft = new Point(540, 70);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, 40, 10);
            upperLeftX -= 40;
            blockArr[i].setColor(color.RED);
        }
        return blockArr;
    }
    public static Block[] createThirdLineOfBlocks() {
        Block[] blockArr = new Block[10];
        Point upperLeft = new Point(540, 80);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, 40, 10);
            upperLeftX -= 40;
            blockArr[i].setColor(color.YELLOW);
        }
        return blockArr;
    }
    public static Block[] createFourthLineOfBlocks() {
        Block[] blockArr = new Block[9];
        Point upperLeft = new Point(540, 90);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, 40, 10);
            upperLeftX -= 40;
            blockArr[i].setColor(color.BLUE);
        }
        return blockArr;
    }
    public static Block[] createFifthLineOfBlocks() {
        Block[] blockArr = new Block[8];
        Point upperLeft = new Point(540, 100);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, 40, 10);
            upperLeftX -= 40;
            blockArr[i].setColor(color.PINK);
        }
        return blockArr;
    }
    public static Block[] createSixthLineOfBlocks() {
        Block[] blockArr = new Block[7];
        Point upperLeft = new Point(540, 110);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY() + 1;
        for (int i = 0; i < blockArr.length; i++) {
            blockArr[i] = new Block(upperLeftX, upperLeftY, 40, 10);
            upperLeftX -= 40;
            blockArr[i].setColor(color.GREEN);
        }
        return blockArr;
    }
}

