package io.github.yokigroup.event.submodule;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.PlayerCharacterSubmoduleAbs;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;
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
    private final Player player;
    private final Publisher<SpriteData> playerPub = new PublisherImpl<>();

    private void publishPlayerSpriteData() {
        playerPub.notifyObservers(player.getSpriteData());
    }

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     * @param modelObs the model Observer.
     */
    public PlayerCharacterSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
        Vector2 playerPos = Vector2Impl.castPair(GameMap.TILE_DIMENSIONS).scale(0.5);
        this.player = new Player(new PositionImpl(playerPos), handler);
        modelObs.addSpritePublisher(RenderState.WORLD, playerPub);
        publishPlayerSpriteData();
    }

    // TODO Change Direction reference
    @Override
    public void changeTile(final Direction dir) {
        handler().handle(GameMapSubmodule.class, s -> {
            s.movePlayerToTile(dir);
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
        player.move(delta);
        publishPlayerSpriteData();
    }

    @Override
    public void movePlayerTo(final Position pos) {
        player.setPos(pos);
        publishPlayerSpriteData();
    }

}
