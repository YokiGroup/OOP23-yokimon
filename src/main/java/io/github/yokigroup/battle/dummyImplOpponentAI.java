package io.github.yokigroup.battle;

import java.util.Optional;

public class dummyImplOpponentAI extends OpponentAI {

    /**
     * @param curr_oppYokimon the Yokimon whose AI must be implemented
     * @return the first attack on the attacks list
     */
    @Override
    public Optional<Attack> getMove(Yokimon curr_oppYokimon) {
        return Optional.of(curr_oppYokimon.getAttacks().get(0));
    }
}
