package world;

import market.Market;

public class MarketTile extends Tile {
    private Market market;
    protected MarketTile() {
        super(TileType.MARKET);
    }

    @Override
    public char getSymbol() {
        return 'M';
    }


    public Market getMarket(){
        return this.market;
    }
}
