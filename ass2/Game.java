import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * author hezi yaffe 208424242.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment enviroment;
    private GUI gui;
    private Counter counter;

    //private constants.
    private static final int BALL_RADIUS = 8;
    private static final int PADDLE_WIDTH = 150;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_UPPER_LEFT_X = 250;
    private static final int PADDLE_UPPER_LEFT_Y = 500;

    //public constants.
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    public static final int GUI_BLOCK_EDGE_SIZE = 20;
    public static final int GUI_UPPER_LEFT_X = 0;
    public static final int GUI_UPPER_LEFT_Y = 0;

    //getters

    /**
     * @return game's gui filed.
     */
    public GUI getGui() {
        return this.gui;
     }

    /**
     * @return game's enviroment filed.
     */
    public GameEnvironment getEvniorment() {
        return this.enviroment;
    }

    /**
     * @return game's sprites filed.
     */
    public SpriteCollection getSpirtes() {
        return this.sprites;
    }

    //setters

    /**
     * update gui filed.
     * @param g is the GUI.
     */
    public void setGui(GUI g) {
        this.gui = g;
    }

    /**
     * creating blocks that will be used as game edges.
     * @return array of blocks that located near to the GUI
     * edges.
     */
    public static Block[] createEdgesArr() {

        Block[] edges = new Block[4];
        //for the top edge
        Point upperLeft1 = new Point(GUI_UPPER_LEFT_X, GUI_UPPER_LEFT_Y);
        //for the left edge
        Point upperLeft2 = new Point(GUI_UPPER_LEFT_X, GUI_UPPER_LEFT_Y);
        //for the bottom edge
        Point bottomRight1 = new Point(GUI_UPPER_LEFT_X, GUI_HEIGHT - GUI_BLOCK_EDGE_SIZE);
        //for the right edge
        Point bottomRight2 = new Point(GUI_WIDTH - GUI_BLOCK_EDGE_SIZE, GUI_UPPER_LEFT_Y);
        // the gui edges as blocks
        edges[0] = new Block(new Rectangle(upperLeft1, GUI_WIDTH, GUI_BLOCK_EDGE_SIZE));
        edges[1] = new Block(new Rectangle(upperLeft2, GUI_BLOCK_EDGE_SIZE, GUI_HEIGHT));
        edges[2] = new Block(new Rectangle(bottomRight1, GUI_WIDTH, GUI_BLOCK_EDGE_SIZE));
        edges[3] = new Block(new Rectangle(bottomRight2, GUI_BLOCK_EDGE_SIZE, GUI_HEIGHT));
        for (int i = 0; i < edges.length; i++) {
            edges[i].setColor(Color.GRAY);
        }
        return edges;
    }

    /**
     * @param g is the current game.
     * add the blocks in the edges to the game.
     */
    public void addEdgesToGame(Game g) {
        //PrintingHitListener p = new PrintingHitListener();
        //BlockRemover remover = new BlockRemover(this, this.counter);
        Block[] edges = createEdgesArr();
        //add edges to the game
        for (Block b : edges) {
            b.initializeHitListenersList();
            b.addToGame(g);
            //b.addHitListener(p);
            //b.addHitListener(remover);
        }
    }

    /**
     * @param g is the current game.
     * create a ball set his velocity and add it to game.
     */
    public static void addBallToGame(Game g) {
        Ball ball1 = new Ball(300, 400, BALL_RADIUS, java.awt.Color.BLACK);
        Ball ball2 = new Ball(200, 279, BALL_RADIUS, java.awt.Color.BLACK);
        Ball ball3 = new Ball(210, 355, BALL_RADIUS, java.awt.Color.BLACK);
        Ball ball4 = new Ball(225, 330, BALL_RADIUS, java.awt.Color.BLACK);
        Ball ball5 = new Ball(253, 240, BALL_RADIUS, java.awt.Color.BLACK);
        Ball ball6 = new Ball(435, 290, BALL_RADIUS, java.awt.Color.BLACK);

        Velocity v1 =  Velocity.fromAngleAndSpeed(280, 12);
        ball1.setVelocity(v1);
        Velocity v2 =  Velocity.fromAngleAndSpeed(310, 9);
        ball2.setVelocity(v2);
        Velocity v3 =  Velocity.fromAngleAndSpeed(310, 9);
        ball3.setVelocity(v3);
        Velocity v4 =  Velocity.fromAngleAndSpeed(310, 9);
        ball4.setVelocity(v4);
        Velocity v5 =  Velocity.fromAngleAndSpeed(310, 9);
        ball5.setVelocity(v5);
        Velocity v6 =  Velocity.fromAngleAndSpeed(310, 9);
        ball6.setVelocity(v6);

        ball1.addToGame(g);
        ball2.addToGame(g);
        ball3.addToGame(g);
        ball4.addToGame(g);
        ball5.addToGame(g);
        ball6.addToGame(g);

    }

    /**
     * @param g is the current game.
     * create a paddle (block) and add it to game.
     */
    public void addPaddleToGame(Game g) {
        Paddle paddle = new Paddle(new Block(PADDLE_UPPER_LEFT_X, PADDLE_UPPER_LEFT_Y
                , PADDLE_WIDTH, PADDLE_HEIGHT), Color.orange);
        paddle.addToGame(g);

    }

    /**
     * @param g is the current game.
     * add the blocks we get in the instructions to the game.
     */
    public void addBlocksToGame(Game g) {
        //counting the removeable blocks in the game
        int counter = 0;
        List<Block[]> listOfBlocksArr = Block.createListOfBlocksArr();
        for (Block[] b : listOfBlocksArr) {
            for (int i = 0; i < b.length; i++) {
                b[i].initializeHitListenersList();
                b[i].addToGame(g);
                counter++;
            }
        }

        //initial counter filed and blockRemover event
        this.counter = new Counter(counter);
        BlockRemover remover = new BlockRemover(this, this.counter);

        //add BlockRemover hit listener to every removable block
        for (Block[] b : listOfBlocksArr) {
            for (int i = 0; i < b.length; i++) {
                b[i].initializeHitListenersList();
                b[i].addHitListener(remover);
            }
        }

    }
    public void addCollidable(Collidable c) {
        this.enviroment.getCollidablesList().add(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.getSprites().add(s);
    }

    public void removeCollidable(Collidable c) {
        this.enviroment.getCollidablesList().remove(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }
    /**
     * @param g is the current game.
     * creating a GUI and new GameEnviorment and
     * SpriteCollection variables, in order to initialize
     * game fileds
     */
    public void initializeGameFileds(Game g) {
        g.setGui(new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT));
        g.enviroment = new GameEnvironment();
        g.sprites = new SpriteCollection();
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        initializeGameFileds(this);
        addBallToGame(this);
        addEdgesToGame(this);
        addBlocksToGame(this);
        addPaddleToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
     public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d =  this.getGui().getDrawSurface();
            drawGuiBackground(d);
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
            if (this.counter.getValue() == 0) {
                this.getGui().close();
                return;
            }
        }
    }

    /**
     * according the instructions, paint GUI back-ground in dark blue.
     * @param d is the drawing surface.
     */
    public void drawGuiBackground(DrawSurface d) {
         Color c = new Color(0, 4, 128);
         d.setColor(c);
        d.fillRectangle(Game.GUI_UPPER_LEFT_X, Game.GUI_UPPER_LEFT_Y, Game.GUI_WIDTH, Game.GUI_HEIGHT);
    }

    public Counter getCounter() {
        return this.counter;
    }
}
