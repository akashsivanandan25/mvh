package party;
import character.*;
import world.Position;

import java.util.*;

public class Party {
    private List<Hero> heroes;
    private Position position;


    public Party(List<Hero> heroes){
        this.heroes = heroes;
        this.position = new Position(0,0);
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public boolean allFainted(){
        int count = 0;
        for (Hero hero : heroes){
            if(hero.isFainted()){
                count ++;
            }
        }

        if (count == heroes.size()){
            return true;
        }
        return false;
    }


    public void reviveAll(){
        for (Hero hero : heroes){
            if (hero.isFainted()){
                hero.heal(50);
            }
        }
    }


    public int getHighestLevel(){
        int maxLevel = -1;
        for  (Hero hero : heroes){
            if (hero.getLevel() > maxLevel){
                maxLevel = hero.getLevel();
            }
        }
        return maxLevel;
    }

    public void addGold(int gold) {
        for  (Hero hero : heroes){
            hero.setGold(hero.getGold() + gold);
        }
    }

    public void addXP(int xp) {
        for (Hero hero : heroes){
            hero.setXP(hero.getXP() + xp);
        }
    }
}
