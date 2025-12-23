package components;

import java.util.ArrayList;
import java.util.List;

public class Island {
    
    public final int X_ORDINATE;
    public final int Y_ORDINATE;
    public final int TARGET_BRIDGE_COUNT; // the number written on the island when solving visually
    public final ArrayList<ArrayList<Integer>> ADJACENT_ISLAND_COORDINATES;

    // bridges built from the island towards the 4 directions, starting north, going clockwise
    public ArrayList<Integer> builtBridges;
    // blockage is a result of a bridge intersecting a direction, perpendicular to it
    public ArrayList<Boolean> directionBlockedForBridgeBuilding = new ArrayList<>(List.of(false, false, false, false));

    // constructor
    public Island(int x, int y, int island_num, ArrayList<ArrayList<Integer>> adjacencyCoordinates) {
        X_ORDINATE = x;
        Y_ORDINATE = y;
        TARGET_BRIDGE_COUNT = island_num;
        ADJACENT_ISLAND_COORDINATES = adjacencyCoordinates;
    }

    // fixed info about island
    public int getXOrdinate() {return X_ORDINATE;}
    public int getYOrdinate() {return Y_ORDINATE;}
    public int getTargetBridgeCount() {return TARGET_BRIDGE_COUNT;}
    public ArrayList<ArrayList<Integer>> getAllAdjacentIslandCoordinates() 
    {return ADJACENT_ISLAND_COORDINATES;}
    public ArrayList<Integer> getAdjacentIslandCoordinates(CardinalDirection cardinalDirection) 
    {return ADJACENT_ISLAND_COORDINATES.get(cardinalDirection.value());}
    public boolean isThereAnAdjacentIslandInThisDirection(CardinalDirection cardinalDirection) 
    {return !ADJACENT_ISLAND_COORDINATES.get(cardinalDirection.value()).isEmpty();}
    public int getNumberOfAdjacentIslands() { 
        int numAdjacentIslands = 0;
        for (ArrayList<Integer> adjacentIslandCoordinates : ADJACENT_ISLAND_COORDINATES) 
        {if (!adjacentIslandCoordinates.isEmpty()) {numAdjacentIslands++;}}
        return numAdjacentIslands;
    }

    // variable info about island
    // total built bridges
    public int getCountOfBridgesBuiltFromIsland() {int sum = 0; for (int n : builtBridges) {sum += n;} return sum;}
    // built bridges in a specific direction, from the island
    public int getCountOfBridgesBuiltFromIsland(CardinalDirection cardinalDirection) {return builtBridges.get(cardinalDirection.equivalentValue());}
    // total bridges that remain to be built
    public int getCountOfBridgesLeftToBuild() {return TARGET_BRIDGE_COUNT - getCountOfBridgesBuiltFromIsland();}
    // whether the island has all its bridges built
    public boolean isComplete() {return getCountOfBridgesLeftToBuild() == 0;}
    public boolean isDirectionBlockedByIntersectingBridge(CardinalDirection cardinalDirection) 
    {return directionBlockedForBridgeBuilding.get(cardinalDirection.equivalentValue());}
    // the following methods do not check if a bridge be built *to* destination island, only *from* this island
    public void howManyBridgesCanBeBuiltFromIsland() {} 
    public void howManyBridgesCanBeBuiltFromIsland(CardinalDirection cardinalDirection) {}
}
