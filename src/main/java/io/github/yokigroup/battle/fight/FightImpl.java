package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.yokimon.YokimonImpl;
import io.github.yokigroup.battle.dmgcalculator.DmgCalculator;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.dmgcalculator.FullImplDmgCalculator;
import io.github.yokigroup.battle.opponentai.FullImplOpponentAI;
import io.github.yokigroup.battle.opponentai.OpponentAI;
import io.github.yokigroup.battle.xpcalculator.FullImplXPCalculator;
import io.github.yokigroup.battle.xpcalculator.XPCalculator;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;

import java.util.List;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The actual {@link Fight} implementation communicating with the Logic.
 *
 * @see FightSubmodule
 */
public final class FightImpl implements Fight {
    static final float FAIL_RATE = 0.05f;
    static final float WEAK_RATE = 0.2f;
    static final float GOOD_RATE = 0.7f;
    static final float SUPER_RATE = 0.2f;
    /* attack success pool */
    private static final WeightedPool<Success> SUCCESS_WEIGHTED_POOL = new WeightedPoolImpl<>();

    static {
        SUCCESS_WEIGHTED_POOL.addElement(Success.FAIL, FAIL_RATE);
        SUCCESS_WEIGHTED_POOL.addElement(Success.WEAK, WEAK_RATE);
        SUCCESS_WEIGHTED_POOL.addElement(Success.GOOD, GOOD_RATE);
        SUCCESS_WEIGHTED_POOL.addElement(Success.SUPER, SUPER_RATE);
    }

    /* parties */
    private final List<Yokimon> myYokimons;
    private final List<Yokimon> oppYokimons;
    private Yokimon currMyYokimon;
    private Yokimon currOppYokimon;
    private Attack selectedAttack = null;


    /* structures */
    private final XPCalculator xpCalc = new FullImplXPCalculator();
    private final DmgCalculator dmgCalc = new FullImplDmgCalculator();
    private final OpponentAI oppAI = new FullImplOpponentAI(dmgCalc);

    /* List to keep in store defeated Yokimons. */
    private final List<Yokimon> defeatedOpps = new LinkedList<>();

    /* Current state of the fight and event publisher */
    private State state;
    private final Publisher<Fight> publisher = new PublisherImpl<>();

    /**
     * Builder to instantiate the fight through the Logic.
     *
     * @param myYokimons  my party
     * @param oppYokimons the opponent party
     * @throws UnsupportedOperationException in case one of the party is empty.
     */
    public FightImpl(final List<Yokimon> myYokimons, final List<Yokimon> oppYokimons) {
        if (myYokimons.isEmpty() || oppYokimons.isEmpty()) {
            throw new UnsupportedOperationException("Must instantiate fight with at least one Yokimon on each party.");
        }
        this.myYokimons = myYokimons.stream().map(YokimonImpl::new).collect(Collectors.toList());
        this.oppYokimons = oppYokimons.stream().map(YokimonImpl::new).collect(Collectors.toList());

        this.currMyYokimon = myYokimons.get(0);
        this.currOppYokimon = oppYokimons.get(0);
        this.state = State.READY_TO_PROGRESS;
        this.selectAttack(currMyYokimon.getAttacks().get(0));
    }

    private int addDamageModifiers(final Success attackSuccessValue, final int damage) {
        return switch (attackSuccessValue) {
            case FAIL -> 0;
            case WEAK -> damage / 2;
            case SUPER -> damage * 2;
            default -> damage;
        };
    }

    @Override
    public void selectAttack(final Attack attack) throws IllegalArgumentException {
        Objects.requireNonNull(attack);
        if (getCurrentMyYokimon().getAttacks().contains(attack)) {
            selectedAttack = Objects.requireNonNull(attack);
        } else {
            throw new IllegalArgumentException("Attack given is not possessed by yokimon "
                    + getCurrentMyYokimon().getName());
        }
    }

    @Override
    public Attack getSelectedAttack() {
        return selectedAttack;
    }

    @Override
    public Success attack() {
        final Success attackSuccessValue = SUCCESS_WEIGHTED_POOL.getRandomizedElement();
        final int damage = addDamageModifiers(attackSuccessValue,
                dmgCalc.getDMG(currMyYokimon, currOppYokimon, selectedAttack));
        if (currOppYokimon.takeDamage(damage)) {
            oppYokimons.remove(0);
            defeatedOpps.add(currMyYokimon);
            if (oppYokimons.isEmpty()) {
                final int xpGain = xpCalc.getXP(defeatedOpps);
                currMyYokimon.takeXp(xpGain);
                state = State.WIN;
                publisher.notifyObservers(this);
            } else {
                currOppYokimon = oppYokimons.get(0);
            }
        }
        return attackSuccessValue;
    }

    @Override
    public Success getAttacked() {
        final Optional<Attack> nextOppAttack = oppAI.getMove(currMyYokimon, currOppYokimon);
        if (nextOppAttack.isEmpty()) {
            throw new UnsupportedOperationException("Yokimon doesn't have any available attack.");
        }
        final Success attackSuccessValue = SUCCESS_WEIGHTED_POOL.getRandomizedElement();
        final int damage = addDamageModifiers(attackSuccessValue,
                dmgCalc.getDMG(currOppYokimon, currMyYokimon, nextOppAttack.get()));
        currMyYokimon.takeDamage(damage);
        if (!currMyYokimon.active()) {
            myYokimons.remove(0);
            if (myYokimons.isEmpty()) {
                state = State.LOSE;
                publisher.notifyObservers(this);
            } else {
                this.currMyYokimon = myYokimons.get(0);
            }
        }
        return attackSuccessValue;
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
        return new YokimonImpl(currMyYokimon);
    }

    @Override
    public Yokimon getCurrentOpponent() {
        return new YokimonImpl(currOppYokimon);
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public double getHPPercentage(final Yokimon yokimon) {
        return (double) yokimon.getActualHp() / yokimon.getMaxHp();
    }

    @Override
    public boolean playerIsFirst() {
        return currMyYokimon.getStat(Yokimon.Stats.SPD) >= currOppYokimon.getStat(Yokimon.Stats.SPD);
    }

}
