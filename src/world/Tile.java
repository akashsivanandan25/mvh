package world;

public abstract class Tile {
     private final TileType type;

    protected Tile(TileType type) {
        this.type = type;
    }

    public boolean isAccessible(){
        return !type.equals(TileType.INACCESSIBLE);
    }


    public abstract char getSymbol();

    public TileType getType(){
        return type;
    }
}