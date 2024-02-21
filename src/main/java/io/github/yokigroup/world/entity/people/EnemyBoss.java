package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.world.entity.GenerationFactory;
import io.github.yokigroup.world.entity.GenerationFactoryImpl;
import io.github.yokigroup.world.entity.Position;

/**
 * Enemy boss extension.
 */
public class EnemyBoss extends Enemy{
    /**
     * Constructs an Enemy object with the specified attributes.
     *
     * @param pos            The position of the Enemy
     * @param messageHandler handle for events
     */
    public EnemyBoss(Position pos, MessageHandler messageHandler) {
        super(pos, messageHandler);
    }
    /**
     * Add a special encounter for this enemy.
     */
    @Override
    protected void encounter() {
            this.getMessageHandler().handle(FightSubmodule.class, fight -> {
                GenerationFactory generator = new GenerationFactoryImpl();
                fight.addEncounter(generator.getBossParty());
            });
    }
}
