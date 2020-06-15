package application;

import biuoop.DrawSurface;
import gameelements.SpriteCollection;
import interfaces.Animation;
import interfaces.Sprite;

import java.util.List;

public class CountdownAnimation implements Animation {
    private boolean stop;
    private double secondsNumber;
    private int startingCount;
    private int currentCountNum;
    private SpriteCollection screen;
    private List<Sprite> background;

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

    //setters
    public void setBackground(List<Sprite> guiBackground) {
        this.background = guiBackground;
    }
    public void doOneFrame(DrawSurface d) {
        for (Sprite s : this.background) {
            s.drawOn(d);
        }
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
