import application.*;
import biuoop.GUI;
import interfaces.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;


/**
 * author hezi yaffe 208424242.
 */
public class Ass5Game {
    /**
     * initialize and run the game.
     * @param args not used.
     */
    public static void main(String[] args) {
        //KeyboardSensor k = GUI.getKeyboardSensor();
        //AnimationRunner ar = new AnimationRunner();
        LevelInformation level1 = new WideEasy();
        LevelInformation level2 = new DirectHit();
        LevelInformation level3 = new Green3();
        LevelInformation level4 = new FinalFour();
        List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
        levelsList.add(level1);
        //levelsList.add(level2);
        levelsList.add(level3);
        levelsList.add(level4);
        GameFlow game = new GameFlow();
        game.runLevels(levelsList);

        }

}
