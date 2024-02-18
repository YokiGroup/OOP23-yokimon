package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.RectangularHitbox;

import java.util.Objects;

/**
 * Altar class represents an altar entity in the game world.
 * An altar gives a Yokimon to the player at the first interaction.
 */
public class Altar extends Entity {
    private AltarState state;
    private static final double RADIUS = 200;
    private static final double HITBOX_SIDE = 125;
    private static final Vector2 dimensions = new Vector2Impl(HITBOX_SIDE*5/10, HITBOX_SIDE);
    /**
     * Constructs an Altar object with the specified attributes.
     * @param pos The position of the Altar
     * @param messageHandler Message handler used to handle events
     */
    public Altar(final Position pos, final MessageHandler messageHandler) {
        super(pos, new RectangularHitbox(pos.getPosition(), dimensions),
                messageHandler, dimensions.scale(1.05), "");
        this.state = AltarState.POWERED;
    }

    @Override
    protected String getResourceURL() {
        return "view/game/textures/altar_" + (state == AltarState.POWERED ? "full" : "empty") + ".png";
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
        //return new YokimonImpl(this.gift);
        // TODO Generate a pokemon
        return null;
    }

    /**
     * Updates the state of the Altar.
     */
    @Override
    public void update() {
        this.getMessageHandler().handle(PlayerCharacterSubmodule.class, pos -> {
            Objects.requireNonNull(pos.getPosition().getPosition(), "Position of the player invalid");

            if (this.state == AltarState.POWERED && pos.getPosition().isValid()
                    && pos.getPosition().inRadius(this.getPos(), RADIUS)) {

                    this.getMessageHandler().handle(PartySubmodule.class, party -> {
                    //party.addYokimon(this.getNewYokimon());
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
