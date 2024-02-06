package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.world.entity.Hitbox;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.Vector;


import java.util.List;

public class Player extends People {

    public Player(String name, Position Pos, Hitbox Hitbox, List<Yokimon> Party) {
        super(name, Pos, Hitbox, Party);
    }

    /**
     * given a vector, it changes the position of the player
     * around the map
     *
     * @param v vector
     * @return message for collision
     */
    public message move(Vector v) {
        return null;
    }

    @Override
    public message update() {
        return null;
    }
}
