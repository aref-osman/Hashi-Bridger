package components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class MapLoader {

    private String mapFileText;
    private String[] linesInMapFileText;
    private ArrayList<ArrayList<Integer>> islandList;
    private ArrayList<ArrayList<Integer>> islandGrid;
    private int mapWidth = 0;  // # of cols
    private int mapHeight = 0; // # of rows

    public MapLoader () {
        // read file
        try {this.mapFileText = Files.readString(Path.of("support_files\\test_data\\islandData.txt"));} 
        catch (IOException e) {throw new RuntimeException(e);}
        parseInput();
        findMapDimensions();
        populateIslandCollection();
    }

    private void parseInput() {
        // split input by line breaks
        this.linesInMapFileText = mapFileText.split("\n");

        // add all islands as arraylists in a bigger allIsland arraylist
        for (String row : linesInMapFileText) {
            // split parameters for each island
            String[] separatedIslandParametersArray = row.split(" ");
            // initialise collection
            ArrayList<Integer> separatedIslandParametersArrayList = new ArrayList<>();
            // convert type from String to Integer
            for (String elem : separatedIslandParametersArray) {separatedIslandParametersArrayList.add(Integer.valueOf(elem));}
            // add island parameters to bigger allIsland collection
            islandList.add(separatedIslandParametersArrayList);
        }
    }

    private void findMapDimensions() {
        // determine map dimensions
        for (ArrayList<Integer> islandDetails : islandList) {
            int islandRow = islandDetails.get(0);
            if (islandRow > this.mapHeight) {this.mapHeight = islandRow + 1;}
            int islandColumn = islandDetails.get(1);
            if (islandColumn > this.mapWidth) {this.mapWidth = islandColumn + 1;}
        }
    }

    private void populateIslandCollection() {
        // initialise collection with the proper dimensions
        for (int r = 0; r < this.mapHeight; r++) {
            ArrayList<Integer> row = new ArrayList<>(Collections.nCopies(mapWidth, 0));
            islandGrid.add(row);
        }

        // populate grid with island numbers at the correct co-ordinates
        for (ArrayList<Integer> islandDetails : islandList) {
            int row = islandDetails.get(0);
            int column = islandDetails.get(1);
            int islandNum = islandDetails.get(2);
            ArrayList<Integer> a = islandGrid.get(row);
            a.set(column, islandNum);
            islandGrid.set(row, a);
        }
    }

    public ArrayList<ArrayList<Integer>> getIslandGrid() {
        return islandGrid;
    }

    public 
}
