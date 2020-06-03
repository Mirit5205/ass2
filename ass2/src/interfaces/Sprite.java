package interfaces;

import biuoop.DrawSurface;

/**
 * author hezi yaffe 208424242.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d is the drawing surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
