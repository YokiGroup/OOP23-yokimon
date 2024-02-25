package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.gen.TileShape;
import io.github.yokigroup.world.gen.TileShapeImpl;
import io.github.yokigroup.world.tile.TileBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Loads all the tiles contained in the json file describing them and puts them in a Set of {@link TileShape}s.
 */
public class TileShapeLoader extends JsonLoader<TileShape> {
    private static final String TILE_JSON_RPATH = "tiles.json";
    private final Map<Set<Direction>, Set<TileBuilder>> tiles = new HashMap<>();
    private final IdJsonLoader<TileBuilder> tileLoader;

    /**
     * Constructor.
     * @throws
     */
    public TileShapeLoader() throws IOException {
        this(new TileLoader());
    }

    /**
     * Instance TileShapeLoader with an existing TileLoader.
     *
     * @param loader loader to use
     * @throws
     */
    public TileShapeLoader(final TileLoader loader) throws IOException {
        super(TILE_JSON_RPATH);
        Objects.requireNonNull(loader);
        this.tileLoader = loader;
    }

    private Pair<Set<Direction>, TileBuilder> load(final int id) {
        final TileBuilder tileBuilder = tileLoader.load(id);
        return new Pair<>(tileBuilder.getAdjacencies(), tileBuilder);
    }

    private void insertTile(final Set<Direction> dirs, final TileBuilder tile) {
        Objects.requireNonNull(dirs);
        Objects.requireNonNull(tile);

        if (!tiles.containsKey(dirs)) {
            tiles.put(dirs, new HashSet<>());
        }
        tiles.get(dirs).add(tile);
    }

    /**
     * @return Set of all the possible {@link TileShape}s, each {@link TileShape} containing all tiles of that shape.
     */
    public Set<TileShape> getAll() {
        final Set<TileShape> shapes = new HashSet<>();

        if (tiles.isEmpty()) {
            doUntilPathException(null, (c, i) -> {
                final var nextTile = load(i + 1);
                insertTile(nextTile.x(), nextTile.y());
            });
        }

        for (final var tileEntry : tiles.entrySet()) {
            shapes.add(new TileShapeImpl(tileEntry.getValue(), tileEntry.getKey()));
        }

        return shapes;
    }
}
