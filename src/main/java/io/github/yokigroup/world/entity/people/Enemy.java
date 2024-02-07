package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.List;

public class Enemy extends People {
    private Enemy.state Action;
    public enum state{
        WANDER,
        FOLLOW
    }
    public Enemy(String name, Position Pos, Hitbox Hitbox, List<Yokimon> Party) {
        super(name, Pos, Hitbox, Party);
        this.Action=state.WANDER;
    }

    /**
     * logic call this method when the player is
     * to close to the player
     */
    public message follow() {
        return null;
    }

    @Override
    public message update() {
        if(this.Action == state.WANDER){
            this.Action=state.FOLLOW;
        }
        else{
            this.Action=state.WANDER;
        }
        return message.OK;
    }
}
