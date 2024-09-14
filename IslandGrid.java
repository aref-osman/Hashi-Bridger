import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class IslandGrid {

    private Island[][] grid;
    
    public IslandGrid(){

        int x;
        int y;
        int num;

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

    
}
