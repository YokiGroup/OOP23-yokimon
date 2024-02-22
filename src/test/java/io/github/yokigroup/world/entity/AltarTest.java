package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AltarTest {
    //private TestMessageHandler testMeg;
    private static final double DISTANCE = 40;

    private static final class TestMessageHandler extends GameMessageHandler {
        public static class TestSubmodule extends GameMapSubmoduleAbs {
            private final Vector2 v = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - DISTANCE,
                    (double) GameMap.TILE_DIMENSIONS.y() / 2);
            private final TileBuilder tile = new TileBuilderImpl(0, "")
                    .addEntity(TileBuilder.EntityType.ALTAR, new PositionImpl(v));
            private final GameMap map = new GameMapBuilderImpl().putTileAt(tile, new Pair<>(0, 0)).build(this.handler());
            //Without public modifier GameMessageHandler reflection cannot access this constructor

            //CheckStyle: RedundantModifier OFF
            public TestSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
                //CheckStyle: RedundantModifier ON

                super(handler, modelObs);
            }

            @Override
            public int getPlayerDistanceFromHome() {
                return 1;
            }

            @Override
            public boolean movePlayerToTile(final Direction dir) {
                return map.movePlayerTileMapPosition(dir);
            }

            @Override
            public Set<Hitbox> getHitboxesOnCurrentTile() {
                return map.getPlayerTile().getHitboxes();
            }

            @Override
            public Set<Entity> getEntitiesOnCurrentTile() {
                return map.getPlayerTile().getEntities();
            }

            @Override
            protected void updateEntities() {

            }

            @Override
            protected void updateTile() {

            }

        }

        @Override
        protected Set<Class<? extends Submodule>> getSubmoduleTypes() {
            return Objects.requireNonNull(Set.of(
                    PlayerCharacterSubmodule.class,
                    TestSubmodule.class,
                    PartySubmodule.class,
                    GameMapSubmodule.class
            ));
        }
    }

    @Test
    void altarTest() {
        final TestMessageHandler testMeg = new TestMessageHandler();
        testMeg.handle(TestMessageHandler.TestSubmodule.class, map -> {
           for (final Entity entity : map.getEntitiesOnCurrentTile()) {

               if (entity instanceof Altar altar) {
                   testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        assertEquals(Altar.AltarState.POWERED,  altar.getState());
                        altar.update();
                        assertEquals(Altar.AltarState.USED,  altar.getState());
                        testMeg.handle(PartySubmodule.class, party -> {
                           assertEquals(1, party.listYokimons().size());
                        });
                   });
               }
           }
        });

    }

}
