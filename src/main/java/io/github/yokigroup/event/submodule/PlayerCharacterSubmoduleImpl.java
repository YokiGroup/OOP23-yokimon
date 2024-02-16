package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;

/**
 * Handles player updates.
 * @author Giovanni Paone
 */
public final class PlayerCharacterSubmoduleImpl extends PlayerCharacterSubmodule {
/*
    private final Entity player;
*/

    // FIXME Replace with proper implementation

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public PlayerCharacterSubmoduleImpl(final MessageHandler handler) {
        super(handler);
    }

    // TODO Change Direction reference
    @Override
    public void changeTile(final Direction dir) {
        //TODO change tile of player by going in dir
    }

    @Override
    public Position getPosition() {
        // FIXME implement
        return new PositionImpl(null);
    }

    @Override
    public void movePlayerBy(final Vector2 delta) {
        // TODO move player by delta
    }

}
