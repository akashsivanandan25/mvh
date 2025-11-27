package character;

import java.util.Collections;
import java.util.List;

public class Paladin extends Hero {
    @Override
    protected void applyLevelUpBonuses() {

    }

    @Override
    public List<StatType> getFavouredStats() {
        return Collections.emptyList();
    }
}
