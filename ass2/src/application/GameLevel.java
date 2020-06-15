package application;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidefeatures.Velocity;
import gameelements.Counter;
import gameelements.GameEnvironment;
import gameelements.SpriteCollection;
import geometryprimitives.Point;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import interfaces.Animation;
import removers.BallRemover;
import removers.BlockRemover;
import scores.ScoreIndicator;
import scores.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * author hezi yaffe 208424242.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter[] counters;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation gameLevel;
    private List<Sprite> background;
    private Counter scoresCounter;
    private int currentLevel;

    //private constants.
    private static final int PADDLE_UPPER_LEFT_X = 250;
    private static final int PADDLE_UPPER_LEFT_Y = 500;
    private static final int BREAK_ALL_BLOCKS_SCORES = 100;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int START_COUNTING_NUMBER = 3;

    //public constants.
    public static final int BALL_RADIUS = 8;
    public static final int PADDLE_WIDTH = 150;
    public static final int PADDLE_HEIGHT = 10;
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    public static final int GUI_BLOCK_EDGE_SIZE = 20;
    public static final int GUI_UPPER_LEFT_X = 0;
    public static final int GUI_UPPER_LEFT_Y = 0;


    //constructor

    public GameLevel(LevelInformation l, Counter s, KeyboardSensor k,
                     AnimationRunner ar, GUI g) {
        this.gameLevel = l;
        this.scoresCounter = s;
        this.keyboard = k;
        this.runner = ar;
        this.gui = g;

    }

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
        return this.environment;
    }

    /**
     * @return game's sprites filed.
     */
    public SpriteCollection getSpirtes() {
        return this.sprites;
    }

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
    public Block[] createEdgesArr() {

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
        edges[0] = new Block(new geometryprimitives.Rectangle(upperLeft1, GUI_WIDTH, GUI_BLOCK_EDGE_SIZE));
        edges[1] = new Block(new geometryprimitives.Rectangle(upperLeft2, GUI_BLOCK_EDGE_SIZE, GUI_HEIGHT));
        edges[2] = new Block(new geometryprimitives.Rectangle(bottomRight1, GUI_WIDTH, GUI_BLOCK_EDGE_SIZE));
        edges[3] = new Block(new geometryprimitives.Rectangle(bottomRight2, GUI_BLOCK_EDGE_SIZE, GUI_HEIGHT));
        for (int i = 0; i < edges.length; i++) {
            if (i == 2) {
                //edges[i].setColor(new Color(0, 4, 128));
                edges[i].setColor(this.gameLevel.getBackgroundColor());
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
    public void addEdgesToGame(GameLevel g) {
        Block[] edges = createEdgesArr();
        this.counters[1] = new Counter(this.gameLevel.numberOfBalls());
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
     * create a ball set his velocity and add it to game.
     */
    public void addBallToGame() {
        Ball[] ballsArr = new Ball[this.gameLevel.numberOfBalls()];
        List<Velocity> ballsVelocities = this.gameLevel.initialBallVelocities();
        List<Point> ballsCenters = this.gameLevel.arrangeBallsOnTopOfPaddle();

        for (int i = 0; i < this.gameLevel.numberOfBalls(); i++) {
            ballsArr[i] = new Ball(ballsCenters.get(i), BALL_RADIUS, this.gameLevel.getBallsColor()[i]);
            ballsArr[i].setVelocity(ballsVelocities.get(i));
            ballsArr[i].addToGame(this);
        }
    }

    /**
     * create a paddle (block) and add it to game.
     */
    public void addPaddleToGame() {
        //Paddle paddle = new Paddle(new Block(PADDLE_UPPER_LEFT_X, PADDLE_UPPER_LEFT_Y
                //, this.gameLevel.paddleWidth(), PADDLE_HEIGHT), Color.orange);
        Paddle paddle = new Paddle(new Block(GUI_WIDTH / 2 - this.gameLevel.paddleWidth() / 2,
                PADDLE_UPPER_LEFT_Y, this.gameLevel.paddleWidth(), PADDLE_HEIGHT), Color.orange);
        paddle.addToGame(this);

    }

    /**
     * add the blocks we get in the instructions to the game.
     */
    public void addBlocksToGame() {
        //counting the removeable blocks in the game
        int counter = this.gameLevel.numberOfBlocksToRemove();
        List<Block> listOfBlocks = this.gameLevel.blocks();

        for (int i = 0; i < listOfBlocks.size(); i++) {
            listOfBlocks.get(i).initializeHitListenersList();
            listOfBlocks.get(i).addToGame(this);
            //counter++;
        }

        /* initial blockRemover counter (counters[0]), blockRemover event,
         * ScoreTrackingListener counter (counters[2]), ScoreTrackingListener event.
         */
        this.counters[0] = new Counter(counter);
        this.counters[2] = this.scoresCounter;
        BlockRemover remover = new BlockRemover(this, this.getRemainingBlocksCounter());
        ScoreTrackingListener score = new ScoreTrackingListener(this.getScoresCounter(),
                this.getRemainingBlocksCounter());

        for (int i = 0; i < this.gameLevel.numberOfBlocksToRemove(); i++) {
            listOfBlocks.get(i).initializeHitListenersList();
            listOfBlocks.get(i).addHitListener(remover);
            listOfBlocks.get(i).addHitListener(score);
        }

    }

    /**
     * add collidable to game enviorment collidable list.
     * @param c is the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.getCollidablesList().add(c);
    }

    /**
     * add sprite to game sprite list.
     * @param s is the sprite we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.getSprites().add(s);
    }

    /**
     * remove collidable from game enviorment collidable list.
     * @param c is the collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidablesList().remove(c);
    }

    /**
     * remove sprite from game sprite list.
     * @param s is the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * @param g is the current game.
     * creating a GUI and new GameEnviorment and
     * SpriteCollection variables, in order to initialize
     * game fileds
     */
    public void initializeGameFileds(GameLevel g) {
        //g.setGui(new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT));
        //g.runner = new AnimationRunner();
        g.keyboard = g.getGui().getKeyboardSensor();
        g.setRunner(this.gui, FRAMES_PER_SECOND);
        g.environment = new GameEnvironment();
        g.sprites = new SpriteCollection();
        g.background = new ArrayList<Sprite>();
    }

    public void setRunner(GUI g, int frames) {
        this.runner.setGui(g);
        this.runner.setFramesPerSecond(frames);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        initializeGameCounterArr();
        initializeGameFileds(this);
        addBallToGame();
        addEdgesToGame(this);
        addBlocksToGame();
        addPaddleToGame();
        addScoreCounterToGame();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        runningcountingdown();
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    public void runningcountingdown() {
        this.runner.setFramesPerSecond(1);
        this.background.add(this.gameLevel.getBackground());
        this.background.addAll(this.gameLevel.getBackgroundSpirtes());
        CountdownAnimation animation = new CountdownAnimation(2,
                START_COUNTING_NUMBER, this.sprites);
        animation.setBackground(this.background);
        this.runner.run(animation);
        this.runner.setFramesPerSecond(FRAMES_PER_SECOND);
    }


    /**
     * checking if there is no blocks left in the current game.
     * @param blocksCounter is the blocks counter.
     * @return true if there is no block left.
     */
    public Boolean isThereNoBlocksLeft(Counter blocksCounter) {
         return blocksCounter.getValue() == 0;
    }

    /**
     * checking if there is no balls left in the current game.
     * @param ballsCounter is the blocks counter.
     * @return true if there is no balls left.
     */
    public Boolean isThereNoBallsLeft(Counter ballsCounter) {
        return ballsCounter.getValue() == 0;
    }

    /**
     * according the instructions, paint GUI back-ground in dark blue.
     * @param d is the drawing surface.
     */
    public void drawGuiBackground(DrawSurface d) {
         Sprite s = this.gameLevel.getBackground();
        List<Sprite> spritesList = this.gameLevel.getBackgroundSpirtes();
        s.drawOn(d);
        for (Sprite sprite : spritesList) {
            sprite.drawOn(d);
        }

    }

    /**
     * @return the remaining blocks counter.
     */
    public Counter getRemainingBlocksCounter() {
        return this.counters[0];
    }

    /**
     * @return the remaining balls counter.
     */
    public Counter getRemainingBallsCounter() {
        return this.counters[1];
    }

    /**
     * @return the score counter.
     */
    public Counter getScoresCounter() {
        return this.counters[2];
    }

    /**
     * initialize array of counters.
     */
    public void initializeGameCounterArr() {
        this.counters = new Counter[3];
    }

    /**
     * initialize score Indicator with game's score counter.
     * @return score indicator.
     */
    public ScoreIndicator initializeScoreIndicator() {
       //return new ScoreIndicator(this.getScoresCounter());
        return new ScoreIndicator(this.scoresCounter);
    }

    /**
     * add score counter to the game.
     */
    public void addScoreCounterToGame() {
        ScoreIndicator scoreCounter = initializeScoreIndicator();
        this.addSprite(scoreCounter);
    }

    public boolean shouldStop() {
        return !this.running;
    }

    public boolean isWin(int listSize, int currentLevel, GameLevel l) {
        return listSize == currentLevel && l.getRemainingBlocksCounter().getValue() == -1;
    }

    public void doOneFrame(DrawSurface d) {
        drawGuiBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (this.getRemainingBallsCounter().getValue() == 0) {
            this.runner.run(new EndLooseScreen(this.keyboard, this.scoresCounter));
            this.running = false;
            this.gui.close();
        }

        //using -1 as a flag in order to get another round of run()
        if (this.getRemainingBlocksCounter().getValue() == -1) {
            //this.getGui().close();
            this.running = false;
        }

        if (isThereNoBlocksLeft(this.getRemainingBlocksCounter())) {
            this.getScoresCounter().increase(BREAK_ALL_BLOCKS_SCORES);
            this.getRemainingBlocksCounter().decrease(1);
        }

        if (isThereNoBallsLeft(this.getRemainingBallsCounter())) {
            //this.getGui().close();
            this.running = false;
        }
    }
}
