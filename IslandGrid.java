import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class IslandGrid {

    private int WIDTH = 6;
    private int HEIGHT = 9;
    private Island[][] grid = new Island[HEIGHT][WIDTH];
    
    public IslandGrid(){

        int x;
        int y;
        int num;

        // try importing all island data and create islands, adding them to the grid
        try {
            File myObj = new File("islandData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] b = data.split(" ");

                x = Integer.parseInt(b[0]);
                y = Integer.parseInt(b[1]);
                num = Integer.parseInt(b[2]);


                


                Island newIsland = new Island(x, y, num);
                grid[x][y] = newIsland;
            }
            myReader.close();
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

