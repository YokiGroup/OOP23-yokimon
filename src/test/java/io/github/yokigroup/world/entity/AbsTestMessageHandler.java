
package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.view.render.observer.NOPModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;

import java.util.Set;
/**
 * Test Class which extends GameMessageHandler.
 */
public final class AbsTestMessageHandler extends GameMessageHandler {
    private static final double X_TEST = 400;
    private static final double Y_TEST = 100;
    private static final double DISTANCE = 40;

    /**
     * Method take Submodules needed by AbsMessageHandler.
     * @return Set of submodules for tests
     */
    private Set<Class<? extends Submodule>> getSubmoduleTypes() {
        return Set.of(
                PlayerCharacterSubmodule.class,
                AbsTestMessageHandler.TestSubmodule.class,
                PartySubmodule.class,
                FightSubmodule.class,
                GameMapSubmodule.class

        );
    }

    /**
     * Constructor of this test class, instantiate ModelObserver and SubModules.
     */
    public AbsTestMessageHandler() {
        this.instantiateSubmodules(new NOPModelObserver(), getSubmoduleTypes());
    }

    /**
     * TestSubmodule for create a dummy map used for Entities test.
     */
    public static class TestSubmodule extends GameMapSubmoduleAbs {
        private final Vector2 v = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - X_TEST,
                (double) GameMap.TILE_DIMENSIONS.y() / 2 - Y_TEST - 1);
        private final Vector2 v2 = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - X_TEST,
                (double) GameMap.TILE_DIMENSIONS.y() / 2 - Y_TEST - 2);
        private final Vector2 vAl = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - DISTANCE,
                (double) GameMap.TILE_DIMENSIONS.y() / 2);
        private final Position pos1 = new PositionImpl(v);
        private final Position pos2 = new PositionImpl(v2);
        private final Position altarPos = new PositionImpl(vAl);

        private final TileBuilder tile = new TileBuilderImpl(0, "")
                .addEntity(TileBuilder.EntityType.ALTAR, altarPos)
                .addEntity(TileBuilder.EntityType.ENEMY, pos1)
                .addEntity(TileBuilder.EntityType.ENEMY, pos2);


        private final GameMap map = new GameMapBuilderImpl().putTileAt(tile, new Pair<>(0, 0)).build(this.handler());
        //Without public modifier GameMessageHandler reflection cannot access this constructor

        //CHECKSTYLE: RedundantModifier OFF

        /**
         * Constructor.
         * @param handler MessageHandler
         * @param modelObs ModelObserver
         */
        public TestSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
//CHECKSTYLE: RedundantModifier ON
            super(handler, modelObs);
        }

        /**
         * Test implementation.
         * @return int
         */
        @Override
        public int getPlayerDistanceFromHome() {
            return 1;
        }

        /**
         * Test implementation.
         * @param dir direction of the tile relative to the one the player's currently on
         * @return boolean
         */
        @Override
        public boolean movePlayerToTile(final Direction dir) {
            return map.movePlayerTileMapPosition(dir);
        }

        /**
         * Test implementation.
         * @return Set Hitbox
         */
        @Override
        public Set<Hitbox> getHitboxesOnCurrentTile() {
            return map.getPlayerTile().getHitboxes();
        }

        /**
         * Test implementation.
         * @return Set Entity
         */
        @Override
        public Set<Entity> getEntitiesOnCurrentTile() {
            return map.getPlayerTile().getEntities();
        }

        @Override
        protected void updateEntities() {

        }

        /**
         * Should return true if all Enemies on the map are dead,
         * but this is a test implementation.
         * @return false
         */
        @Override
        protected boolean areAllEnemiesSlain() {
            return false;
        }

        }

}
