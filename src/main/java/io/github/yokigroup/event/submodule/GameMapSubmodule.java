package io.github.yokigroup.event.submodule;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilder;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link GameMapSubmoduleAbs}.
 * @author Giovanni Paone
 */
public final class GameMapSubmodule extends GameMapSubmoduleAbs {
    private final GameMap gameMap;
    private final Publisher<SpriteData> tilePub = new PublisherImpl<>();
    private final Publisher<Set<SpriteData>> entityPub = new PublisherImpl<>();

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public GameMapSubmodule(final MessageHandler handler, ModelObserver modelObs) {
        super(handler, modelObs);
        final Pair<Integer, Integer> playerTilePos = new Pair<>(MAP_DIM.x() / 2 + 1, MAP_DIM.y() / 2 + 1);
        final GameMapBuilder builder = new GameMapBuilderImpl();

        builder.changeMapDimensions(MAP_DIM);
        builder.changePlayerTileMapPosition(playerTilePos);
        builder.putHomeTileAt(playerTilePos);
        this.gameMap = builder.build(handler);

        modelObs.addWorldSpritePublisher(tilePub);
        modelObs.addWorldSpritePublishers(entityPub);
        tilePub.notifyObservers(gameMap.getPlayerTile().getSpriteData());
        entityPub.notifyObservers(getEntitiesOnCurrentTile().stream().map(Entity::getSpriteData).collect(Collectors.toSet()));
    }

    @Override
    public boolean movePlayerToTile(Direction dir) {
        boolean success = gameMap.movePlayerTileMapPosition(dir);
        if (success) {
            tilePub.notifyObservers(gameMap.getPlayerTile().getSpriteData());
            entityPub.notifyObservers(
                    getEntitiesOnCurrentTile().stream()
                    .map(Entity::getSpriteData)
                    .collect(Collectors.toSet())
            );
        }
        return success;
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
