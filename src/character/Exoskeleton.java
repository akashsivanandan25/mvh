package character;

public class Exoskeleton extends Monster{
    protected Exoskeleton(String name, int level, int maxHP, int baseDamage, int defence) {
        super(name, level, maxHP, baseDamage, defence);
    }

    @Override
    protected void applyFavouredStatMultiplier() {
        super.setDefence((int) (super.getDefence() * 1.10f));
    }

    @Override
    public float dodgeChance() {
        return getDodgeChance();
    }
}
