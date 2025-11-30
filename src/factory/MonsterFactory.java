package factory;

import character.Dragon;
import character.Exoskeleton;
import character.Spirit;
import character.Monster;
import utils.Dice;

import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {
    private final List<Dragon> dragons = new ArrayList<>();
    private final List<Exoskeleton> exoskeletons = new ArrayList<>();
    private final List<Spirit> spirits = new ArrayList<>();
    private static List<Monster> monsterPool = new ArrayList<>();


    public static void setPool(List<Monster> spawnPool){
        monsterPool = spawnPool;
    }


    public Monster createDragon(String line) {
        String[] d = line.trim().split("\\s+");
        return new Dragon(
                d[0],
                1,
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[3])
        );
    }

    public Monster createExoskeleton(String line) {
        String[] d = line.trim().split("\\s+");
        return new Exoskeleton(
                d[0],
                1,
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[3])
        );
    }

    public Monster createSpirit(String line) {
        String[] d = line.trim().split("\\s+");
        return new Spirit(
                d[0],
                1,
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[3])
        );
    }

    public static Monster randomMonster() {
        Monster m = monsterPool.get(Dice.rollIndex(monsterPool.size())).copy();
        m.applyFavouredStatMultiplier();
        return m;
    }}