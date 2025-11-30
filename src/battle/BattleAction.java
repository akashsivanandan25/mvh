package battle;

import character.Hero;

public interface BattleAction {
    public void execute(Battle battle);
    public String getDescription();
    public Hero getAttacker();
}
