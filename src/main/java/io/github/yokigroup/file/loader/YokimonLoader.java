package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.attack.Color;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.yokimon.YokimonImpl;
import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.util.json.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Instances yokimons loading them from the json file describing them.
 */
public final class YokimonLoader extends IdJsonLoader<Yokimon> {
    // path of yokimon json file relative to JsonParser.ROOT
    private static final String YOKIMON_JSON_PATH = "yokimons.json";
    private static final String YOKI_NAME_JPATHF = "$.%d.name";
    private static final String YOKI_COLOR_JPATHF = "$.%d.color";
    private static final String YOKI_STATS_JPATHF = "$.%d.stats.%s";
    private static final String YOKI_GROWTH_RATE_JPATHF = "$.%d.growthRate";
    private static final String YOKI_LMOVES_JPATHF = "$.%d.moves[*]";

    /**
     * Class constructor.
     * @throws
     */
    public YokimonLoader() throws IOException {
        super(YOKIMON_JSON_PATH);
    }

    private Map<Yokimon.Stats, Integer> getBaseStats(final int id) {
        final Map<Yokimon.Stats, Integer> baseStats = new HashMap<>();
        final JsonParser parser = getParser();
        for (final var stat: Yokimon.Stats.values()) {
            baseStats.put(stat, parser.read(String.format(YOKI_STATS_JPATHF, id, stat)));
        }
        return baseStats;
    }

    @Override
    public Yokimon load(final int id) {
        final AttackLoader attackLoader;
        try {
            attackLoader = new AttackLoader();
        } catch (IOException e) {
            throw new GameInitFailException(e);
        }
        final JsonParser parser = getParser();

        final String name = parser.read(String.format(YOKI_NAME_JPATHF, id));
        final Color color = Color.valueOf(parser.read(String.format(YOKI_COLOR_JPATHF, id)));
        final Map<Yokimon.Stats, Integer> baseStats = getBaseStats(id);
        final Yokimon.GrowthRate growthRate = Yokimon.GrowthRate.valueOf(parser.read(String.format(YOKI_GROWTH_RATE_JPATHF, id)));
        final List<Map<String, Integer>> learnableMovesTemp =  parser.read(String.format(YOKI_LMOVES_JPATHF, id));
        final Map<Integer, Attack> learnableMoves = new HashMap<>();
        learnableMovesTemp.forEach(elem -> {
            learnableMoves.put(
                    elem.get("level"),
                    attackLoader.load(elem.get("skillId"))
            );
        });

        return new YokimonImpl(id, name, color, baseStats, growthRate, learnableMoves);
    }
}
