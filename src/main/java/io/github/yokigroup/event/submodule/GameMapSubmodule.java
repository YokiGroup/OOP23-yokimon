package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.Updatable;
import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;
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
 *
 * @author Giovanni Paone
 */
public final class GameMapSubmodule extends GameMapSubmoduleAbs {
    private final GameMap gameMap;
    private final Publisher<SpriteData> tilePub = new PublisherImpl<>();
    private final Publisher<Set<SpriteData>> entityPub = new PublisherImpl<>();
    private Pair<Integer, Integer> playerTilePos;
    private final Pair<Integer, Integer> homeTilePos;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     * @param modelObs the model Observer.
     */
    public GameMapSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
        playerTilePos = new Pair<>(MAP_DIM.x() / 2, MAP_DIM.y() / 2);
        homeTilePos = playerTilePos;
        final GameMapBuilder builder = new GameMapBuilderImpl();

        builder.changeMapDimensions(MAP_DIM);
        builder.changePlayerTileMapPosition(playerTilePos);
        builder.putHomeTileAt(playerTilePos);
        this.gameMap = builder.build(handler);

        modelObs.addSpritePublisher(RenderState.WORLD, tilePub);
        modelObs.addSpritePublishers(RenderState.WORLD, entityPub);
        tilePub.notifyObservers(gameMap.getPlayerTile().getSpriteData());
        entityPub.notifyObservers(getEntitiesOnCurrentTile().stream().map(Entity::getSpriteData).collect(Collectors.toSet()));
    }

    @Override
    public void deactivate() {
        super.deactivate();
        gameMap.getTileAt(playerTilePos).getEntities().forEach(Updatable::resetDTime);
    }

    @Override
    public int getPlayerDistanceFromHome() {
        return Math.abs(playerTilePos.x() - homeTilePos.x()) + Math.abs(playerTilePos.y() - homeTilePos.y());
    }

    @Override
    public boolean movePlayerToTile(final Direction dir) {
        final boolean success = gameMap.movePlayerTileMapPosition(dir);
        if (success) {
            final Pair<Integer, Integer> dirOffset = dir.getOffset();
            // reset dtime of previous tile
            gameMap.getTileAt(playerTilePos).getEntities().forEach(Updatable::resetDTime);
            tilePub.notifyObservers(gameMap.getPlayerTile().getSpriteData());
            playerTilePos = new Pair<>(playerTilePos.x() + dirOffset.x(), playerTilePos.y() + dirOffset.y());
            publishEntitySpriteData();
        }
        return success;
    }



    private void publishEntitySpriteData() {
        entityPub.notifyObservers(
                getEntitiesOnCurrentTile().stream()
                        .map(Entity::getSpriteData)
                        .collect(Collectors.toSet())
        );

    }

    @Override
    protected void updateEntities() {
        gameMap.getPlayerTile().updateEntities();
        publishEntitySpriteData();
    }

    @Override
    protected boolean areAllEnemiesSlain() {
        return gameMap.areAllEnemiesSlain();
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
