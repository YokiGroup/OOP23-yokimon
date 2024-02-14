package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Position;

import java.util.List;

public class Player extends People {

    public Player(Position pos, Hitbox hitbox, List<Yokimon> party, MessageHandler messageHandler) {
        super(pos, hitbox, party, messageHandler);
    }

    /**
     * given a vector, it changes the position of the player
     * around the map
     *
     * @param v vector
     *
     */
    public void move(Vector2 v) {

    }

    @Override
    public void update() {
        if(!this.active){
            return;
        }
    }
}
