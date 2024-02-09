package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
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
     * @param name The name of the Enemy
     * @param Pos The position of the Enemy
     * @param Hitbox The hitbox of the Enemy
     * @param Party The party of Yokimon belonging to the Enemy
     */
    public Enemy(String name, Position Pos, Hitbox Hitbox, List<Yokimon> Party) {
        super(name, Pos, Hitbox, Party);
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
     * @return message Status message
     */
    public message follow() {
        return null;
    }

    /**
     * Updates the state of the Enemy (switches between wander and follow).
     * @return message Status message
     */
    @Override
    public message update() {
        if (this.Action == state.wander) {
            this.Action = state.follow;
        } else {
            this.Action = state.wander;
        }
        return message.ok;
    }
}
