public class Counter {
    private int counter;

    //constructor
    public Counter(int numberOfBlocks) {
        this.counter = numberOfBlocks;
    }
    // add number to current count.
    void increase(int number) {
        this.counter += number;
    }

    // subtract number from current count.
    void decrease(int number) {
        this.counter -= number;

    }

    // get current count.
    int getValue() {
        return this.counter;
    }
}
