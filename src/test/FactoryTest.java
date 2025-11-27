package test;

import factory.ConfigLoader;
import character.*;
import item.*;

import java.util.List;

public class FactoryTest {
    public static void main(String[] args) {

        ConfigLoader loader = new ConfigLoader();

        /* ================= HEROES ================= */
        System.out.println("=== HEROES ===");
        List<Hero> heroes = loader.loadHeroes();
        for (Hero h : heroes) {
            System.out.println(h.getClass().getSimpleName() + " -> " + h.getName());
        }

        /* ================= MONSTERS ================= */
        System.out.println("\n=== MONSTERS ===");
        List<Monster> monsters = loader.loadMonsters();
        for (Monster m : monsters) {
            System.out.println(m.getClass().getSimpleName() + " -> " + m.getName());
        }

        /* ================= WEAPONS ================= */
        System.out.println("\n=== WEAPONS ===");
        List<Weapon> weapons = loader.loadWeapons();
        for (Weapon w : weapons) {
            System.out.println(w.getName() + "  DMG=" + w.getbaseDamage());
        }

        /* ================= ARMOR ================= */
        System.out.println("\n=== ARMOR ===");
        List<Armour> armour = loader.loadArmour();
        for (Armour a : armour) {
            System.out.println(a.getName() + "  DR=" + a.damageReduction());
        }

        /* ================= POTIONS ================= */
        System.out.println("\n=== POTIONS ===");
        List<Potion> potions = loader.loadPotions();
        for (Potion p : potions) {
            System.out.println(p.getName() + "  ->  " + p.getAffectedStat());
        }

        /* ================= SPELLS ================= */
        System.out.println("\n=== SPELLS ===");
        List<Spell> spells = loader.loadSpells();
        for (Spell s : spells) {
            System.out.println(s.getName() + "  type=" + s.getSpellType());
        }
    }
}