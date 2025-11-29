package battle;

public class AttackResults {
    private int damageDealt;
    private boolean hitSuccessful;
    private boolean targetDefeated;

    public AttackResults(boolean targetDefeated, int damageDealt, boolean hitSuccessful) {
        this.hitSuccessful = hitSuccessful;
        this.targetDefeated = targetDefeated;
        this.damageDealt = damageDealt;
    }

    public boolean isHitSuccessful() {
        return hitSuccessful;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public boolean isTargetDefeated() {
        return targetDefeated;
    }
}
