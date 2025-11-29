package battle;

import character.DebuffType;

public class SpellResult {
    private int damageDealt;
    DebuffType debuffApplied;
    boolean hitSuccessful;
    boolean targetDefeated;

    public SpellResult(int damageDealt, DebuffType debuffApplied,  boolean hitSuccessful, boolean targetDefeated) {
        this.damageDealt = damageDealt;
        this.debuffApplied = debuffApplied;
        this.hitSuccessful = hitSuccessful;
        this.targetDefeated = targetDefeated;
    }
    public int getDamageDealt() {
        return this.damageDealt;
    }

    public DebuffType getDebuffApplied() {
        return this.debuffApplied;
    }

    public boolean isHitSuccessful() {
        return this.hitSuccessful;
    }

    public boolean isTargetDefeated() {
        return this.targetDefeated;
    }
}
