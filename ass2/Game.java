import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.List;

/**
 * author hezi yaffe 208424242.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment enviroment;
    private GUI gui;
    private Counter[] counters;

    //private constants.
    private static final int BALL_RADIUS = 8;
    private static final int PADDLE_WIDTH = 150;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_UPPER_LEFT_X = 250;
    private static final int PADDLE_UPPER_LEFT_Y = 500;
    private static final int NUM_OF_BALLS = 50;
    private static final int END_GAME_MILLISECONDS = 800;
    private static final int BREAK_ALL_BLOCKS_SCORES = 100;

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
        Point upperLeft1 = new Point(GUI_UPPER_LEFT_X, GUI_UPPER_LEFT_Y + 30);
        //for the left edge
        Point upperLeft2 = new Point(GUI_UPPER_LEFT_X, GUI_UPPER_LEFT_Y);
        //for the bottom edge
        Point bottomRight1 = new Point(GUI_UPPER_LEFT_X + GUI_BLOCK_EDGE_SIZE, GUI_HEIGHT - GUI_BLOCK_EDGE_SIZE);
        //for the right edge
        Point bottomRight2 = new Point(GUI_WIDTH - GUI_BLOCK_EDGE_SIZE, GUI_UPPER_LEFT_Y);
        // the gui edges as blocks
        edges[0] = new Block(new Rectangle(upperLeft1, GUI_WIDTH, GUI_BLOCK_EDGE_SIZE));
        edges[1] = new Block(new Rectangle(upperLeft2, GUI_BLOCK_EDGE_SIZE, GUI_HEIGHT));
        edges[2] = new Block(new Rectangle(bottomRight1, GUI_WIDTH, GUI_BLOCK_EDGE_SIZE ));
        edges[3] = new Block(new Rectangle(bottomRight2, GUI_BLOCK_EDGE_SIZE, GUI_HEIGHT));
        for (int i = 0; i < edges.length; i++) {
            if (i == 2) {
                edges[i].setColor(new Color(0, 4, 128));
                continue;
            }
            edges[i].setColor(Color.GRAY);
        }
        return edges;
    }

    /**
     * @param g is the current game.
     * add the blocks in the edges to the game.
     */
    public void addEdgesToGame(Game g) {
        Block[] edges = createEdgesArr();
        this.counters[1] = new Counter(NUM_OF_BALLS);
        BallRemover ballRemover =  new BallRemover(this, this.getRemainingBallsCounter());
        //add edges to the game
        for (int i = 0; i < edges.length; i++) {
            edges[i].initializeHitListenersList();
            edges[i].addToGame(g);
            if (i == 2) {
                edges[i].addHitListener(ballRemover);
            }
        }
    }

    /**
     * @param g is the current game.
     * create a ball set his velocity and add it to game.
     */
    public void addBallToGame(Game g) {
        Ball[] ballsArr = new Ball[NUM_OF_BALLS];
        Velocity[] velocityArr = new Velocity[NUM_OF_BALLS];
        //random location for the balls (x,y)
        int[] randomArr = new int[NUM_OF_BALLS * 2];

        for (int i = 0; i < randomArr.length - 1; i++) {
            randomArr[i] = (int)(Math.random() * 100 + 200);
            randomArr[i + 1] = (int)(Math.random() * 100 + 300);
        }

        for (int i = 0; i < NUM_OF_BALLS; i++) {
            ballsArr[i] = new Ball(randomArr[i], randomArr[i + 1], BALL_RADIUS, java.awt.Color.BLACK);
            velocityArr[i] = Velocity.fromAngleAndSpeed(280, 12);
            ballsArr[i].setVelocity(velocityArr[i]);
            ballsArr[i].addToGame(g);

        }
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
        //List<Block[]> listOfBlocksArr = Block.createListOfBlocksArr();
        List<Block[]> listOfBlocksArr = Block.createGameBlocks();
        for (Block[] b : listOfBlocksArr) {
            for (int i = 0; i < b.length; i++) {
                b[i].initializeHitListenersList();
                b[i].addToGame(g);
                counter++;
            }
        }

        /* initial blockRemover counter (counters[0]), blockRemover event,
         * ScoreTrackingListener counter (counters[2]), ScoreTrackingListener event.
         */
        this.counters[0] = new Counter(counter);
        this.counters[2] = new Counter(0);
        BlockRemover remover = new BlockRemover(this, this.getRemainingBlocksCounter());
        ScoreTrackingListener score = new ScoreTrackingListener(this.getScoresCounter(),
                this.getRemainingBlocksCounter());

        //add BlockRemover hit listener to every removable block
        for (Block[] b : listOfBlocksArr) {
            for (int i = 0; i < b.length; i++) {
                b[i].initializeHitListenersList();
                b[i].addHitListener(remover);
                b[i].addHitListener(score);
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
        initializeGameCounterArr();
        initializeGameFileds(this);
        addBallToGame(this);
        addEdgesToGame(this);
        addBlocksToGame(this);
        addPaddleToGame(this);
        addScoreCounterToGame();
    }

    /**
     * Run the game -- start the animation loop.
     */
     public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
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
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            //using -1 as a flag in order to get another round of run()
            if (this.getRemainingBlocksCounter().getValue() == -1) {
                sleeper.sleepFor(END_GAME_MILLISECONDS);
                this.getGui().close();
                return;
            }

            if (isThereNoBlocksLeft(this.getRemainingBlocksCounter())) {
                this.getScoresCounter().increase(BREAK_ALL_BLOCKS_SCORES);
                this.getRemainingBlocksCounter().decrease(1);
            }

            if (isThereNoBallsLeft(this.getRemainingBallsCounter())) {
                sleeper.sleepFor(END_GAME_MILLISECONDS);
                this.getGui().close();
                return;

            }
        }
    }

    public Boolean isThereNoBlocksLeft(Counter blocksCounter) {
         return blocksCounter.getValue() == 0;
    }

    public Boolean isThereNoBallsLeft(Counter ballsCounter) {
        return ballsCounter.getValue() == 0;
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

    public Counter getRemainingBlocksCounter() {
        return this.counters[0];
    }

    public Counter getRemainingBallsCounter() {
        return this.counters[1];
    }

    public Counter getScoresCounter() {
        return this.counters[2];
    }

    public void initializeGameCounterArr() {
        this.counters = new Counter[3];
    }

    public ScoreIndicator initializeScoreIndicator() {
       return new ScoreIndicator(this.getScoresCounter());
    }

    public void addScoreCounterToGame() {
        ScoreIndicator scoreCounter = initializeScoreIndicator();
        this.addSprite(scoreCounter);
    }

}
