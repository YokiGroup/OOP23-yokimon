package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.drawqueue.DrawQueue;
import io.github.yokigroup.view.render.painter.Painter;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.world.GameMap;

import java.util.Locale;
import java.util.function.Consumer;

public class FightObserver extends ViewObserver<Fight> {
    private static final String YOKIMON_SPRITES_ROOT_DIR = "io/github/yokigroup/view/textures/yokimons/";
    final Consumer<String> setPlayerYokimonLabel;
    final Consumer<String> setEnemyYokimonLabel;

    public FightObserver(final Painter painter) {
        super(painter);
        setPlayerYokimonLabel = painter::setPlayerYokimonLabel;
        setEnemyYokimonLabel = painter::setEnemyYokimonLabel;
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

    private String getYokimonHPStr(final Yokimon yokimon) {
        final String hpFmtStr = "%d/%d HP";
        return String.format(hpFmtStr, yokimon.getActualHp(), yokimon.getStat(Yokimon.Stats.HP));
    }

    private void drawYokimonStats(final Fight fight) {
        setEnemyYokimonLabel.accept(getYokimonHPStr(fight.getCurrentMyYokimon()));
        setPlayerYokimonLabel.accept(getYokimonHPStr(fight.getCurrentOpponent()));
    }

    @Override
    public void update(final Fight lastArg, final Fight arg) {
        final DrawQueue fightDrawQueue = painter().drawQueue(RenderState.FIGHT);
        drawYokimons(fightDrawQueue, arg);
        drawYokimonStats(arg);
    }
}
