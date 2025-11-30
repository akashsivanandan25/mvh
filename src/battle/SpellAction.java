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

        SpellResult result = performCast();

        if(!result.isHitSuccessful()){
            battle.log(target.getName() + " dodged " + spell.getName() + "!");
            return;
        }

        battle.log(caster.getName() + " casts " + spell.getName() +
                " on " + target.getName() + " for " + result.getDamageDealt());

        if(result.getDebuffApplied() != null){
            battle.log(target.getName() + " suffers debuff: " + result.getDebuffApplied());
        }

        if(result.isTargetDefeated())
            battle.log(target.getName() + " has been defeated!");
    }

    private SpellResult performCast() {

        if(caster.getMP() < spell.getManaCost())
            return new SpellResult(0,null,false,false);

        caster.reduceMP(spell.getManaCost());

        if(target.dodged())
            return new SpellResult(0,null,false,false);

        int raw = spell.getBaseDamage() + caster.getDex();
        int finalDamage = Math.max(1, raw - target.getDefence());

        target.takeDamage(finalDamage);

        DebuffType debuff = null;
        int amount = finalDamage/4;

        switch(spell.getSpellType()){
            case ICE:
                debuff = DebuffType.DAMAGE; break;
            case FIRE:
                debuff = DebuffType.DEFENCE; break;
            case LIGHTNING:
                debuff = DebuffType.DODGE; break;
        }

        if(debuff != null)
            target.applyDebuff(debuff, amount);

        boolean dead = target.isFainted();

        return new SpellResult(finalDamage,debuff,true,dead);
    }

    @Override
    public String getDescription() {
        return caster.getName() + " casts " + spell.getName();
    }

    @Override
    public Hero getAttacker() { return caster; }
}