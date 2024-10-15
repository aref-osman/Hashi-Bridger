import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Island {

    private int WIDTH = 6;
    private int HEIGHT = 9;

    // island-specific details
    private IslandCoordinate islandCoordinate;
    private int num;

    
    // weighting: 1-> single and 2-> double (value in inner ArrayList)
    private ArrayList<IslandCoordinate> adjIslandCoordinates = new ArrayList<IslandCoordinate>(4);
    private ArrayList<Integer> adjIslandWeightings = new ArrayList<Integer>(4);

    // default constructor when creating an island; coordinate & number needed
    public Island(int r, int c, int num, int[][] intGrid) {
        
        this.islandCoordinate = new IslandCoordinate(r, c);
        this.num = num;

        // get the column and row data points of the island
        int rCount = 0;
        int cCount = 0;
        int[] rowData = new int[WIDTH];
        int[] colData = new int[HEIGHT];
        
        for (int row = 0; row < intGrid.length; row++) {
            for (int col = 0; col < intGrid[0].length; col++) {
                // row of data
                if (r == row) {
                    rowData[rCount] = intGrid[row][col];
                    rCount++;
                }
                // column of data
                if (c == col) {
                    colData[cCount] = intGrid[row][col];
                    cCount++;
                }   
            }
        }

        // find adjacencies
        int thisIslandNum; // temp variable

        // north
        int northernAdjacencyRow = -1;
        int northernAdjacencyCol = -1;
        for (int n = 0; n <= r - 2; n++) { // start 2 rows above and keep going up
            thisIslandNum = intGrid[n][c];
            if (thisIslandNum != 0) {
                northernAdjacencyRow = n;
                northernAdjacencyCol = c;
            }
        }

        // east
        int easternAdjacencyRow = -1;
        int easternAdjacencyCol = -1;
        for (int e = WIDTH - 1; e >= c + 2; e--) {
            thisIslandNum = intGrid[r][e];
            if (thisIslandNum != 0) {
                easternAdjacencyCol = e;
                easternAdjacencyRow = r;
            }
        }

        // south
        int southernAdjacencyRow = -1;
        int southernAdjacencyCol = -1;
        for (int s = HEIGHT - 1; s >=  r + 2; s--) {
            thisIslandNum = intGrid[s][c];
            if (thisIslandNum != 0) {
                southernAdjacencyRow = s;
                southernAdjacencyCol = c;
            }
        }

        // west
        int westernAdjacencyRow = -1;
        int westernAdjacencyCol = -1;
        for (int w = 0; w <= c - 2; w++) {
            thisIslandNum = intGrid[r][w];
            if (thisIslandNum != 0) {
                westernAdjacencyCol = w;
                westernAdjacencyRow = r;
            }
            
        }

        IslandCoordinate northCoordinate = new IslandCoordinate(northernAdjacencyRow, northernAdjacencyCol);
        IslandCoordinate eastCoordinate = new IslandCoordinate(easternAdjacencyRow, easternAdjacencyCol);
        IslandCoordinate southCoordinate = new IslandCoordinate(southernAdjacencyRow, southernAdjacencyCol);
        IslandCoordinate westCoordinate = new IslandCoordinate(westernAdjacencyRow, westernAdjacencyCol);
        
        // finish initialisation of adjacent islands (add them all to ArrayList)
        List<IslandCoordinate> l = Arrays.asList(northCoordinate, eastCoordinate, southCoordinate, westCoordinate);
        adjIslandCoordinates.addAll(l);

        // initialise bridge weightings with no bridges in all directions
        for (int i = 0; i < 4; i++) {
            adjIslandWeightings.add(0);
        }
        
    }

    // get island co-ordinates
    public int getRow(){
        return islandCoordinate.getRow();
    }
    public int getCol(){
        return islandCoordinate.getCol();
    }
    public IslandCoordinate getCoordinates(){
        return islandCoordinate;
    }

    // get total number of bridges that need to be built from this island
    public int getTargetBridgeCount(){
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
    public int getBridgesBuiltInDirection(int direction){
        return adjIslandWeightings.get(direction);
    }

    // check if coordinate of adj island exists in specific direction (before retrieving, in case null)
    public boolean checkAdjIslandExists(int direction) {
        // invalid or non-existing co-ordinates have co-ordinates (-1, -1), as all row and col values are >= 0
        return (adjIslandCoordinates.get(direction).getRow() != -1);
    }
    // get coordinate of adj island in specific direction
    public IslandCoordinate getAdjIslandCoordinate(int direction){
        return adjIslandCoordinates.get(direction);
    }
    // get number of adjacent islands
    public int getNumberofAdjIslands(){
        int adjIslandCount = 0;
        for (int d = 0; d < 4; d++) {
            if (checkAdjIslandExists(d)) {
                adjIslandCount++;
            }
        }
        return adjIslandCount;
    }

    // build a (half) bridge from an island
    // other half (connection to the other island is to be built separately)
    public void buildBridge(int direction, int weight){
        adjIslandWeightings.set(direction, weight);
    }
}
