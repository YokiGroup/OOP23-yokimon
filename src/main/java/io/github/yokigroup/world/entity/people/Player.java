package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Position;

import java.util.List;
import java.util.Optional;

/**
 * This class describe the behaviour of the player entity in game map
 */
public class Player extends People {

    /**
     * Constructor of the player.
     * @param id identification id
     * @param pos initial Pos
     * @param hitBox hitBox of the player
     * @param party expandable party of the player
     * @param messageHandler handler of Events
     */
    public Player(final int id, final Position pos, final Hitbox hitBox, final List<Yokimon> party,
                  final MessageHandler messageHandler) {
        super(id, pos, hitBox, party, messageHandler);
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
        if (!this.getPos().isValid(this.getMessageHandler())) {
            this.getMessageHandler().handle(GameMapSubmodule.class, map -> {
                this.setPos(new PositionImpl(new Vector2Impl( (double) map.getGameMap().getTileDimensions().x()/2,
                        (double) map.getGameMap().getTileDimensions().y() / 2 )));
            });
        }
    }

    /**
     * upDate move the player according to keyboards input taken.
     */
    @Override
    public void update() {
        Vector2 dir = new Vector2Impl(0 , 0);
        if (!this.getIsActive()) {
            return;
        }
        resetPosition();
        move(dir);
    }
}
