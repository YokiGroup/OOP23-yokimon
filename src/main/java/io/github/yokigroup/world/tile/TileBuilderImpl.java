package io.github.yokigroup.world.tile;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.entity.Altar;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.entity.people.Enemy;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of a builder class to create a tile object.
 */
public class TileBuilderImpl implements TileBuilder {
    private final Set<Pair<EntityType, Position>> entities;
    private final Set<Hitbox> hitboxes;
    private final Set<Direction> adjacencies;
    private final int id;

    /**
     * Creates an empty tile with no entities, hitboxes nor adjacencies.
     * @param id The id of the tile.
     */
    public TileBuilderImpl(final int id) {
        this.entities = new HashSet<>();
        this.hitboxes = new HashSet<>();
        this.adjacencies = new HashSet<>();
        this.id = id;
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
    public final TileBuilder addEntity(final EntityType entityType, final Position position) {
        this.entities.add(new Pair<>(entityType, position));
        return this;
    }

    @Override
    public final TileBuilder addAllEntities(final Set<Pair<EntityType, Position>> entities) {
        this.entities.addAll(entities);
        return this;
    }

    @Override
    public final TileBuilder addAdjacency(final Direction direction) {
        this.adjacencies.add(direction);
        return this;
    }

    @Override
    public final Tile build(final MessageHandler handler) {
        Set<Entity> createdEntities = generateEntities(handler);
        return new TileImpl(id, this.adjacencies, this.hitboxes, createdEntities);
    }

    private Set<Entity> generateEntities(final MessageHandler handler) {
        return this.entities.stream()
                .map(e -> switch (e.x()) {
                    case ENEMY -> new Enemy(e.y(), handler);
                    case ALTAR -> new Altar(e.y(), handler);
                }).collect(Collectors.toSet());
    }
}
