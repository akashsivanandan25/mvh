package state;

import character.Hero;
import core.GameContext;
import world.*;

public class ExplorationState implements  GameState {
    @Override
    public GameState handleInput(GameContext context, String input) {
        char inputChar =  input.toLowerCase().trim().charAt(0);

        switch (inputChar) {
            case 'w':
                return move(context, Direction.UP);
            case 's':
                return move(context, Direction.DOWN);
            case 'd':
                return move(context, Direction.RIGHT);
            case 'a':
                return move(context, Direction.LEFT);
            case 'i':
                context.ui().showParty(context.party().getHeroes());
                break;
            case 'm':
                return openMarket(context);
            case 'q':
                // quitstate
                break;
        }
        return this;
    }

    @Override
    public void render(GameContext context) {
        context.ui().drawWorld(context.world());
        context.ui().msg("WASD to move, I to view party info");
    }

    @Override
    public void onEnter(GameContext context) {
        context.ui().msg("Exploration begun. Move with WASD.");
    }

    private GameState move(GameContext context, Direction direction) {
        Position curr = context.world().getPartyPosition();
        Position next = curr.move(direction);

        if (!Position.isValidPos(next)) {
            context.ui().msg("Invalid position! You can't walk off the map");
            return this;
        }

        Tile t = context.world().tileAtPos(next);

        if (!t.isAccessible()){
            return this;
        }

        TileType tileType = t.getType();
        context.world().setPartyPosition(next);
        return handleTileMove(tileType, context);
    }

    private GameState handleTileMove(TileType tileType, GameContext context) {
        if (tileType == TileType.MARKET){
            context.ui().msg("You have moved to a Market tile! Press M to access the market");
        }

        if (tileType == TileType.WORLD){
            boolean isBattle = true;
              if  (isBattle){
                  // call battle state
                  return new BattleState();
              }
        }
        return this;
    }

    private GameState openMarket(GameContext context) {
        Tile tile = context.world().tileAtPos(context.world().getPartyPosition());

        if (!(tile instanceof world.MarketTile)) {
            context.ui().msg("You are not standing on a market tile.");
            return this;
        }

        context.ui().msg("Choose hero to shop for");
        context.ui().showParty(context.party());
        int userInput = context.in().nextInt();

        if (userInput < 0 || userInput > context.party().size() - 1){
            context.ui().msg("Invalid input");
            return this;
        }

        Hero h = context.party().get(userInput);
        return new MarketState(((MarketTile) tile).getMarket(), h);
    }
}
