package application;

import collidefeatures.Velocity;
import geometryprimitives.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;
import sprites.Paddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinalFour implements LevelInformation {
    private int ballsNum;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite gameBackground;
    private List<Block> blocksLevel;
    private Color backgroundColor = BACKGROUND_COLOR;
    private static final Color BACKGROUND_COLOR = new Color(10, 100, 220);
    private static final int NUM_OF_BLOCKS = 90;
    private static final int NUM_OF_BALLS = 3;
    private static final Double BLOCK_WIDTH = 50.667;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_LINE_SIZE = 15;

    public List<geometryprimitives.Point> arrangeBallsOnTopOfPaddle() {
        List<Point> ballsCenters = new ArrayList<Point>();
        ballsCenters.add(new Point(350 , 400));
        ballsCenters.add(new Point(450 , 400));
        ballsCenters.add(new Point(400 , 450));
        return ballsCenters;
    }

    public Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities() {
        List<Velocity> BallVelocities = new ArrayList<Velocity>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            BallVelocities.add(Velocity.fromAngleAndSpeed(320, 10));
        }
        return BallVelocities;
    }

    public Color[] getBallsColor() {
        Color[] ballsColors = new Color[NUM_OF_BALLS];
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            ballsColors[i] = new Color(255,255,255);
        }
        return ballsColors;
    }

    public int paddleSpeed() {
        return Paddle.PADDLE_STEP * 3;

    }
    public int paddleWidth() {
        return GameLevel.PADDLE_WIDTH;

    }
    // the level name will be displayed at the top of the screen.
    public String levelName() {
        return "FinallFour";
    }
    // Returns a sprite with the background of the level
    public Sprite getBackground() {
        Block backgroundBlock = new Block(new Point(GameLevel.GUI_UPPER_LEFT_X,
                GameLevel.GUI_UPPER_LEFT_Y), GameLevel.GUI_WIDTH, GameLevel.GUI_HEIGHT);
        backgroundBlock.setColor(this.backgroundColor);
        return backgroundBlock;
    }

    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks() {
        List<Block> listOfBlocks = createGameLevelBlocks();
        for (Block b : listOfBlocks) {
            for (int i = 0; i < listOfBlocks.size(); i++) {
                b.initializeHitListenersList();
            }
        }
        return listOfBlocks;
    }
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }

    public List<Sprite> getBackgroundSpirtes() {
        return new ArrayList<Sprite>();
    }
    public List<Block> createGameLevelBlocks() {
        List<Block> blocksArrList = new ArrayList<>();
        Color[] blocksColors = {Color.BLUE, Color.YELLOW, Color.PINK,
                Color.GREEN, Color.RED, Color.orange};

        Block[][] arrayOfBlockArr = {new Block[BLOCK_LINE_SIZE], new Block[BLOCK_LINE_SIZE],
                new Block[BLOCK_LINE_SIZE], new Block[BLOCK_LINE_SIZE], new Block[BLOCK_LINE_SIZE],
                new Block[BLOCK_LINE_SIZE]};
        Point upperLeft = new Point(GameLevel.GUI_WIDTH - GameLevel.GUI_BLOCK_EDGE_SIZE
                - BLOCK_WIDTH, Block.BLOCK_Y_START_POINT);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        //initial blocks array
        for (int i = 0; i < arrayOfBlockArr.length; i++) {
            for (int j = 0; j < arrayOfBlockArr[i].length; j++) {
                arrayOfBlockArr[i][j] = new Block(upperLeftX, upperLeftY,
                        BLOCK_WIDTH, BLOCK_HEIGHT);
                arrayOfBlockArr[i][j].setColor(blocksColors[i]);
                //gap between blocks
                upperLeftX -= BLOCK_WIDTH;
                blocksArrList.add(arrayOfBlockArr[i][j]);
            }
            //initial x location of the first block in every line
            // (we modify it in the inner loop)
            upperLeftX = upperLeft.getX();
            //initial y location of the first block in every line
            upperLeftY = upperLeftY + BLOCK_HEIGHT;

        }
        return blocksArrList;

    }
}
