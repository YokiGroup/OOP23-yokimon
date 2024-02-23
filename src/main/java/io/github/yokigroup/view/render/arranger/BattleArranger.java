package io.github.yokigroup.view.render.arranger;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.observer.Publisher;

public interface BattleArranger {
    void setPlayerYokimon(Publisher<SpriteData> playerYokimonSpritePub);
    void setEnemyYokimon(Publisher<SpriteData> enemyYokimonSpritePub);
}
