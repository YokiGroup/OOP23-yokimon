package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Set;

/**
 * Abstract class of a submodule containing information about the game map.
 * @see GameMap
 * @author Giovanni Paone
 */
public abstract class GameMapSubmoduleAbs extends Submodule {
    /**
     * Dimensions of the map to instance.
     */
    protected static final Pair<Integer, Integer> MAP_DIM = new Pair<>(5, 5);


    /**
     * @param handler to init the submodule with
     */
    public GameMapSubmoduleAbs(final MessageHandler handler) {
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
     * @return set containing entities on the current tile
     */
    public abstract Set<Entity> getEntitiesOnCurrentTile();

    @Override
    public final void update() {
        /*
        this function should query the player's position and consider whether to change Tile if the player is crossing
        the tile border.
         */
        handler().handle(PlayerCharacterSubmodule.class, s -> {

            //s.getPosition().isValid();
        });
    }
}
