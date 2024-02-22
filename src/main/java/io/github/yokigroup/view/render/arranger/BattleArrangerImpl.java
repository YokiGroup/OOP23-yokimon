package io.github.yokigroup.view.render.arranger;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.render.Painter;

public class BattleArrangerImpl extends Arranger implements BattleArranger {
    public BattleArrangerImpl() {
        super(null);
    }

    @Override
    public void setPlayerYokimon(final Publisher<SpriteData> playerYokimonSpritePub) {
        //playerYokimonSpritePub.
    }

    @Override
    public void setEnemyYokimon(final Publisher<SpriteData> enemyYokimonSpritePub) {

    }
}
