package world;

import inventory.Inventory;
import market.Market;

public class MarketTile extends Tile {
    private Market market;
    public MarketTile() {
        super(TileType.MARKET);
        this.market = new Market(new Inventory());
    }

    @Override
    public char getSymbol() {
        return 'M';
    }


    public Market getMarket(){
        return this.market;
    }
}
