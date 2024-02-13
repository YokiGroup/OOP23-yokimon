package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.dmgcalculator.FullImplDmgCalculator;
import io.github.yokigroup.battle.nextyokimon.NextYokimon;
import io.github.yokigroup.battle.nextyokimon.DummyImplNextYokimon;
import io.github.yokigroup.battle.opponentai.OpponentAI;
import io.github.yokigroup.battle.opponentai.DummyImplOpponentAI;
import io.github.yokigroup.battle.xpcalculator.FullImplXPCalculator;
import io.github.yokigroup.battle.xpcalculator.XPCalculator;

import java.util.*;

/**
 * The actual Fight implementation communicating with the Logic.
 */
public final class FightImpl implements Fight {

    private static final int TOPLIMIT_FAIL = 30;
    private static final int TOPLIMIT_WEAK = 70;
    private static final int TOPLIMIT_GOOD = 120;

    /* parties */
    private final List<Yokimon> myYokimons;
    private final List<Yokimon> oppYokimons;
    private Yokimon currMyYokimon;
    private Yokimon currOppYokimon;


    /* structures */
    private final XPCalculator xpCalc = new FullImplXPCalculator();
    private final DmgCalculator dmgCalc = new FullImplDmgCalculator();
    private final OpponentAI oppAI = new DummyImplOpponentAI();         //TODO: SUBSTITUTE NEW AND FULLER VERSIONS
    private final NextYokimon nextYok = new DummyImplNextYokimon();

    /* List to keep in store defeated Yokimons. */
    private final List<Yokimon> defeatedOpps = new LinkedList<>();
    /* Current state of the fight. */
    private State state;
    /* The Observers subscribed to this fight. */
    Set<FightObserver> observerSet = new HashSet<>();

    /**
     * Builder to instantiate the fight through the Logic.
     * @param myYokimons my party
     * @param oppYokimons the opponent party
     */
    public FightImpl(final List<Yokimon> myYokimons, final List<Yokimon> oppYokimons) {
        this.myYokimons = myYokimons;
        this.oppYokimons = oppYokimons;

        if (nextYok.getNext(myYokimons).isEmpty() || nextYok.getNext(oppYokimons).isEmpty()) {
            throw new RuntimeException("Must instantiate fight with at least one Yokimon on each party.");
        }
        this.currMyYokimon = nextYok.getNext(myYokimons).get();
        this.currOppYokimon = nextYok.getNext(oppYokimons).get();
        this.state = State.READY_TO_PROGRESS;
    }

    /*
    @Override
    public void progress(Attack myAttack) {
        if (state.equals(State.READY_TO_PROGRESS)) {
            attack(myAttack);
            if (state.equals(State.READY_TO_PROGRESS)) {
                getAttacked();
            }
        }
    }
     */

    @Override
    public Success attack(final Attack myAttack) {

        int damage = dmgCalc.getDMG(currMyYokimon, currOppYokimon, myAttack);
        currOppYokimon.takeDamage(damage);

        if (!currOppYokimon.active()) {
            oppYokimons.remove(currOppYokimon);
            defeatedOpps.add(currMyYokimon);

            final Optional<Yokimon> nextOppYok = nextYok.getNext(oppYokimons);
            if (nextOppYok.isPresent()) {
                currOppYokimon = nextOppYok.get();

            } else {
                int xpGain = xpCalc.getXP(defeatedOpps);
                currMyYokimon.takeXp(xpGain);
                state = State.WIN;
                notifyObservers();
                return Success.VICTORY;
            }
        }
        return successRate(damage);
    }

    @Override
    public Success getAttacked() {

        final Optional<Attack> nextOppAttack = oppAI.getMove(currMyYokimon, currOppYokimon);

        if (nextOppAttack.isEmpty()) {
            throw new RuntimeException("Yokimon doesn't have any available attack.");
        }
        int damage = dmgCalc.getDMG(currOppYokimon, currMyYokimon, nextOppAttack.get());
        currMyYokimon.takeDamage(damage);

        if (!currMyYokimon.active()) {
            myYokimons.remove(currMyYokimon);

            final Optional<Yokimon> nextMyYok = nextYok.getNext(myYokimons);

            if (nextMyYok.isPresent()) {
                currMyYokimon = nextMyYok.get();
                return successRate(damage);
            } else {
                state = State.LOSE;
                notifyObservers();
                return Success.LOSS;
            }
        }
        return successRate(damage);
    }

    private Success successRate(final int damage) {
       if (damage < TOPLIMIT_FAIL) {
           return Success.FAIL;
       }
       if (damage < TOPLIMIT_WEAK) {
           return Success.WEAK;
       }
       if (damage < TOPLIMIT_GOOD) {
           return Success.GOOD;
       }
       return Success.SUPER;
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

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void addObserver(FightObserver obs) {
        observerSet.add(obs);
    }

    @Override
    public void notifyObservers () {
        for (var obs : observerSet) {
            obs.update(this, state);
        }
    }

}
