package interfaces;

import biuoop.DrawSurface;

import java.util.List;

public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}
