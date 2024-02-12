package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Position;

import java.util.List;

public class Player extends People {

    public Player(String name, Position pos, Hitbox hitbox, List<Yokimon> party) {
        super(name, pos, hitbox, party);
    }

    /**
     * given a vector, it changes the position of the player
     * around the map
     *
     * @param v vector
     * @return message for collision
     */
    public message move(Vector2 v) {
        return null;
    }

    @Override
    public message update() {
        return null;
    }
}
