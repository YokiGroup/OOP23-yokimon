package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.json.InvalidJsonException;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Loads {@link TileBuilder TileBuilders} from the tile's json configuration.
 */
public final class TileLoader extends IdJsonLoader<TileBuilder> {
    private static final String TILE_JSON_RPATH = "tiles.json";
    private static final String TILE_SHAPE_JPATHF = "$.%s.shape[*]";
    private static final String HOME = "home";

    /**
     * Initializes a TileLoader by parsing the default tiles.json.
     */
    public TileLoader() throws IOException {
        super(TILE_JSON_RPATH);
    }

    private <T> Set<T> ifNullReturnEmpty(final Set<T> set) {
        return set != null ? set : Set.of();
    }

    private Hitbox getHitbox(final int id, final int index) {
        final JsonParser parser = getParser();
        final String tileHitboxTypeJPATHF = "$.%s.hitboxes[%d]";
        final String tileHitboxTypeRPATH = ".type";
        final String tileHitboxPositionRPATH = ".position";
        final String tileHitboxDimensionsRPATH = ".dimensions";
        final String tileHitboxRadiusRpath = ".radius";
        final String formattedHitboxJPATH = String.format(tileHitboxTypeJPATHF, id == -1 ? HOME : String.valueOf(id), index);
        final String type = parser.read(formattedHitboxJPATH + tileHitboxTypeRPATH);
        final Vector2 pos = getVector2(formattedHitboxJPATH + tileHitboxPositionRPATH);

        return switch (type) {
            case "rect" -> {
                final Vector2 dim = getVector2(formattedHitboxJPATH + tileHitboxDimensionsRPATH);
                yield new RectangularHitbox(pos, dim);
            }
            case "circle" -> {
                final double radius = parser.read(formattedHitboxJPATH + tileHitboxRadiusRpath);
                yield new CircularHitbox(pos, radius);
            }
            default -> throw new InvalidJsonException(
                    String.format("Invalid type of hitbox index %d of name %s: received %s", index, id, type)
            );
        };
    }

    private Set<Hitbox> getHitboxes(final int id) {
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            c.add(getHitbox(id, i));
        }));
    }

    private Set<Pair<TileBuilder.EntityType, Position>> getEntities(final int id) {
        final String spawnPositionJPATH = "$." + (id == -1 ? HOME : id) + ".spawns[%d]";
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            final String formattedJPath = String.format(spawnPositionJPATH, i);
            final Position position = new PositionImpl(getVector2(formattedJPath));
            final String type = getParser().read(formattedJPath + ".type");
            c.add(
                    switch (type) {
                        case "enemy" -> new Pair<>(TileBuilder.EntityType.ENEMY, position);
                        case "altar" -> new Pair<>(TileBuilder.EntityType.ALTAR, position);
                        default -> throw new InvalidJsonException(String.format("invalid type %s", type));
            });
        }));
    }

    private Set<Direction> getTileDirs(final int id) {
        final List<String> rawTileDirs = getParser().read(String.format(TILE_SHAPE_JPATHF, id == -1 ? HOME : String.valueOf(id)));
        final Set<Direction> tileDirs = new HashSet<>();

        rawTileDirs.forEach(d -> tileDirs.add(Direction.valueOf(d)));
        return tileDirs;
    }

    private String getResourceURL(final int id) {
        final String resourceURLJPATH = "$." + (id == -1 ? HOME : String.valueOf(id)) + ".texture";
        return getParser().read(resourceURLJPATH);
    }

    private TileBuilder getTileAt(final int id) {
        final TileBuilder tileBuilder = new TileBuilderImpl(id, getResourceURL(id));
        tileBuilder.addAllHitboxes(getHitboxes(id));
        tileBuilder.addAllEntities(getEntities(id));
        tileBuilder.addAllAdjacencies(getTileDirs(id));
        return tileBuilder;
    }

    /**
     * @return {@link TileBuilder} representing the home tile
     */
    public TileBuilder getHomeTile() {
        return getTileAt(-1);
    }

    @Override
    public TileBuilder load(final int id) {
        return getTileAt(id);
    }
}
