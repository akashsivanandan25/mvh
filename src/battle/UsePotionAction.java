package battle;

import character.Hero;
import item.Potion;

public class UsePotionAction implements BattleAction {

    private final Hero hero;
    private final Potion potion;

    public UsePotionAction(Hero hero, Potion potion) {
        this.hero = hero;
        this.potion = potion;
    }

    @Override
    public void execute(Battle battle) {
        potion.apply(hero);
        battle.log(getDescription());
    }

    @Override
    public String getDescription() {
        return hero.getName() + " uses " + potion.getName() +
                " (+" + potion.getAffectedStats().size() + " buff)";
    }
}