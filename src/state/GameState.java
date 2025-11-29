package state;

import core.GameContext;

public interface GameState {
    GameState handleInput(GameContext context, String input);
    void render(GameContext context);
    void onEnter(GameContext context);
}
