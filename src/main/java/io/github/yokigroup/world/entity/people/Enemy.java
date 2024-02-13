package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.Submodule;
import io.github.yokigroup.event.submodule.SubmoduleMapImpl;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.List;

/**
 * Enemy class represents an enemy entity in the game world.
 * It provides methods to manage and manipulate enemy entities.
 */
public class Enemy extends People {

    private Enemy.state Action;

    /**
     * Constructs an Enemy object with the specified attributes.
     * @param Pos The position of the Enemy
     * @param Hitbox The hitbox of the Enemy
     * @param Party The party of Yokimon belonging to the Enemy
     */
    public Enemy(Position Pos, Hitbox Hitbox, List<Yokimon> Party, MessageHandler messageHandler) {
        super(Pos, Hitbox, Party, messageHandler);
        this.Action = state.wander;
    }

    /**
     * Represents the state of the Enemy (wander or follow).
     */
    public enum state {
        wander,
        follow
    }
    /**
     * Logic calls this method when the player is too close to the enemy.
     *
     */
    public void follow() {

    }

    public void wander(){

    }
    /**
     * Updates the state of the Enemy (switches between wander and follow).
     *
     */
    @Override
    public void update() {
        /*
        this.getMessageHandler().handle(SubmoduleMapImpl.class, map -> {
            map.process();

        });
        */

    }
}
