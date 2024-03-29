package io.github.yokigroup.world.entity;


import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.WeightedPoolImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * This class is used for generate yokimon or list of them for
 * both giving a Yokimon to the player when it steps near to an Altar and
 * generate the foes party.
 */
public class GenerationFactoryImpl implements GenerationFactory {
    private static final YokimonLoader YOKIMON_LOADER;

    static {
        try {
            YOKIMON_LOADER = new YokimonLoader();
        } catch (IOException e) {
            throw new GameInitFailException(e);
        }
    }

    private static final Map<Integer, Yokimon> YOKIMONS = YOKIMON_LOADER.getAll();
    private static final int NUMBER_OF_YOKIMON = YOKIMONS.size();
    private static final int PROBABILITY_CHANCE_MAX = NUMBER_OF_YOKIMON + 2;
    private final WeightedPoolImpl<Integer> variableNum = new WeightedPoolImpl<>();
    private static final int MULTIPLIER_DIFFICULTY = 5;
    private static final int DIVISOR = 4;
    private static final int LEGEND_ID = 16;
    private static final int BOSS_LEVEL = 40;
    /**
     * Constructor of this class.
     */
    public GenerationFactoryImpl() {
        IntStream.range(1, MULTIPLIER_DIFFICULTY + 1).forEach(i -> variableNum.addElement(i, 1f));
    }

    private Yokimon getYokimon(final int power) {
        final int finalPower = Math.abs(power);
        final WeightedPoolImpl<Integer> yokimonID = new WeightedPoolImpl<>();

        IntStream.range(1, NUMBER_OF_YOKIMON)
                .forEach(i -> yokimonID.addElement(i, PROBABILITY_CHANCE_MAX - i + finalPower * 2));

        final Yokimon gift = YOKIMONS.get(yokimonID.getRandomizedElement());
        gift.setLevel(power * MULTIPLIER_DIFFICULTY + variableNum.getRandomizedElement());
        return Objects.requireNonNull(gift);
    }

    @Override
    public final Yokimon getYokimonAltar(final int power) {
        return Objects.requireNonNull(this.getYokimon(power != 0 ? power : 1));
    }

    @Override
    public final List<Yokimon> getEnemyParty(final int power) {
        final List<Yokimon> enemyParty = new ArrayList<>();
        IntStream.rangeClosed(0, power / DIVISOR)
                .forEach(i -> enemyParty.add(getYokimon(power - 1)));
        return Objects.requireNonNull(List.copyOf(enemyParty));
    }

    /**
     * Return the yokimon with Legend ID.
     *
     * @param level level
     * @return Yokimon legend
     */
    private Yokimon getLegend(final int level) {
        final Yokimon gift = YOKIMONS.get(LEGEND_ID);
        Objects.requireNonNull(gift, "Yokimon loaded was null");
        gift.setLevel(level);
        return Objects.requireNonNull(gift);
    }

    @Override
    public final Yokimon getLegendAltar(final int power) {
        return getLegend(power * MULTIPLIER_DIFFICULTY + variableNum.getRandomizedElement());
    }

    @Override
    public final List<Yokimon> getBossParty() {
        final List<Yokimon> enemyParty = new ArrayList<>();
        IntStream.range(0, 3)
                .forEach(i -> enemyParty.add(getYokimon(BOSS_LEVEL - 3)));
        enemyParty.add(getLegend(BOSS_LEVEL));
        return Objects.requireNonNull(List.copyOf(enemyParty));
    }
}


