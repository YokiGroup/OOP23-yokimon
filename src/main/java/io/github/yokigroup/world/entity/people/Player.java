package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmoduleImpl;
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
    /**
     * Constructor of the player.
     * @param pos initial Pos
     * @param messageHandler handler of Events
     */
    public Player(final Position pos,
                  final MessageHandler messageHandler) {
        super(pos, messageHandler);
    }

    /**
     * given a vector, it changes the position of the player
     * around the map.
     * @param vector vector
     */
    private void move(final Vector2 vector) {
       this.collisionCheck(vector);
    }

    /**
     * It brings his position back to the centre of the tile.
     */
    @Override
    public void resetPosition() {

            this.getMessageHandler().handle(GameMapSubmoduleImpl.class, map -> {
                this.setPos(new PositionImpl(new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2,
                        (double) GameMap.TILE_DIMENSIONS.y() / 2)));
            });

    }

    /**
     * upDate move the player according to keyboards input taken.
     */
    @Override
    public void update() {
        if (!this.getActive()) {
            return;
        }
        Vector2 dir = new Vector2Impl(0, 0);
        if (!this.getPos().isValid()) {
            resetPosition();
        }
        move(dir.scale(SCALE));
    }
}
