package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;

/**
 * Handles player updates.
 * @author Giovanni Paone
 */
public final class PlayerCharacterSubmodule extends Submodule {
/*
    private final Entity player;
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
    public PlayerCharacterSubmodule(final MessageHandler handler) {
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
     * @return position of player character.
     */
    public Position getPosition() {
        // FIXME implement
        return new PositionImpl(null);
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
