package market;

import character.Hero;
import inventory.Inventory;
import item.*;
import java.util.*;

public class Market {
    private Inventory inventory;

    public Market(Inventory inventory){
        this.inventory = inventory;
    }

    public List<Item> getItemsforSale(){
        return this.inventory.getInventoryItems();
    }

    public boolean buy(Hero hero, Item item){
        if (hero.canBuy(item)){
            hero.setGold(
                    hero.getGold() - item.getBuyingPrice()
            );
            inventory.remove(item);

            return true;
        }
        return false;
    }


    public void sell(Hero hero, Item item){
        hero.setGold(
                hero.getGold() + item.getSellingPrice()
        );
        hero.removeItem(item);
        inventory.add(item);
    }

    public void addItem(Item item){
        this.inventory.add(item);
    }

    public boolean repair(Hero hero, Item item){
        int cost = item.getBuyingPrice() / 2;
        if (hero.getGold() < cost) return false;

        hero.setGold(hero.getGold() - cost);
        item.repair();
        return true;
    }
}
