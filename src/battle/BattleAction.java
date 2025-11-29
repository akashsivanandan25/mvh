package battle;

public interface BattleAction {
    public void execute(Battle battle);
    public String getDescription();
}
