package character;

public class Exoskeleton extends Monster{
    public Exoskeleton(String name, int level, int maxHP, int baseDamage, int defence) {
        super(name, level, maxHP, baseDamage, defence);
    }

    @Override
    public void applyFavouredStatMultiplier() {
        super.setDefence((int) (super.getDefence() * 1.10f));
    }

    @Override
    public Monster copy() {
        return new Exoskeleton(this.getName(), this.getLevel(), this.getMaxHP(), this.getBaseDamage(), this.getDefence());
    }

    @Override
    public float dodgeChance() {
        return getDodgeChance();
    }
}
