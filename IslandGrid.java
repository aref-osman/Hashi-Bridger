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

        // find and store all corner islands
        getCornerIslands();
    }

    // get corner island co-ordinates (function only called inside constructor)
    private void getCornerIslands() {
        ArrayList<IslandCoordinate> cornerIslandCoordinates = new ArrayList<IslandCoordinate>();
        int targetRow = -1;
        int targetCol = -1;

        // 1: top left 1 (topmost in left column)
        // cycle through grid top down then right and stop at the first value
        for (int col = 0; col < WIDTH; col++) {
            for (int row = 0; row < HEIGHT; row++) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    // add this island to the list of corner islands
                    cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    // break loop after the first island is found
                    break;
                }
            }
        }

        

        // 2: top left 2 (leftmost in top row)
        // cycle through grid from left to right then down and stop at the first value
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    // check if this island is also the topmost in the left column. if not, then add it
                    if (!thisIsland.getCoordinates().equals(cornerIslandCoordinates.get(cornerIslandCoordinates.size()-1))) {
                        // add this island to the list of corner islands
                        cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    }
                    // break loop after the first island is found
                    break;
                }
            }
        }

        // 3: top right 1 (rightmost in top row)
        // cycle through grid right to left then down and stop at the first value
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = WIDTH - 1; col >= 0; col--) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    // add this island to the list of corner islands
                    cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    // break loop after the first island is found
                    break;
                }
            }
        }

        // 4: top right 2 (topmost in right column)
        // cycle through grid top down then left and stop at the first value
        for (int col = WIDTH - 1; col >= 0; col--) {
            for (int row = 0; row < HEIGHT; row++) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    if (!thisIsland.getCoordinates().equals(cornerIslandCoordinates.get(cornerIslandCoordinates.size()-1))) {
                        // add this island to the list of corner islands
                        cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    }
                    // break loop after the first island is found
                    break;
                }
            }
        }

        // 5: bottom right 1 (bottommost in right column)
        // cycle through grid down up then left and stop at the first value
        for (int col = WIDTH - 1; col >= 0; col--) {
            for (int row = HEIGHT - 1; row >= 0; row--) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    // add this island to the list of corner islands
                    cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    // break loop after the first island is found
                    break;
                }
            }
        }
        
        // 6: bottom right 2 (rightmost in bottom row)
        // cycle through grid right to left then up and stop at the first value
        for (int row = HEIGHT - 1; row >= 0; row--) {
            for (int col = WIDTH - 1; col >= 0; col--) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    if (!thisIsland.getCoordinates().equals(cornerIslandCoordinates.get(cornerIslandCoordinates.size()-1))) {
                        // add this island to the list of corner islands
                        cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    }
                    // break loop after the first island is found
                    break;
                }
            }
        }

        // 7: bottom left 1 (leftmost in bottom row)
        // cycle through grid left to right then up and stop at the first value
        for (int row = HEIGHT - 1; row >= 0; row--) {
            for (int col = 0; col < WIDTH; col++) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    // add this island to the list of corner islands
                    cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    // break loop after the first island is found
                    break;
                }
            }
        }

        // 8: bottom left 2 (bottommost in left column)
        // cycle through grid down up then right and stop at the first value
        for (int col = 0; col < WIDTH; col++) {
            for (int row = HEIGHT - 1; row >= 0; row--) {
                Island thisIsland = grid[row][col];
                if (thisIsland != null) {
                    // if island found, this is the first island
                    targetRow = thisIsland.getRow();
                    targetCol = thisIsland.getCol();
                    if (!thisIsland.getCoordinates().equals(cornerIslandCoordinates.get(cornerIslandCoordinates.size()-1))) {
                        // add this island to the list of corner islands
                        cornerIslandCoordinates.add(new IslandCoordinate(targetRow, targetCol));
                    }
                    // break loop after the first island is found
                    break;
                }
            }
        }

        // add all island types to arraylist of islands
        for (IslandCoordinate i : cornerIslandCoordinates) {
            cornerIslands.add(grid[i.getRow()][i.getCol()]);
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
    // build a bridge from an island to its adjacent island in a direction?

    // build independently buildable bridges (can be built as the first bridge)
    // consult planning doc

    // check if path for bridge is possible (empty)

}

