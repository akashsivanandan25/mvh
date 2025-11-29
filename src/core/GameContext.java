package core;

import world.World;
import character.Hero;
import java.util.List;

public class GameContext {
    private final World world;
    private final List<Hero> party;
    private final Renderer renderer;
    private final InputHandler input;

    public GameContext(World world, List<Hero> party, Renderer renderer, InputHandler input) {
        this.world = world;
        this.party = party;
        this.renderer = renderer;
        this.input = input;
    }

    public World world()      { return world; }
    public List<Hero> party() { return party; }
    public Renderer ui()      { return renderer; }
    public InputHandler in()  { return input; }
}