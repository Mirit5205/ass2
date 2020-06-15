package application;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameelements.Counter;
import interfaces.Animation;

public class EndWinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter scoresCounter;

    public EndWinScreen(KeyboardSensor k, Counter s) {
        this.keyboard = k;
        this.scoresCounter = s;
        this.stop = false;
    }
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is "
                + scoresCounter.getValue(), 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    public boolean shouldStop() {
        return this.stop;
    }
}
