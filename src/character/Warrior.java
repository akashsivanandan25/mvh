package character;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warrior extends Hero{
    private final List<StatType> favouredStats;

    public Warrior(String name, int level, int maxHP, int maxMp, int strength, int dex, int agility) {
        super(name, level, maxHP, maxMp, strength, dex, agility);
        this.favouredStats = new ArrayList<>();
        this.favouredStats.add(StatType.STRENGTH);
        this.favouredStats.add(StatType.AGILITY);
    }

    @Override
    protected void applyLevelUpBonuses() {
        this.buffStrength(10);
        this.buffAgility(10);
        this.buffDex(5);
    }

    @Override
    public List<StatType> getFavouredStats() {
        return Collections.unmodifiableList(favouredStats);
    }

    @Override
    public Hero copy() {
        return new Warrior(
                this.name,
                this.level,
                this.maxHP,
                this.getMaxMP(),
                this.getStrength(),
                this.getDex(),
                this.getAgility()
        );
    }
}
