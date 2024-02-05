package io.github.yokigroup.battle.OpponentAI;


import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

import java.util.Optional;

public abstract class OpponentAI {
    /**
     *
     * @param curr_oppYokimon the Yokimon whose AI must be implemented
     * @return the most suitable attack for the opponent to use
     */
    public abstract Optional<Attack> getMove(Yokimon curr_oppYokimon);
}