package io.github.yokigroup.battle.opponentai;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

import java.util.Optional;

/**
 * The AI responsible for choosing the next best move for the opponent to use.
 * Different criteria for the choice are applied for different implementations of this interface.
 */
public abstract class OpponentAI {

    /**
     * @param currOppYokimon the Yokimon whose AI must be implemented
     * @return the most suitable attack for the opponent to use
     */
    public abstract Optional<Attack> getMove(Yokimon currOppYokimon);
}
