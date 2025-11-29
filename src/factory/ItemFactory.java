package factory;

import item.*;
import character.StatType;
import item.SpellType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemFactory {

    /* ================= WEAPON ================= */

    public Weapon createWeapon(String line) {
        String[] d = line.trim().split("\\s+");

        String name         = d[0];
        int damage          = Integer.parseInt(d[1]);
        int price           = Integer.parseInt(d[2]);
        int levelRequired   = Integer.parseInt(d[3]);
        int hands           = Integer.parseInt(d[4]);

        return new Weapon(name, price, levelRequired, 999, damage, hands);
    }


    public Armour createArmour(String line) {
        String[] d = line.trim().split("\\s+");

        String name         = d[0];
        int reduction       = Integer.parseInt(d[1]);
        int price           = Integer.parseInt(d[2]);
        int levelRequired   = Integer.parseInt(d[3]);

        return new Armour(name, price, levelRequired, 999, reduction);
    }


    public Potion createPotion(String line){
        String[] d = line.trim().split("\\s+");

        String name    = d[0];
        int price      = Integer.parseInt(d[1]);
        int requiredLv = Integer.parseInt(d[2]);
        int effect     = Integer.parseInt(d[3]);

        String statField = String.join(" ", Arrays.copyOfRange(d, 4, d.length));

        List<StatType> stats = new ArrayList<>();

        if(statField.toLowerCase().startsWith("all")){
            stats.addAll(Arrays.asList(StatType.values()));

            return new Potion(name, price, requiredLv, effect, stats);
        }

        String[] parts = statField.split("/");

        for(String s : parts){
            s = s.trim().toUpperCase();

            if(s.equals("DEFENSE")) continue; // ignore unsupported stat for now

            stats.add(StatType.valueOf(s));
        }

        return new Potion(name, price, requiredLv, effect, stats);
    }
    public Spell createSpell(String line, SpellType type) {
        String[] d = line.trim().split("\\s+");

        String name         = d[0];
        int damage          = Integer.parseInt(d[1]);
        int price           = Integer.parseInt(d[2]);
        int levelRequired   = Integer.parseInt(d[3]);
        int manaCost        = Integer.parseInt(d[4]);

        return new Spell(
                name,
                price,
                levelRequired,
                200000,
                type,
                damage,
                manaCost
        );
    }
}