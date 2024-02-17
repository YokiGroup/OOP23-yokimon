package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.event.MessageHandler;

import io.github.yokigroup.event.submodule.*;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Enemy class represents an enemy entity in the game world.
 * It provides methods to manage and manipulate enemy entities.
 */
public class Enemy extends People {
    /**
     * This value represent the maximum distance at which the player will be in sight.
     */
    private static final double RADIUS_PLAYER = 60.00;
    /**
     * This value represent the maximum distance at which the enemy will.
     * go from his initial pos.
     */
    private static final double RADIUS_INITIAL_POS = 50.00;
    /**
     * SCALE Offset of for general movement.
     */
    private static final double SCALE = 10;
    /**
     * Velocity Offset of the enemy when following the player.
     */
    private static final double VELOCITY = 1.5;

    /**
     * Default value for random directions.
     */
    private static final float DEFAULT_POOL_VALUE = 0.1f;
    private State state;
    /**
     * Constructs an Enemy object with the specified attributes.
     * @param pos The position of the Enemy
     * @param messageHandler handle for events
     */
    public Enemy(final Position pos, final MessageHandler messageHandler) {
        super(pos, messageHandler);
        this.state = State.WANDER;

    }

    /**
     * Reset the position of the enemy to the initial one if it's not valid.
     */
    @Override
    public void resetPosition() {

            this.state = State.WANDER;
            this.setPos(this.getInitialPos());

    }

    /**
     * Represents the state of the Enemy (wander or follow).
     */
    public enum State {
        /**
         * In this state the Enemy wander around his initial pos.
         */
        WANDER,
        /**
         * In this stale the Enemy will follow the player.
         */
        FOLLOW
    }

    /**
     * Update calls this method when the player is too close to the enemy.
     * @param playerPos position of the player
     * @return vector to follow the player
     */
    private Vector2 follow(final Vector2 playerPos) {
        Objects.requireNonNull(playerPos, "Player position NULL in follow");
        return new Vector2Impl(this.getPos().getPosition().minus(playerPos)
                .normalize().scale(VELOCITY).plus(this.getPos().getPosition()));
    }

    /**
     * method used to randomize the direction where the enemy will go when
     * is wandering.
     * @return Vector2
     */
    private Vector2 wander() {
        WeightedPoolImpl<Direction> directionWeightedPool = new WeightedPoolImpl<>();

        Stream.of(Direction.values())
                .filter(dir -> this.getInitialPos().inRadius(this.getPos().testMovePosition(dir.get().scale(SCALE)), RADIUS_INITIAL_POS))
                .filter(dir -> this.getPos().testMovePosition(dir.get().scale(SCALE)).isValid())
                .forEach(dir -> directionWeightedPool.addElement(dir, DEFAULT_POOL_VALUE));
        if (directionWeightedPool.size() < 1) {
            directionWeightedPool.addElement(Direction.DEFAULT_STAND, DEFAULT_POOL_VALUE);
        }
           return directionWeightedPool.getRandomizedElement().get();

    }

    /**
     * return a new position given a vector, checking the hitBox of all the
     * entity in the tile.
     * @param vector vector given
     *
     */
    private void move(final Vector2 vector) {

        this.setPos(new PositionImpl(this.getPos().getPosition().plus(vector)));
        this.nonEntityCollisionCheck();
        this.getMessageHandler().handle(GameMapSubmodule.class, map -> {

        for (Entity entity : map.getEntitiesOnCurrentTile()) {
             if (entity instanceof Player && this.getHitBox().collidesWith(entity.getHitBox()).isPresent()) {
                 this.getMessageHandler().handle(FightSubmodule.class, fight -> {
                     this.getMessageHandler().handle(PartySubmodule.class, playerParty -> {
                         fight.addEncounter();
                         this.shut();
                     });
                 });
             }
        }

            map.getEntitiesOnCurrentTile().stream()
                    .map(entity -> this.getHitBox().collidesWith(entity.getHitBox()))
                    .filter(Optional::isPresent)
                    .forEach(block -> this.setPos(new PositionImpl(this.getPos().getPosition().plus(block.get()))));

            });
    }
    /**
     * Updates the state of the Enemy (switches between wander and follow).
     *
     */
    @Override
    public void update() {
        if (!this.getIsActive()) {
            return;
        }
        this.getMessageHandler().handle(PlayerCharacterSubmodule.class, pos -> {
            Objects.requireNonNull(pos.getPosition().getPosition(), "Position of the player isNull");
            if (!this.getPos().isValid()) {
                resetPosition();
            }
            if (this.getPos().inRadius(pos.getPosition(), RADIUS_PLAYER)) {
                this.state = State.FOLLOW;
            }
            Vector2 v;
            if (this.state == State.WANDER) {
                v = new Vector2Impl(wander());
            } else {
                v = new Vector2Impl(follow(pos.getPosition().getPosition()));
            }
            this.toDirection(v);
            this.move(v.scale(SCALE));

        });

    }
}
