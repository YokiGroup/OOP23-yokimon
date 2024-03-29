package io.github.yokigroup.battle.opponentai;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.fight.Fight;

import java.util.Optional;

/**
 * Used as a {@link Fight} component.
 * The AI responsible for choosing the next best {@link Attack} for the opponent to use.
 * Different criteria for the choice are applied for different implementations of this interface.
 */
public abstract class OpponentAI {

    /**
     * @param currMyYokimon Yokimon from player's party currently involved in the fight
     * @param currOppYokimon the Yokimon whose AI must be implemented
     * @return the most suitable {@link Attack} for the opponent to use
     */
    public abstract Optional<Attack> getMove(Yokimon currMyYokimon, Yokimon currOppYokimon);
}
