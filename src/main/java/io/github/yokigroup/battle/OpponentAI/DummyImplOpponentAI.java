package io.github.yokigroup.battle.OpponentAI;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.DmgCalculator.DmgCalculator;
import io.github.yokigroup.battle.Yokimon;

import java.util.Optional;

/**
 * Dummy version of the AI, where the opponent always chooses the first attack on the attacks list.
 */
public class DummyImplOpponentAI extends OpponentAI {

    private final DmgCalculator dmcCalc;

    /**
     * Instantiate the damage calculator from which to evaluate the best attack to use.
     * @param dmgCalc
     */
    public DummyImplOpponentAI(final DmgCalculator dmgCalc) {
        dmcCalc = dmgCalc;
    }

    /**
     * @param currOppYokimon the Yokimon whose AI must be implemented
     * @return the first attack on the attacks list
     */
    @Override
    public Optional<Attack> getMove(final Yokimon currOppYokimon) {
        if (currOppYokimon.getAttacks() == null || currOppYokimon.getAttacks().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(currOppYokimon.getAttacks().get(0));
    }
}
