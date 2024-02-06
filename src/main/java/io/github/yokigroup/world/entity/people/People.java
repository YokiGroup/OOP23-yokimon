package io.github.yokigroup.world.entity.people;



import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Hitbox;
import io.github.yokigroup.world.entity.Position;

import java.util.List;

/**
 *
 */
public abstract class People extends Entity {

    private final static float DEFAULT_DIRECTION=0;
    /**
     * position the entity is first spawned
     */
    protected final Position initialPos;
    /**
     * direction where the entity is looking
     */
    protected float direction;

    protected boolean Active;

    protected List<Yokimon> Party;

    public People(String name, Position Pos, Hitbox Hitbox, List<Yokimon> Party) {
        super(name, Pos, Hitbox);
        this.Party=List.copyOf(Party);
        this.direction=DEFAULT_DIRECTION;
        this.Active=true;
        this.initialPos=Pos;

    }

    /**
     * return the direction in which the player or a mpc is currently watching
     *
     * @return the angle in radiant
     */
    public float getDirection() {
        return this.direction;
    }

    /**
     * return is the person isActive or not (if is not
     * it will not perform any action)
     *
     * @return true if is active, false if is not
     */
    public boolean getIsActive() {
        return this.Active;
    }

    /**
     * set is the person Active (if is not
     * it will not perform any action
     */
    public void setActive() {
        this.Active=true;
    }

    /**
     * set is the person not Active (if is not
     * it will not perform any action
     */
    public void shut() {
        this.Active=false;
    }

    /**
     * return the party of the people as list of yokimon
     *
     * @return List<Yokimon> </Yokimon>
     */
    public List<Yokimon> getListOfYokimon() {
        return this.Party;
    }

    /**
     * @param newYokimon add a new yokimon to the party
     * @return message
     */
    public Entity.message addYokimon(Yokimon newYokimon) {
        this.Party.add(newYokimon);
        return message.OK;
    }

    /**
     * @param newYokimons add a new list of yokimon to the party
     * @return message
     */
    public Entity.message addListOfYokimon(List<Yokimon> newYokimons) {
        if(this.Party.addAll(newYokimons)){
            return message.OK;
        }
        else
            return message.ERROR;

    }

    /**
     * return the current position of the entity
     *
     * @return Position, X e Y
     */
    public Position getInitialPos() {
        return this.initialPos;
    }

}

