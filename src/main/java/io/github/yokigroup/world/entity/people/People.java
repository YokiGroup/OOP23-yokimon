package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;

import java.util.List;
import java.util.Objects;

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
     * This list contains the lists of the yokimons that player and
     * entity will use in the fight system.
     */
    private final List<Yokimon> party;

    /**
     * Used to calculate where this People is looking.
     */
    private static final double CRITICAL_UP_RIGHT = 45;
    /**
     * Used to calculate where this People is looking.
     */
    private static final double CRITICAL_DOWN_RIGHT = 135;
    /**
     * Used to calculate where this People is looking.
     */
    private static final double CRITICAL_DOWN_LEFT = 225;
    /**
     * Used to calculate where this People is looking.
     */
    private static final double CRITICAL_UP_LEFT = 315;

    /**
     * Constructs a People object with the specified attributes.
     * @param id of the people
     * @param pos The position of the People
     * @param hitBox The hitBox of the People
     * @param party The party of Yokimon belonging to the People
     * @param messageHandler handler of events
     */
    public People(final int id, final Position pos, final Hitbox hitBox, final List<Yokimon> party,
                  final MessageHandler messageHandler) {
        super(id, pos, hitBox, messageHandler);
        this.party = List.copyOf(party);
        this.direction = DEFAULT_DIRECTION;
        this.active = true;
        this.initialPos = pos;
    }

    /**
     * Direction enum represents the possible default directions in the game world.
     */
    public enum Direction {
        /**
         * The entity doesn't move
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
    public final void toDirection(final Vector2 v) {
        Objects.requireNonNull(v, "Vector passed to toDirection was null");
        if (v.getX() == 0 && v.getY() > 0) {
            this.direction = Direction.RIGHT;
        } else if (v.getX() == 0 && v.getY() < 0) {
            this.direction = Direction.LEFT;
        }
        double degree = Math.toDegrees(Math.atan(v.getY() / v.getX()));
        if (degree > CRITICAL_UP_RIGHT && degree < CRITICAL_DOWN_RIGHT) {
            this.direction = Direction.RIGHT;
        } else if (degree > CRITICAL_DOWN_LEFT && degree < CRITICAL_UP_LEFT) {
            this.direction = Direction.LEFT;
        } else if (degree >= CRITICAL_DOWN_RIGHT && degree <= CRITICAL_DOWN_LEFT) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.DOWN;
        }
    }
    /**
     * Returns whether the people entity is active or not.
     * @return boolean True if the people entity is active, false otherwise
     */
    public final boolean getIsActive() {
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
     * Returns the party of Yokimon belonging to the people entity.
     * @return List<Yokimon> The party of Yokimon
     */
    public List<Yokimon> getListOfYokimon() {
        return List.copyOf(this.party);
    }

    /**
     * Adds a new Yokimon to the party of the people entity.
     * @param newYokimon The new Yokimon to add
     * @return true if the operation worked
     */
    public final boolean addYokimon(final Yokimon newYokimon) {
        if (this.party == null) {
            return false;
        }
        this.party.add(newYokimon);
        return true;
    }

    /**
     * Adds a list of new Yokimon to the party of the people entity.
     * @param newYokimons The list of new Yokimon to add
     * @return message Status message
     */
    public final boolean addListOfYokimon(final List<Yokimon> newYokimons) {
       return this.party.addAll(newYokimons);
    }
    /**
     * Returns the initial position of the entity.
     * @return Position Initial position of the entity
     */
    public final Position getInitialPos() {
        return this.initialPos;
    }

    /**
     * If the position is not valid this method reset the position of the map to
     * the initial one, or to the centre of the tile if the entity is the player.
     */
    public abstract void resetPosition();

}
