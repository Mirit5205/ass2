package gameelements;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * author hezi yaffe 208424242.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * a getter for spritesList filed.
     * @return spritesList filed.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * initialize spirit list.
     */
    public void initializeSpiritList() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * adding sprite to sprite list.
     * @param s the sprite we want to add to the list.
     */
    public void addSprite(Sprite s) {
        if (this.sprites == null) {
        List<Sprite> spritesList = new ArrayList<Sprite>();
        spritesList.add(s);
        } else {
            this.sprites.add(s);
        }
    }

    /**
     *  notify all the sprites in the list that
     *  time is passed.
     */
    public void notifyAllTimePassed() {
        //call timePassed() on all sprites.
        List<Sprite> spritesDuplicate = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spritesDuplicate) {
           s.timePassed();
       }
    }

    /**
     * draw all the sprites in the list.
     * @param d is the drawing surface.
     */
    public void drawAllOn(DrawSurface d) {
        //call drawOn(d) on all sprites.
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
