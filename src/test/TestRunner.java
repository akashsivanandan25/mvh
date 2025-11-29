package test;

import core.*;
import state.*;
import world.*;
import character.*;
import factory.ConfigLoader;

import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("=== SYSTEM TEST START ===");

        /* 1️⃣ LOAD GAME DATA */
        ConfigLoader loader = new ConfigLoader();
        List<Hero> heroes   = loader.loadHeroes();
        List<Monster> mobs  = loader.loadMonsters();
        System.out.println("Loaded Heroes: " + heroes.size());
        System.out.println("Loaded Monsters: " + mobs.size());

        // Display first 3 heroes
        for(int i=0; i < Math.min(3, heroes.size()); i++){
            System.out.println(" > " + heroes.get(i).getName());
        }

        /* 2️⃣ BUILD WORLD */
        World world = new World();
        System.out.println("\nWorld created. Start Pos = " +
                world.getPartyPosition().getX() + "," + world.getPartyPosition().getY());

        /* 3️⃣ CONTEXT + GAME LOOP */
        GameContext context = new GameContext(
                world,
                heroes.subList(0, Math.min(3, heroes.size())), // choose first 1–3 heroes
                new Renderer(),
                new InputHandler()
        );

        GameState state = new ExplorationState();
        state.onEnter(context);

        /* 4️⃣ BASIC LOOP FOR EXPLORATION + MARKET */
        while(!(state instanceof QuitState)) {

            state.render(context);

            context.ui().msg("Enter Command: ");
            String input = context.in().nextString();
            GameState next = state.handleInput(context,input);

            // state change detection
            if(next != state){
                state = next;
                state.onEnter(context);
            }
        }

        System.out.println("=== TEST END ===");
    }
}