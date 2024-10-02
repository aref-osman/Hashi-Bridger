import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class IslandGrid {

    // grid dimensions
    // minimum dimensions is 5 by 5
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

    // store corner islands
    private ArrayList<Island> cornerIslands = new ArrayList<Island>();
    
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
    }

    // get corner island co-ordinates (function only called inside constructor)
    private void getCornerIslands(){
        ArrayList<IslandCoordinate> cornerIslandCoordinates = new ArrayList<IslandCoordinate>();
        int targetRow;
        int targetCol;

        // top left 1 (topmost in left column)
        // first, find smallest col number, and amongst all the islands that share that
        //  number, find the island that has the smallest row number
        targetCol = cols.get(0);
        targetRow = cols.get(0);
        for (int l = 0; l < ISLAND_COUNT; l++) {
            // if a lowest col number is found
            if (cols.get(l) < targetCol || (cols.get(l) == targetCol && rows.get(l) < targetRow)) {
                targetCol = cols.get(l);
                targetRow = rows.get(l);
            }
        }

        // top left 2 (leftmost in top row)
        // top right 1 (rightmost in top row)
        // top right 2 (topmost in right column)
        // bottom right 1 (bottommost in right column)
        // bottom right 2 (rightmost in bottom row)
        // bottom left 1 (leftmost in bottom row)
        // bottom left 2 (bottommost in left column)

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
    // build a bridge from an island to its adjacent island in a direction?

    // build independently buildable bridges (can be built as the first bridge)
    // consult planning doc

    // check if path for bridge is possible (empty)

}

