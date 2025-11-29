package item;

import character.Hero;
import character.StatType;

import java.util.List;

public class Potion extends Item{
    private final int effectAmount;
    private final List<StatType> statsAffected;

    public Potion(String name, int price, int level, int amount, List<StatType> stats){
        super(name,price,level,1);
        this.statsAffected = stats;
        this.effectAmount = amount;
    }

    public void apply(Hero hero){
        for(StatType stat : statsAffected){
            switch(stat){
                case HEALTH:    hero.heal(effectAmount); break;
                case MANA:      hero.buffMP(effectAmount); break;
                case STRENGTH:  hero.buffStrength(effectAmount); break;
                case AGILITY:   hero.buffAgility(effectAmount); break;
                case DEXTERITY: hero.buffDex(effectAmount); break;
            }
        }
        super.use();
    }

    public List<StatType> getAffectedStats() {
        return this.statsAffected;
    }
}
