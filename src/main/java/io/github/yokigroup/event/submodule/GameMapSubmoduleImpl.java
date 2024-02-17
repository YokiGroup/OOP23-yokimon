package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.GameMapSubmodule;
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
public final class GameMapSubmoduleImpl extends GameMapSubmodule {
    private final GameMap gameMap;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public GameMapSubmoduleImpl(final MessageHandler handler) {
        super(handler);
        final Pair<Integer, Integer> playerTilePos = new Pair<>(MAP_DIM.x()/2+1, MAP_DIM.y()/2+1);
        final GameMapBuilder builder = new GameMapBuilderImpl();

        builder.changeMapDimensions(MAP_DIM);
        builder.changePlayerTileMapPosition(playerTilePos);
        builder.putHomeTileAt(playerTilePos);
        this.gameMap = builder.build(handler);
    }

    @Override
    public GameMap getGameMap() {
        return this.gameMap;
    }

    @Override
    public Set<Hitbox> getHitboxesOnCurrentTile() {
        return gameMap.getPlayerTile().getHitboxes();
    }

    @Override
    public Set<Entity> getEntitiesOnCurrentTile() {
        return gameMap.getPlayerTile().getEntities();
    }

}
