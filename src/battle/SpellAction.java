package battle;

import character.Hero;
import character.Monster;
import character.DebuffType;
import item.Spell;
import item.SpellType;

public class SpellAction implements BattleAction {
    private final Hero caster;
    private final Monster target;
    private final Spell spell;

    public SpellAction(Hero caster, Monster target, Spell spell) {
        this.caster = caster;
        this.target = target;
        this.spell = spell;
    }

    @Override
    public void execute(Battle battle) {

        if (caster.getMP() < spell.getManaCost()) {
            battle.log(caster.getName() + " failed to cast " + spell.getName() + " (not enough MP)");
            return;
        }

        caster.reduceMP(spell.getManaCost());

        int damage = spell.getBaseDamage() + caster.getDex();
        target.setHealth(target.getHealth() - damage);

        if (spell.getSpellType().equals(SpellType.ICE)) {
            target.applyDebuff(DebuffType.DAMAGE, damage / 4);
        } else if (spell.getSpellType().equals(SpellType.FIRE)) {
            target.applyDebuff(DebuffType.DEFENCE, damage / 4);
        } else if (spell.getSpellType().equals(SpellType.LIGHTNING)) {
            target.applyDebuff(DebuffType.DODGE, damage / 4);
        }

        battle.log(caster.getName() + " casts " + spell.getName() +
                " on " + target.getName() + " for " + damage);
    }

    @Override
    public String getDescription() {
        return caster.getName() + " casts " + spell.getName();
    }
}