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
    private static final String TILE_HITBOX_TYPE_JPATHF = "$.%s.hitboxes[%d]";

    public TileLoader() {
        super(TILE_JSON_RPATH);
    }

    private <T> Set<T> ifNullReturnEmpty(final Set<T> set) {
        return set != null ? set : Set.of();
    }

    private Hitbox getHitbox(final String name, final int index) {
        JsonParser parser = getParser();
        final String formattedHitboxJPath = String.format(TILE_HITBOX_TYPE_JPATHF, name, index);
        final String type = parser.read(formattedHitboxJPath  +  ".type");
        final Vector2 pos = getVector2(formattedHitboxJPath  +  ".position");

        Hitbox retHBox;
        switch (type) {
            case "rect":
                final Vector2 dim = getVector2(formattedHitboxJPath  +  ".dimensions");
                retHBox = new RectangularHitbox(pos, dim);
                break;

            case "circle":
                final double radius = parser.read(formattedHitboxJPath  +  ".radius");
                retHBox = new CircularHitbox(pos, radius);
                break;

            default:
                throw new RuntimeException(
                        String.format("invalid type of hitbox index %d of name %s: received %s", index, name, type)
                );
        }
        return retHBox;
    }

    private Set<Hitbox> getHitboxes(final String name) {
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            c.add(getHitbox(name, i));
        }));
    }

    private Set<Vector2>  getSpawnPositions(final String name) {
        final String spawnPositionJPath = "$." + name + ".spawns[%d]";
        return ifNullReturnEmpty(doUntilPathException(new HashSet<>(), (c, i) -> {
            c.add(getVector2(String.format(spawnPositionJPath, i)));
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
