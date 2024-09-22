import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class IslandGrid {

    private int WIDTH = 6;
    private int HEIGHT = 9;
    private int[][] intGrid = new int[HEIGHT][WIDTH];
    private Island[][] grid = new Island[HEIGHT][WIDTH];
    
    public IslandGrid(){

        int x = 0;
        int y = 0;
        int num = 0;

        // try importing all island data and create islands, adding them to the grid
        try {
            File myObj = new File("islandData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] b = data.split(" ");

                // basic island info (location & bridge number)
                y = Integer.parseInt(b[0]);
                x = Integer.parseInt(b[1]);
                num = Integer.parseInt(b[2]);

                intGrid[x][y] = num;

            }

            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    int specialX = i;
                    int specialY = j;
                    System.out.print(intGrid[specialX][specialY] + " ");
                }
                System.out.println();
            }

            myReader.close();

            // initialise variable here to save memory (to avoid re-initialising every time variable is used)
            boolean found = false;
            
            // after all grid data is added, search for adjacencies and add Island data type to proper grid, for each island
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    x = i;
                    y = j;
                    // System.out.println("row: " + i + ", col: " + j);
                    
                    // meta island info (adj island coordinates)
                    ArrayList<IslandCoordinate> adjIslandCoordinates = new ArrayList<IslandCoordinate>(4);

                    // IMPORTANT condition for meta data population: islands cannot be directly above/below or directly to the right/left of another island
                    // or to rephrase: minimum gridspace between two islands is 2

                    // NORTH 0:
                    // check north (only if island is not already in top two rows)
                    if (y != 0 && y != 1) {
                        found = false;
                        // go through all grid spots above island, starting from the island, then upwards
                        for (int northernIsland = y - 1; northernIsland >= 0; northernIsland--) {
                            System.out.println("North search; y-1: " + Integer.toString(y-1) + "; var: " + Integer.toString(northernIsland));
                            if (intGrid[northernIsland][x] != 0) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(x, northernIsland);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                                // once an adjacent island is found, stop the search
                                break;
                            }
                        }
                        if (!found) {
                            adjIslandCoordinates.add(null);
                        }
                    }
                    // EAST 1:
                    // check east (only if island is not already in rightmost two columns)
                    if (x != (WIDTH - 1) && x != (WIDTH - 2)) {
                        found = false;
                        // go through all grid spots east of the island, starting from the island, then east
                        for (int easternIsland = x + 1; easternIsland < WIDTH; easternIsland++) {
                            if (intGrid[y][easternIsland] != 0) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(easternIsland, y);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                                // once an adjacent island is found, stop the search
                                break;
                            }
                        }
                        if (!found) {
                            adjIslandCoordinates.add(null);
                        }
                    }
                    // SOUTH 2:
                    // check south (only if island is not already in bottom two rows)
                    if (y != (HEIGHT - 1) && y != (HEIGHT - 2)) {
                        found = false;
                        // go through all grid spots south of the island, starting from the island, then south
                        for (int southernIsland = y + 1; southernIsland < HEIGHT; southernIsland++) {
                            if (intGrid[southernIsland][x] != 0) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(x, southernIsland);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                                // once an adjacent island is found, stop the search
                                break;
                            }
                        }
                        if (!found) {
                            adjIslandCoordinates.add(null);
                        }
                    }
                    // WEST 3:
                    // check west (only is island is not already in leftmost two columns)
                    if (x != 0 && x != 1) {
                        found = false;
                        // go through all grid spots west of the island, starting from the island, then west
                        for (int westernIsland = x - 1; westernIsland >= 0; westernIsland--) {
                            if (intGrid[y][westernIsland] != 0) {
                                found = true;
                                IslandCoordinate adjIslandCoordinate = new IslandCoordinate(westernIsland, y);
                                adjIslandCoordinates.add(adjIslandCoordinate);
                                // once an adjacent island is found, stop the search
                                break;
                            }
                        }
                        if (!found) {
                            adjIslandCoordinates.add(null);
                        }
                    }

                    // all adjacencies found by here
                    //Island newIsland = new Island(x, y, num, adjIslandCoordinates);
                    for (int k = 0; k < 4; k++) {
                        System.out.println(adjIslandCoordinates.get(k));
                    }
                    //grid[y][x] = newIsland;

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

