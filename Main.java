public class Main {
    public static void main(String[] args){
        
        // no further functionality beyond setting up grid, so won't use HashiBridger class for now
        // HashiBridger gameInstant = new HashiBridger();
        IslandGrid g = new IslandGrid();

        Island island = g.getIsland(2);
        System.out.println(island.checkAdjIslandExists(0));
        System.out.println(island.checkAdjIslandExists(1));
        System.out.println(island.checkAdjIslandExists(2));
        System.out.println(island.checkAdjIslandExists(3));

        System.out.println(g.getNumIslandsInRow(7));
        System.out.println(g.getNumIslandsInCol(4));

    }
}