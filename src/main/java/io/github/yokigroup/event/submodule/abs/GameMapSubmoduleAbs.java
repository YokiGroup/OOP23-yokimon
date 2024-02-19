package io.github.yokigroup.event.submodule.abs;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Optional;
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
    private Direction lastDirection = null;

    private Optional<Direction> checkTileChange() {
        final Pair<Integer, Integer> mapDim = GameMap.TILE_DIMENSIONS;
        final Vector2 playerPos = handler().handle(PlayerCharacterSubmodule.class, PlayerCharacterSubmodule::getPosition).getPosition();
        final double upperBoundProp = 39. / 40.;
        final double lowerBoundProp = 1. / 40.;

        if (playerPos.getX() > mapDim.x() * upperBoundProp) {
            return Optional.of(Direction.RIGHT);
        } else if (playerPos.getX() < mapDim.x() * lowerBoundProp) {
            return Optional.of(Direction.LEFT);
        } else if (playerPos.getY() > mapDim.y() * upperBoundProp) {
            return Optional.of(Direction.DOWN);
        } else if (playerPos.getY() < mapDim.y() * lowerBoundProp) {
            return Optional.of(Direction.UP);
        }
        return Optional.empty();
    }

    private Vector2 relocatedPosition(Direction dir) {
        final Vector2 dirVec = Vector2Impl.castPair(dir.getOffset());
        final Vector2 halfMap = Vector2Impl.castPair(GameMap.TILE_DIMENSIONS).scale(0.5);
        return dirVec.times(halfMap).plus(halfMap);
    }

    /**
     * @param handler to init the submodule with
     */
    public GameMapSubmoduleAbs(final MessageHandler handler, ModelObserver modelObs) {
        super(handler, modelObs);
    }

    /**
     * Moves the player to the tile contained in the given direction.
     * @param dir direction of the tile relative to the one the player's currently on
     * @return true if the tile change was successful
     */
    public abstract boolean movePlayerToTile(Direction dir);

    /**
     * @return hitboxes contained in the tile the player's currently on.
     */
    public abstract Set<Hitbox> getHitboxesOnCurrentTile();

    /**
     * Gets the entities contained in the Tile the player's currently on.
     * @return set containing entities on the current tile
     */
    public abstract Set<Entity> getEntitiesOnCurrentTile();

    protected abstract void updateEntities();
    protected abstract void updateTile();

    @Override
    protected final void updateCode(double delta) {
        updateEntities();
        /*
        this function should query the player's position and consider whether to change Tile if the player is crossing
        the tile border.
         */
        checkTileChange().ifPresent(a -> {
            if (lastDirection == null || a != lastDirection.getComplementary()) {
                if (movePlayerToTile(a)) {
                    handler().handle(PlayerCharacterSubmodule.class, s -> {
                        s.movePlayerTo(new PositionImpl(relocatedPosition(a.getComplementary())));
                    });
                    lastDirection = a;
                }
            }
        });
    }
}
