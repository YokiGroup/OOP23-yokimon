package io.github.yokigroup.world.tile;

import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.Direction;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of a builder class to create a tile object.
 */
public class TileBuilderImpl implements TileBuilder {
    private final Set<Entity> entities;
    private final Set<Hitbox> hitboxes;
    private final Set<Direction> adjacencies;

    public TileBuilderImpl() {
        this.entities = new HashSet<>();
        this.hitboxes = new HashSet<>();
        this.adjacencies = new HashSet<>();
    }

    @Override
    public final void addHitbox(final Hitbox hitbox) {
        this.hitboxes.add(hitbox);
    }

    @Override
    public final void addAllHitboxes(final Set<Hitbox> hitboxes) {
        this.hitboxes.addAll(hitboxes);
    }

    @Override
    public final void addEntity(final Entity entity) {
        this.entities.add(entity);
    }

    @Override
    public final void addAllEntities(final Set<Entity> entities) {
        this.entities.addAll(entities);
    }

    @Override
    public final void addAdjacency(final Direction direction) {
        this.adjacencies.add(direction);
    }

    @Override
    public Tile build(final int id) {
        return new TileImpl(id, this.adjacencies, this.hitboxes, this.entities);
    }
}
