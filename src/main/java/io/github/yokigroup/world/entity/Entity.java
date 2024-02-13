package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Objects;

/**
 * Entity class represents a generic entity in the game world.
 * It provides methods to manage and manipulate entities.
 */
public abstract class Entity {


    private Position pos;
    private Hitbox hitbox;
    private final MessageHandler messageHandler;

    /**
     * Constructs an Entity object with the specified attributes.
     * @param pos The position of the Entity
     * @param hitbox The hitbox of the Entity
     */
    public Entity(Position pos, Hitbox hitbox, MessageHandler messageHandler){
        this.messageHandler = messageHandler;
        this.setHitbox(hitbox);
        this.setPos(pos);
    }

    /**
     * return the current position of the entity
     * @return Position, X e Y
     */
    protected Position getPos() {
        return pos;
    }

    /**
     * return the messageHandler of this class
     * @return MessageHandler
     */
    public MessageHandler getMessageHandler() {
        return this.messageHandler;
    }

    /**
     * Set a new position for the entity, if it's valid it changes it
     *
     */
    protected void setPos(Position pos) {
        Objects.requireNonNull(pos, "Pos passed to the entity was null");
        if(pos.isValid()){
            this.pos = pos;
            this.hitbox.setPosition(pos.turnIntoVector());
        }

    }

    protected Hitbox getHitbox(){
        return this.hitbox;
    }

    /**
     * Updates the state of the entity.
     */
    protected void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public abstract void update();

}
