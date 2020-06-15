package application;

import collidefeatures.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DirectHit implements LevelInformation {
    private int ballsNum;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite gameBackground;
    private List<Block> blocksLevel;
    private Color backgroundColor = BACKGROUND_COLOR ;

    //level constants
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
    private static final int NUM_OF_BALLS = 1;
    private static final int NUM_OF_BLOCKS = 1;
    private static final int BLOCK_WIDTH = (GameLevel.GUI_WIDTH
            - GameLevel.GUI_BLOCK_EDGE_SIZE * 2) / 8;
    private static final int BLOCK_HEIGHT = (GameLevel.GUI_HEIGHT
            - GameLevel.GUI_BLOCK_EDGE_SIZE * 2) / 15;
    public static final Color BALLS_COLOR = new Color(255, 255, 255);

    public List<geometryprimitives.Point> arrangeBallsOnTopOfPaddle() {
        List<Point> ballsCenters = new ArrayList<Point>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            ballsCenters.add(new Point(400, 400));
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
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            ballsColors[i] = BALLS_COLOR;
        }
        return ballsColors;
    }
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities() {
        List<Velocity> BallVelocities = new ArrayList<Velocity>();
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            BallVelocities.add(Velocity.fromAngleAndSpeed(280, 10));
        }
        return BallVelocities;
    }

    public int paddleSpeed() {
        return Paddle.PADDLE_STEP;
    }

    public int paddleWidth() {
        return GameLevel.PADDLE_WIDTH;

    }
    // the level name will be displayed at the top of the screen.
    public String levelName() {
        return "Direct Hit";
    }

    // Returns a sprite with the background of the level
    public List<Sprite> getBackgroundSpirtes() {
        List<Sprite> spritesList = new ArrayList<Sprite>();
        Color[] greenArr = new Color[3];
        Rectangle[] rectangles = new Rectangle[3];
        Block[] lineBlocks = new Block[2];
        Color blocksColor = new Color(0, 255, 0);
        lineBlocks[0] =  new Block(GameLevel.GUI_WIDTH / 2 - 150, GameLevel.GUI_HEIGHT / 2,
                300, 1);
        lineBlocks[1] =  new Block(GameLevel.GUI_WIDTH / 2, GameLevel.GUI_HEIGHT / 2 - 150,
                1, 300);
        lineBlocks[0].setColor(blocksColor);
        lineBlocks[1].setColor(blocksColor);
        spritesList.add(lineBlocks[0]);
        spritesList.add(lineBlocks[1]);


        for (int i = 0; i < greenArr.length; i++) {
            greenArr[i] = new Color(0, 255 - 20 * i, 0);
            rectangles[i] = new Rectangle(new Point(250 + 20 * i , 150 + 20
            * i), 300 - 40 * i,
                    300 - 40 * i, greenArr[i] );
            spritesList.add(rectangles[i]);
        }
        return spritesList;
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
        //for (Block b : listOfBlocks) {
            for (int i = 0; i < listOfBlocks.size() - 5; i++) {
                listOfBlocks.get(i).initializeHitListenersList();
            }
        //}
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

        //create array of random colors
        for (int i = 0; i < colorsArr.length; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colorsArr[i] = new Color(r, g, b);

        }

        //x location of the first block
        geometryprimitives.Point upperLeft = new Point(GameLevel.GUI_WIDTH / 2 - BLOCK_WIDTH / 2
                ,GameLevel.GUI_HEIGHT / 2 - BLOCK_HEIGHT);
        double upperLeftX = upperLeft.getX();
        double upperLeftY = upperLeft.getY();
        //create destroyable blocks
        for (int i = 0; i < arrayOfBlocks.length; i++) {
            arrayOfBlocks[i] = new Block(upperLeftX, upperLeftY,
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            arrayOfBlocks[i].setColor(colorsArr[i]);
            blocksArrList.add(arrayOfBlocks[i]);
        }

        //create undestroyable blocks
        Block b1 = new Block(upperLeftX - 1, upperLeftY,
               1, BLOCK_HEIGHT);
        Block b2 = new Block(upperLeftX + BLOCK_WIDTH, upperLeftY,
                1, BLOCK_HEIGHT);
        Block b3 = new Block(upperLeftX, upperLeftY - 1,
                BLOCK_WIDTH, 1);
        Block b4 = new Block(upperLeftX, upperLeftY + BLOCK_HEIGHT ,
                BLOCK_WIDTH / 3, 1);
        Block b5 = new Block(upperLeftX + 2 * BLOCK_WIDTH / 3, upperLeftY + BLOCK_HEIGHT,
                BLOCK_WIDTH / 3, 1);
        Block[] arrOfShiledBlocks = new Block[]{b1, b2, b3, b4, b5};

        for (int i = 0; i < arrOfShiledBlocks.length; i++) {
            arrOfShiledBlocks[i].setColor(BACKGROUND_COLOR);
            blocksArrList.add(arrOfShiledBlocks[i]);
        }

        return blocksArrList;
    }
}
