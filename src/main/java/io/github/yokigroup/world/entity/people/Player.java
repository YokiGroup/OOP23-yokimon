package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.Position;

import java.util.Optional;

/**
 * This class describe the behaviour of the player entity in game map.
 */
public class Player extends People {

    private static final double SCALE = 10;
    /**
     * Constructor of the player.
     * @param id identification id
     * @param pos initial Pos
     * @param messageHandler handler of Events
     */
    public Player(final int id, final Position pos,
                  final MessageHandler messageHandler) {
        super(id, pos, messageHandler);
    }

    /**
     * given a vector, it changes the position of the player
     * around the map.
     * @param vector vector
     */
    private void move(final Vector2 vector) {

        this.setPos(new PositionImpl(this.getPos().getPosition().plus(vector)));
        this.nonEntityCollisionCheck();
        this.getMessageHandler().handle(GameMapSubmodule.class, map -> {

            map.getEntitiesOnCurrentTile().stream()
                    .map(entity -> this.getHitBox().collidesWith(entity.getHitBox()))
                    .filter(Optional::isPresent)
                    .forEach(entity -> this.setPos(new PositionImpl(this.getPos().getPosition().plus(entity.get()))));

        });
    }

    /**
     * If the player is out of map, it brings his position back to the centre of the tile.
     */
    @Override
    public void resetPosition() {
        if (!this.getPos().isValid()) {
            this.getMessageHandler().handle(GameMapSubmodule.class, map -> {
                this.setPos(new PositionImpl(new Vector2Impl( (double) GameMap.TILE_DIMENSIONS.x() / 2,
                        (double) GameMap.TILE_DIMENSIONS.y() / 2 )));
            });
        }
    }

    /**
     * upDate move the player according to keyboards input taken.
     */
    @Override
    public void update() {
        Vector2 dir = new Vector2Impl(0, 0);
        if (!this.getIsActive()) {
            return;
        }
        resetPosition();
        move(dir.scale(SCALE));
    }
}
