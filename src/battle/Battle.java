package battle;

import character.Hero;
import character.Monster;
import factory.MonsterFactory;
import party.Party;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

    private final Party party;
    private final List<Monster> monsters;
    private final List<String> log = new ArrayList<>();
    private final Random rng = new Random();

    public Battle(Party party, MonsterFactory factory) {
        this.party = party;
        this.monsters = pick(factory);
    }

    public void log(String msg) {
        log.add(msg);
    }

    public List<String> getLog() {
        return log;
    }

    public List<Monster> getMonsters() {
        return this.monsters;
    }

    private List<Monster> pick(MonsterFactory factory) {
        int count = party.getHeroes().size();
        List<Monster> list = new ArrayList<>();
        for (int i = 0; i < count; i++) list.add(factory.randomMonster());
        return list;
    }

    /**
     * Check if battle is complete. Battle is complete if all monsters are dead, or if all heroes have fainted
     * @return true / false depending on whether battle is complete
     */
    public boolean isComplete() {
        boolean monsterAlive = false;
        for (Monster m : monsters) if (m.getHealth() > 0) monsterAlive = true;
        boolean heroAlive = false;
        for (Hero h : party.getHeroes()) if (!h.isFainted()) heroAlive = true;
        return !monsterAlive || !heroAlive;
    }

    public boolean heroesWon() {
        for (Hero h : party.getHeroes()) if (!h.isFainted()) return true;
        return false;
    }

    /**
     * Recover 10% MP and 10% HP for all non fainted heroes
     */
    public void endOfRoundRegen(){
        for (Hero h : party.getHeroes()) {
            if (!h.isFainted()) {
                h.heal(10);
                h.recoverMP(10);
            }
        }
        log("End of round regen applied.");
    }


    /**
     * Called once per round, pass list of actions (one for every alive hero)
     */
    public void processHeroTurn(List<BattleAction> actions) {
        for (BattleAction action : actions) {

            if (isComplete()) return;

            action.execute(this);

            for (Monster m : monsters) {
                if (m.getHealth() <= 0) log(m.getName() + " has been defeated.");
            }
        }
    }


    public void processMonsterTurn() {

        for (Monster m : monsters) {
            if (m.getHealth() <= 0) continue;
            if (isComplete()) return;

            Hero target = pickRandomHero();
            if (target == null) return;

            int dmg = m.getBaseDamage();
            target.takeDamage(target.getHealth() - dmg);

            log(m.getName() + " attacks " + target.getName() + " for " + dmg);

            if (target.isFainted()) log(target.getName() + " has fallen.");
        }
    }

    private Hero pickRandomHero() {
        List<Hero> list = new ArrayList<>();
        for (Hero h : party.getHeroes()) {
            if (!h.isFainted()){
                list.add(h);
            };
        }
        if (list.isEmpty()){
            return null;
        } else {
            return list.get(utils.Dice.roll(list.size()));
        }
    }

    public void executeRound(List<BattleAction> heroActions) {
        processHeroTurn(heroActions);
        if (isComplete()) return;

        processMonsterTurn();
        if (isComplete()) return;

        endOfRoundRegen();
    }

    public Monster getFirstAliveMonster() {
        for (Monster m : monsters) {
            if (m.getHealth() > 0){ return m;}
        }
        return null;
    }
}