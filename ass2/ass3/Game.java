import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;
import java.util.List;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment enviroment;
    private GUI gui;
    private static int BALL_RADIUS = 10;
    private static int PADDLE_WIDTH = 150;
    private static int PADDLE_HEIGHT = 20;
    private static int GUI_WIDTH = 600;
    private static int GUI_HEIGHT = 600;


    public void addCollidable(Collidable c) {
        this.enviroment.addCollidable(c);
    }
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    public GUI getGui() {
        return this.gui;
     }
    public void setGui(GUI gui) {
        this.gui = gui;
    }
    public GameEnvironment getEvniorment() {
        return this.enviroment;
    }
    public SpriteCollection getSpirtes() {
        return this.sprites;
    }
    public void setSprites(SpriteCollection sprites) {
        this.sprites = sprites;
    }
    public static Block[] createEdgesArr() {
        /*
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
         */
        Block[] edges = new Block[4];
        //for the top edge
        Point upperLeft1 = new Point(0, 0);
        //for the left edge
        Point upperLeft2 = new Point(0, 0);
        //for the bottom edge
        Point bottomRight1 = new Point(0, 580);
        //for the right edge
        Point bottomRight2 = new Point(580, 0);
        // the gui edges as blocks
        edges[0] = new Block(new Rectangle(upperLeft1, 600, 20));
        edges[1] = new Block(new Rectangle(upperLeft2, 20, 600));
        edges[2] = new Block(new Rectangle(bottomRight1, 600, 20));
        edges[3] = new Block(new Rectangle(bottomRight2, 20, 600));
        for(int i = 0; i < edges.length; i++) {
            edges[i].setColor(Color.GRAY);
        }
        return edges;
    }

    public void addEdgesToGame(Game g) {
        Block[] edges = createEdgesArr();
        //add edges to the game
        for (Block b : edges) {
            b.addToGame(g);
        }
    }
    public static void addBallToGame(Game g) {
        Ball ball1 = new Ball(300, 400, BALL_RADIUS, java.awt.Color.BLACK);
        //Ball ball2 = new Ball(200, 279, BALL_RADIUS, java.awt.Color.BLACK);
        Ball ball3 = new Ball(30, 550, BALL_RADIUS, java.awt.Color.BLACK);
        Velocity v1 =  Velocity.fromAngleAndSpeed(280, 12);
        ball1.setVelocity(v1);
        //Velocity v2 =  Velocity.fromAngleAndSpeed(310, 9);
        //ball2.setVelocity(v2);
        //Velocity v3 =  Velocity.fromAngleAndSpeed(450, 15);
       // ball3.setVelocity(v3);


        ball1.addToGame(g);
        //ball2.addToGame(g);
       // ball3.addToGame(g);

    }

    public void addPaddleToGame(Game g) {
        Paddle paddle = new Paddle(new Block(250,500
                ,PADDLE_WIDTH,PADDLE_HEIGHT), Color.orange);
        paddle.addToGame(g);

    }
    public static void addBlocksToGame(Game g) {
        List<Block[]> listOfBlocksArr = Block.createListOfBlocksArr();
        for (Block[] b : listOfBlocksArr) {
            for (int i = 0; i < b.length; i++) {
                b[i].addToGame(g);
            }

        }
    }
    public void initializeGameFileds(Game g) {
        g.setGui( new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT));
        g.enviroment = new GameEnvironment();
        g.sprites = new SpriteCollection();
    }


    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        initializeGameFileds(this);
        addBallToGame(this);
        addEdgesToGame(this);
        addBlocksToGame(this);
        addPaddleToGame(this);
    }

    // Run the game -- start the animation loop.
     public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d =  this.getGui().getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                Sleeper sleeper = new Sleeper();
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
