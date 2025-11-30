package state;

import battle.*;
import character.Hero;
import character.Monster;
import core.GameContext;
import factory.MonsterFactory;
import item.Equipment;
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

        context.ui().msg("\n----- MONSTERS -----");
        for (Monster m : battle.getMonsters()) {
            context.ui().msg(m.getName() + " HP:" + m.getHealth()
                    + " DMG:" + m.getBaseDamage()
                    + " DEF:" + m.getDefence()
                    + " Dodge:" + m.getDodgeChance());
        }

        context.ui().msg("\nChoose an action:");
        context.ui().msg("[A]ttack   [S]pell   [P]otion   [E]quip   [Q]uit");
        for (String entry : battle.getLog())
            context.ui().msg(entry);
        battle.getLog().clear();

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
                context.ui().msg("Invalid input.");
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
            if (a.getAttacker().equals(h)){return true;}
        return false;
    }

    public Monster chooseMonster(GameContext context) {
        context.ui().msg("Choose a monster:");
        int count = 0;
        for (Monster m : battle.getMonsters()) {
            if (m.getHealth() > 0) {
                context.ui().msg(count + ":  " + m.getName() + " HP = " + m.getHealth());
                count++;
            }
        }
        int userInput = context.in().nextInt();
        if (userInput >= battle.getMonsters().size() || userInput < 0) {
            return null;
        }
        return battle.getMonsters().get(userInput);
    }
}