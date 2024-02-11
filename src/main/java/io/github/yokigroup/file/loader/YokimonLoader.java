package io.github.yokigroup.file.loader;

import com.jayway.jsonpath.PathNotFoundException;
import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Color;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.util.json.JsonParser;
import io.github.yokigroup.util.json.JsonParserImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class YokimonLoader extends AbstractJsonLoader<Yokimon>{
    // path of yokimon json file relative to JsonParser.ROOT
    private static final String RELYOKIMONPATH = "yokimons.json";

    public YokimonLoader() {
        super(JsonParser.ROOT+"/"+RELYOKIMONPATH);
    }

    private Map<Yokimon.Stats, Integer> getBaseStats(int id){
        Map<Yokimon.Stats, Integer> baseStats = new HashMap<>();
        JsonParser parser = getParser();
        for(var stat: Yokimon.Stats.values()){
            baseStats.put(stat, parser.read(String.format("$.%d.stats.%s", id, stat)));
        }
        return baseStats;
    }

    @Override
    public Yokimon load(int id) {
        JsonParser parser = getParser();
        final String name = parser.read(String.format("$.%d.name", id));
        final Color color = Color.valueOf(parser.read(String.format("$.%d.color", id)));
        final Map<Yokimon.Stats, Integer> baseStats = getBaseStats(id);
        final Yokimon.GrowthRate growthRate = Yokimon.GrowthRate.valueOf(parser.read(String.format("$.%d.growthRate", id)));
        List<Integer> learnableMovesTemp =  parser.read(String.format("$.%d.moves[*].level", id));
        Map<Integer, Attack> learnableMoves = new HashMap<>();
        for (var moveLevel: learnableMovesTemp) {
            learnableMoves.put(moveLevel, null);
        }

        return new YokimonImpl(name, color, baseStats, growthRate, learnableMoves);
    }
}
