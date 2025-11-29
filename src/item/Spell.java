package item;

public class Spell extends Item {
    private final int baseDamage;
    private final SpellType spellType;
    private final int manaCost;

    public Spell(String name, int price, int levelRequired, int maxUses, SpellType spellType, int baseDamage,  int manaCost) {
        super(name, price, levelRequired, maxUses);
        this.spellType = spellType;
        this.manaCost = manaCost;
        this.baseDamage = baseDamage;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getBaseDamage() {
        return baseDamage;
    }


    // TODO: make a spellcast function: needs to be passed a source and a target
}
