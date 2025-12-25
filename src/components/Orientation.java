package components;

public enum Orientation {
    VERTICAL(0),
    HORIZONTAL(1);

    private final int value;

    // constructor
    private Orientation(int value) {this.value = value;}
    
    public int value() {return value;}
    public Orientation opposite() {return Orientation.values()[1 - value];}
}
