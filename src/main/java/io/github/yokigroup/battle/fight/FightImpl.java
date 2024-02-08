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

public class FightImpl implements Fight {

    /* parties */
    private final List<Yokimon> myYokimons;
    private final List<Yokimon> oppYokimons;
    private Yokimon currMyYokimon;
    private Yokimon currOppYokimon;


    /* structures */
    private final XPCalculator XPCalc = new dummyImplXPCalculator();
    private final DMGCalculator DMGCalc = new basicImplDMGCalculator();
    private final OpponentAI oppAI = new dummyImplOpponentAI(DMGCalc);
    private final NextYokimon nextYok = new dummyImplNextYokimon();



    /* boolean that triggers end of fight */
    private boolean isOver;

    /* to keep in store list of defeated Yokimons */
    private final List<Yokimon> defeatedOpps = new LinkedList<>();



    public FightImpl(List<Yokimon> myYokimons, List<Yokimon> oppYokimons){
        this.myYokimons = myYokimons;
        this.oppYokimons = oppYokimons;
    }

    @Override
    public success attack(Attack myAttack) {                                            ////missing
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
    public int getXP(Yokimon yokimon) {
        return XPCalc.getXP(defeatedOpps);
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
    private void update_myCurr() {
        nextYok.getNext(myYokimons);
    };
    private void update_oppCurr() {
        nextYok.getNext(oppYokimons);
    };
}
