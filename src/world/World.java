package world;

import core.GameContext;
import factory.ConfigLoader;
import item.Armour;
import item.Potion;
import item.Spell;
import item.Weapon;
import utils.Gameconstants;

import java.util.ArrayList;
import java.util.List;

public class World {
    private Tile[][] tiles;
    private Position partyPosition;

    public World(){
        this.tiles = new Tile[Gameconstants.WORLD_HEIGHT][Gameconstants.WORLD_WIDTH];
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

        ConfigLoader loader = new ConfigLoader();

        List<Weapon>  weapons = loader.loadWeapons();
        List<Armour>  armour  = loader.loadArmour();
        List<Potion>  potions = loader.loadPotions();
        List<Spell>   spells  = loader.loadSpells();


        int k = 0;
        for (int r = 0; r < Gameconstants.WORLD_HEIGHT; r++) {
            for (int c = 0; c < Gameconstants.WORLD_WIDTH; c++) {

                Tile t = pool.get(k++);      // only once

                if (t instanceof MarketTile) {
                    MarketTile m = (MarketTile) t;
                    java.util.Random rand = new java.util.Random();

                    for (int i = 0; i < 3; i++)
                        m.getMarket().addItem(weapons.get(rand.nextInt(weapons.size())));
                    for (int i = 0; i < 2; i++)
                        m.getMarket().addItem(armour.get(rand.nextInt(armour.size())));
                    for (int i = 0; i < 3; i++)
                        m.getMarket().addItem(potions.get(rand.nextInt(potions.size())));
                    for (int i = 0; i < 2; i++)
                        m.getMarket().addItem(spells.get(rand.nextInt(spells.size())));
                }

                tiles[r][c] = t;
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

    public Position getPartyPosition(){
        return this.partyPosition;
    }

    public void setPartyPosition(Position pos){
        this.partyPosition = pos;
    }
}
