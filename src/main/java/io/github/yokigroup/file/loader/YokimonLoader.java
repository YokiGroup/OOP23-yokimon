package io.github.yokigroup.file.loader;

import io.github.yokigroup.battle.*;
import io.github.yokigroup.util.json.JsonParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YokimonLoader extends AbstractJsonLoader<Yokimon>{
    // path of yokimon json file relative to JsonParser.ROOT
    private static final String YOKIMONJSONPATH = "yokimons.json";
    private static final String YOKINAMEJPATHF = "$.%d.name";
    private static final String YOKICOLORJPATHF = "$.%d.color";
    public static final String YOKISTATSJPATHF = "$.%d.stats.%s";
    public static final String YOKIGROWTHRATEJPATHF = "$.%d.growthRate";
    public static final String YOKILMOVESJPATHF = "$.%d.moves[*].level";

    public YokimonLoader() {
        super(YOKIMONJSONPATH);
    }

    private Map<Yokimon.Stats, Integer> getBaseStats(int id){
        Map<Yokimon.Stats, Integer> baseStats = new HashMap<>();
        JsonParser parser = getParser();
        for(var stat: Yokimon.Stats.values()){
            baseStats.put(stat, parser.read(String.format(YOKISTATSJPATHF, id, stat)));
        }
        return baseStats;
    }

    @Override
    public Yokimon load(int id) {
        JsonParser parser = getParser();
        final String name = parser.read(String.format(YOKINAMEJPATHF, id));
        final Color color = Color.valueOf(parser.read(String.format(YOKICOLORJPATHF, id)));
        final Map<Yokimon.Stats, Integer> baseStats = getBaseStats(id);
        final Yokimon.GrowthRate growthRate = Yokimon.GrowthRate.valueOf(parser.read(String.format(YOKIGROWTHRATEJPATHF, id)));
        List<Integer> learnableMovesTemp =  parser.read(String.format(YOKILMOVESJPATHF, id));
        Map<Integer, Attack> learnableMoves = new HashMap<>();
        for (var moveLevel: learnableMovesTemp) {
            learnableMoves.put(moveLevel, YokimonDatabase.bite);
        }

        return new YokimonImpl(name, color, baseStats, growthRate, learnableMoves);
    }
}
