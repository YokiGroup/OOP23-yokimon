package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.gen.wfc.WaveFunctionCollapse;
import io.github.yokigroup.world.gen.wfc.WaveFunctionCollapseImpl;
import io.github.yokigroup.world.tile.TileBuilder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the wave function collapse algorithm wrapper.
 */
public class WFCWrapperImpl implements WFCWrapper {
    private final Set<TileShape> tileShapes;
    private final WaveFunctionCollapse wfc;

    /**
     * Initializes the wave function collapse algorithm with the tile shape's rules.
     * @param dimensions The dimensions of the 2d map.
     * @param shapes All the shapes that will be generated.
     */
    public WFCWrapperImpl(final Pair<Integer, Integer> dimensions, final Set<TileShape> shapes) {
        this.tileShapes = Set.copyOf(shapes);
        // TODO: filter duplicate shapes.
        final Set<Set<Direction>> convertedShapes = this.tileShapes.stream()
                .map(TileShape::getPossibleDirections)
                .collect(Collectors.toSet());
        this.wfc = new WaveFunctionCollapseImpl(dimensions, convertedShapes);
    }

    @Override
    public final void setStaticTile(final Pair<Integer, Integer> position, final Set<Direction> tile) {
        this.wfc.setStaticShape(position, Set.of(tile));
    }

    @Override
    public final void runWFC() {
        wfc.generateShapeMap();
    }

    @Override
    public final TileBuilder getTileAt(final Pair<Integer, Integer> position) {
        final Optional<WeightedPool<TileBuilder>> tilePool =  this.tileShapes.stream()
                .filter(s -> compareShapes(s.getPossibleDirections(), wfc.getShapeAt(position)))
                .map(TileShape::getTiles)
                .findFirst();
        if (tilePool.isEmpty()) {
            throw new IllegalStateException("Couldn't get the tile at that location.");
        }
        return tilePool.get().getRandomizedElement();
    }

    /**
     *
     * @param shape1 The first shape to check.
     * @param shape2 The second shape to check.
     * @return True if the two shapes are the same.
     */
    private boolean compareShapes(final Set<Direction> shape1, final Set<Direction> shape2) {
        final Set<Direction> fullSet = new HashSet<>();
        fullSet.addAll(shape1);
        fullSet.addAll(shape2);
        for (final Direction dir : fullSet) {
            if (!shape2.contains(dir) || !shape1.contains(dir)) {
                return false;
            }
        }
        return true;
    }
}
