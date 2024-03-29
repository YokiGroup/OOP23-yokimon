package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.tile.TileBuilder;

import java.util.Set;

/**
 * A TileShape contains a set of all the tiles of a specific shape.
 */
public class TileShapeImpl implements TileShape {
    private final Set<Direction> shape;
    private final WeightedPool<TileBuilder> tiles;

    /**
     * Creates a TileShape set, containing all the tiles of a specific shape.
     * @param tiles The tiles of this shape.
     * @param shape The shape of the tile.
     */
    public TileShapeImpl(final Set<TileBuilder> tiles, final Set<Direction> shape) {
        if (shape == null) {
            throw new IllegalArgumentException("The 'shape' argument is null.");
        } else if (tiles == null) {
            throw new IllegalArgumentException("The 'tiles' argument is null");
        }
        this.shape = Set.copyOf(shape);
        this.tiles = new WeightedPoolImpl<>();
        for (final var t : Set.copyOf(tiles)) {
            this.tiles.addElement(t, 1.0f);
        }
    }

    @Override
    public final WeightedPool<TileBuilder> getTiles() {
        return new WeightedPoolImpl<>(this.tiles);
    }

    @Override
    public final Set<Direction> getPossibleDirections() {
        return Set.copyOf(this.shape);
    }
}
