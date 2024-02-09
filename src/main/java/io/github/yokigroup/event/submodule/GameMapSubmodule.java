package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.tile.Tile;

/**
 * Submodule containing a GameMap and relevant methods to query the map's state.
 * @author Giovanni Paone
 */
public class GameMapSubmodule extends Submodule {
    private GameMap gameMap;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public GameMapSubmodule(final MessageHandler handler) {
        super(handler);
        // FIXME replace with actual implementation
        this.gameMap = new GameMap() {
            @Override
            public Tile getTileAt(final Pair<Integer, Integer> position) {
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
    public GameMap getGameMap() {
        return this.gameMap;
    }

    /**
     * Gets the entities contained in the Tile the player's currently on.
     */
    public void getEntitiesOnCurrentTile() {
        // FIXME consider whether this should be here
        // TODO implement
    }

    @Override
    public void process() {
        /*
        this function should query the player's position and consider whether to change Tile if the player is crossing
        the tile border.
         */
        // TODO implement
    }
}
