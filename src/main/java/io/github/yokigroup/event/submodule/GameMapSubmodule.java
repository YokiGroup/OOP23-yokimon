package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.tile.Tile;

public class GameMapSubmodule extends Submodule{
    private GameMap gameMap;

    public GameMapSubmodule(MessageHandler handler) {
        super(handler);
        // FIXME replace with actual implementation
        this.gameMap = new GameMap() {
            @Override
            public Tile getTileAt(Pair<Integer, Integer> position) {
                return null;
            }

            @Override
            public Pair<Integer, Integer> getPlayerWorldPosition() {
                return null;
            }
        };
    }

    /**
     * @return Submodule's GameMap reference
     */
    public GameMap getGameMap(){
        return this.gameMap;
    }

    @Override
    public void process() {

    }
}
