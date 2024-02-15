package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Objects;

/**
 * Altar class represents an altar entity in the game world.
 * An altar gives a Yokimon to the player at the first interaction.
 */
public class Altar extends Entity {
    private final Yokimon gift;
    private AltarState state;
    private static final double RADIUS = 4;

    /**
     * Constructs an Altar object with the specified attributes.
     * @param id id of the altar
     * @param pos The position of the Altar
     * @param hitBox The hitBox of the Altar
     * @param yokimon The Yokimon to give at the first interaction
     * @param messageHandler Message handler used to handle events
     */
    public Altar(final int id, final Position pos, final Hitbox hitBox,
                 final Yokimon yokimon, final MessageHandler messageHandler) {
        super(id, pos, hitBox, messageHandler);
        this.gift = new YokimonImpl(yokimon);
        this.state = AltarState.POWERED;
    }

    /**
     * Returns the current state of the Altar (if it's used or not).
     * @return altarState Current state of the Altar
     */
    public final AltarState getState() {
        return this.state;
    }
    /**
     * Returns a Yokimon if the altar is powered, otherwise an optional empty.
     * @return Optional<Yokimon> The Yokimon from the Altar
     */
    public final Yokimon getNewYokimon() {
            return new YokimonImpl(this.gift);
    }

    /**
     * Updates the state of the Altar.
     */
    @Override
    public void update() {
        this.getMessageHandler().handle(PlayerCharacterSubmodule.class, pos -> {
            Objects.requireNonNull(pos.getPosition().getPosition(), "Position of the player invalid");

            if (pos.getPosition().isValid()
                    && pos.getPosition().inRadius(this.getPos(), RADIUS)
                    && this.state == AltarState.POWERED) {

                    this.getMessageHandler().handle(PartySubmodule.class, party -> {
                    party.addYokimon(this.getNewYokimon());
                    this.state = AltarState.USED;

                });

            }
        });
    }

    /**
     * Represents the state of the Altar (used or powered).
     */
    public enum AltarState {
        /**
         * If the altar is in this state will no longer give a yokimon.
         */
        USED,
        /**
         * If the altar is in this state is active and will give a yokimon.
         */
        POWERED
    }

}
