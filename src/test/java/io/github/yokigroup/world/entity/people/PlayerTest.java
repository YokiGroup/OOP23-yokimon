package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private TestMessageHandler testMeg;

    private static final class TestMessageHandler extends GameMessageHandler {
        public static class TestSubmodule extends GameMapSubmoduleAbs {

            private final TileBuilder tile = new TileBuilderImpl(0, "");
            private final GameMap map = new GameMapBuilderImpl().putTileAt(tile, new Pair<>(0, 0)).build(this.handler());

             TestSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
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


            protected void updateEntities() {

            }

            @Override
            protected void updateTile() {

            }

        }

        @Override
        protected Set<Class<? extends Submodule>> getSubmoduleTypes() {
            return Set.of(
                    PlayerCharacterSubmodule.class,
                    TestMessageHandler.TestSubmodule.class,
                    PartySubmodule.class,
                    GameMapSubmodule.class
            );
        }
    }

    @BeforeEach
    void setUp() {
        testMeg = new TestMessageHandler();
    }

    @Test
    void move() {
        final int scalable = 50;
        final int scaleTot = 250;
        testMeg.handle(GameMapSubmodule.class, map -> {
            assertEquals(1, map.getEntitiesOnCurrentTile().size());
            map.getEntitiesOnCurrentTile().forEach(en -> {
                System.out.println(en.getHitBox().toString());
                System.out.println(en.getPos().getPosition().getX() + "    " + en.getPos().getPosition().getY());
            });
        });
        testMeg.handle(PlayerCharacterSubmodule.class, play -> {
            for (double i = scalable; i < scaleTot; i = i + scalable) {
                play.movePlayerBy(new Vector2Impl(play.getPosition().getPosition().getX() + i,
                        play.getPosition().getPosition().getY()));
                System.out.println("x =" + play.getPosition().getPosition().getX() + "y= "
                        + play.getPosition().getPosition().getY());
                System.out.println("x =" + GameMap.TILE_DIMENSIONS.x() / 2 + "y= " + GameMap.TILE_DIMENSIONS.y() / 2);
                //assertEquals(GameMap.TILE_DIMENSIONS.x() / 2 + i, play.getPosition().getPosition().getX());
                /* assertEquals(new PositionImpl
                                (new Vector2Impl(GameMap.TILE_DIMENSIONS.x() + i, GameMap.TILE_DIMENSIONS.y())),
                        play.getPosition());*/
            }
        });

    }
}
