import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite>  spritesList;

    //a getter for spritesList variable
    public List<Sprite> getSpritesList() {
        return this.spritesList;
    }
    //initialize spirit list
    public void initializeSpiritList() {
        this.spritesList = new ArrayList<Sprite>();
    }
    public void addSprite(Sprite s) {
        if (this.spritesList == null) {
        List<Sprite> spritesList = new ArrayList<Sprite>();
        spritesList.add(s);
        } else {
            this.spritesList.add(s);
        }
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
       for(Sprite s : this.spritesList) {
           s.timePassed();
       }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for(Sprite s : this.spritesList) {
            s.drawOn(d);
        }
    }
}
