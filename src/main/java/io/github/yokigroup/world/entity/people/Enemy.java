package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.event.MessageHandler;

import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.entity.Position;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Enemy class represents an enemy entity in the game world.
 * It provides methods to manage and manipulate enemy entities.
 */
public class Enemy extends People {
    /**
     * This value represent the maximum distance at which the player will be in sight.
     */
    private static final double RADIUS_PLAYER = 400.00;
    /**
     * This value represent the maximum distance at which the enemy will.
     * go from his initial pos.
     */
    private static final double RADIUS_INITIAL_POS = 300.00;
    /**
     * SCALE Offset of for general movement.
     */
    private static final double SCALE = 2.2;
    /**
     * Velocity Offset of the enemy when following the player.
     */
    private static final double VELOCITY = 2.6;


    private Direction wanderDir = Direction.DEFAULT_STAND;
    /**
     * Default value for random directions.
     */
    private static final float DEFAULT_POOL_VALUE = 0.1f;
    private static final float BONUS_POOL_VALUE = 25f;
    private State state;
    /**
     * Constructs an Enemy object with the specified attributes.
     * @param pos The position of the Enemy
     * @param messageHandler handle for events
     */
    public Enemy(final Position pos, final MessageHandler messageHandler) {
        super(pos, messageHandler, "view/game/textures/enemy.png");
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
     * Return the Entity state.
     * @return Enemy.state
     */
    public final Enemy.State getState() {
        return this.state;
    }

    /**
     * Update calls this method when the player is too close to the enemy.
     * @param playerPos position of the player
     * @return vector to follow the player
     */
    private Vector2 follow(final Vector2 playerPos) {
        Objects.requireNonNull(playerPos, "Player position NULL in follow");

        return new Vector2Impl(playerPos.minus(this.getPos().getPosition()).normalize()
                .scale(VELOCITY));
    }

    /**
     * method used to randomize the direction where the enemy will go when
     * is wandering.
     * @return Vector2
     */
    private Vector2 wander() {
        final WeightedPoolImpl<Direction> directionWeightedPool = new WeightedPoolImpl<>();

        Stream.of(Direction.values())
                .filter(dir -> this.getInitialPos().inRadius(this.getPos()
                        .testMovePosition(dir.get().scale(SCALE)), RADIUS_INITIAL_POS))
                .filter(dir -> this.getPos().testMovePosition(dir.get().scale(SCALE)).isValid())
                .forEach(dir -> directionWeightedPool
                        .addElement(dir, dir == wanderDir ? BONUS_POOL_VALUE : DEFAULT_POOL_VALUE));
        if (directionWeightedPool.size() < 1) {
            directionWeightedPool.addElement(Direction.DEFAULT_STAND, DEFAULT_POOL_VALUE);
        }
        wanderDir = directionWeightedPool.getRandomizedElement();
           return wanderDir.get();

    }

    /**
     * return a new position given a vector, checking the hitBox of all the
     * entity in the tile.
     * @param vector vector given
     *
     */
    private void move(final Vector2 vector) {
        this.collisionCheck(vector);
        this.getMessageHandler().handle(PlayerCharacterSubmodule.class, player -> {
            if (this.getHitBox().collidesWith(player.getPlayerEntity().getHitBox()).isPresent()) {
                this.getMessageHandler().handle(FightSubmodule.class, fight -> {
                    fight.addEncounter();
                    this.shut();
                });
            }
        });
    }
    /**
     * Updates the state of the Enemy (switches between wander and follow).
     *
     */
    @Override
    public void update() {
        if (!this.getActive()) {
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
            this.setDirection(v);
            this.move(v.scale(SCALE));

        });

    }
}
