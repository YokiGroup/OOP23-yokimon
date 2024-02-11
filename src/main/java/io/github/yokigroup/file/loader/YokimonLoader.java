package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.util.json.JsonParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YokimonLoader extends AbstractJsonLoader<Yokimon>{
    // path of yokimon json file relative to JsonParser.ROOT
    private static final String YOKIMON_JSON_PATH = "yokimons.json";
    private static final String YOKI_NAME_JPATHF = "$.%d.name";
    private static final String YOKI_COLOR_JPATHF = "$.%d.color";
    public static final String YOKI_STATS_JPATHF = "$.%d.stats.%s";
    public static final String YOKI_GROWTH_RATE_JPATHF = "$.%d.growthRate";
    public static final String YOKI_LMOVES_JPATHF = "$.%d.moves[*].level";

    public YokimonLoader() {
        super(YOKIMON_JSON_PATH);
    }

    private Map<Yokimon.Stats, Integer> getBaseStats(int id){
        Map<Yokimon.Stats, Integer> baseStats = new HashMap<>();
        JsonParser parser = getParser();
        for(var stat: Yokimon.Stats.values()){
            baseStats.put(stat, parser.read(String.format(YOKI_STATS_JPATHF, id, stat)));
        }
        return baseStats;
    }

    @Override
    public Yokimon load(int id) {
        JsonParser parser = getParser();
        final String name = parser.read(String.format(YOKI_NAME_JPATHF, id));
        final Color color = Color.valueOf(parser.read(String.format(YOKI_COLOR_JPATHF, id)));
        final Map<Yokimon.Stats, Integer> baseStats = getBaseStats(id);
        final Yokimon.GrowthRate growthRate = Yokimon.GrowthRate.valueOf(parser.read(String.format(YOKI_GROWTH_RATE_JPATHF, id)));
        List<Integer> learnableMovesTemp =  parser.read(String.format(YOKI_LMOVES_JPATHF, id));
        Map<Integer, Attack> learnableMoves = new HashMap<>();
        for (var moveLevel: learnableMovesTemp) {
            learnableMoves.put(moveLevel, YokimonDatabase.bite);
        }

        return new YokimonImpl(name, color, baseStats, growthRate, learnableMoves);
    }
}
