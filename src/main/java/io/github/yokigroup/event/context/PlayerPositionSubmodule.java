package io.github.yokigroup.event.context;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.tile.TileMap;

/**
 * Handles player position updates
 */
public class PlayerPositionSubmodule implements Submodule{
    Entity player;
    TileMap map;
    enum Direction{
        UP,DOWN,LEFT,RIGHT
    }

    public PlayerPositionSubmodule(Entity player, TileMap map){
        this.player = player;
        this.map = map;
    }

    void changeTile(Direction dir) {
        //TODO change tile of player by going in dir
    }

    void movePlayerBy(Vector2 delta) {
        // TODO move player by delta
    }

    @Override
    public void process() {
        // TODO collision check
    }
}
