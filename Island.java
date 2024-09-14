import java.util.ArrayList;

public class Island {

    private IslandCoordinate islandCoordinate;
    private int num;
    // N --> 0 and W --> 3, clockwise 0-3 (position in outer ArrayList)
    // weighting: 1-> single and 2-> double (value in inner ArrayList)
    private ArrayList<IslandCoordinate> adjIslandCoordinates = new ArrayList<IslandCoordinate>(4);
    private ArrayList<Integer> adjIslandWeightings = new ArrayList<Integer>(4);

    // default constructor when creating an island; coordinate & number needed
    public Island(int x, int y, int num) {
        this.islandCoordinate = new IslandCoordinate(x, y);
        this.num = num;
    }

    // get island co-ordinates
    public int getXOrdinate(){
        return islandCoordinate.getXOrdinate();
    }
    public int getYOrdinate(){
        return islandCoordinate.getYOrdinate();
    }


    // get total number of bridges that need to be built from this island
    public int getTotalBridgeCount(){
        return num;
    }
    
    // get number of bridges already built
    public int getTotalBridgesBuiltCount(){
        int n = 0;
        for (int bridgeCount : adjIslandWeightings) {
            n += bridgeCount;
        }
        return n;
    }

    // get number of bridges yet to be built
    public int getRemainingBridgeCount(){
        return (num - getTotalBridgesBuiltCount());
    }

    // get number of bridges built in specific direction
    public int getBridgeCount(int direction){
        return adjIslandWeightings.get(direction);
    }
}
