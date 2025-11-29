package inventory;
import java.util.*;
import item.*;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void add(Item item) {
        this.items.add(item);
    }

    public void remove(Item item) {
        this.items.remove(item);
    }

    public List<Weapon> getWeapons(){
        List<Weapon> toReturn = new ArrayList<>();
        for(Item item : items){
            if (item instanceof Weapon){
                toReturn.add((Weapon) item);
            }
        }
        return toReturn;
    }

    public List<Armour> getArmours(){
        List<Armour> toReturn = new ArrayList<>();
        for (Item item : items){
            if (item instanceof Armour){
                toReturn.add((Armour) item);
            }
        }
        return toReturn;
    }

    public List<Spell> getSpells(){
        List<Spell> toReturn = new ArrayList<>();
        for (Item item : items){
            if (item instanceof Spell){
                toReturn.add((Spell) item);
            }
        }
        return toReturn;
    }


    public List<Potion> getPotions(){
        List<Potion> toReturn = new ArrayList<>();
        for (Item item : items){
            if (item instanceof Potion){
                toReturn.add((Potion) item);
            }
        }
        return toReturn;
    }

    public Item getItemByName(String name){
        for(Item item : items){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public List<Item> getInventoryItems(){
        return this.items;
    }
}
