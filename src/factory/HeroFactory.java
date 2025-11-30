package factory;

import character.*;
import core.InputHandler;
import core.Renderer;

import java.util.ArrayList;
import java.util.List;

public class HeroFactory {

    public static List<Hero> createParty(List<Hero> baseHeroes, InputHandler in, Renderer ui) {

        List<Hero> party = new ArrayList<>();

        while (party.size() < 3) {

            ui.msg("\nChoose hero (or -1 to start):");
            for (int i = 0; i < baseHeroes.size(); i++)
                ui.msg(i + ": " + baseHeroes.get(i).getName() + baseHeroes.get(i).getFavouredStats());

            int c = in.nextInt();
            if (c == -1 && !party.isEmpty()) break;
            if (c < 0 || c >= baseHeroes.size()) continue;

            party.add(baseHeroes.get(c).copy());
        }
        return party;
    }

    public Hero createWarrior(String line) {
        String[] d = line.trim().split("\\s+");
        return new Warrior(
                d[0],
                1,
                calcHP(),
                Integer.parseInt(d[1]),
                Integer.parseInt(d[2]),
                Integer.parseInt(d[3]),
                Integer.parseInt(d[4])
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
                Integer.parseInt(d[3]),
                Integer.parseInt(d[4])
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
                Integer.parseInt(d[3]),
                Integer.parseInt(d[4])
        );
    }

    private int calcHP() {
        int level = 1;
        return level * 100;
    }
}