package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.world.GameMap;

import java.util.Locale;

public class FightObserver extends ViewObserver<Fight> {
    private static final String YOKIMON_SPRITES_ROOT_DIR = "io/github/yokigroup/view/textures/yokimons/";

    public FightObserver(final Painter painter) {
        super(painter);
    }

    private void drawYokimons(final DrawQueue drawQueue, final Fight fight) {
        final Vector2 gameMapVec = Vector2Impl.castPair(GameMap.TILE_DIMENSIONS);
        final double xYokimonSpritePlacement = .3;
        final double yYokimonSpritePlacement = .67;
        final double yokimonSpriteDim = .22;
        drawQueue.addToDrawQueue(
                new SpriteData(
                        YOKIMON_SPRITES_ROOT_DIR + fight.getCurrentMyYokimon().getName().toLowerCase(Locale.ROOT) + ".png",
                        gameMapVec.times(new Vector2Impl(xYokimonSpritePlacement, yYokimonSpritePlacement)),
                        gameMapVec.scale(yokimonSpriteDim),
                        1
                )
        );
        drawQueue.addToDrawQueue(
                new SpriteData(
                        YOKIMON_SPRITES_ROOT_DIR + fight.getCurrentOpponent().getName().toLowerCase(Locale.ROOT) + ".png",
                        gameMapVec.times(new Vector2Impl(1 - xYokimonSpritePlacement, yYokimonSpritePlacement)),
                        gameMapVec.scale(yokimonSpriteDim),
                        1,
                        true
                )
        );
    }

    @Override
    public void update(final Fight lastArg, final Fight arg) {
        final DrawQueue fightDrawQueue = painter().drawQueue(RenderState.FIGHT);
        drawYokimons(fightDrawQueue, arg);
    }
}
