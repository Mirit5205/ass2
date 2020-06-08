package application;

import collidefeatures.Velocity;
import gameelements.Counter;
import geometryprimitives.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import removers.BlockRemover;
import scores.ScoreTrackingListener;
import sprites.Block;
import sprites.Paddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WideEasy implements LevelInformation {
    private int ballsNum;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite gameBackground;
    private List<Block> blocksLevel;

    //constants
    private static final int NUM_OF_BALLS = 10;


    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocities;

    }

    public int paddleSpeed() {
        return Paddle.PADDLE_STEP;

    }
    public int paddleWidth() {
        return GameLevel.PADDLE_WIDTH * 2;

    }
    // the level name will be displayed at the top of the screen.
    public String levelName() {
        return "Wide Easy";
    }
    // Returns a sprite with the background of the level
    public Sprite getBackground() {
        return this.gameBackground;
    }
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks() {
        //counting the removeable blocks in the game
        int counter = 0;
        List<Block> listOfBlocks = createGameLevelBlocks();
        for (Block b : listOfBlocks) {
            for (int i = 0; i < listOfBlocks.size(); i++) {
                b.initializeHitListenersList();
                counter++;
            }
        }
        return listOfBlocks;
    }
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove() {
        return this.blocksLevel.size();
    }

    public List<Block> createGameLevelBlocks() {
        List<Block> blocksArrList = new ArrayList<>();
        //array of colors for the blocks blocks
        Color[] colorArr = {Color.GRAY, Color.GREEN, Color.RED, Color.PINK, Color.YELLOW, Color.BLUE};
        //blocks lines size
        Block[][] arrayOfBlockArr = {new Block[2], new Block[2], new Block[2],
                new Block[3], new Block[2], new Block[2]};
        //x location of the first block
        geometryprimitives.Point upperLeft = new Point(GameLevel.GUI_WIDTH - GameLevel.GUI_BLOCK_EDGE_SIZE
                - Block.BLOCK_WIDTH, Block.BLOCK_Y_START_POINT);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        //initial blocks array
        for (int i = 0; i < arrayOfBlockArr.length; i++) {
            for (int j = 0; j < arrayOfBlockArr[i].length; j++) {
                arrayOfBlockArr[i][j] = new Block(upperLeftX, upperLeftY,
                        Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT);
                arrayOfBlockArr[i][j].setColor(colorArr[i]);
                blocksArrList.add(arrayOfBlockArr[i][j]);

            }
        }
        return blocksArrList;
    }
}
