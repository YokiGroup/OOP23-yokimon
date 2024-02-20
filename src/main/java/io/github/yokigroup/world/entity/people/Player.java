package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.Position;


/**
 * This class describe the behaviour of the player entity in game map.
 */
public class Player extends People {

    private static final double SCALE = 10;
    private static final double RAY_HITBOX = 50;
    /**
     * Constructor of the player.
     * @param pos initial Pos
     * @param messageHandler handler of Events
     */
    public Player(final Position pos,
                  final MessageHandler messageHandler) {
        super(pos, messageHandler, RAY_HITBOX, "player.png");
    }

    /**
     * Given a vector, it changes the position of the player.
     * around the map.
     * @param vector vector
     */
    public void move(final Vector2 vector) {
        if (!this.getPos().isValid()) {
            resetPosition();
        }
        this.collisionCheck(vector.scale(SCALE));
    }

    /**
     * It brings his position back to the centre of the tile.
     */
    @Override
    public void resetPosition() {
            this.getMessageHandler().handle(GameMapSubmodule.class, map -> {
                this.setPos(new PositionImpl(new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2,
                        (double) GameMap.TILE_DIMENSIONS.y() / 2)));
            });
    }

    /**
     * Do not call this method
     */
    @Override
    public void update() { }
}
