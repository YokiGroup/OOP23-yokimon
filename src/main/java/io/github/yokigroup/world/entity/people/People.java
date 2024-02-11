package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
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
     * @param name The name of the People
     * @param pos The position of the People
     * @param hitbox The hitbox of the People
     * @param party The party of Yokimon belonging to the People
     */
    public People(String name, Position pos, Hitbox hitbox, List<Yokimon> party) {
        super(name, pos, hitbox);
        this.Party = List.copyOf(party);
        this.direction = DEFAULT_DIRECTION;
        this.active = true;
        this.initialPos = pos;
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
     * @return message Status message
     */
    public Entity.message addYokimon(Yokimon newYokimon) {
        this.Party.add(newYokimon);
        return message.ok;
    }

    /**
     * Adds a list of new Yokimon to the party of the people entity.
     * @param newYokimons The list of new Yokimon to add
     * @return message Status message
     */
    public Entity.message addListOfYokimon(List<Yokimon> newYokimons) {
        if (this.Party.addAll(newYokimons)) {
            return message.ok;
        } else
            return message.error;
    }

    /**
     * Returns the initial position of the entity.
     * @return Position Initial position of the entity
     */
    public Position getInitialPos() {
        return this.initialPos;
    }

}
