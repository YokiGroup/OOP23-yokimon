package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;

public class AltarLegend extends Altar{
    /**
     * Constructs an Altar object with the specified attributes.
     *
     * @param pos            The position of the Altar
     * @param messageHandler Message handler used to handle events
     */
    public AltarLegend(Position pos, MessageHandler messageHandler) {
        super(pos, messageHandler);
    }

    @Override
    public Yokimon getNewYokimon() {
        final GenerationFactory generator = new GenerationFactoryImpl();
        return this.getMessageHandler().handle(GameMapSubmodule.class, map -> {
            return generator.getLegendAltar(map.getPlayerDistanceFromHome());
        });


    }
}
