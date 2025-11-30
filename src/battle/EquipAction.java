package battle;

import character.Hero;
import item.Equipment;

public class EquipAction implements BattleAction {

    private final Hero hero;
    private final Equipment equipment;

    public EquipAction(Hero hero, Equipment equipment) {
        this.hero = hero;
        this.equipment = equipment;
    }

    @Override
    public void execute(Battle battle) {
        hero.equip(equipment);
        battle.log(getDescription());
    }

    @Override
    public String getDescription() {
        return hero.getName() + " equips " + equipment.getName();
    }

    @Override
    public Hero getAttacker() {
        return this.hero;
    }
}