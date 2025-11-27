package world;

import utils.Gameconstants;

import java.util.ArrayList;
import java.util.List;

public class World {
    private Tile[][] tiles;
    public Position partyPosition;

    public World(){
        generateTiles();
        placePartyStarting();
    }

    private void generateTiles() {
        int total = Gameconstants.WORLD_HEIGHT * Gameconstants.WORLD_WIDTH;
        int inaccessibleCount = (int) (total * 0.2f);
        int marketCount    = (int) (total * 0.3f);
        int commonCount =  total -  inaccessibleCount - marketCount;

        List<Tile> pool = new ArrayList<Tile>(total);
        for (int i = 0; i < inaccessibleCount; i ++) pool.add(new InaccessibleTile());
        for (int i = 0; i < marketCount; i ++) pool.add(new MarketTile());
        for (int i = 0; i < commonCount; i ++) pool.add(new CommonTile());

        java.util.Collections.shuffle(pool);

        int k = 0;
        for (int r = 0; r < Gameconstants.WORLD_HEIGHT; r ++){
            for (int c = 0; c < Gameconstants.WORLD_WIDTH; c ++){
                this.tiles[r][c] = pool.get(k++);
            }
        }
    }

    private void placePartyStarting(){
        for (int r = 0; r < Gameconstants.WORLD_HEIGHT; r ++){
            for (int c = 0; c < Gameconstants.WORLD_WIDTH; c ++){
                if (this.tiles[r][c].isAccessible()){
                    this.partyPosition = new Position(r, c);
                    return;
                }
            }
        }
    }

    public Tile tileAtPos(Position pos){
        return tiles[pos.getX()][pos.getY()];
    }

    public Tile[][] getTiles(){
        return this.tiles;
    }
}
