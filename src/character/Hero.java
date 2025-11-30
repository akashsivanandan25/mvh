package character;

import core.GameContext;
import inventory.Inventory;
import item.*;

import java.util.List;

public abstract class Hero extends Character {
    private int experience;
    private int mp;
    private int maxMP;
    private int strength;
    private int dex;
    private int agility;
    private int gold;
    private Inventory inventory;
    private Weapon equippedWeapon;
    private Armour equippedArmour;
    private int freeHands;

    protected Hero(String name, int level, int maxHP, int maxMp, int strength, int dex, int agility) {
        super(name, level, maxHP);
        this.maxMP = maxMp;
        this.strength = strength;
        this.mp = maxMP;
        this.dex = dex;
        this.agility = agility;
        this.gold = 1000;
        this.inventory = new Inventory();
        this.equippedWeapon = null;
        this.equippedArmour = null;
        this.freeHands = 2;
    }

    @Override
    public float dodgeChance() {
        return 0.02f * this.agility;
    }

//    public void attack(){
    // TODO: implement attackign a monster

//    }

    // TODO: Implement castSpell once Monster is defined

    public void usePotion(Potion potion) {
        if (potion.isUsable()) {
            potion.apply(this);
        }
    }

    public void buffAgility(int amount) {
        this.agility += utils.MathUtil.percentage(this.agility, amount);
    }

    public void buffStrength(int amount) {
        this.strength += utils.MathUtil.percentage(this.strength, amount);
    }

    public void buffDex(int amount) {
        this.dex += utils.MathUtil.percentage(this.dex, amount);
    }

    public void buffMP(int amount) {
        this.mp += utils.MathUtil.percentage(this.mp, amount);
    }

    public boolean canEquip(Equipment equipment) {
        return equipment.getHandsRequired() <= freeHands && this.level >= equipment.getRequiredLevel();
    }

    public boolean equip(Equipment item) {
        if (!canEquip(item)) {
            return false;
        }

        if (item instanceof Weapon) {
            if (this.equippedWeapon != null) {
                unEquip(this.equippedWeapon);
            }

            this.equippedWeapon = (Weapon) item;
            this.freeHands -= item.getHandsRequired();
            this.inventory.remove((Item) item);
            return true;
        }

        if (item instanceof Armour) {
            if (this.equippedArmour != null) {
                unEquip(this.equippedArmour);
            }
            this.equippedArmour = (Armour) item;
            this.inventory.remove((Item) item);
            return true;
        }
        return false;
    }

    private boolean unEquip(Item item) {
        if (item == null) {
            return false;
        }

        if (item == this.equippedWeapon) {
            this.freeHands += this.equippedWeapon.getHandsRequired();
            this.equippedWeapon = null;
            this.inventory.add(item);
            return true;
        }

        if (item == this.equippedArmour) {
            this.equippedArmour = null;
            this.inventory.add(item);
            return true;
        }

        return false;
    }

    protected abstract void applyLevelUpBonuses();

    public void gainExperience(int amount) {
        this.experience += amount;

        while (this.experience >= xpToNextLevel()) {
            this.experience -= xpToNextLevel();
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;

        this.maxMP += utils.MathUtil.percentage(this.maxMP, 5);
        this.maxHP += utils.MathUtil.percentage(this.maxHP, 5);

        applyLevelUpBonuses();

        this.health = this.maxHP;
        this.mp = this.maxMP;
    }


    private int xpToNextLevel() {
        return this.level * 100;
    }

    public boolean canBuy(Item item) {
        return item.getBuyingPrice() <= this.gold;
    }

    public abstract List<StatType> getFavouredStats();


    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public int getMaxMP() {
        return this.maxMP;
    }

    public int getMP() {
        return this.mp;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void recoverMP(int amount) {
        this.mp += utils.MathUtil.percentage(this.mp, amount);
    }

    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }

    public Armour getEquippedArmour() {
        return this.equippedArmour;
    }

    public int getStrength() {
        return this.strength;
    }

    public void reduceMP(int amount) {
        this.mp -= amount;
    }

    public int getDex() {
        return this.dex;
    }

    public int getAgility() {
        return this.agility;
    }

    public <T> T chooseFromList(GameContext ctx, List<T> items, String label) {
        ctx.ui().msg(label + "  (-1 to cancel)");

        for (int i = 0; i < items.size(); i++)
            ctx.ui().msg(i + ": " + items.get(i).toString());

        int choice = ctx.in().nextInt();
        if (choice == -1) return null;
        if (choice < 0 || choice >= items.size()) return null;

        return items.get(choice);
    }

    public Spell chooseSpell(GameContext context) {
        return chooseFromList(context, inventory.getSpells(), "Choose a spell, press -1 to exit");
    }

    public Potion choosePotion(GameContext context) {
        return chooseFromList(context, inventory.getPotions(), "Choose a potion, press -1 to exit");
    }

    public Weapon chooseWeapon(GameContext context) {
        List<Weapon> list = inventory.getWeapons();
        return chooseFromList(context, list, "Choose weapon");
    }

    public Armour chooseArmour(GameContext context) {
        List<Armour> list = inventory.getArmours();
        return chooseFromList(context, list, "Choose armour");
    }

    public Equipment chooseEquipment(GameContext context) {
        context.ui().msg("Press w to choose a weapon, and a to choose armour");
        char userChoice = context.in().nextString().toLowerCase().trim().charAt(0);
        if (userChoice == 'w'){
            Weapon chosenWeapon = chooseWeapon(context);
            return (Equipment) chosenWeapon;
        } else if (userChoice == 'a'){
            Armour chosenArmour = chooseArmour(context);
            return (Equipment) chosenArmour;
        }
        return null;
    }

    public int getXP(){
        return this.experience;
    }

    public void setXP(int xp){
        this.experience = xp;
    }

    public abstract Hero copy();
}