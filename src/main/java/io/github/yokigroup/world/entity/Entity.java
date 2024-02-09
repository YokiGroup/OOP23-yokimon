package io.github.yokigroup.world.entity;

import io.github.yokigroup.world.entity.hitbox.Hitbox;

/**
 * Entity class represents a generic entity in the game world.
 * It provides methods to manage and manipulate entities.
 */
public abstract class Entity {
    /**
     * message for the return methods of entity and his
     * ereditance
     */
    public enum message{
        ok,
        error,
        outOfMap,
        used
    }
    //cambialo cretino
    protected final String name;
    protected Position pos;
    protected Hitbox hitbox;

    /**
     * Constructs an Entity object with the specified attributes.
     * @param name The name of the Entity
     * @param pos The position of the Entity
     * @param Hitbox The hitbox of the Entity
     */
    public Entity(String name, Position pos, Hitbox Hitbox){
        this.hitbox=Hitbox;
        this.name=name;
        this.pos=pos;
    }
    /**
     * return the current position of the entity
     * @return Position, X e Y
     */
    public Position getPosition(){
        return this.pos;
    }
    /**
     * Set a new position for the entity, if it's valid it changes it
     * otherwise it return an error message
     * @return message
     */
    public Entity.message setPosition(Position pos){
        // FIXME move hitbox position as well
        if(pos.isValid()){
            this.pos=pos;
            return message.ok;
        }
        else
            return message.outOfMap;
    }
    /**
     * return the name of the entity, it's univocal,
     * so it also works as an ID
     * @return String name
     */
    public String getName(){
        return this.name;
    }

    /**
     * return type of the Hitbox
     * @return Hitbox
     */
    public Hitbox getHitbox(){
        return this.hitbox;
    }

    /**
     * Updates the state of the entity.
     * @return message Status message
     */
    public abstract Entity.message update();
}
