package application;

import biuoop.DrawSurface;
import gameelements.SpriteCollection;
import interfaces.Animation;

public class CountdownAnimation implements Animation {
    private boolean stop;
    private double secondsNumber;
    private int startingCount;
    private int currentCountNum;
    private SpriteCollection screen;

    //getters
    public double getSecondsNumber() {
        return secondsNumber;
    }

    public int getStartingCount() {
        return startingCount;
    }

    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen){
        this.secondsNumber = numOfSeconds;
        this.startingCount = countFrom;
        this.screen = gameScreen;
        this.currentCountNum = countFrom;
    }

    public void doOneFrame(DrawSurface d) {
        GameLevel.drawGuiBackground(d);
        this.screen.drawAllOn(d);
        this.stop = false;
        if (currentCountNum == 0) {
            this.stop = true;
        }
        d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                Integer.toString(this.currentCountNum), 45);
        this.currentCountNum--;
    }

    public boolean shouldStop() {
        return this.stop;
    }
}
