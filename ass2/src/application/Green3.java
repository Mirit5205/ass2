package application;

import collidefeatures.Velocity;
import geometryprimitives.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.SomethingCool;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Green3 implements LevelInformation {
    private Color backgroundColor = BACKGROUND_COLOR;

    //constants
    private static final int NUM_OF_BALLS = 5;
    private static final Color BACKGROUND_COLOR = new Color(0, 150, 100);
    private static final int NUM_OF_BLOCKS = 40;
    private static final int BLOCK_WIDTH = 60;
    private static final int BLOCK_HEIGHT = 35;




    public List<Point> arrangeBallsOnTopOfPaddle() {

        List<Point> ballsCenters = new ArrayList<Point>();
        for (int i = 0; i < NUM_OF_BALLS ; i++) {
            ballsCenters.add(new Point(350 + 100 * i, 400));

        }
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
            BallVelocities.add(Velocity.fromAngleAndSpeed(300, 8));
        }
        return BallVelocities;

    }

    public Color[] getBallsColor() {
        Color[] ballsColors = new Color[NUM_OF_BALLS];

        for (int i = 0; i < NUM_OF_BALLS; i++) {
            ballsColors[i] = new Color (0, 0, 0);
        }

        return ballsColors;
    }

    public int paddleSpeed() {
        return Paddle.PADDLE_STEP * 2;

    }
    public int paddleWidth() {
        return GameLevel.PADDLE_WIDTH;

    }
    // the level name will be displayed at the top of the screen.
    public String levelName() {
        return "Green3";
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
        List<Sprite> sprites = new ArrayList<Sprite>();
        Ball[][] lolipopCenterArr = new Ball[5][5];
        Block[][] stemArr = new Block[5][5];
        Ball[][] circels1 = new Ball[5][5];
        Ball[][] circels2 = new Ball[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                lolipopCenterArr[i][j] = new Ball(100 + 150 * i, 100 + 110 * j, 25, Color.YELLOW);
                stemArr[i][j] = new Block(100 + 150 * i, 125 + 110 * j, 2, 30);
                circels1[i][j] = new Ball(100 + 150 * i, 100 + 110 * j, 15, Color.PINK);
                circels2[i][j] = new Ball(100 + 150 * i, 100 + 110 * j, 5, Color.yellow);

                stemArr[i][j].setColor(Color.white);
                sprites.add(lolipopCenterArr[i][j]);
                sprites.add(stemArr[i][j]);
                sprites.add(circels1[i][j]);
                sprites.add(circels2[i][j]);
            }
        }


        //SomethingCool s = new SomethingCool(new Ball(400, 600, 150, Color.red));
        //sprites.add(s);
        return sprites;
    }

    public List<Block> createGameLevelBlocks() {
        List<Block> blocksArrList = new ArrayList<>();
        Color[] blocksColors = new Color[5];
        for ( int i = 0; i < blocksColors.length; i++ ) {
            blocksColors[i] = new Color(0, 150 + 20 * i, 0);
        }
        Block[][] arrayOfBlockArr = {new Block[10], new Block[9], new Block[8],
                new Block[7], new Block[6]};
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
