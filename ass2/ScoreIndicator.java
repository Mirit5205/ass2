import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    public ScoreIndicator(Counter score) {
        this.scoreCounter = score;
    }

    public void drawOn(DrawSurface surface) {
        Block scoreBlock = Block.createScoreBlock();
        Point upperLeft = scoreBlock.getCollisionRectangle().getUpperLeft();
        double height = scoreBlock.getCollisionRectangle().getHeight();
        double width = scoreBlock.getCollisionRectangle().getWidth();
        //location of the hit counter
        int scoreCounterXLocation = (int) (upperLeft.getX() + width / 2 - 50);
        int scoreCounterYLocation = (int) (upperLeft.getY() + height / 2 + 2);
        String scoreValue = Integer.toString(this.scoreCounter.getValue());
        //assign color variable the block color
        Color c = scoreBlock.getCollisionRectangle().getColor();
        //draw the block edges in black
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
        //draw the block according to his color
        surface.setColor(c);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
        //draw the score value in black
        surface.setColor(Color.black);
        surface.drawText(scoreCounterXLocation, scoreCounterYLocation, "score:" +" " + scoreValue, 20);
    }

    public void timePassed() {
        ;
    }


}
