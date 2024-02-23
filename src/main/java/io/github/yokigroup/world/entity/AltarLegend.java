package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;

/**
 * Altar which always return a sonGuWong Yokimon.
 */
public class AltarLegend extends Altar {
    /**
     * Constructs an Altar object with the specified attributes.
     *
     * @param pos            The position of the Altar
     * @param messageHandler Message handler used to handle events
     */
    public AltarLegend(final Position pos, final MessageHandler messageHandler) {
        super(pos, messageHandler);
    }

    /**
     * Return always a sonGuWong.
     */
    @Override
    protected Yokimon getNewYokimon() {
        final GenerationFactory generator = new GenerationFactoryImpl();
        return this.getMessageHandler().handle(GameMapSubmodule.class, map -> {
            return generator.getLegendAltar(map.getPlayerDistanceFromHome());
        });


    }
}
