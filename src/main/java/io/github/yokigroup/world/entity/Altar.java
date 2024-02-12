package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerPositionSubmodule;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.people.Player;

import java.util.Optional;

/**
 * Altar class represents an altar entity in the game world.
 * An altar gives a Yokimon to the player at the first interaction.
 */
public class Altar extends Entity {

    private Yokimon gift;
    private altarState state;
    /**
     * Constructs an Altar object with the specified attributes.
     * @param Pos The position of the Altar
     * @param Hitbox The hitbox of the Altar
     * @param yokimon The Yokimon to give at the first interaction
     */
    public Altar(Position Pos, Hitbox Hitbox, Yokimon yokimon, MessageHandler messageHandler) {
        super(Pos, Hitbox, messageHandler);
        this.gift = yokimon;
        this.state = altarState.powered;
    }

    /**
     * Returns the current state of the Altar (if it's used or not).
     * @return altarState Current state of the Altar
     */
    public Altar.altarState getState() {
        return this.state;
    }
    /**
     * Returns a Yokimon if the altar is powered, otherwise an optional empty.
     * @return Optional<Yokimon> The Yokimon from the Altar
     */
    public Yokimon getNewYokimon() {
            return this.gift;
    }

    /**
     * Updates the state of the Altar.
     */
    @Override
    public void update() {
        this.getMessageHandler().handle(PlayerPositionSubmodule.class, pos -> {
            //TODO pavo deve implementare la posizione del player
            if(pos.equals(null) && this.state == altarState.powered){
                this.getMessageHandler().handle(PartySubmodule.class, party ->{
                    party.addYokimon(this.getNewYokimon());
                    this.state = altarState.used;
                });

            }
        });
    }

    /**
     * Represents the state of the Altar (used or powered).
     */
    public enum altarState {
        used,
        powered
    }

}
