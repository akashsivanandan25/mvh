package world;

public class InaccessibleTile extends Tile {
    protected InaccessibleTile() {
        super(TileType.INACCESSIBLE);
    }

    @Override
    public char getSymbol() {
        return 'X';
    }
}
