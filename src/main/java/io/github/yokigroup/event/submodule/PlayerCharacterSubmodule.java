package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.entity.Position;

public abstract class PlayerCharacterSubmodule extends Submodule {
    public PlayerCharacterSubmodule(MessageHandler handler) {
        super(handler);
    }

    /**
     * attempts to change the tile of the player relative to the one it's in currently.
     *
     * @param dir direction to change the tile from, relative to the player's current tile
     */
    public abstract void changeTile(Direction dir);

    /**
     * @return position of player character.
     */
    public abstract Position getPosition();

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
