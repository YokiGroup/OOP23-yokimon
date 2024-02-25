package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Arrays;
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
     * Ray of the hitBox.
     */
    private final double dimensions;

    /**
     * Constructs a People object with the specified attributes.
     *
     * @param pos            The position of the People
     * @param messageHandler handler of events
     * @param dimensions     Ray of the circularHitbox
     * @param resourceURL    String
     */
    public People(final Position pos, final MessageHandler messageHandler,
                  final double dimensions, final String resourceURL) {
        super(pos, new CircularHitbox(pos.getPosition(), dimensions / 2), messageHandler,
                new Vector2Impl(dimensions, dimensions), resourceURL);
        this.direction = DEFAULT_DIRECTION;
        this.initialPos = pos.copyOf();
        this.dimensions = dimensions;
    }

    @Override
    public final Hitbox getHitBox() {
        if (!isActive()) {
            return null;
        }
        return super.getHitBox();
    }

    /**
     * Return the data sprite of this entity.
     *
     * @return SpriteData
     */
    @Override
    public SpriteData getSpriteData() {
        if (!isActive()) {
            return null;
        }
        final SpriteData superSpriteData = super.getSpriteData();
        return new SpriteData(
                superSpriteData.spriteURL(),
                superSpriteData.position(),
                superSpriteData.dim(),
                superSpriteData.priority(),
                getDirection().get().getX() == -1
        );
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
        private final transient Vector2 vector;

        /**
         * Return the direction where this People is looking.
         *
         * @param vector givenDirection
         * @return Direction
         */
        public static Direction valueOf(final Vector2 vector) {
            return Arrays.stream(Direction.values()).filter(v -> vector.equals(v.get())).findFirst().orElse(null);
        }

        /**
         * Constructor of Direction.
         *
         * @param vector vector
         */
        Direction(final Vector2 vector) {
            this.vector = vector;
        }

        /**
         * return the vector stored in this direction.
         *
         * @return vector
         */
        public Vector2 get() {
            return vector;
        }
    }

    /**
     * Returns the direction in which the people entity is currently looking.
     *
     * @return float The angle in radiant
     */
    public final Direction getDirection() {
        return this.direction;
    }

    /**
     * Turns a vector to the direction where the entity is looking.
     *
     * @param v vector
     */
    public final void setDirection(final Vector2 v) {
        Objects.requireNonNull(v, "Vector passed to toDirection was null");
        if (v.getY() == 0 && v.getX() == 0) {
            return;
        }
        this.direction = Direction.valueOf(new Vector2Impl(Math.signum(v.getX()), Math.signum(v.getY())));
    }

    /**
     * Returns the initial position of the entity.
     *
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
     * Return the ray value of this People Entity.
     *
     * @return double
     */
    public final double getDimensions() {
        return this.dimensions;
    }

    /**
     * Check if the entity is colliding.
     *
     * @param vector vector2
     */
    protected final void collisionCheck(final Vector2 vector) {
        if (!isActive()) {
            return;
        }

        this.setPos(new PositionImpl(this.getPos().getPosition().plus(vector)));
        this.getMessageHandler().handle(GameMapSubmodule.class, map -> {
            map.getHitboxesOnCurrentTile().stream()
                    .map(block -> Objects.requireNonNull(this.getHitBox()).collidesWith(block))
                    .filter(Optional::isPresent)
                    .forEach(block -> this.setPos(new PositionImpl(this.getPos().getPosition().plus(block.get()))));
        });
        this.getMessageHandler().handle(GameMapSubmodule.class, map -> {

            map.getEntitiesOnCurrentTile().stream()
                    .map(entity -> Objects.requireNonNull(this.getHitBox()).collidesWith(entity.getHitBox()))
                    .filter(Optional::isPresent)
                    .forEach(entity -> this.setPos(new PositionImpl(this.getPos().getPosition().plus(entity.get()))));

        });

    }

}
