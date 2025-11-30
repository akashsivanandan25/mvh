package battle;

import character.Hero;
import item.Potion;
import character.StatType;

public class UsePotionAction implements BattleAction {

    private final Hero hero;
    private final Potion potion;

    public UsePotionAction(Hero hero, Potion potion) {
        this.hero = hero;
        this.potion = potion;
    }

    @Override
    public void execute(Battle battle) {

        if(potion == null){
            battle.log(hero.getName() + " tried to use a potion but has none.");
            return;
        }

        if(!potion.isUsable()){
            battle.log(potion.getName() + " is empty and cannot be used.");
            return;
        }

        potion.apply(hero);
        StringBuilder buff = new StringBuilder();

        for(StatType stat : potion.getAffectedStats()){
            buff.append(stat).append(" ");
        }

        battle.log(hero.getName() + " uses " + potion.getName()
                + " → +" + potion.getEffectAmount() + " to [" + buff + "]");

        if(!potion.isUsable()){
            hero.getInventory().remove(potion);
            battle.log(potion.getName() + " has been fully consumed.");
        }
    }

    @Override
    public String getDescription() {
        return hero.getName() + " uses " + potion.getName();
    }

    @Override
    public Hero getAttacker() {
        return hero;
    }
}