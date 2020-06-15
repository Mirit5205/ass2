package application;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameelements.Counter;
import interfaces.LevelInformation;
import scores.ScoreIndicator;

import java.util.List;

public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter scoresCounter;
    private GUI gui;


    public GameFlow() {
        //this.animationRunner = ar;
    }//, ...) { ... }

    /**
     * initialize score Indicator with game's score counter.
     * @return score indicator.
     */
    public void initializeScoreCounter() {
        this.scoresCounter = new Counter(0);
    }

    public void initializeKeyboardSensor() {
        this.keyboardSensor = gui.getKeyboardSensor();
    }

    public void initializeGui() {
        this.gui = new GUI("Arkanoid", GameLevel.GUI_WIDTH, GameLevel.GUI_HEIGHT);
    }

    public void initializeAnimationRunner() {
         this.animationRunner = new AnimationRunner();
    }

    public boolean isWin(int listSize, int currentLevel, GameLevel l) {
        return listSize == currentLevel && l.getRemainingBlocksCounter().getValue() == -1;
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
        initializeScoreCounter();
        initializeAnimationRunner();
        initializeGui();
        initializeKeyboardSensor();
        int currentLevel = 0;

        for (LevelInformation levelInfo : levels) {
            currentLevel++;

            GameLevel level = new GameLevel(levelInfo, this.scoresCounter,
                    this.keyboardSensor, this.animationRunner, this.gui);

            level.initialize();

            while ((level.getRemainingBlocksCounter().getValue() != -1) &&
                    (level.getRemainingBallsCounter().getValue() != 0)) {
                level.run();
            }

            if (isWin(levels.size(), currentLevel, level)) {
                this.animationRunner.run(new EndWinScreen(this.keyboardSensor, this.scoresCounter));
                this.gui.close();
            }


            if (level.getRemainingBallsCounter().getValue() == 0) {
                break;
            }

        }
    }
}
