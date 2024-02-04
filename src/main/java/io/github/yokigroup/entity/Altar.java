package io.github.yokigroup.entity;



import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.entity.people.People;

import java.util.Optional;

/**
 * an altar which gives you a yokimon at the first interaction
 */
public class Altar extends Entity{

    private Optional<Yokimon> gift;
    private altarState state;
    public Altar(String name, Position Pos, Hitbox Hitbox, Optional<Yokimon> yokimon) {
        super(name, Pos, Hitbox);
        this.gift=yokimon;  //
        this.state=altarState.NEW;
    }

    @Override
    message update() {
        if(this.state==altarState.NEW){
            this.state=altarState.USED;
            return message.OK;
        }
        else
            return message.USED;
    }

    public enum altarState{
        USED,
        NEW
    }

    /**
     * Return the current state of the Altar (if it's used or not)
     * @return State
     */
    public Altar.altarState getState(){
        return this.state;
    }

    /**
     * return a Yokimon if the altar is new otherwise
     * an optional empty
     * @return Optional<Yokimon>
     */
    public Optional<Yokimon> getNewYokimon(){
        if(this.state == altarState.NEW){
            return this.gift;
        }
        else
            return Optional.empty();

    }


}
