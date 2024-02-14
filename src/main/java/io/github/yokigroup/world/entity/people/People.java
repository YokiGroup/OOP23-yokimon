package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;

import java.util.List;

/**
 * People class represents a generic person entity in the game world.
 * It provides methods to manage and manipulate people entities.
 */
public abstract class People extends Entity {

    private final static float DEFAULT_DIRECTION = 0;
    /**
     * The position the entity is first spawned.
     */
    protected final Position initialPos;
    /**
     * The direction where the entity is looking.
     */
    protected float direction;

    protected boolean active;

    protected List<Yokimon> Party;

    /**
     * Constructs a People object with the specified attributes.
     * @param pos The position of the People
     * @param hitBox The hitBox of the People
     * @param party The party of Yokimon belonging to the People
     */
    public People(Position pos, Hitbox hitBox, List<Yokimon> party, MessageHandler messageHandler) {
        super(pos, hitBox, messageHandler);
        this.Party = List.copyOf(party);
        this.direction = DEFAULT_DIRECTION;
        this.active = true;
        this.initialPos = pos;
    }

    /**
     * Direction enum represents the possible default directions in the game world.
     */
    public enum Direction {
        UP(new Vector2Impl(0, -1)),
        UP_RIGHT(new Vector2Impl(1, -1)),
        RIGHT(new Vector2Impl(1, 0)),
        DOWN_RIGHT(new Vector2Impl(1, 1)),
        DOWN(new Vector2Impl(0,1)),
        LEFT_DOWN(new Vector2Impl(-1, 1)),
        LEFT(new Vector2Impl(-1,0)),
        UP_LEFT(new Vector2Impl(-1, -1));

        private final Vector2 vector;
        Direction(Vector2 vector) {
            this.vector = vector;
        }
        public Vector2 get(){
            return vector;
        }
    }
    /**
     * Returns the direction in which the people entity is currently looking.
     * @return float The angle in radiant
     */
    public float getDirection() {
        return this.direction;
    }

    /**
     * Returns whether the people entity is active or not.
     * @return boolean True if the people entity is active, false otherwise
     */
    public boolean getIsActive() {
        return this.active;
    }

    /**
     * Sets the people entity as active.
     */
    public void setActive() {
        this.active = true;
    }

    /**
     * Sets the people entity as inactive.
     */
    public void shut() {
        this.active = false;
    }

    /**
     * Returns the party of Yokimon belonging to the people entity.
     * @return List<Yokimon> The party of Yokimon
     */
    public List<Yokimon> getListOfYokimon() {
        return this.Party;
    }

    /**
     * Adds a new Yokimon to the party of the people entity.
     * @param newYokimon The new Yokimon to add
     * @return true if the operation worked
     */
    public boolean addYokimon(Yokimon newYokimon) {
        if(this.Party == null){
            return false;
        }
        this.Party.add(newYokimon);
        return true;
    }

    /**
     * Adds a list of new Yokimon to the party of the people entity.
     * @param newYokimons The list of new Yokimon to add
     * @return message Status message
     */
    public boolean addListOfYokimon(List<Yokimon> newYokimons) {
       return this.Party.addAll(newYokimons);
    }
    /**
     * Returns the initial position of the entity.
     * @return Position Initial position of the entity
     */
    public Position getInitialPos() {
        return this.initialPos;
    }

}
