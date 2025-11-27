package item;

public abstract class Item {
    private final String name;
    private final int price;
    private final int levelRequired;
    private final int maxUses;
    private int currentUses;

    public Item(String name, int price, int levelRequired, int maxUses) {
        this.name = name;
        this.price = price;
        this.levelRequired = levelRequired;
        this.maxUses = maxUses;
        this.currentUses = 0;
    }

    public String getName(){
        return this.name;
    }
    public void use(){
        this.currentUses++;
    }

    public boolean isUsable(){
        return this.currentUses < this.maxUses;
    }

    public void repair(){
        this.currentUses = 0;
    }

    public int getSellingPrice(){
        return this.price / 2;
    }

    public int getBuyingPrice(){
        return this.price;
    }

    public int getLevelRequired(){
        return this.levelRequired;
    }


    public int getMaxUses(){
        return this.maxUses;
    }
}
