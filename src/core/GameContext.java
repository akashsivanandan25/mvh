package core;

import party.Party;
import world.World;

public class GameContext {
    private final World world;
    private final Party party;
    private final Renderer renderer;
    private final InputHandler input;

    public GameContext(World world, Party party, Renderer renderer, InputHandler input) {
        this.world = world;
        this.party = party;
        this.renderer = renderer;
        this.input = input;
    }

    public World world()      { return world; }
    public Party party() { return party; }
    public Renderer ui()      { return renderer; }
    public InputHandler in()  { return input; }
}