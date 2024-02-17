package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;

/**
 * Abstract class of a submodule containing information about the player entity.
 * @see io.github.yokigroup.world.entity.people.Player
 * @author Giovanni Paone
 */
public abstract class PlayerCharacterSubmoduleAbs extends Submodule {

    /**
     * @param handler to init the submodule with
     */
    public PlayerCharacterSubmoduleAbs(final MessageHandler handler) {
        super(handler);
    }

    /**
     * attempts to change the tile of the player relative to the one it's in currently.
     * @param dir direction to change the tile from, relative to the player's current tile
     */
    public abstract void changeTile(Direction dir);

    /**
     * @return position of player character.
     */
    public abstract Position getPosition();

    /**
     * @return Entity represented by the player
     */
    public abstract Entity getPlayerEntity();

    /**
     * moves player as specified by the input vector.
     *
     * @param delta vector to move the player by
     */
    public abstract void movePlayerBy(Vector2 delta);

    @Override
    public void update() {
        // TODO collision check
    }
}
