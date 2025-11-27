package world;

public class CommonTile extends Tile{
    protected CommonTile() {
        super(TileType.WORLD);
    }

    @Override
    public char getSymbol() {
        return '.';
    }
}
