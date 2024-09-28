public class IslandCoordinate {

    // row and column fields
    private int r;
    private int c;

    // constructor
    public IslandCoordinate(int r, int c){
        this.r = r;
        this.c = c;
    }

    // basic getters
    public int getRow(){
        return r;
    }
    public int getCol(){
        return c;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + r;
        result = prime * result + c;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IslandCoordinate other = (IslandCoordinate) obj;
        if (r != other.r)
            return false;
        if (c != other.c)
            return false;
        return true;
    }
}
