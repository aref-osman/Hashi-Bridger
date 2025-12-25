package components;

public enum CardinalDirection {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int value;

    // constructor
    private CardinalDirection(int value) {this.value = value;}

    public int value() {return value;}
    public CardinalDirection opposite() {return CardinalDirection.values()[(value + 2) % 4];}
    public Orientation getOrientation() {
        return switch (this) {case NORTH -> Orientation.VERTICAL; case EAST -> Orientation.HORIZONTAL;
            case SOUTH -> Orientation.VERTICAL; case WEST -> Orientation.HORIZONTAL;};}
}
