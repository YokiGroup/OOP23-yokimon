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

    /**
     * Creates an empty tile with no entities, hitboxes nor adjacencies.
     */
    public TileBuilderImpl() {
        this.entities = new HashSet<>();
        this.hitboxes = new HashSet<>();
        this.adjacencies = new HashSet<>();
    }

    @Override
    public final TileBuilder addHitbox(final Hitbox hitbox) {
        this.hitboxes.add(hitbox);
        return this;
    }

    @Override
    public final TileBuilder addAllHitboxes(final Set<Hitbox> hitboxes) {
        this.hitboxes.addAll(hitboxes);
        return this;
    }

    @Override
    public final TileBuilder addEntity(final Entity entity) {
        this.entities.add(entity);
        return this;
    }

    @Override
    public final TileBuilder addAllEntities(final Set<Entity> entities) {
        this.entities.addAll(entities);
        return this;
    }

    @Override
    public final TileBuilder addAdjacency(final Direction direction) {
        this.adjacencies.add(direction);
        return this;
    }

    @Override
    public Tile build(final int id) {
        return new TileImpl(id, this.adjacencies, this.hitboxes, this.entities);
    }
}
