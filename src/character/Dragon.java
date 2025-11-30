package character;

public class Dragon extends Monster {
    public Dragon(String name, int level, int maxHP, int baseDamage, int defence) {
        super(name, level, maxHP, baseDamage, defence);
    }

    @Override
    public void applyFavouredStatMultiplier() {
        int damage = this.getBaseDamage();
        damage += (int) (damage * 0.3f);
        this.setBaseDamage(damage);
    }

    @Override
    public Monster copy() {
        return new Dragon(this.getName(), this.getLevel(), this.getMaxHP(), this.getBaseDamage(), this.getDefence());
    }

    @Override
    public float dodgeChance() {
        return getDodgeChance();
    }
}
