import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;



public class IslandGrid {

    // grid dimensions
    // minimum dimensions is 5 by 5
    // islandData.txt must be ordered across then down (pre-process if necessary)
    private int WIDTH = 6;
    private int HEIGHT = 9;

    // number/Island-type grids
    private int[][] intGrid = new int[HEIGHT][WIDTH];
    private Island[][] grid = new Island[HEIGHT][WIDTH];

    // ArrayLists storing the grid info, each by a specific info type (row, col, num etc...)
    private int ISLAND_COUNT = 0;
    private ArrayList<Integer> rows = new ArrayList<Integer>();
    private ArrayList<Integer> cols = new ArrayList<Integer>();
    private ArrayList<Integer> nums = new ArrayList<Integer>();
    private ArrayList<Integer> islandsPerRow = new ArrayList<Integer>(WIDTH);
    private ArrayList<Integer> islandsPerCol = new ArrayList<Integer>(HEIGHT);
    
    // store built bridges into separate ArrayList
    // inside arraylist is per col/row
    // outside arraylist is the list of rows/cols
    private ArrayList<ArrayList<Integer>> horizontalBridges = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> verticalBridges = new ArrayList<ArrayList<Integer>>();

    // store islands by number of adjacencies (stored info is island position in stored lists)
    private ArrayList<Integer> oneAdjacencyIslands = new ArrayList<Integer>();
    private ArrayList<Integer> twoAdjacencyIslands = new ArrayList<Integer>();
    private ArrayList<Integer> threeAdjacencyIslands = new ArrayList<Integer>();
    private ArrayList<Integer> fourAdjacencyIslands = new ArrayList<Integer>();
    
