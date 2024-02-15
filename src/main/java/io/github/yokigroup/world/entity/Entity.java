package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Objects;

/**
 * Entity class represents a generic entity in the game world.
 * It provides methods to manage and manipulate entities.
 */
public abstract class Entity {


    private Position pos;
    private Hitbox hitBox;
    private final MessageHandler messageHandler;
    private final int id;

    /**
     * Constructs an Entity object with the specified attributes.
     * @param id id of the entity
     * @param pos The position of the Entity
     * @param hitBox The hitBox of the Entity
     * @param messageHandler handler for the events
     */
    public Entity(final int id, final Position pos, final Hitbox hitBox, final MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        this.id = id;
        this.setHitBox(hitBox);
        this.setPos(pos);
    }

    /**
     * Returns the entity id
     * @return int
     */
    public final int getId(){
        return this.id;
    }

    /**
     * return the current position of the entity.
     * @return Position, X e Y
     */
    public final Position getPos() {
        return pos;
    }

    /**
     * return the messageHandler of this class.
     * @return MessageHandler
     */
    public final MessageHandler getMessageHandler() {
        return this.messageHandler;
    }

    /**
     * Set a new position for the entity, if it's valid it changes it.
     * @param pos new position
     */
    public final void setPos(final Position pos) {
        Objects.requireNonNull(pos, "Pos passed to the entity was null");
        if (pos.isValid(this.messageHandler)) {
            this.pos = pos;
            this.hitBox.setPosition(pos.getPosition());
        }

    }

    /**
     * Return the hitBox of the current Entity.
     * @return This hitBox
     */
    public final Hitbox getHitBox() {
        return this.hitBox;
    }

    /**
     * Set a new hitBox.
     * @param hitBox new hitBox
     */
    protected final void setHitBox(Hitbox hitBox) {
        this.hitBox = hitBox;
    }
    /**
     * Updates the state of the entity.
     */
    public abstract void update();

}
