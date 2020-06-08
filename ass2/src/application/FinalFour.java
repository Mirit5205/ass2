package application;

import collidefeatures.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;

import java.util.List;

public class FinalFour implements LevelInformation {
    private int ballsNum;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite gameBackground;
    private List<Block> blocksLevel;

    public int numberOfBalls() {
        return this.ballsNum;
    }
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocities;

    }

    public int paddleSpeed() {
        return this.paddleSpeed;

    }
    public int paddleWidth() {
        return this.paddleWidth;

    }
    // the level name will be displayed at the top of the screen.
    public String levelName() {
        return this.levelName;
    }
    // Returns a sprite with the background of the level
    public Sprite getBackground() {
        return this.gameBackground;
    }
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks() {
        return this.blocksLevel;

    }
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove() {
        return this.blocksLevel.size();
    }
}