    // constructor
    public IslandGrid(){
        // initialise islandsPerRow and islandsPerCol to contain the intended number of elements
        for (int i = 0; i < HEIGHT; i++) {
            islandsPerRow.add(0);
        }
        for (int i = 0; i < WIDTH; i++) {
            islandsPerCol.add(0);
        }

        int r = 0;
        int c = 0;
        int n = 0;

        // try importing all island data and create islands, adding them to a temporary int grid
        try {
            // import file and open scanner
            File myObj = new File("islandData.txt");
            Scanner myReader = new Scanner(myObj);
            // iterate through each line in text file
            while (myReader.hasNextLine()) {
                // extract island info (location & bridge number)
                String data = myReader.nextLine();
                String[] island = data.split(" ");
                r = Integer.parseInt(island[0]); // row
                c = Integer.parseInt(island[1]); // col
                n = Integer.parseInt(island[2]);

                // gradually populate integer grid values, once per island
                intGrid[r][c] = n;

                // store island data in separate arraylists
                rows.add(r);
                cols.add(c);
                nums.add(n);
                ISLAND_COUNT++;

                // increment count of islands in row / col matching this island
                islandsPerRow.set(r, islandsPerRow.get(r) + 1);
                islandsPerCol.set(c, islandsPerCol.get(c) + 1);
            }
            // close the reader to prevent memory leak
            myReader.close();
            // populate Island-type grid with Island objects
            for (int i = 0; i < ISLAND_COUNT; i++) {
                grid[rows.get(i)][cols.get(i)] = new Island(rows.get(i), cols.get(i), nums.get(i), intGrid);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Oops! No file found!");
        }

        // store all islands into four lists, by the number of adjacencies
        sortIslandsByAdjacencies();
    }

    // TO DO: FINISH THIS METHOD
    // sort islands into 4 lists, by number of adjacent islands
    private void sortIslandsByAdjacencies() {
        for (int i = 0; i <= ISLAND_COUNT; i++ ) {
            int adjIslandCount = grid[rows.get(i)][cols.get(i)].getNumberofAdjIslands();
            if (adjIslandCount == 1) {
                oneAdjacencyIslands.add(i);
            } else if (adjIslandCount == 2) {
                twoAdjacencyIslands.add(i);
            } else if (adjIslandCount == 3) {
                threeAdjacencyIslands.add(i);
            } else {
                fourAdjacencyIslands.add(i);
            }
        }
    }


    // get island by position (row & col)
    public Island getIsland(int r, int c) {
        return grid[r][c];
    }
    // get island by order (across then down) - IMPORTANT NOTE: position p --> index p - 1
    public Island getIsland(int p){
        return grid[rows.get(p-1)][cols.get(p-1)];
    }

    // get number of islands in each row/col
    public int getNumIslandsInRow(int r){
        return islandsPerRow.get(r);
    }
    public int getNumIslandsInCol(int c){
        return islandsPerCol.get(c);
    }

    // print grid
    public void printGrid(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getTargetBridgeCount() + " ");    
                } else {
                    // empty grid spots (no islands) marked with -1
                    System.out.print("-1 ");
                }
            }
            System.out.println();
        }
    }

    // build a bridge between two islands?
    // step 0: this method should only execute after checking that the bridge is
    // actually possible to build. Otherwise, throw an exception or return false (in the checking method, not this one) 
    // step 1: change both islands' info
    // step 2: change the appropriate bridge arraylist
    public void buildBridge(int positionOne, int positionTwo, int orientation, int weight){
        int directionOne = -1;
        int directionTwo = -1;
        // vertical
        if (orientation == 0){

            // col is the same for both islands
            int thisCol = cols.get(positionOne);
            // rows are different (initialise here, set later)
            int firstRow = -1;
            int lastRow = -1;

            // island one is the bottom island
            if (positionOne > positionTwo) {
                // starting row
                directionTwo = 2; // south
                firstRow = rows.get(positionTwo);

                // ending row
                directionOne = 0; // north
                lastRow = rows.get(positionOne);
            }
            // island one is the top island
            else {
                // starting row
                directionOne = 2; // south
                firstRow = rows.get(directionOne);

                // ending row
                directionTwo = 0; // north
                lastRow = rows.get(directionTwo);
            }

            Integer[] bothRows = new Integer[]{firstRow, lastRow};

            // update bridge list
            verticalBridges.get(thisCol).addAll(Arrays.asList(bothRows));
            Collections.sort(verticalBridges.get(thisCol));
            // THIS SHOULD BE THE END OF VERTICAL BRIDGE BUILDING (check this)
        }
        // horizontal
        else {
            
            // row is the same for both islands
            int thisRow = rows.get(positionOne);
            // cols are different (initialise here, set later)
            int firstCol = -1;
            int lastCol = -1;

            // island one is the right island
            if (positionOne > positionTwo) {
                // starting col
                directionTwo = 1; // east
                firstCol = cols.get(positionTwo);
                
                // ending col
                directionOne = 3; // west
                lastCol = cols.get(positionOne);
            }
            // island one is the left island
            else {
                // starting col
                directionOne = 1; // east
                firstCol = cols.get(positionOne);

                // ending col
                directionTwo = 3; // west
                lastCol = cols.get(positionTwo);
            }

            Integer[] bothCols = new Integer[]{firstCol, lastCol};

            // update bridge list
            horizontalBridges.get(thisRow).addAll(Arrays.asList(bothCols));
            Collections.sort(horizontalBridges.get(thisRow));
            // THIS SHOULD BE THE END OF HORIZONTAL BRIDGE BUILDING (check this)
        }

        // build a half bridge from island one
        grid[rows.get(positionOne)][cols.get(positionOne)].buildBridge(directionOne, weight);
        // build a half bridge from island two
        grid[rows.get(positionTwo)][cols.get(positionTwo)].buildBridge(directionTwo, weight);
        
        // update bridge list (consider moving this up in the code)
        // vertical
    }

    // build a bridge from an island to its adjacent island in a direction?

    // build independently buildable bridges (can be built as the first bridge)
    // consult planning doc

    // check if path for bridge is possible (empty)
    // assumptions: the two islands are adjacent
    // 1) inputs: 

}

