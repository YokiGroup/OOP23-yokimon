package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.gen.TileDirections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WaveFunctionCollapseTest {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private WaveFunctionCollapse wfc;
    private Map<String, Set<TileDirections>> shpDic;

    @BeforeEach
    public void init() {
        shpDic = new HashMap<>();
        shpDic.put("UDLR", Set.of(TileDirections.UP, TileDirections.DOWN, TileDirections.LEFT, TileDirections.RIGHT));
        shpDic.put("DLR", Set.of(TileDirections.DOWN, TileDirections.LEFT, TileDirections.RIGHT));
        shpDic.put("ULR", Set.of(TileDirections.UP, TileDirections.LEFT, TileDirections.RIGHT));
        shpDic.put("UDL", Set.of(TileDirections.UP, TileDirections.DOWN, TileDirections.LEFT));
        shpDic.put("UDR", Set.of(TileDirections.UP, TileDirections.DOWN, TileDirections.RIGHT));
        shpDic.put("UL", Set.of(TileDirections.UP, TileDirections.LEFT));
        shpDic.put("UR", Set.of(TileDirections.UP, TileDirections.RIGHT));
        shpDic.put("DL", Set.of(TileDirections.DOWN, TileDirections.LEFT));
        shpDic.put("DR", Set.of(TileDirections.DOWN, TileDirections.RIGHT));
        shpDic.put("LR", Set.of(TileDirections.LEFT, TileDirections.RIGHT));
        shpDic.put("UD", Set.of(TileDirections.UP, TileDirections.DOWN));
        wfc = new WaveFunctionCollapseImpl(new Pair<>(WIDTH, HEIGHT), new HashSet<>(shpDic.values()));
    }

    @Test
    void testGetShapeAt() {
        wfc.generateShapeMap();
        // TODO: complete the test
    }

    @Test
    void setStaticShape() {
        for (int i = 1; i < WIDTH - 1; i++) {
            wfc.setStaticShape(new Pair<>(i, 0), Set.of(shpDic.get("ULR"), shpDic.get("UL"), shpDic.get("UR")));
            wfc.setStaticShape(new Pair<>(i, HEIGHT - 1), Set.of(shpDic.get("DLR"), shpDic.get("DL"), shpDic.get("DR")));
        }
        for (int j = 1; j < HEIGHT - 1; j++) {
            wfc.setStaticShape(new Pair<>(0, j), Set.of(shpDic.get("UDR"), shpDic.get("UR"), shpDic.get("DR")));
            wfc.setStaticShape(new Pair<>(WIDTH - 1, j), Set.of(shpDic.get("UDL"), shpDic.get("UL"), shpDic.get("DL")));
        }
        wfc.setStaticShape(new Pair<>(0, 0), Set.of(shpDic.get("UR")));
        wfc.setStaticShape(new Pair<>(WIDTH - 1, 0), Set.of(shpDic.get("UL")));
        wfc.setStaticShape(new Pair<>(0, HEIGHT - 1), Set.of(shpDic.get("DR")));
        wfc.setStaticShape(new Pair<>(WIDTH - 1, HEIGHT - 1), Set.of(shpDic.get("DL")));
        wfc.setStaticShape(new Pair<>(WIDTH / 2 - 1, HEIGHT / 2 - 1), Set.of(shpDic.get("UDLR")));
        wfc.generateShapeMap();
        // TODO: complete the test
    }
}
