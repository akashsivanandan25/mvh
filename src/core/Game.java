package core;

import character.Monster;
import factory.ConfigLoader;
import factory.HeroFactory;
import factory.MonsterFactory;
import party.Party;
import state.*;
import world.World;
import character.Hero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Game {

    private GameState state;
    private final GameContext context;

    public static void main(String[] args) {
        System.out.println("=== GAME START ===");
        showInstructions();
        ConfigLoader loader = new ConfigLoader();
        List<Hero> availableHeroes = loader.loadHeroes();
        List<Monster> spawnPool     = loader.loadMonsters();
        System.out.println("Loaded " + availableHeroes.size() + " heroes");
        System.out.println("Loaded " + spawnPool.size()     + " monsters");

        MonsterFactory.setPool(spawnPool);

        List<Hero> partyHeroes = HeroFactory.createParty(availableHeroes, new InputHandler(), new Renderer());
        Party party = new Party(partyHeroes);

        World world = new World();

        GameContext context = new GameContext(world, party, new Renderer(), new InputHandler());
        GameState state = new ExplorationState();
        state.onEnter(context);

        while (!(state instanceof QuitState)) {
            state.render(context);

            if (state instanceof BattleState) {
                GameState next = state.handleInput(context, "");

                if (next != state) {
                    state = next;
                    state.onEnter(context);
                    continue;
                }
            }

            context.ui().msg(">");
            String input = context.in().nextString();

            GameState next = state.handleInput(context, input);

            if (next != state) {
                state = next;
                state.onEnter(context);
            }
        }
        System.out.println("=== GAME END ===");
    }

    private static void showInstructions() {
        try {
            Files.lines(Paths.get("src/resources/GameInstructions.txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Instruction file missing.");
        }
    }

    public Game(World world, List<Hero> party) {
        Party gameParty = new Party(party);
        this.context = new GameContext(
                world,
                gameParty,
                new Renderer(),
                new InputHandler()
        );
        this.state = new ExplorationState();   // starting state
        state.onEnter(context);
    }

    public void run() {
        while (!(state instanceof QuitState)) {
            state.render(context);
            context.ui().msg(">");
            String input = context.in().nextString();
            GameState next = state.handleInput(context, input);

            if (next != state) {
                state = next;
                state.onEnter(context);
            }
        }
    }
}