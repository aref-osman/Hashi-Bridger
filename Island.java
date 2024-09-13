public class Island {

    private int x;
    private int y;
    private int num;
    // N --> 0 and W --> 3, clockwise 0-3
    // weighting: 1-> single and 2-> double
    private int[] bridges = new int[4];


    public Island(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }

    // get island co-ordinates
    public int[] getCoordinates(){
        int a[2];
        a[0] = x; a[1] = y;
        return a;
    }

    // get total number of bridges that need to be built from this island
    public int getTotalBridgeCount(){
        return num;
    }
    
    // get number of bridges already built
    public int getTotalBridgesBuiltCount(){
        int x = 0;
        for (int i : bridges) {x += bridges[i];}
        return x;
    }

    // get number of bridges yet to be built
    public int getRemainingBridgeCount(){
        return (num - getTotalBridgesBuiltCount());
    }

    // get number of bridges built in specific direction
    public int getBridgeCount(int direction){
        return bridges[direction];
    }
}
