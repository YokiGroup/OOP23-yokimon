package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.gen.wfc.WaveFunctionCollapse;
import io.github.yokigroup.world.gen.wfc.WaveFunctionCollapseImpl;
import io.github.yokigroup.world.gen.wfc.WfcShapeDirection;
import io.github.yokigroup.world.tile.Tile;

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
        final Set<Set<WfcShapeDirection>> convertedShapes = this.tileShapes.stream()
                .map(TileShape::getPossibleDirections)
                .map(this::tileShapeToWfc)
                .collect(Collectors.toSet());
        this.wfc = new WaveFunctionCollapseImpl(dimensions, convertedShapes);
    }

    @Override
    public final void setStaticTile(final Pair<Integer, Integer> position, final Set<TileShape.TileDirections> tile) {
        this.wfc.setStaticShape(position, Set.of(tileShapeToWfc(tile)));
    }

    @Override
    public final void runWFC() {
        wfc.generateShapeMap();
    }

    @Override
    public final Tile getTileAt(final Pair<Integer, Integer> position) {
        final Optional<WeightedPool<Tile>> tilePool =  this.tileShapes.stream()
                .filter(s -> compareShapes(s.getPossibleDirections(), wfcToTileShape(wfc.getShapeAt(position))))
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
    private boolean compareShapes(final Set<TileShape.TileDirections> shape1, final Set<TileShape.TileDirections> shape2) {
        Set<TileShape.TileDirections> fullSet = new HashSet<>();
        fullSet.addAll(shape1);
        fullSet.addAll(shape2);
        for (TileShape.TileDirections dir : fullSet) {
            if (!shape2.contains(dir) || !shape1.contains(dir)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a tile shape from TileDirections to WfcShapeDirection.
     * @param shape The shape of the tile.
     * @return The set in WfcShapeDirection format.
     */
    private Set<WfcShapeDirection> tileShapeToWfc(final Set<TileShape.TileDirections> shape) {
        final Set<WfcShapeDirection> convertedShapes = new HashSet<>();
        if (shape.contains(TileShape.TileDirections.UP)) {
            convertedShapes.add(WfcShapeDirection.UP);
        }
        if (shape.contains(TileShape.TileDirections.DOWN)) {
            convertedShapes.add(WfcShapeDirection.DOWN);
        }
        if (shape.contains(TileShape.TileDirections.LEFT)) {
            convertedShapes.add(WfcShapeDirection.LEFT);
        }
        if (shape.contains(TileShape.TileDirections.RIGHT)) {
            convertedShapes.add(WfcShapeDirection.RIGHT);
        }
        return convertedShapes;
    }

    /**
     * Converts a tile shape from WfcShapeDirection to TileDirections.
     * @param shape The shape of the tile.
     * @return The set in TileDirections format.
     */
    private Set<TileShape.TileDirections> wfcToTileShape(final Set<WfcShapeDirection> shape) {
        final Set<TileShape.TileDirections> convertedShapes = new HashSet<>();
        if (shape.contains(WfcShapeDirection.UP)) {
            convertedShapes.add(TileShape.TileDirections.UP);
        }
        if (shape.contains(WfcShapeDirection.DOWN)) {
            convertedShapes.add(TileShape.TileDirections.DOWN);
        }
        if (shape.contains(WfcShapeDirection.LEFT)) {
            convertedShapes.add(TileShape.TileDirections.LEFT);
        }
        if (shape.contains(WfcShapeDirection.RIGHT)) {
            convertedShapes.add(TileShape.TileDirections.RIGHT);
        }
        return convertedShapes;
    }
}
