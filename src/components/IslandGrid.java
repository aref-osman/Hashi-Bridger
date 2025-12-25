package components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IslandGrid {

    private String mapFileText; // raw file input
    private String[] linesInMapFileText; // raw file input as lines
    private ArrayList<ArrayList<Integer>> rawIslandList; // list of parsed island co-ordinates
    private ArrayList<ArrayList<Integer>> numberGrid; // use only during Island construction
    
    private int mapWidth = 0;  // # of cols
    private int mapHeight = 0; // # of rows
    private ArrayList<ArrayList<Island>> islandGrid;

    public IslandGrid () {
        readInputFile();
        parseIslandInformation();
        calculateMapDimensionsImplicitly();
        createEmptyIslandGrid();
        addIslandNumbersToNumberGrid();
        addIslandsToIslandGrid();
    }

    private void readInputFile() {
        try {this.mapFileText = Files.readString(Path.of("support_files\\test_data\\islandData.txt"));} 
        catch (IOException e) {throw new RuntimeException(e);}
    }
    private void parseIslandInformation() {
        // split input by line breaks
        this.linesInMapFileText = mapFileText.split("\n");

        // store all islands as arraylists of their properties, in order of parsing
        for (String row : linesInMapFileText) {
            // split parameters for each island
            String[] separatedIslandParametersArray = row.split(" ");
            // initialise collection
            ArrayList<Integer> separatedIslandParametersArrayList = new ArrayList<>();
            // convert type from String to Integer
            for (String elem : separatedIslandParametersArray) {separatedIslandParametersArrayList.add(Integer.valueOf(elem));}
            // add island parameters to bigger allIsland collection
            rawIslandList.add(separatedIslandParametersArrayList);
        }
    }
    private void calculateMapDimensionsImplicitly() {
        // determine map dimensions
        for (ArrayList<Integer> islandDetails : rawIslandList) {
            int islandRow = islandDetails.get(0);
            if (islandRow > this.mapHeight) {this.mapHeight = islandRow + 1;}
            int islandColumn = islandDetails.get(1);
            if (islandColumn > this.mapWidth) {this.mapWidth = islandColumn + 1;}
        }
    }
    private void createEmptyIslandGrid() {
        // initialise collection with the proper dimensions
        for (int r = 0; r < this.mapHeight; r++) {
            ArrayList<Island> row = new ArrayList<>(this.mapWidth);
            for (int c = 0; c < this.mapWidth; c++) {row.add(null);}
            islandGrid.add(row);
        }
    }
    private void addIslandNumbersToNumberGrid() {
        // populate grid with island numbers at the correct co-ordinates
        for (ArrayList<Integer> islandDetails : rawIslandList) {
            int row = islandDetails.get(0);
            int column = islandDetails.get(1);
            int islandNum = islandDetails.get(2);
            ArrayList<Integer> r = numberGrid.get(row);
            r.set(column, islandNum);
            numberGrid.set(row, r);
        }
    }
    private void addIslandsToIslandGrid() {}

    private ArrayList<Integer> getCoordinatesOfAdjacentIsland(Island island, CardinalDirection direction) {
        int islandRow = island.getYOrdinate();
        int islandColumn = island.getXOrdinate();
        switch (direction) {
            case CardinalDirection.NORTH -> {
                // cycle through incrementally lower y-values for same x-values
                for (int r = islandRow - 1; r >= 0; r--) {
                    if (!(numberGrid.get(r).get(islandColumn) == 0)) {
                        return new ArrayList<>(List.of(islandColumn, r));
                    }
                }
            }
            case CardinalDirection.EAST -> {
                // cycle through incrementally bigger x-values for same y-values
                for (int c = islandColumn + 1; c < mapWidth; c++) {
                    if (!(numberGrid.get(islandRow).get(c) == 0)) {
                        return new ArrayList<>(List.of(c, islandRow));
                    }
                }
            }
            case CardinalDirection.SOUTH -> {
                // cycle through incrementally bigger y-values for same x-values
                for (int r = islandRow + 1; r < mapHeight; r++) {
                    if (!(numberGrid.get(r).get(islandColumn) == 0)) {
                        return new ArrayList<>(List.of(islandColumn, r));
                    }
                }
            }

            case CardinalDirection.WEST -> {
                // cycle through incrementally lower x-values for same y-values
                for (int c = islandColumn - 1; c >= 0; c--) {
                    if (!(numberGrid.get(islandRow).get(c) == 0)) {
                        return new ArrayList<>(List.of(c, islandRow));
                    }
                }
            }
        
            default -> {
                return null; // invalid direction given
            }
        }
        return null; // no adjacent island found in the specified direction
    }

    public ArrayList<ArrayList<Integer>> getIslandGrid() {
        return islandGrid;
    }
    public void howManyBridgesCanBeBuiltBetweenThisAndAnotherIsland(Island islandB) {} // TO DO
    
    public void solvePuzzle() {}
    public void printSolution() {}

    
}
