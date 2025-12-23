package components;

public enum CardinalDirection {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int value;

    // constructor
    private CardinalDirection(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
