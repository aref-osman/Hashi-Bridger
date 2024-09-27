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
    
    public IslandGrid(){

        int r = 0;
        int c = 0;
        int num = 0;

        // try importing all island data and create islands, adding them to the grid
        try {
            File myObj = new File("islandData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] b = data.split(" ");

                // basic island info (location & bridge number)
                r = Integer.parseInt(b[0]); // row
                c = Integer.parseInt(b[1]); // col
                num = Integer.parseInt(b[2]);

                // set island number
                intGrid[r][c] = num;
                

            }

            // print grid
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    int specialX = i;
                    int specialY = j;
                    System.out.print(intGrid[specialX][specialY] + " ");
                }
                System.out.println();
            }

            // close the reader to prevent memory leak
            myReader.close();

            // initialise variable here to save memory (to avoid re-initialising every time variable is used)
            boolean found = false;
            
            // after all grid data is added, search for adjacencies and add Island data type to proper grid, for each island
            for (int row = 0; row < HEIGHT; row++) { // cycle through each row
                for (int col = 0; col < WIDTH; col++) { // cycle through each column
                    
                    // meta island info (adj island coordinates)
                    ArrayList<IslandCoordinate> adjIslandCoordinates = new ArrayList<IslandCoordinate>(4);

                    // IMPORTANT condition for meta data population: islands cannot be directly above/below or directly to the right/left of another island
                    // or to rephrase: minimum gridspace between two islands is 2

                    // NORTH 0:
                    // check north (only if island is not already in top two rows)
                    if (row >= 2) {
                        found = false;
                        // go through all grid spots above island, starting from the island, then upwards
                        for (int northernIsland = row - 2; northernIsland >= 0; northernIsland--) {
                            if (intGrid[northernIsland][col] != 0 && !found) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(northernIsland, col);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                            }
                        }
                        if (!found) {
                            IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                            adjIslandCoordinates.add(blankIslandCoordinate);
                        }
                    } else {
                        IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                        adjIslandCoordinates.add(blankIslandCoordinate);
                    }
                    
                    // EAST 1:
                    // check east (only if island is not already in rightmost two columns)
                    if (col <= (WIDTH - 2)) {
                        found = false;
                        // go through all grid spots east of the island, starting from the island, then east
                        for (int easternIsland = col + 2; easternIsland < WIDTH; easternIsland++) {
                            if (intGrid[row][easternIsland] != 0 && !found) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(row, easternIsland);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                            }
                        }
                        if (!found) {
                            IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                            adjIslandCoordinates.add(blankIslandCoordinate);
                        }
                    } else {
                        IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                        adjIslandCoordinates.add(blankIslandCoordinate);
                    }

                    // SOUTH 2:
                    // check south (only if island is not already in bottom two rows)
                    if (row <= (HEIGHT - 2)) {
                        found = false;
                        // go through all grid spots south of the island, starting from the island, then south
                        for (int southernIsland = row + 2; southernIsland < HEIGHT; southernIsland++) {
                            if (intGrid[southernIsland][col] != 0 && !found) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(southernIsland, col);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                            }
                        }
                        if (!found) {
                            IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                            adjIslandCoordinates.add(blankIslandCoordinate);
                        }
                    } else {
                        IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                        adjIslandCoordinates.add(blankIslandCoordinate);
                    }

                    // WEST 3:
                    // check west (only is island is not already in leftmost two columns)
                    if (col >= 2) {
                        found = false;
                        // go through all grid spots west of the island, starting from the island, then west
                        for (int westernIsland = col - 2; westernIsland >= 0; westernIsland--) {
                            if (intGrid[row][westernIsland] != 0) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(row, westernIsland);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                            }
                        }
                        if (!found) {
                            IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                            adjIslandCoordinates.add(blankIslandCoordinate);
                        }
                    } else {
                        IslandCoordinate blankIslandCoordinate = new IslandCoordinate(-1, -1);
                        adjIslandCoordinates.add(blankIslandCoordinate);
                    }

                    // all adjacencies found by here
                    // Island newIsland = new Island(row, col, num, adjIslandCoordinates);
                    for (int k = 0; k < 4; k++) {
                        IslandCoordinate a = adjIslandCoordinates.get(k);
                        System.out.println("Row:" + Integer.toString(a.getRow()));
                        System.out.println("Col:" + Integer.toString(a.getCol()));
                    }
                    // grid[row][col] = newIsland;

                    // STOPPED CODING HERE
                }
            }

            
            
        } catch (FileNotFoundException e) {
            System.out.println("Oops! No file found!");
        }

        
    }

    public Island getIsland(int x, int y) {
        return grid[x][y];
    }

    public void printGrid(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getTotalBridgeCount() + " ");    
                } else {
                    System.out.print("0 ");
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

