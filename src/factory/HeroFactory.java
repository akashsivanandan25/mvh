package factory;

import character.*;

public class HeroFactory {

    public Hero createWarrior(String line) {
        String[] d = line.trim().split("\\s+");
        return new Warrior(
                d[0],                    // name
                1,                       // level
                calcHP(),                // maxHP
                Integer.parseInt(d[1]),  // MP
                Integer.parseInt(d[2]),  // STR
                Integer.parseInt(d[4]),  // DEX
                Integer.parseInt(d[3])   // AGI
        );
    }

    public Hero createPaladin(String line) {
        String[] d = line.trim().split("\\s+");
        return new Paladin(
                d[0],
                1,
                calcHP(),
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[4]),
                Integer.parseInt(d[3])
        );
    }

    public Hero createSorcerer(String line) {
        String[] d = line.trim().split("\\s+");
        return new Sorcerer(
                d[0],
                1,
                calcHP(),
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[4]),
                Integer.parseInt(d[3])
        );
    }

    private int calcHP() {
        int level = 1;
        return level * 100;   // HP = level × 100
    }
}