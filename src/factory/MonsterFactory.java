package factory;

import character.Dragon;
import character.Exoskeleton;
import character.Spirit;
import character.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {
    private final List<Dragon> dragons = new ArrayList<>();
    private final List<Exoskeleton> exoskeletons = new ArrayList<>();
    private final List<Spirit> spirits = new ArrayList<>();

    public Monster createDragon(String line) {
        String[] d = line.trim().split("\\s+");
        Dragon dragon =  new Dragon(
                d[0],
                1,
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[3])
        );
        dragons.add(dragon);
        return dragon;
    }

    public Monster createExoskeleton(String line) {
        String[] d = line.trim().split("\\s+");
        Exoskeleton exo =  new Exoskeleton(
                d[0],
                1,
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[3])
        );
        exoskeletons.add(exo);
        return exo;
    }

    public Monster createSpirit(String line) {
        String[] d = line.trim().split("\\s+");
        Spirit spirit = new Spirit(
                d[0],
                1,
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[3])
        );
        spirits.add(spirit);
        return spirit;
    }

    public Monster randomMonster() {
        List<Monster> all = new ArrayList<>();
        all.addAll(dragons);
        all.addAll(exoskeletons);
        all.addAll(spirits);
        return all.get(utils.Dice.roll(all.size())).copy();
    }
}