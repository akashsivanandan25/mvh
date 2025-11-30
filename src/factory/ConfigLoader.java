package factory;

import character.Hero;
import character.Monster;
import item.*;

import java.io.*;
import java.util.*;

public class ConfigLoader {

    private List<String> readFile(String filename) {
        String path = "src/resources/" + filename;
        File file = new File(path);
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.startsWith("#") && !line.toLowerCase().contains("name")) {
                    lines.add(line.trim());
                }
            }
        } catch(Exception e){
            System.err.println("Failed to read file: " + path);
        }

        return lines;
    }
    /* ================= HEROES ================= */

    public List<Hero> loadHeroes() {
        List<Hero> heroes = new ArrayList<>();
        HeroFactory factory = new HeroFactory();

        for (String line : readFile("Warriors.txt")) {
            heroes.add(factory.createWarrior(line));
        }
        for (String line : readFile("Paladins.txt")) {
            heroes.add(factory.createPaladin(line));
        }
        for (String line : readFile("Sorcerers.txt")) {
            heroes.add(factory.createSorcerer(line));
        }

        return heroes;
    }


    public List<Monster> loadMonsters() {
        MonsterFactory f = new MonsterFactory();
        List<Monster> list = new ArrayList<>();

        for(String l : readFile("Dragons.txt"))       list.add(f.createDragon(l));
        for(String l : readFile("Exoskeletons.txt"))  list.add(f.createExoskeleton(l));
        for(String l : readFile("Spirits.txt"))       list.add(f.createSpirit(l));

        return list;
    }

    public List<Spell> loadSpells(){
        ItemFactory f = new ItemFactory();
        List<Spell> spells = new ArrayList<>();

        for(String s : readFile("IceSpells.txt"))       spells.add(f.createSpell(s, SpellType.ICE));
        for(String s : readFile("FireSpells.txt"))      spells.add(f.createSpell(s, SpellType.FIRE));
        for(String s : readFile("LightningSpells.txt")) spells.add(f.createSpell(s, SpellType.LIGHTNING));

        return spells;
    }

    public List<Weapon> loadWeapons(){
        ItemFactory f = new ItemFactory();
        List<Weapon> list = new ArrayList<>();
        for(String s : readFile("Weaponry.txt")) list.add(f.createWeapon(s));
        return list;
    }

    public List<Armour> loadArmour(){
        ItemFactory f = new ItemFactory();
        List<Armour> list = new ArrayList<>();
        for(String s : readFile("Armory.txt")) list.add(f.createArmour(s));
        return list;
    }

    public List<Potion> loadPotions(){
        ItemFactory f = new ItemFactory();
        List<Potion> list = new ArrayList<>();
        for(String s : readFile("Potions.txt")) list.add(f.createPotion(s));
        return list;
    }
}