package factory;

import item.*;
import character.StatType;
import item.SpellType;

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


    public Potion createPotion(String line) {
        String[] d = line.trim().split("\\s+");

        String name         = d[0];
        int effect          = Integer.parseInt(d[1]);
        int price           = Integer.parseInt(d[2]);
        int levelRequired   = Integer.parseInt(d[3]);
        StatType stat       = StatType.valueOf(d[4].toUpperCase());

        return new Potion(name, price, levelRequired, 1, stat, effect);
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