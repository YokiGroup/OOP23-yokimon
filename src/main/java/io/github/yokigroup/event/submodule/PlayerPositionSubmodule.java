package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;

/**
 * Handles player position updates.
 * @author Giovanni Paone
 */
public class PlayerPositionSubmodule extends Submodule {
/*
    private final Entity player;
    private final TileMap gameMap;
*/

    // FIXME Replace with proper implementation
    /**
     * THIS WILL BE REMOVED AS SOON AS GameMap IS FINISHED.
     */
    public enum Direction {
        /**
         * Up direction.
         */
        UP,
        /**
         * Down direction.
         */
        DOWN,
        /**
         * Left direction.
         */
        LEFT,
        /**
         * Right direction.
         */
        RIGHT
    }

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public PlayerPositionSubmodule(final MessageHandler handler) {
        super(handler);
    }

    // TODO Change Direction reference
    /**
     * attempts to change the tile of the player relative to the one it's in currently.
     * @param dir direction to change the tile from, relative to the player's current tile
     */
    public void changeTile(final Direction dir) {
        //TODO change tile of player by going in dir
    }

    /**
     * moves player as specified by the input vector.
     * @param delta vector to move the player by
     */
    public void movePlayerBy(final Vector2 delta) {
        // TODO move player by delta
    }

    @Override
    public void process() {
        // TODO collision check
    }
}
