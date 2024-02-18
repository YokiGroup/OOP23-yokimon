package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import java.util.Set;

public class TestMessageHandler extends GameMessageHandler {


        private final Set<Class<? extends Submodule>> subModules;

        public TestMessageHandler(Set<Class<? extends Submodule>> subModules) {
            this.subModules = Set.copyOf(subModules);
        }

        public static class TestSubmodule extends GameMapSubmoduleAbs {
            private static final double X_TEST = 150;
            private static final double Y_TEST = 120;
            private static final double DISTANCE = 40;
            static final private Vector2 vP = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - DISTANCE,
                    (double) GameMap.TILE_DIMENSIONS.y() / 2);
            private static final Vector2 v2 = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - X_TEST,
                    (double) GameMap.TILE_DIMENSIONS.y() / 2 - Y_TEST - 2);
            private static final Vector2 v3 = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - 150,
                    (double) GameMap.TILE_DIMENSIONS.y() / 2 - Y_TEST - 3);
            private static final Position altarPos = new PositionImpl(vP);
            private static final Position pos1 = new PositionImpl(v2);
            private static final Position pos2 = new PositionImpl(v3);
            final GameMap map;

            public TestSubmodule(final MessageHandler handler) {
                super(handler);
                TileBuilder tile = new TileBuilderImpl(0).addEntity(TileBuilder.EntityType.ALTAR, altarPos)
                        .addEntity(TileBuilder.EntityType.ENEMY, altarPos)
                        .addEntity(TileBuilder.EntityType.ENEMY, pos1)
                        .addEntity(TileBuilder.EntityType.ENEMY, pos2);
                this.map = new GameMapBuilderImpl().putTileAt(tile, new Pair<>(0, 0)).build(this.handler());
            }

            @Override
            public GameMap getGameMap() {
                return map;
            }

            @Override
            public Set<Hitbox> getHitboxesOnCurrentTile() {
                return map.getPlayerTile().getHitboxes();
            }

            @Override
            public Set<Entity> getEntitiesOnCurrentTile() {
                return map.getPlayerTile().getEntities();
            }
        }

        @Override
        protected Set<Class<? extends Submodule>> getSubmoduleTypes() {
            return this.subModules;
        }
}

