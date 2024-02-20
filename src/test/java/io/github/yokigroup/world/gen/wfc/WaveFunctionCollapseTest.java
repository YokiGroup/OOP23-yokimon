package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WaveFunctionCollapseTest {
    private static final int WIDTH = 15;
    private static final int HEIGHT = 15;
    private WaveFunctionCollapse wfc;
    private Map<String, Set<Direction>> shpDic;

    @BeforeEach
    public void init() {
        shpDic = new HashMap<>();
        shpDic.put("UDLR", Set.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        shpDic.put("DLR", Set.of(Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        shpDic.put("ULR", Set.of(Direction.UP, Direction.LEFT, Direction.RIGHT));
        shpDic.put("UDL", Set.of(Direction.UP, Direction.DOWN, Direction.LEFT));
        shpDic.put("UDR", Set.of(Direction.UP, Direction.DOWN, Direction.RIGHT));
        shpDic.put("UL", Set.of(Direction.UP, Direction.LEFT));
        shpDic.put("UR", Set.of(Direction.UP, Direction.RIGHT));
        shpDic.put("DL", Set.of(Direction.DOWN, Direction.LEFT));
        shpDic.put("DR", Set.of(Direction.DOWN, Direction.RIGHT));
        shpDic.put("LR", Set.of(Direction.LEFT, Direction.RIGHT));
        wfc = new WaveFunctionCollapseImpl(new Pair<>(WIDTH, HEIGHT), new HashSet<>(shpDic.values()));
    }

    @Test
    void testGetShapeAt() {
        wfc.generateShapeMap();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                assertTrue(shpDic.containsValue(wfc.getShapeAt(pos)));
            }
        }
    }

    @Test
    void setStaticShape() {
        final Pair<Integer, Integer> posCenter = new Pair<>(WIDTH / 2, HEIGHT / 2);
        wfc.setStaticShape(posCenter, Set.of(shpDic.get("UDLR")));
        wfc.generateShapeMap();
        // Check if the shapes are an actual shape
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                assertTrue(shpDic.containsValue(wfc.getShapeAt(pos)));
            }
        }
        // Check if the static shape is correct
        assertEquals(shpDic.get("UDLR"), wfc.getShapeAt(posCenter));
    }
}
