package io.github.yokigroup.world.entity;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.people.Player;

import java.util.Optional;

/**
 * Altar class represents an altar entity in the game world.
 * An altar gives a Yokimon to the player at the first interaction.
 */
public class Altar extends Entity {

    private Optional<Yokimon> gift;
    private altarState state;

    /**
     * Constructs an Altar object with the specified attributes.
     * @param name The name of the Altar
     * @param Pos The position of the Altar
     * @param Hitbox The hitbox of the Altar
     * @param yokimon The Yokimon to give at the first interaction
     */
    public Altar(String name, Position Pos, Hitbox Hitbox, Optional<Yokimon> yokimon) {
        super(name, Pos, Hitbox);
        this.gift = yokimon;
        this.state = altarState.powered;
    }

    /**
     * Updates the state of the Altar from powered to used, giving the player the Yokimon inside.
     * @param you The player object
     * @return message Status message
     */
    public message update(Player you) {
        if (this.state == altarState.powered && this.getNewYokimon().isPresent()) {
            this.state = altarState.used;
            you.addYokimon(this.getNewYokimon().get());
            return message.ok;
        } else
            return message.used;
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
    public Optional<Yokimon> getNewYokimon() {
        if (this.state == altarState.powered) {
            return this.gift;
        } else
            return Optional.empty();
    }

    /**
     * Updates the state of the Altar.
     * @return message Status message
     */
    @Override
    public message update() {
        return null;
    }

    /**
     * Represents the state of the Altar (used or powered).
     */
    public enum altarState {
        used,
        powered
    }

}
