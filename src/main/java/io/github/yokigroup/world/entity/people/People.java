package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Objects;
import java.util.Optional;

/**
 * People class represents a generic person entity in the game world.
 * It provides methods to manage and manipulate people entities.
 */
public abstract class People extends Entity {

    /**
     * Direction where the People Entities first watch.
     */
    private static final Direction DEFAULT_DIRECTION = Direction.UP;
    /**
     * The position the entity is first spawned.
     */
    private final Position initialPos;

    /**
     * Direction where the player is looking.
     */
    private Direction direction;
    /**
     * People get up-dated only if active is true.
     */
    private boolean active;
    /**
     * Default hitBot of the people.
     */
    private static final double HITBOX_RADIUS = 50;
    private static final Vector2 DIMENSIONS = new Vector2Impl(HITBOX_RADIUS * 2, HITBOX_RADIUS * 2);
    /**
     * Constructs a People object with the specified attributes.
     * @param pos The position of the People
     * @param messageHandler handler of events
     * @param resourceURL String
     */
    public People(final Position pos, final MessageHandler messageHandler, final String resourceURL) {
        super(pos, new CircularHitbox(pos.getPosition(), HITBOX_RADIUS), messageHandler, DIMENSIONS, resourceURL);
        this.direction = DEFAULT_DIRECTION;
        this.active = true;
        this.initialPos = pos.copyOf();
    }

    @Override
    public final Hitbox getHitBox() {
        if (!active) {
            return null;
        }
        return super.getHitBox();
    }

    @Override
    public SpriteData getSpriteData() {
        if (!active) {
            return null;
        }
        return super.getSpriteData();
    }

    /**
     * Direction enum represents the possible default directions in the game world.
     */
    public enum Direction {
        /**
         * The entity doesn't move.
         */
        DEFAULT_STAND(new Vector2Impl(0, 0)),
        /**
         * UP direction.
         */
        UP(new Vector2Impl(0, -1)),
        /**
         * UP_RIGHT direction.
         */
        UP_RIGHT(new Vector2Impl(1, -1)),
        /**
         * RIGHT direction.
         */
        RIGHT(new Vector2Impl(1, 0)),
        /**
         * DOWN_RIGHT direction.
         */
        DOWN_RIGHT(new Vector2Impl(1, 1)),
        /**
         * DOWN direction.
         */
        DOWN(new Vector2Impl(0, 1)),
        /**
         * LEFT_DOWN direction.
         */
        LEFT_DOWN(new Vector2Impl(-1, 1)),
        /**
         * LEFT direction.
         */
        LEFT(new Vector2Impl(-1, 0)),
        /**
         * UP_LEFT direction.
         */
        UP_LEFT(new Vector2Impl(-1, -1));

        /**
         * Vector which represent the direction.
         */
        private final Vector2 vector;

        /**
         * Constructor of Direction.
         * @param vector vector
         */
        Direction(final Vector2 vector) {
            this.vector = vector;
        }

        /**
         * return the vector stored in this direction.
         * @return vector
         */
        public Vector2 get() {
            return vector;
        }
    }
    /**
     * Returns the direction in which the people entity is currently looking.
     * @return float The angle in radiant
     */
    public final Direction getDirection() {
        return this.direction;
    }

    /**
     * Turns a vector to the direction where the entity is looking.
     * @param v vector
     */
    public final void setDirection(final Vector2 v) {
        Objects.requireNonNull(v, "Vector passed to toDirection was null");
        if (v.getY() == 0 && v.getX() == 0) {
            return;
        }
        if (Math.abs(v.getX()) > Math.abs(v.getY())) {
            if (v.getX() > 0) {
                this.direction = Direction.RIGHT;
            } else {
                this.direction = Direction.LEFT;
            }
        } else {
            if (v.getY() > 0) {
                this.direction = Direction.DOWN;
            } else {
                this.direction = Direction.UP;
            }
        }
    }
    /**
     * Returns whether the people entity is active or not.
     * @return boolean True if the people entity is active, false otherwise
     */
    public final boolean getActive() {
        return this.active;
    }

    /**
     * Sets the people entity as active.
     */
    public final void setActive() {
        this.active = true;
    }

    /**
     * Sets the people entity as inactive.
     */
    public final void shut() {
        this.active = false;
    }

    /**
     * Returns the initial position of the entity.
     * @return Position Initial position of the entity
     */
    public final Position getInitialPos() {
        return this.initialPos.copyOf();
    }

    /**
     * If the position is not valid this method reset the position of the map to
     * the initial one, or to the centre of the tile if the entity is the player.
     */
    public abstract void resetPosition();

    /**
     * Check if the entity is colliding.
     * @param vector vector2
     */
    protected final void collisionCheck(final Vector2 vector) {
        if(!active) return;

        this.setPos(new PositionImpl(this.getPos().getPosition().plus(vector)));
        this.getMessageHandler().handle(GameMapSubmodule.class, map -> {
            map.getHitboxesOnCurrentTile().stream()
                    .map(block -> this.getHitBox().collidesWith(block))
                    .filter(Optional::isPresent)
                    .forEach(block -> this.setPos(new PositionImpl(this.getPos().getPosition().plus(block.get()))));
        });
        this.getMessageHandler().handle(GameMapSubmodule.class, map -> {

            map.getEntitiesOnCurrentTile().stream()
                    .map(entity -> this.getHitBox().collidesWith(entity.getHitBox()))
                    .filter(Optional::isPresent)
                    .forEach(entity -> this.setPos(new PositionImpl(this.getPos().getPosition().plus(entity.get()))));

        });

    }

}
