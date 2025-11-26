package item;

import character.StatType;

public class Potion extends Item{
    private final StatType affectedStat;
    private final float effectAmount;

    public Potion(String name, int price, int levelRequired, int maxUses, StatType affectedStat, float effectAmount) {
        super(name, price, levelRequired, maxUses);
        this.affectedStat = affectedStat;
        this.effectAmount = effectAmount;
    }


    public void apply(){
        // TODO: Pass a character object here
    }
}
