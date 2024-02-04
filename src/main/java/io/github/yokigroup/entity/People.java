package io.github.yokigroup.entity;

import battle.Yokimon;

import java.util.List;

/**
 *
 */
public interface People extends Entity{
    /**
     * return the direction in which the player or a mpc is currently watching
     * @return the angle in radiant
     */
    public float getDirection();

    /**
     * return is the person isActive or not (if is not
     * it will not perform any action)
     * @return true if is active, false if is not
     */
    public boolean getIsActive();

    /**
     * set is the person Active (if is not
     * it will not perform any action
     */
    public void setActive();

    /**
     * set is the person not Active (if is not
     * it will not perform any action
     */
    public void shut();

    /**
     * return the party of the people as list of yokimon
     * @return List<Yokimon> </Yokimon>
     */
    public List<Yokimon> getListOfYokimon();

    /**
     *
     * @param newYokimon add a new yokimon to the party
     * @return message
     */
    public Entity.message addYokimon(Yokimon newYokimon);

    /**
     *
     * @param newYokimons add a new list of yokimon to the party
     * @return message
     */
    public Entity.message addListOfYokimon(List<Yokimon> newYokimons);

    /**
     * return the current position of the entity
     * @return Position, X e Y
     */
    public Position getInitialPos();

}

