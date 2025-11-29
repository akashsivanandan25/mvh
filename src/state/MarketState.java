package state;

import core.GameContext;
import market.Market;
import item.Item;
import character.Hero;

import java.util.List;

public class MarketState implements GameState {

    private final Market market;
    private final Hero hero;           // shopping hero

    public MarketState(Market market, Hero hero) {
        this.market = market;
        this.hero   = hero;
    }

    @Override
    public GameState handleInput(GameContext context, String input) {
        char c = input.trim().toLowerCase().charAt(0);

        switch (c){
            case 'a':
                this.buy(context);
                break;
            case 's':
                this.sell(context);
                break;
            case 'r':
                this.repair(context);
                break;
            case 'x':
                return new ExplorationState();

        }
        return this;
    }

    private void buy(GameContext context) {
        List<Item> itemList = market.getItemsforSale();
        if (itemList.isEmpty()){
            context.ui().msg("No items for sale");
            return;
        }

        context.ui().showItemList(itemList, "Select item number to buy, and -1 to cancel\nAvailable items: ");
        int index = context.in().nextInt();
        if (index == -1){ return;}

        if (index < 1 || index > itemList.size()) {
            context.ui().msg("Invalid selection");
            return;
        }

        Item chosenItem = itemList.get(index - 1);

        if (!hero.canBuy(chosenItem)){
            context.ui().msg("Insufficient gold! Please choose another item or exit the market");
            return;
        }

        if (market.buy(hero, chosenItem)){
            hero.addItem(chosenItem);
            context.ui().msg("Successfully purchased " + chosenItem.getName() +"!");
        }
    }

    private void sell(GameContext context) {
        List<Item> heroItems = this.hero.getInventory().getInventoryItems();
        if (heroItems.isEmpty()){
            context.ui().msg("No items for sale");
            return;
        }

        context.ui().showItemList(heroItems, "Select item number to sell, and -1 to cancel");
        int index = context.in().nextInt();

        if (index == -1){
            context.ui().msg("No problem! Thank you, come again");
            return;
        }

        if (index < 1 || index > heroItems.size()) {
            context.ui().msg("Invalid selection.");
            return;
        }


        Item chosenItem = heroItems.get(index - 1);

        if (chosenItem != null){
            market.sell(hero, chosenItem);
            context.ui().msg("Successfully sold " + chosenItem.getName() +"!");
        }
    }

    private void repair(GameContext context) {
        List<Item> heroItems = this.hero.getInventory().getInventoryItems();
        if (heroItems.isEmpty()){
            context.ui().msg("No items for repair! Please choose another action or exit the market");
            return;
        }

        context.ui().showItemList(heroItems, "Select item number to repair and -1 to cancel");
        int index = context.in().nextInt();
        if (index == -1){
            context.ui().msg("No problem! Thank you, come again");
            return;
        }

        if (index < 1 || index > heroItems.size()) {
            context.ui().msg("Invalid selection.");
            return;
        }

        Item chosenItem = heroItems.get(index - 1);
        if (chosenItem != null){
            if (!market.repair(hero, chosenItem)){
                context.ui().msg("Unable to repair item. Please try again");
                return;
            }
            else {
                context.ui().msg("Successfully repaired " + chosenItem.getName() +"!");
                return;
            }
        }
    }

    @Override
    public void render(GameContext ctx) {
        ctx.ui().clear();
        ctx.ui().msg("=== MARKET ===");
        ctx.ui().msg("Gold: " + hero.getGold());
        ctx.ui().showItemList(market.getItemsforSale(), "Items for sale: ");
        ctx.ui().msg("[B]uy  [S]ell  [R]epair  [X] Exit");
    }

    @Override
    public void onEnter(GameContext ctx) {
        ctx.ui().msg("Entered Market.");
    }

}