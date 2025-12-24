package components;

import java.util.ArrayList;
import java.util.List;

public class Island {
    
    private final int X_ORDINATE;
    private final int Y_ORDINATE;
    private final int TARGET_BRIDGE_COUNT; // the number written on the island when solving visually
    private final ArrayList<ArrayList<Integer>> ADJACENT_ISLAND_COORDINATES;

    // bridges built from the island towards the 4 directions, starting north, going clockwise
    private ArrayList<Integer> builtBridges;
    // blockage is a result of a bridge intersecting a direction, perpendicular to it
    private ArrayList<Boolean> directionBlockedForBridgeBuilding = new ArrayList<>(List.of(false, false, false, false));

    // constructor
    public Island(int x, int y, int island_num, ArrayList<ArrayList<Integer>> adjacencyCoordinates) {
        X_ORDINATE = x;
        Y_ORDINATE = y;
        TARGET_BRIDGE_COUNT = island_num;
        ADJACENT_ISLAND_COORDINATES = adjacencyCoordinates;
    }

    private int getSumOfItemsInIntegerArrayList(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (Integer number : arrayList) {sum += number;}
        return sum;
    }

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

    public int getCountOfBridgesBuiltFromIsland() {return getSumOfItemsInIntegerArrayList(builtBridges);} // total built bridges
    public int getCountOfBridgesBuiltFromIsland(CardinalDirection cardinalDirection) {return builtBridges.get(cardinalDirection.value());} // built bridges in a specific direction, from the island
    public int getCountOfBridgesLeftToBuild() {return TARGET_BRIDGE_COUNT - getCountOfBridgesBuiltFromIsland();} // total bridges that remain to be built
    public boolean isComplete() {return getCountOfBridgesLeftToBuild() == 0;} // whether the island has all its bridges built
    public boolean isDirectionBlockedByIntersectingBridge(CardinalDirection cardinalDirection) 
    {return directionBlockedForBridgeBuilding.get(cardinalDirection.value());}
    public int howManyBridgesCanBeBuiltFromIsland(CardinalDirection cardinalDirection) {
        if (isThereAnAdjacentIslandInThisDirection(cardinalDirection) && 
        !isDirectionBlockedByIntersectingBridge(cardinalDirection) && !isComplete()) 
        {return 2 - getCountOfBridgesBuiltFromIsland(cardinalDirection);} else {return 0;}
    } // does not check if a bridge be built *to* destination island, only *from* this island
    public int howManyBridgesCanBeBuiltFromIsland() {
        return getSumOfItemsInIntegerArrayList(howManyBridgesCanBeBuiltFromEachDirectionOnIsland());
    } // does not check if a bridge be built *to* destination island, only *from* this island
    public ArrayList<Integer> howManyBridgesCanBeBuiltFromEachDirectionOnIsland() {
        ArrayList<Integer> bridgesThatCanBeBuilt = new ArrayList<>(List.of(0, 0, 0, 0));
        if (isComplete()) {return bridgesThatCanBeBuilt;} 
        else {
            for (CardinalDirection cardinalDirection : CardinalDirection.values()) {
                if (isThereAnAdjacentIslandInThisDirection(cardinalDirection) && 
                    !isDirectionBlockedByIntersectingBridge(cardinalDirection)) {
                        bridgesThatCanBeBuilt.set(
                            cardinalDirection.value(), (2 - getCountOfBridgesBuiltFromIsland(cardinalDirection))
                        );
                }
            }
            return bridgesThatCanBeBuilt;
        }
    } // does not check if a bridge be built *to* destination island, only *from* this island 

    public CardinalDirection whatIsTheDirectionToAnotherIsland(Island islandB) {
        int x_1 = getXOrdinate();
        int y_1 = getYOrdinate();
        int x_2 = islandB.getXOrdinate();
        int y_2 = islandB.getYOrdinate();
        if ((x_1 == x_2) && (y_1 > y_2)) {return CardinalDirection.NORTH;}
        else if ((x_1 == x_2) && (y_1 < y_2)) {return CardinalDirection.SOUTH;}
        else if ((x_1 > x_2) && (y_1 == y_2)) {return CardinalDirection.WEST;} 
        else if ((x_1 < x_2) && (y_1 == y_2)) {return CardinalDirection.EAST;}
        else {return null;} // either it's the same island, or they don't share a vertical or horizontal line
    }
}
