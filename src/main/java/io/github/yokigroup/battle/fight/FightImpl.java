package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.NextYokimon.NextYokimon;
import io.github.yokigroup.battle.NextYokimon.dummyImplNextYokimon;
import io.github.yokigroup.battle.OpponentAI.OpponentAI;
import io.github.yokigroup.battle.OpponentAI.dummyImplOpponentAI;
import io.github.yokigroup.battle.XPCalculator.XPCalculator;
import io.github.yokigroup.battle.XPCalculator.dummyImplXPCalculator;

import java.util.List;

public class FightImpl implements Fight {

    /* parties */
    private final List<Yokimon> myYokimons;
    private final List<Yokimon> oppYokimons;
    private Yokimon currMyYokimon;
    private Yokimon currOppYokimon;

    /* structures */
    private final OpponentAI oppAI = new dummyImplOpponentAI();
    private final XPCalculator XPCalc = new dummyImplXPCalculator();
    private final NextYokimon nextYok = new dummyImplNextYokimon();

    /* boolean that triggers end of fight */
    private boolean isOver;

    public FightImpl(List<Yokimon> myYokimons, List<Yokimon> oppYokimons){
        this.myYokimons = myYokimons;
        this.oppYokimons = oppYokimons;
    }

    @Override
    public success attack(Attack myAttack) {
        return null;
    }

    @Override
    public success getAttacked() {
        return null;
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public boolean victory() {
        return false;
    }

    @Override
    public int getXP(Yokimon yokimon) {
        return 0;
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
    private void update_myCurr() {};
    private void update_oppCurr() {};
}
