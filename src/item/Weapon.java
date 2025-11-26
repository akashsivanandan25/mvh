package item;

public class Weapon extends Item implements Equipment {
    private int baseDamage;
    private final int handsRequired;

    public Weapon(String name, int price, int levelRequired, int maxUses, int damage, int handsRequired) {
        super(name, price, levelRequired, maxUses);
        this.baseDamage = damage;
        this.handsRequired = utils.MathUtil.clamp(handsRequired, 1, 2);
    }

    public boolean isOneHanded(){
        return this.handsRequired == 1;
    }

    public boolean isTwoHanded(){
        return this.handsRequired == 2;
    }

    public int getbaseDamage(){
        return this.baseDamage;
    }

    @Override
    public boolean equip(Item item) {
        return false;
    }

    @Override
    public boolean unEquip(Item item) {
        return false;
    }
}
