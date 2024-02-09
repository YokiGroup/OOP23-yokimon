package io.github.yokigroup.world.tile;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * A tile contains the data of an entire part of the map: its entities and hitboxes manly.
 */
public class TileImpl implements Tile {
    private Set<Vector2> spawnLocations;
    private Set<Hitbox> hitboxes;
    private Set<Entity> entities;

    /**
     * Creates a tile with static and dynamic entities.
     * @param hitboxes The invisible walls in a tile.
     * @param spawnLocations The entity spawn locations in the tile.
     */
    public TileImpl(final Set<Hitbox> hitboxes, final Set<Vector2> spawnLocations) {
        this.entities = new HashSet<>();
        this.hitboxes = hitboxes;
        this.spawnLocations = spawnLocations;
    }

    /**
     * Creates a tile with no hitboxes and no entities.
     */
    public TileImpl() {
        this(new HashSet<>(), new HashSet<>());
    }

    @Override
    public final Set<Hitbox> getHitboxes() {
        return Set.copyOf(this.hitboxes);
    }

    @Override
    public final Set<Vector2> getEntitySpawnLocations() {
        return Set.copyOf(spawnLocations);
    }

    @Override
    public final void addHitbox(final Hitbox hitbox) {
        this.hitboxes.add(hitbox);
    }

    @Override
    public final void addSpawnLocation(final Vector2 pos) {
        this.spawnLocations.add(pos);
    }

    @Override
    public final void spawnEntities(final WeightedPool<Entity> entityPool) {
        this.spawnLocations
                .forEach((pos) -> {
                    final Entity entity = entityPool.getRandomizedElement();
                    // FIXME: entity uses position instead of Vector2
                    //entity.setPosition(pos);
                    this.entities.add(entity);
                });
    }

    @Override
    public final Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

    @Override
    public final void updateEntities() {
        this.entities.forEach(Entity::update);
    }
}
