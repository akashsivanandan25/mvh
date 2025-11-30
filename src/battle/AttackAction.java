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
        AttackResults result = performAttack();

        if (!result.isHitSuccessful()) {
            battle.log(target.getName() + " dodged the attack!");
            return;
        }

        battle.log(attacker.getName() + " attacks " + target.getName()
                + " for " + result.getDamageDealt());

        if(result.isTargetDefeated())
            battle.log(target.getName() + " has been defeated!");
    }

    private AttackResults performAttack() {

        if(attacker == null || target == null)
            return new AttackResults(false,0,false);

        if(target.dodged())
            return new AttackResults(false,0,false);

        int str = attacker.getStrength();
        int wep = (attacker.getEquippedWeapon() == null) ? 0 :
                attacker.getEquippedWeapon().getbaseDamage();

        int raw = str + wep;
        int reduced = (int)(raw - (raw * (target.getDefence()/100.0)));
        int finalDamage = Math.max(1,reduced);

        target.takeDamage(finalDamage);
        boolean dead = target.isFainted();

        return new AttackResults(dead,finalDamage,true);
    }


    @Override
    public String getDescription() {
        return attacker.getName() + " attacks " + target.getName() + " for " + this.damage;
    }

    public Hero getAttacker() {
        return this.attacker;
    }
}
