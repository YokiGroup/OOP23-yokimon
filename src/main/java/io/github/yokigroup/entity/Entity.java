package io.github.yokigroup.entity;


/**
 * This class represent the concept of entity
 */
public interface Entity {
    /**
     * message for the return methods of entity and his
     * ereditance
     */
    public enum message{
        OK,
        OVER_FLOW,
        ERROR
    }

    /**
     * return the current position of the entity
     * @return Position, X e Y
     */
    public Position getPosition();

    /**
     * return the name of the entity, it's univocal,
     * so it also works as an ID
     * @return String name
     */
    public String getName();

    public Hitbox getHitbox();

    /**
     * update the state of the entity
     */
    public void update();
}
