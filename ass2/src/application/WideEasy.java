package application;

import collidefeatures.Velocity;
import geometryprimitives.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WideEasy implements LevelInformation {
    private Color backgroundColor = BACKGROUND_COLOR;

    //level constants
    private static final int NUM_OF_BALLS = 10;
    private static final int BLOCK_Y_START_POINT = 250;
    private static final int BLOCK_WIDTH = (GameLevel.GUI_WIDTH
            - GameLevel.GUI_BLOCK_EDGE_SIZE * 2) / 10;
    private static final int BLOCK_HEIGHT = (GameLevel.GUI_HEIGHT
            - GameLevel.GUI_BLOCK_EDGE_SIZE * 2) / 20;
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private static final int BALLS_RADIUS = 8;
    private static final int NUM_OF_BLOCKS = 10;


    public List<Point> arrangeBallsOnTopOfPaddle() {
        List<Point> ballsCenters = new ArrayList<Point>();
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            ballsCenters.add(new Point(255 + 30 * i, 400 - 10 * i));
            ballsCenters.add(new Point(435 + 30 * i, 360 + 10 * i));
        }
        return ballsCenters;
    }
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    public Color[] getBallsColor() {
        Color[] ballsColors = new Color[NUM_OF_BALLS];
        Random rand = new Random();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            ballsColors[i] = new Color (r, g, b);
        }
        return ballsColors;
    }
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities() {
        List<Velocity> BallVelocities = new ArrayList<Velocity>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            BallVelocities.add(Velocity.fromAngleAndSpeed(280, 12));
        }
        return BallVelocities;

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
    public List<Sprite> getBackgroundSpirtes() {
        List<Sprite> spritesList = new ArrayList<Sprite>();
        Color[] yellowArr = new Color[3];
        Color[] grayArr = new Color[3];
        Ball[] sunCircles = new Ball[3];
        Ball[] cloudCircles = new Ball[3];
        Block[] dropsBlocks = new Block[10];
        Color dropsColor = new Color(51, 187,255);
        for (int i = 1; i <= dropsBlocks.length / 2; i++) {
            dropsBlocks[i - 1] = new Block(650, 140 + 15 * i,1, 2 );
            dropsBlocks[dropsBlocks.length - i] =
                    new Block(670, 150 + 15 * i,1, 2 );
            dropsBlocks[i - 1].setColor(dropsColor);
            dropsBlocks[dropsBlocks.length - i].setColor(dropsColor);
            spritesList.add(dropsBlocks[i - 1]);
            spritesList.add(dropsBlocks[dropsBlocks.length - i]);
        }

        for (int i = 0; i < yellowArr.length; i++) {
            yellowArr[i] = new Color(255, 255 - 20 * i, 0);
            grayArr[i] = new Color(112 + 10 * i,
                    128 + 10 * i , 144 + 10 * i);
            sunCircles[i] = new Ball(150, 100, 35 - 5 * i, yellowArr[i] );
            cloudCircles[i] = new Ball(650 + 10 * i, 100 + 2 * i,
                    30 + 2 * i, grayArr[i] );
            spritesList.add(sunCircles[i]);
            spritesList.add(cloudCircles[i]);
        }
        return spritesList;
    }

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

    public List<Block> createGameLevelBlocks() {
        List<Block> blocksArrList = new ArrayList<>();
        Block[] arrayOfBlocks = new Block[NUM_OF_BLOCKS];
        Color[] colorsArr = new Color[arrayOfBlocks.length];
        Random rand = new Random();
        for (int i = 0; i < colorsArr.length; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colorsArr[i] = new Color(r, g, b);

        }
        //x location of the first block
        geometryprimitives.Point upperLeft = new Point(GameLevel.GUI_WIDTH - GameLevel.GUI_BLOCK_EDGE_SIZE
                - BLOCK_WIDTH, BLOCK_Y_START_POINT);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        //initial blocks array
        for (int i = 0; i < arrayOfBlocks.length; i++) {
                arrayOfBlocks[i] = new Block(upperLeftX, upperLeftY,
                        BLOCK_WIDTH, BLOCK_HEIGHT);
                arrayOfBlocks[i].setColor(colorsArr[i]);
                blocksArrList.add(arrayOfBlocks[i]);
                upperLeftX -= BLOCK_WIDTH;
            }
        return blocksArrList;
    }
}
