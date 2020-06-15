package sprites;

import biuoop.DrawSurface;
import geometryprimitives.Point;
import interfaces.Sprite;

public class SomethingCool implements Sprite {
    public Ball sun;

    public SomethingCool (Ball b) {
        sun = b;
    }
    public void drawOn(DrawSurface d) {
        this.sun.drawOn(d);
    }

    public void timePassed() {
            this.sun.setCenter(new Point(this.sun.getCenter().getX(),
                    this.sun.getCenter().getY() - 5));
    }
}
