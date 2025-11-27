package character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorcerer extends Hero{
    private final List<StatType> favouredStats;
    public Sorcerer(String name, int level, int maxHP, int maxMp, int strength, int dex, int agility) {
        super(name, level, maxHP, maxMp, strength, dex, agility);
        this.favouredStats = new ArrayList<>();
        this.favouredStats.add(StatType.DEX);
        this.favouredStats.add(StatType.AGILITY);
    }

    @Override
    protected void applyLevelUpBonuses() {
        this.buffDex(10);
        this.buffAgility(10);
        this.buffStrength(5);
    }

    @Override
    public List<StatType> getFavouredStats() {
        return Collections.unmodifiableList(favouredStats);
    }
}
