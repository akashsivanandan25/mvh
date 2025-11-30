# Run Instructions

Compile

```bash
javac --release 8 -d out $(find src -name "*.java")
```

Run
```bash
java -cp out core.Game
```

# Package	Responsibility

* core: State machine, input + UI routing, execution context.
* state: Game flow: selection → exploration → battle → quit.
* character: Hero + monster entities with stats, scaling, combat interfaces.
* battle: Turn engine, actions, damage resolution, logging.
* item: Weapons, armor, potions, spells. 
* factory: Instantiates monsters independent of engine logic.

Responsibilities are weakly coupled 

## Design Principles
* State and combat logic kept inside hero / monster classes
* All actions share a common interface
* New items / monsters / spells etc can be easily added with no modification needed
* Classes handle only one concern: either UI or battle or entity-specific logic or data 
* To add new monsters, we can simply update the Monster factory
* New in-game items can be added by modifying the config txt files

## Extra features implemented
* Players can cancel spell/potion/equipment screens without losing their whole turn
* Danger indicators shown to players if they choose to attack high level monsters
* All heroes can choose their actions first, letting the round resolve itself as a batch
* Battle actions are logged in a battle log
