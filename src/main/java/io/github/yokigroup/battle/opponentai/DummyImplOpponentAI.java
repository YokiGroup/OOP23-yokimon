package io.github.yokigroup.battle.opponentai;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;

import java.util.Optional;

/**
 * Dummy version of {@link OpponentAI}, where the opponent always chooses the first {@link Attack} on the attacks list.
 */
public class DummyImplOpponentAI extends OpponentAI {

    /**
     * @param currMyYokimon Yokimon from player's party currently involved in the fight
     * @param currOppYokimon the Yokimon whose AI must be implemented
     * @return the first attack on the attacks list
     */
    @Override
    public Optional<Attack> getMove(final Yokimon currMyYokimon, final Yokimon currOppYokimon) {
        if (currOppYokimon.getAttacks() == null || currOppYokimon.getAttacks().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(currOppYokimon.getAttacks().get(0));
    }
}
