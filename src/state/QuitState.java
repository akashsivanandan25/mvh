package state;

import core.GameContext;

public class QuitState implements GameState {
    @Override
    public GameState handleInput(GameContext context, String input) {
//        context.ui().msg("Are you sure you want to quit? Press Y to confirm and N to go back");
        char playerChar = input.toLowerCase().trim().charAt(0);
        if (playerChar == 'y') {
            System.exit(0);
            // quit game
        }
        else {
        }

        return this;
    }

    @Override
    public void render(GameContext context) {

    }

    @Override
    public void onEnter(GameContext context) {

    }
}
