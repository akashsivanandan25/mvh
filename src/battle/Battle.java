package battle;

import character.Hero;
import character.Monster;
import factory.MonsterFactory;
import party.Party;

import java.util.ArrayList;
import java.util.List;

public class Battle {

    private final Party party;
    private final List<Monster> monsters;
    private final List<String> log = new ArrayList<>();

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
        int avgHeroLevel = avgLevel(party.getHeroes());

        List<Monster> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Monster m = factory.randomMonster();
            m.applyFavouredStatMultiplier();
            scaleMonsterToLevel(m, avgHeroLevel);
            list.add(m);
        }
        return list;
    }

    private void scaleMonsterToLevel(Monster m, int heroLevel) {

        int monsterLevel = m.getLevel();

        double scale = (double) heroLevel / monsterLevel;
        if(scale < 0.4) scale = 0.4;
        if(scale > 1.8) scale = 1.8;

        m.setLevel(heroLevel);
        m.setBaseDamage((int)(m.getBaseDamage() * scale));
        m.setDefence((int)(m.getDefence() * scale));
        m.setHealth((int)(m.getHealth() * scale));
    }
    private int avgLevel(List<Hero> heroes) {
        int level = 0;
        int count = 0;
        for (Hero h : heroes) {
            level += h.getLevel();
            count ++;
        }
        return (int) level/count;
    }


    /**
     * Check if battle is complete. Battle is complete if all monsters are dead, or if all heroes have fainted
     * @return true / false depending on whether battle is complete
     */
    public boolean isComplete() {
        boolean monsterAlive = false;
        for (Monster m : monsters)
            if (m.getHealth() > 0) {
                monsterAlive = true;
                break;
            }
        boolean heroAlive = false;
        for (Hero h : party.getHeroes())
            if (!h.isFainted()) {
                heroAlive = true;
                break;
            }
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
            target.takeDamage(dmg);

            log(m.getName() + " attacks " + target.getName() + " for " + dmg);

            if (target.isFainted()) log(target.getName() + " has fallen.");
        }
    }

    private Hero pickRandomHero() {
        List<Hero> list = new ArrayList<>();
        for (Hero h : party.getHeroes()) {
            if (!h.isFainted()){
                list.add(h);
            }
        }
        if (list.isEmpty()){
            return null;
        } else {
            return list.get(utils.Dice.rollIndex(list.size()));
        }
    }

    public void executeRound(List<BattleAction> heroActions) {
        log("⏳ Round start");
        processHeroTurn(heroActions);
        if (isComplete()) return;

        log("👹 Monster phase");
        processMonsterTurn();
        if (isComplete()) return;

        log("💚 Regen phase");
        endOfRoundRegen();
        log("⏳ Round end");
    }

    public int calcGoldReward() {
        int total = 0;
        for (Monster m : monsters) {
            int lvl = m.getLevel();
            total += lvl * 10;
            total += (int)(m.getDefence() * 0.5);
            total += (int)(m.getBaseDamage() * 0.5);
        }
        return Math.max(total, 5);
    }

    public int calcXPReward() {
        int total = 0;
        for (Monster m : monsters) {
            int lvl = m.getLevel();
            total += lvl * 15;
            total += m.getBaseDamage();
            total += m.getDefence() / 2;
        }
        return Math.max(total, 10);
    }

}