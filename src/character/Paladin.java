package character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paladin extends Hero {
    private final List<StatType> favouredStats;
    public Paladin(String name, int level, int maxHP, int maxMp, int strength, int dex, int agility) {
        super(name, level, maxHP, maxMp, strength, dex, agility);
        this.favouredStats = new ArrayList<>();
        favouredStats.add(StatType.STRENGTH);
        favouredStats.add(StatType.DEXTERITY);
    }

    @Override
    protected void applyLevelUpBonuses() {
        this.buffDex(10);
        this.buffStrength(10);
        this.buffAgility(5);
    }

    @Override
    public List<StatType> getFavouredStats() {
        return Collections.unmodifiableList(favouredStats);
    }

    @Override
    public Hero copy() {
        return new Paladin(
                this.name,
                this.level,
                this.maxHP,
                this.getMP(),
                this.getStrength(),
                this.getDex(),
                this.getAgility()
        );
    }
}
