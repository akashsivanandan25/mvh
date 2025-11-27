package character;

public class Dragon extends Monster {
    protected Dragon(String name, int level, int maxHP, int baseDamage, int defence) {
        super(name, level, maxHP, baseDamage, defence);
    }

    @Override
    protected void applyFavouredStatMultiplier() {
        int damage = this.getBaseDamage();
        damage += (int) (damage * 0.3f);
        this.setBaseDamage(damage);
    }

    @Override
    public float dodgeChance() {
        return getDodgeChance();
    }
}
