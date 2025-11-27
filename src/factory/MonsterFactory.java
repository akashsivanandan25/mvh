package factory;

import character.Dragon;
import character.Exoskeleton;
import character.Spirit;
import character.Monster;

public class MonsterFactory {

    public Monster createDragon(String line) {
        String[] d = line.trim().split("\\s+");
        return new Dragon(
                d[0],                       // name
                1,                          // base level
                Integer.parseInt(d[1]),     // HP
                Integer.parseInt(d[2]),     // base damage
                Integer.parseInt(d[3])      // defense
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
}