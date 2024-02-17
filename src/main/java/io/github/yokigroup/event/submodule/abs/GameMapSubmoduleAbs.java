package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Set;

/**
 * Submodule containing a GameMap and relevant methods to query the map's state.
 * @author Giovanni Paone
 */
public abstract class GameMapSubmoduleAbs extends Submodule {
    protected final Pair<Integer, Integer> MAP_DIM = new Pair<>(5, 5);

    public GameMapSubmoduleAbs(MessageHandler handler) {
        super(handler);
    }

    /**
     * @return Submodule's GameMap reference
     */
    public abstract GameMap getGameMap();

    /**
     * @return hitboxes contained in the tile the player's currently on.
     */
    public abstract Set<Hitbox> getHitboxesOnCurrentTile();

    /**
     * Gets the entities contained in the Tile the player's currently on.
     */
    public abstract Set<Entity> getEntitiesOnCurrentTile();

    @Override
    public void update() {
        /*
        this function should query the player's position and consider whether to change Tile if the player is crossing
        the tile border.
         */
        handler().handle(PlayerCharacterSubmodule.class, s -> {

            //s.getPosition().isValid();
        });
    }
}
