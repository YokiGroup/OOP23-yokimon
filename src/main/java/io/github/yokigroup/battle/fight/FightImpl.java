package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.DMGcalculator.DMGCalculator;
import io.github.yokigroup.battle.DMGcalculator.basicImplDMGCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.NextYokimon.NextYokimon;
import io.github.yokigroup.battle.NextYokimon.dummyImplNextYokimon;
import io.github.yokigroup.battle.OpponentAI.OpponentAI;
import io.github.yokigroup.battle.OpponentAI.dummyImplOpponentAI;
import io.github.yokigroup.battle.XPCalculator.XPCalculator;
import io.github.yokigroup.battle.XPCalculator.dummyImplXPCalculator;

import java.util.LinkedList;
import java.util.List;

/**
 * The actual Fight implementation communicating with the Logic.
 */
public final class FightImpl implements Fight {

    /* parties */
    private final List<Yokimon> myYokimons;
    private final List<Yokimon> oppYokimons;
    private Yokimon currMyYokimon;
    private Yokimon currOppYokimon;


    /* structures */
    private final XPCalculator xpCalc = new dummyImplXPCalculator();
    private final DMGCalculator dmgCalc = new basicImplDMGCalculator();
    private final OpponentAI oppAI = new dummyImplOpponentAI(dmgCalc);
    private final NextYokimon nextYok = new dummyImplNextYokimon();


    /**
     * Boolean that triggers end of the fight.
     */
    private boolean isOver;

    /**
     * List to keep in store defeated Yokimons.
     */
    private final List<Yokimon> defeatedOpps = new LinkedList<>();


    /**
     * Builder to instantiate the fight through the Logic
     * @param myYokimons my party
     * @param oppYokimons the opponent party
     */
    public FightImpl(final List<Yokimon> myYokimons, final List<Yokimon> oppYokimons) {
        this.myYokimons = myYokimons;
        this.oppYokimons = oppYokimons;
    }

    @Override
    public success attack(final Attack myAttack) {                                      ////missing
        return null;
    }

    @Override
    public success getAttacked() {                                                      ////missing
        return null;
    }

    @Override
    public boolean isOver() {
        return myYokimons.isEmpty() || oppYokimons.isEmpty();
    }

    @Override
    public boolean victory() {
        return oppYokimons.isEmpty();
    }

    @Override
    public int getXP(final Yokimon yokimon) {
        return xpCalc.getXP(defeatedOpps);
    }

    @Override
    public Yokimon getCurrentMyYokimon() {
        return currMyYokimon;
    }

    @Override
    public Yokimon getCurrentOpponent() {
        return currOppYokimon;
    }

    /* utilities to update Yokimons involved in fight */
    private void updateMyCurr() {
        nextYok.getNext(myYokimons);
    };
    private void updateOppCurr() {
        nextYok.getNext(oppYokimons);
    };
}
