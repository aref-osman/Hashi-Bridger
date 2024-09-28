import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class IslandGrid {

    // minimum dimensions is 5 by 5
    private int WIDTH = 6;
    private int HEIGHT = 9;
    private int[][] intGrid = new int[HEIGHT][WIDTH];
    private Island[][] grid = new Island[HEIGHT][WIDTH];
    private int ISLAND_COUNT = 0;
    private ArrayList<Integer> rows = new ArrayList<Integer>();
    private ArrayList<Integer> cols = new ArrayList<Integer>();
    private ArrayList<Integer> nums = new ArrayList<Integer>();
    private ArrayList<Integer> islandsPerRow = new ArrayList<Integer>(WIDTH);
    private ArrayList<Integer> islandsPerCol = new ArrayList<Integer>(HEIGHT);
    
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
            File myObj = new File("islandData.txt");
            Scanner myReader = new Scanner(myObj);
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
                Island newIsland = new Island(rows.get(i), cols.get(i), nums.get(i), intGrid);
                grid[rows.get(i)][cols.get(i)] = newIsland;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Oops! No file found!");
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

    public void printGrid(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getTotalBridgeCount() + " ");    
                } else {
                    System.out.print("-1 ");
                }
            }
            System.out.println();
        }
    }

    // public void populateIslandsWithAdjacentCoordinates() {
    //     // IMPORTANT condition: islands cannot be directly above/below or directly to the right/left of another island
    //     // or to rephrase: minimum gridspace between two islands is 2
    //     int x;
    //     int y;
    //     for (int i = 0; i < HEIGHT; i++) {
    //         for (int j = 0; j < WIDTH; j++) {
    //             // check if island is null before trying to find its adjacencies    
    //             if (grid[i][j] != null) {

    //                 Island thisIsland = grid[i][j];
    //                 x = thisIsland.getXOrdinate();
    //                 y = thisIsland.getYOrdinate();

    //                 // check north (only if island is not already in top row)
    //                 if (y != 0 || y != 1) {
    //                     // go through all grid spots above island, starting from the island, then upwards
    //                     for (int northernIsland = y - 1; northernIsland >= 0; northernIsland--) {
    //                         if (grid[northernIsland][x] != null) {
    //                             IslandCoordinate adjIslandCoordinate = new IslandCoordinate(x, northernIsland);
    //                             thisIsland.addAdjIslandCoordinates(adjIslandCoordinate, 0);
    //                             // once an adjacent island is found 
    //                         }
    
    //                     }
    //                 }
    //             } 
    //         }
    //     }
    // }
        
}

