import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

public class GameEnviormentTest2 {
    // draw the ball on the given DrawSurface

     static private void drawAnimation(Ball b, GUI gui) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Line trajectory;
        while (true) {
            trajectory = b.getTrajectoryLine();
           CollisionInfo collisionInfo = b.getGameEnvironment().getClosestCollision(trajectory);
            b.moveOneStep(collisionInfo);
            DrawSurface d = gui.getDrawSurface();
            drawCollidables(b.getGameEnvironment().getCollidablesList(),gui, d);
           // drawGuiEdges(edges ,gui, d);
            b.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void drawCollidables (List<Collidable> collidables, GUI gui, DrawSurface d) {
        for (Collidable c : collidables) {
            c.getCollisionRectangle().drawOn(d);
        }
    }
    public static Block[] createEdgesArr() {
        Block[] edges = new Block[4];
        //for the top edge
        Point upperLeft1 = new Point(20, 0);
        //for the left edge
        Point upperLeft2 = new Point(0, 0);
        //for the bottom edge
        Point bottomRight1 = new Point(0, 580);
        //for the right edge
        Point bottomRight2 = new Point(580, 0);
        // the gui edges as blocks
        edges[0] = new Block(new Rectangle(upperLeft1, 560, 20));
        edges[1] = new Block(new Rectangle(upperLeft2, 20, 580));
        edges[2] = new Block(new Rectangle(bottomRight1, 580, 20));
        edges[3] = new Block(new Rectangle(bottomRight2, 20, 600));

        for(int i = 0; i < edges.length; i++) {
            edges[i].setColor(Color.GRAY);
        }
        return edges;
    }

    public static void addEdgesToCollidableList (GameEnvironment g) {
        Block[] edges = createEdgesArr();
        for (int i = 0; i < edges.length; i++) {
            g.addCollidable(edges[i]);
        }
    }

    public static void drawGuiEdges(Block[] guiEdges, GUI gui, DrawSurface d) {

        for (int i = 0; i < guiEdges.length; i++) {
                guiEdges[i].drawOn(d);
            }
    }
    public static void main (String[] args) {
        GUI gui = new GUI("title",600,600);
        GameEnvironment environment = new GameEnvironment();
        Ball b = new Ball(345, 250, 10, java.awt.Color.BLACK);
        b.setVelocity(12, 17);
        //Block[] edges = createEdgesArr();
        Block.addListOfBlocksArrToCollidableList( Block.createListOfBlocksArr(), environment);
        CollisionInfo c;
        Point oldCenter = b.getCenter();
        Line trajectory = b.getTrajectoryLine();
        // the gui edges as blocks
        addEdgesToCollidableList( environment);

        b.setGameEnvironment(environment);
        c = environment.getClosestCollision(trajectory);
        drawAnimation(b, gui);
    }
}
