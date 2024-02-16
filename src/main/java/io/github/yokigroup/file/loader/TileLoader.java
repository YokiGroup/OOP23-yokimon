package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.json.InvalidJsonException;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;

import java.util.HashSet;
import java.util.Set;

public class TileLoader extends IdJsonLoader<TileBuilder> {
    private static final String TILE_JSON_RPATH = "tiles.json";

    public TileLoader() {
        super(TILE_JSON_RPATH);
    }

    private <T> Set<T> ifNullReturnEmpty(final Set<T> set) {
        return set != null ? set : Set.of();
    }

    private Hitbox getHitbox(final int id, final int index) {
        JsonParser parser = getParser();
        final String TILE_HITBOX_TYPE_JPATHF = "$.%s.hitboxes[%d]";
        final String TILE_HITBOX_TYPE_RPATH = ".type";
        final String TILE_HITBOX_POSITION_RPATH = ".position";
        final String TILE_HITBOX_DIMENSIONS_RPATH = ".dimensions";
        final String TILE_HITBOX_RADIUS_RPATH = ".radius";
        final String formattedHitboxJPath = String.format(TILE_HITBOX_TYPE_JPATHF, id == -1 ? "home" : ""+id, index);
        final String type = parser.read(formattedHitboxJPath  +  TILE_HITBOX_TYPE_RPATH);
        final Vector2 pos = getVector2(formattedHitboxJPath  +  TILE_HITBOX_POSITION_RPATH);

        return switch (type) {
            case "rect" -> {
                final Vector2 dim = getVector2(formattedHitboxJPath + TILE_HITBOX_DIMENSIONS_RPATH);
                yield new RectangularHitbox(pos, dim);
            }
            case "circle" -> {
                final double radius = parser.read(formattedHitboxJPath + TILE_HITBOX_RADIUS_RPATH);
                yield new CircularHitbox(pos, radius);
            }
            default -> throw new RuntimeException(
                    String.format("invalid type of hitbox index %d of name %s: received %s", index, id, type)
            );
        };
    }

    private Set<Hitbox> getHitboxes(final int id) {
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            c.add(getHitbox(id, i));
        }));
    }

    private Set<Pair<TileBuilder.EntityType, Position>> getEntities(final int id) {
        final String SPAWN_POSITION_JPATH = "$." + (id == -1 ? "home" : id) + ".spawns[%d]";
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            final String formattedJPath = String.format(SPAWN_POSITION_JPATH, i);
            Position position = new PositionImpl(getVector2(formattedJPath));
            String type = getParser().read(formattedJPath + ".type");
            c.add(switch (type) {
                case "enemy" -> new Pair<>(TileBuilder.EntityType.ENEMY, position);
                case "altar" -> new Pair<>(TileBuilder.EntityType.ALTAR, position);
                default -> throw new InvalidJsonException(String.format("invalid type %s", type));
            });
        }));
    }

    private TileBuilder getTileAt(final int id) {
        TileBuilder tileBuilder = new TileBuilderImpl(id);
        tileBuilder.addAllHitboxes(getHitboxes(id));
        tileBuilder.addAllEntities(getEntities(id));
        return tileBuilder;
    }

    public TileBuilder getHomeTile() {
        return getTileAt(-1);
    }

    @Override
    public TileBuilder load(final int id) {
        return getTileAt(id);
    }
}
