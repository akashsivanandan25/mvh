package character;

public abstract class Monster extends Character {
    private int baseDamage;
    private int defence;
    private float dodgeChance;

    protected Monster(String name, int level, int maxHP, int baseDamage, int defence) {
        super(name, level, maxHP);
        this.baseDamage = baseDamage;
        this.defence = defence;
        this.dodgeChance = 0.05f;
        applyFavouredStatMultiplier();
    }

    public void attack(Hero hero){
        // TODO: IMPLEMENT WHEN BATTLE IS FLESHED OUT

    }

    public void applyDebuff(DebuffType debuffType, int amount){
        if (debuffType == DebuffType.DAMAGE){
            baseDamage -= Math.max(0, amount);
        } else if (debuffType == DebuffType.DEFENCE){
            defence -= Math.max(0, amount);
        }  else if (debuffType == DebuffType.DODGE){
            dodgeChance -= Math.max(0, amount/100f);
        }
    }


    protected abstract void applyFavouredStatMultiplier();


    public float getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(float dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public abstract Monster copy();

    public void setHealth(int amount){
        this.health = amount;
    }
}
