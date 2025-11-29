package battle;

import character.Hero;
import character.Monster;
import factory.MonsterFactory;
import party.Party;

import java.util.ArrayList;
import java.util.List;

public class Battle {
    Party party;
    List<Monster> monsters;
    private final java.util.List<String> log = new java.util.ArrayList<>();

    public void log(String msg) {
        log.add(msg);
    }

    public java.util.List<String> getLog() {
        return log;
    }


    public Battle(Party party, MonsterFactory factory) {
        this.party = party;
        this.monsters = pick(factory);
    }

    private List<Monster> pick(MonsterFactory factory) {
        int count = party.getHeroes().size();
        List<Monster> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(factory.randomMonster());
        }
        return list;
    }

    /**
     * Check if battle is complete. Battle is complete if all monsters are dead, or if all heroes have fainted
     * @return true / false depending on whether battle is complete
     */
    public boolean isComplete() {
        boolean monstersAlive = false;
        for (Monster m : monsters) {
            if (m.getHealth() > 0) {
                monstersAlive = true;
                break;
            }
        }

        boolean heroesAlive = false;
        for (Hero h : party.getHeroes()) {
            if (h.isFainted()) {
                heroesAlive = true;
                break;
            }
        }

        return (!monstersAlive) || (!heroesAlive);
    }

    /**
     * Called when battle is complete to check if heroes won
     * @return true / false depending on whether heroes won
     */
    public boolean heroesWon(){
        for (Hero h : party.getHeroes()) {
            if (!h.isFainted()) {
                return true;
            }
        }
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
    }

    public void processHeroTurn(){

    }

    public void processMonsterTurn(){

    }
}
