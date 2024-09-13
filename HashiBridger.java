public class HashiBridger {

    private static int[][] islandGrid = new int[4][4];

    public HashiBridger() {
    }

    public void addIsland(int x, int y, int num){
        islandGrid[x][y] = num;
    }

    public void printIslandGrid() {
        for (int i = 0; i < islandGrid.length; i++) {
            for (int j = 0; j < islandGrid.length; j++) {
                System.out.print(Integer.toString(islandGrid[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public int getNextIsland(String orientation, int indexA, int indexBStart, int indexBEnd) {
        
        int c = 0;
        
        for (int i = indexBStart; i < indexBEnd; i++) {
            
            if (orientation == "vertical") {
                c = islandGrid[i][indexA];    
            } else {
                c = islandGrid[indexA][i];
            }
            
            if (c != 0) {
                return c;
            }
        }

        return c;
    }
    
}
