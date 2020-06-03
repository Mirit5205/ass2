package interfaces;

/**
 * author hezi yaffe 208424242.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl is the listener we want to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl is the listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}
