package gameelements;

/**
 * author hezi yaffe 208424242.
 */
public class Counter {
    private int counter;

    /**
     * @param count is the initial counter number.
     */
    //constructor
    public Counter(int count) {
        this.counter = count;
    }

    /**
     * add number to current count.
     * @param number is the number we want to add
     * to the current count.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number is the number we want to subtract
     * from the current count.
     */
    public void decrease(int number) {
        this.counter -= number;

    }

    /**
     * get current count.
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}
