package io.github.yokigroup.file.loader;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileImpl;

import java.util.HashSet;
import java.util.Set;

public class TileLoader extends IdJsonLoader<Tile>{
    private static final String TILE_JSON_RPATH = "tiles.json";

    public TileLoader() {
        super(TILE_JSON_RPATH);
    }

    private <T> Set<T> ifNullReturnEmpty(final Set<T> set) {
        return set != null ? set : Set.of();
    }

    private Hitbox getHitbox(final String name, final int index) {
        JsonParser parser = getParser();
        final String TILE_HITBOX_TYPE_JPATHF = "$.%s.hitboxes[%d]";
        final String TILE_HITBOX_TYPE_RPATH = ".type";
        final String TILE_HITBOX_POSITION_RPATH = ".position";
        final String TILE_HITBOX_DIMENSIONS_RPATH = ".dimensions";
        final String TILE_HITBOX_RADIUS_RPATH = ".radius";
        final String formattedHitboxJPath = String.format(TILE_HITBOX_TYPE_JPATHF, name, index);
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
                    String.format("invalid type of hitbox index %d of name %s: received %s", index, name, type)
            );
        };
    }

    private Set<Hitbox> getHitboxes(final String name) {
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            c.add(getHitbox(name, i));
        }));
    }

    private Set<Vector2>  getSpawnPositions(final String name) {
        final String SPAWN_POSITION_JPATH = "$." + name + ".spawns[%d]";
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            c.add(getVector2(String.format(SPAWN_POSITION_JPATH, i)));
        }));
    }

    private Tile getTileAt(String name) {
        return new TileImpl(getHitboxes(name), getSpawnPositions(name));
    }

    public Tile getHomeTime() {
        return getTileAt("home");
    }

    @Override
    public Tile load(final int id) {
        return getTileAt(""+id);
    }
}
