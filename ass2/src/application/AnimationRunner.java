package application;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    //setters
    public void setGui(GUI g) {
        this.gui = g;
    }

    public void setFramesPerSecond(int frames) {
        this.framesPerSecond = frames;
    }
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            Sleeper sleeper = new Sleeper();
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
