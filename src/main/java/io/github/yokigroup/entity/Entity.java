package io.github.yokigroup.entity;

import io.github.yokigroup.entity.hitbox.Hitbox;

/**
 * This class represent the concept of entity
 */
public abstract class Entity {
    /**
     * message for the return methods of entity and his
     * ereditance
     */
    public enum message{
        OK,
        OVER_FLOW,
        ERROR,
        OUT_OF_MAP,
        USED
    }

    protected final String name;
    protected Position Pos;
    protected Hitbox Hitbox_type;

    public Entity(String name, Position Pos, Hitbox Hitbox){
        this.Hitbox_type=Hitbox;
        this.name=name;
        this.Pos=Pos;
    }
    /**
     * return the current position of the entity
     * @return Position, X e Y
     */
    public Position getPosition(){
        return this.Pos;
    }
    /**
     * Set a new position for the entity, if it's valid it changes it
     * otherwise it return an error message
     * @return message
     */
    public Entity.message setPosition(Position Pos){
        if(Pos.isValid()){
            this.Pos=Pos;
            return message.OK;
        }
        else
            return message.OUT_OF_MAP;
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
        return this.Hitbox_type;
    }

    /**
     * update the state of the entity
     */
    public abstract Entity.message update();
}
