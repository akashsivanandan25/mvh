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

    private boolean statCheck(Hero h, Monster m, GameContext ctx) {

        boolean bad = false;

        if (m.getDefence() > h.getStrength() * 1.3) {
            ctx.ui().msg("⚠ Monster defence is high. Your attacks may do little damage.");
            bad = true;
        }

        if (m.getBaseDamage() > h.getHealth()) {
            ctx.ui().msg("⚠ This enemy can one-shot you.");
            bad = true;
        }

        if (m.getLevel() > h.getLevel() + 2) {
            ctx.ui().msg("⚠ Level gap detected. Fight is risky.");
            bad = true;
        }

        return bad;
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

        Hero current = nextUnactedHero(context);
        if (current == null) {
            context.ui().msg("\n⏳ Waiting for round to resolve...");
            context.ui().msg("Press any key to continue...");
            return;
        }

        context.ui().msg("\n▶ " + current.getName() + "'s turn");
        context.ui().msg("Choose: [A]ttack [S]pell [P]otion [E]quip [Q]uit");

        for (String entry : battle.getLog())
            context.ui().msg(entry);
        battle.getLog().clear();

    }

    @Override
    public GameState handleInput(GameContext context, String input) {

        Hero current = nextUnactedHero(context);
        if (current == null) {
            battle.executeRound(pendingActions);
            pendingActions.clear();

            context.ui().msg("\n=== Round Complete ===");
            for (String log : battle.getLog()) context.ui().msg(log);
            battle.getLog().clear();

            if (battle.isComplete()) {
                if (battle.heroesWon()) {
                    int gold = battle.calcGoldReward();
                    int xp   = battle.calcXPReward();
                    context.party().addGold(gold);
                    context.party().addXP(xp);
                    context.ui().msg("Victory! +" + gold + " Gold +" + xp + " XP");
                    return new ExplorationState();
                }
                context.ui().msg("You lose!");
                return new QuitState();
            }
            return this;
        }
        if (input == null || input.trim().isEmpty())
            return this;

        input = input.toLowerCase().trim();

        if (input.equals("q")) return new QuitState();

        switch (input) {
            case "a":
                Monster target = chooseMonster(context);
                if (target == null) return this;

                if (statCheck(current, target, context)) {
                    context.ui().msg("Proceed anyway? (y/n)");
                    String choice = context.in().nextString().trim().toLowerCase();
                    if (!choice.equals("y")) return this;
                }
                pendingActions.add(new AttackAction(current, target));
                break;

            case "s":
                Monster m2 = chooseMonster(context);
                if (m2 == null) return this;

                Spell spell = current.chooseSpell(context);
                if (spell == null) return this;

                pendingActions.add(new SpellAction(current, m2, spell));
                break;

            case "p":
                Potion p = current.choosePotion(context);
                if (p == null) return this;
                pendingActions.add(new UsePotionAction(current, p));
                break;

            case "e":
                Equipment eq = current.chooseEquipment(context);
                if (eq == null) return this;
                pendingActions.add(new EquipAction(current, eq));
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
        for (int i = 0; i < battle.getMonsters().size(); i++) {
            Monster m = battle.getMonsters().get(i);
            if (m.getHealth() > 0)
                context.ui().msg(i + ":  " + m.getName() + " HP = " + m.getHealth());
        }

        String raw = context.in().nextString().trim();
        int idx;
        try { idx = Integer.parseInt(raw); }
        catch(Exception ignore) { return null; }

        if (idx < 0 || idx >= battle.getMonsters().size())
            return null;

        return battle.getMonsters().get(idx);
    }
}
