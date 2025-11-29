package battle;

import character.Hero;
import character.Monster;

public class AttackAction implements BattleAction {
    private Hero attacker;
    private Monster target;
    private int damage;

    public AttackAction(Hero attacker, Monster target) {
        this.attacker = attacker;
        this.target = target;
    }

    public void getFinalDamage() {
        int raw = attacker.getEquippedWeapon().getbaseDamage() + attacker.getStrength();
        double reduction = raw * (target.getDefence() / 100.0);
        this.damage = Math.max(1, (int)(raw - reduction));
    }

    @Override
    public void execute(Battle battle) {
        int roll = utils.Dice.roll(99);
        int dodge = (int) target.getDodgeChance();

        if (roll <= dodge) {
            this.damage = 0;
            battle.log(target.getName() + " dodged the attack!");
            return;
        }
        getFinalDamage();
        target.setHealth(target.getHealth() - this.damage);

    }    @Override
    public String getDescription() {
        return attacker.getName() + " attacks " + target.getName() + " for " + this.damage;
    }

    public Hero getAttacker() {
        return this.attacker;
    }
}
