package io.github.yokigroup.event.submodule;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.Entity;

/**
 * Handles player position updates.
 */
public class PlayerPositionSubmodule implements Submodule {
    private final Entity player;
    private final GameMap map;

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
     * Instantiates a PlayerPositionSubmodule.
     * @param player player to keep track of the position to
     * @param map map containing player
     */
    public PlayerPositionSubmodule(final Entity player, final GameMap map) {
        this.player = player;
        this.map = map;
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
