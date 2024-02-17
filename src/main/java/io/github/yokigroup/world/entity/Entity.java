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

    /**
     * Constructs an Entity object with the specified attributes.
     * @param pos The position of the Entity
     * @param hitBox The hitBox of the Entity
     * @param messageHandler handler for the events
     */
    public Entity(final Position pos, final Hitbox hitBox, final MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        this.setHitBox(hitBox);
        this.setPos(pos);
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
        if (pos.isValid()) {
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
    protected final void setHitBox(final Hitbox hitBox) {
        this.hitBox = hitBox;
    }
    /**
     * Updates the state of the entity.
     */
    public abstract void update();

    /**
     * Control if the entity is equal to another.
     * @param o Entity
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Entity entity = (Entity) o;
        return Objects.equals(pos, entity.pos) && Objects.equals(hitBox, entity.hitBox);
    }

    /**
     * Return and hash code of the entity.
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(pos, hitBox);
    }
}
