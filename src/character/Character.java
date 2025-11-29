package character;

public abstract class Character {
    String name;
    int level;
    int health;
    int maxHP;
    boolean isFainted;

    protected Character(String name, int level, int maxHP) {
        this.name = name;
        this.level = level;
        this.maxHP = maxHP;
        this.health = maxHP;
        this.isFainted = false;
    }


    public void takeDamage(int damage)
    {
        this.health -= damage;
        if (health <= 0){
            this.isFainted = true;
        }
    }

    public void heal(int amount){
        this.health += utils.MathUtil.percentage(this.health, amount);
    }

    public boolean isFainted(){
        return this.isFainted;
    }

    public abstract float dodgeChance();

    public int getLevel(){
        return this.level;
    }

    public String getName(){
        return this.name;
    }

    public int getHealth(){
        return this.health;
    }

    public int getMaxHP(){
        return this.maxHP;
    }

    public void setHealth(int health){
        this.health = health;
    }
}
