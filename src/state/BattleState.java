package state;

import battle.*;
import character.Hero;
import character.Monster;
import core.GameContext;
import factory.MonsterFactory;
import item.Potion;
import item.Spell;

import java.util.ArrayList;
import java.util.List;

public class BattleState implements GameState {

    private Battle battle;
    private boolean awaitingActions = true;
    private final List<BattleAction> pendingActions = new ArrayList<>();

    @Override
    public void onEnter(GameContext context) {
        this.battle = new Battle(context.party(), new MonsterFactory());
        context.ui().msg("=== A battle begins! ===");
    }

    @Override
    public void render(GameContext context) {

        context.ui().msg("\n----- HEROES -----");
        for (Hero h : context.party().getHeroes()) {
            context.ui().msg(h.getName() + " HP:" + h.getHealth()
                    + " MP:" + h.getMP()
                    + " STR:" + h.getStrength()
                    + " DEX:" + h.getDex()
                    + " AGI:" + h.getAgility());
        }

        System.out.println("\n----- MONSTERS -----");
        for (Monster m : battle.getMonsters()) {
            context.ui().msg(m.getName() + " HP:" + m.getHealth()
                    + " DMG:" + m.getBaseDamage()
                    + " DEF:" + m.getDefence()
                    + " Dodge:" + m.getDodgeChance());
        }

        System.out.println("\nChoose an action:");
        System.out.println("[A]ttack   [S]pell   [P]otion   [E]quip   [Q]uit");
    }

    @Override
    public GameState handleInput(GameContext context, String input) {

        input = input.toLowerCase().trim();

        if (input.equals("q")){ return new QuitState();}

        Hero current = nextUnactedHero(context);

        if (current == null) {
            battle.executeRound(pendingActions);
            pendingActions.clear();

            if (battle.isComplete()) {
                return battle.heroesWon() ? new ExplorationState() : new QuitState();
            }

            return this;
        }

        switch (input) {
            case "a":
                Monster target = battle.getFirstAliveMonster();
                pendingActions.add(new AttackAction(current, target));
                break;

            case "s":
                Spell spell = current.chooseSpell(context);
                Monster m = battle.getFirstAliveMonster();
                pendingActions.add(new SpellAction(current, m, spell));
                break;

            case "p":
                Potion p = current.choosePotion(context);
                pendingActions.add(new UsePotionAction(current, p));
                break;

            case "e":
                pendingActions.add(new EquipAction(current, current.chooseEquipment(context)));
                break;

            default:
                System.out.println("Invalid input.");
                return this;
        }
        return this;
    }

    private Hero nextUnactedHero(GameContext ctx) {
        for (Hero h : ctx.party().getHeroes()) {
            if (!h.isFainted() && !heroAlreadyQueued(h)) {
                return h;
            }
        }
        return null;
    }

    private boolean heroAlreadyQueued(Hero h) {
        for (BattleAction a : pendingActions)
            if (a instanceof AttackAction && ((AttackAction) a).getAttacker() == h) return true;
        return false;
    }
}