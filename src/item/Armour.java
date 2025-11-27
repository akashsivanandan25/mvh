package item;

public class Armour extends Item implements Equipment {
    private final float damageReduction;
    public Armour(String name, int price, int levelRequired, int maxUses, float damageReduction) {
        super(name, price, levelRequired, maxUses);
        this.damageReduction = damageReduction;
    }

    public float damageReduction() {
        return this.damageReduction;
    }

    @Override
    public int getHandsRequired() {
        return 0;
    }

    @Override
    public int getRequiredLevel() {
        return super.getLevelRequired();
    }
}
