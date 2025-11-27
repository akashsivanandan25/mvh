package item;

import character.Hero;
import character.StatType;

public class Potion extends Item{
    private final StatType affectedStat;
    private final int effectAmount;

    public Potion(String name, int price, int levelRequired, int maxUses, StatType affectedStat, int effectAmount) {
        super(name, price, levelRequired, maxUses);
        this.affectedStat = affectedStat;
        this.effectAmount = effectAmount;
    }


    public void apply(Hero character){
        switch (affectedStat){
            case AGILITY:
                character.buffAgility(effectAmount);
                break;
            case DEX:
                character.buffDex(effectAmount);
                break;
            case STRENGTH:
                character.buffStrength(effectAmount);
                break;
            case HP:
                character.heal(effectAmount);
                break;
            case MP:
                character.buffMP(effectAmount);
                break;
            default:
                break;
        }
        super.use();
    }
}
