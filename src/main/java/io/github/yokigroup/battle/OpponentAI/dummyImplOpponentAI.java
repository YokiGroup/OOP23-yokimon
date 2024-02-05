package io.github.yokigroup.battle.OpponentAI;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.DMGcalculator.DMGcalculator;
import io.github.yokigroup.battle.Yokimon;

import java.util.Optional;

public class dummyImplOpponentAI extends OpponentAI {

    private final DMGcalculator DMGCalc;

    public dummyImplOpponentAI(DMGcalculator dmgCalc) {
        DMGCalc = dmgCalc;
    }

    /**
     * @param curr_oppYokimon the Yokimon whose AI must be implemented
     * @return the first attack on the attacks list
     */
    @Override
    public Optional<Attack> getMove(Yokimon curr_oppYokimon) {
        if (curr_oppYokimon.getAttacks() == null || curr_oppYokimon.getAttacks().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(curr_oppYokimon.getAttacks().get(0));
    }
}
