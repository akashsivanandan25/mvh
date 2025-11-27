package core;

import character.Hero;
import character.Monster;
import inventory.Inventory;
import item.Item;
import world.Tile;
import world.World;

import java.util.List;

public class Renderer {

    public void msg(String s) {
        System.out.println(s);
    }

    public void drawWorld(World w) {
        Tile[][] t = w.getTiles();
        for (int r = 0; r < t.length; r++) {
            for (int c = 0; c < t[r].length; c++) {
                System.out.print(t[r][c].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void showHeroStats(Hero h) {
        System.out.println(h.getName() +
                " LV:" + h.getLevel() +
                " HP:" + h.getHealth() + "/" + h.getMaxHP() +
                " MP:" + h.getMaxHP() + "/" + h.getMaxMP());
    }

    public void showParty(List<Hero> party) {
        for (Hero h : party) showHeroStats(h);
    }

    public void showMonsterStats(List<Monster> list) {
        for (Monster m : list) {
            System.out.println(m.getName() +
                    " HP:" + m.getHealth() + "/" + m.getMaxHP());
        }
    }

    public void showInventory(Inventory inv) {
        List<Item> items = inv.getInventory();
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            System.out.println((i + 1) + ") " + it.getName() +
                    " LVREQ:" + it.getLevelRequired() +
                    " BUY:" + it.getBuyingPrice() +
                    " SELL:" + it.getSellingPrice());
        }
    }

    public void showMarketMenu() {
        System.out.println("MARKET:");
        System.out.println("[1] Buy");
        System.out.println("[2] Sell");
        System.out.println("[3] Repair");
        System.out.println("[4] Leave");
    }

    public void showBattleScreen(List<Hero> heroes, List<Monster> monsters) {
        System.out.println("\n=== HEROES ===");
        showParty(heroes);
        System.out.println("\n=== MONSTERS ===");
        showMonsterStats(monsters);
    }
}