package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilder;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Set;

/**
 * Submodule containing a GameMap and relevant methods to query the map's state.
 * @author Giovanni Paone
 */
public final class GameMapSubmodule extends Submodule {
    private final GameMap gameMap;
    private final Pair<Integer, Integer> MAP_DIM = new Pair<>(5, 5);

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public GameMapSubmodule(final MessageHandler handler) {
        super(handler);
        final Pair<Integer, Integer> playerTilePos = new Pair<>(MAP_DIM.x()/2+1, MAP_DIM.y()/2+1);
        final GameMapBuilder builder = new GameMapBuilderImpl();

        builder.setMapDimensions(MAP_DIM);
        builder.setPlayerTileMapPosition(playerTilePos);
        builder.setHomeTileAt(playerTilePos);
        this.gameMap = builder.build(handler);
    }

    /**
     * @return Submodule's GameMap reference
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    /**
     * @return hitboxes contained in the tile the player's currently on.
     */
    public Set<Hitbox> getHitboxesOnCurrentTile() {
        return gameMap.getPlayerTile().getHitboxes();
    }

    /**
     * Gets the entities contained in the Tile the player's currently on.
     */
    public Set<Entity> getEntitiesOnCurrentTile() {
        return gameMap.getPlayerTile().getEntities();
    }

    @Override
    public void process() {
        /*
        this function should query the player's position and consider whether to change Tile if the player is crossing
        the tile border.
         */
        handler().handle(PlayerCharacterSubmodule.class, s -> {

            //s.getPosition().isValid();
        });
    }
}
