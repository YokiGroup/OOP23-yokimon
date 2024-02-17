package io.github.yokigroup.event.submodule;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.PlayerCharacterSubmoduleAbs;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.people.Player;

/**
 * Implementation of {@link PlayerCharacterSubmoduleAbs}.
 * @author Giovanni Paone
 */
public final class PlayerCharacterSubmodule extends PlayerCharacterSubmoduleAbs {
    private final Entity player;
    private Publisher<SpriteData> playerPub = new PublisherImpl<>();

    // FIXME Replace with proper implementation

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public PlayerCharacterSubmodule(final MessageHandler handler, ModelObserver modelObs) {
        super(handler);
        Vector2 playerPos = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2, (double) GameMap.TILE_DIMENSIONS.y() / 2);
        this.player = new Player(new PositionImpl(playerPos), handler);
        modelObs.addWorldSpritePublisher(1, playerPub);
    }

    // TODO Change Direction reference
    @Override
    public void changeTile(final Direction dir) {
        handler().handle(GameMapSubmodule.class, s -> {
            s.getGameMap().movePlayerTileMapPosition(dir);
        });
    }

    @Override
    public Position getPosition() {
        return player.getPos();
    }

    @Override
    public Entity getPlayerEntity() {
        return player;
    }

    @Override
    public void movePlayerBy(final Vector2 delta) {
        player.setPos(
                new PositionImpl(player.getPos().getPosition().plus(delta))
        );
        playerPub.notifyObservers(new SpriteData("view/game/textures/michele.png", this.player.getPos().getPosition(), new Vector2Impl(100, 100)));
    }

}
