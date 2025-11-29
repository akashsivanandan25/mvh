package core;

import state.*;
import world.World;
import character.Hero;
import java.util.List;

public class Game {

    private GameState state;
    private final GameContext context;

    public Game(World world, List<Hero> party) {
        this.context = new GameContext(
                world,
                party,
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