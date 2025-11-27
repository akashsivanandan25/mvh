package character;

public class Spirit extends Monster {
    public Spirit(String name, int level, int maxHP, int baseDamage, int defence) {
        super(name, level, maxHP, baseDamage, defence);
    }

    @Override
    protected void applyFavouredStatMultiplier() {
        this.setDodgeChance((float) (this.getDodgeChance() * 1.1));
    }

    @Override
    public float dodgeChance() {
        return this.getDodgeChance();
    }
}
