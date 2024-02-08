package io.github.yokigroup.world.tile;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;

import java.util.Collections;
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

    /**
     *
     * @return All the hitboxes in a tile.
     */
    @Override
    public Set<Hitbox> getHitboxes() {
        return Set.copyOf(this.hitboxes);
    }

    /**
     *
     * @return All the entity spawn locations on the tile.
     */
    @Override
    public Set<Vector2> getEntitySpawnLocations() {
        return Set.copyOf(spawnLocations);
    }

    /**
     * Adds a hitbox to the tile.
     * @param hitbox The hitbox to add to the tile.
     */
    @Override
    public void addHitbox(final Hitbox hitbox) {
        this.hitboxes.add(hitbox);
    }

    /**
     *
     * @param pos The spawn position to add to the tile.
     */
    @Override
    public void addSpawnLocation(final Vector2 pos) {
        this.spawnLocations.add(pos);
    }

    /**
     * Spawns all the entities in the tile.
     * @param entityPool The pool of entities to randomly add to the tile.
     */
    @Override
    public void spawnEntities(final WeightedPool<Entity> entityPool) {
        this.spawnLocations
                .forEach((pos) -> {
                    final Entity entity = entityPool.getRandomizedElement();
                    // FIXME: entity uses position instead of Vector2
                    //entity.setPosition(pos);
                    this.entities.add(entity);
                });
    }

    /**
     *
     * @return All the entities in a tile.
     */
    @Override
    public Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

    /**
     * Runs the update method on all the entities on the tile.
     */
    @Override
    public void updateEntities() {
        this.entities.forEach(Entity::update);
    }
}
