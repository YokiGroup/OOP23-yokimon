package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.gen.TileDirections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        for (int i = 1; i < WIDTH - 1; i++) {
            final Pair<Integer, Integer> posTop = new Pair<>(i, 0);
            final Pair<Integer, Integer> posBottom = new Pair<>(i, HEIGHT - 1);
            wfc.setStaticShape(posTop, Set.of(shpDic.get("DLR"), shpDic.get("DL"), shpDic.get("DR")));
            wfc.setStaticShape(posBottom, Set.of(shpDic.get("ULR"), shpDic.get("UL"), shpDic.get("UR")));
        }
        for (int j = 1; j < HEIGHT - 1; j++) {
            final Pair<Integer, Integer> posLeft = new Pair<>(0, j);
            final Pair<Integer, Integer> posRight = new Pair<>(WIDTH - 1, j);
            wfc.setStaticShape(posLeft, Set.of(shpDic.get("UDR"), shpDic.get("UR"), shpDic.get("DR")));
            wfc.setStaticShape(posRight, Set.of(shpDic.get("UDL"), shpDic.get("UL"), shpDic.get("DL")));
        }
        final Pair<Integer, Integer> cornerDR = new Pair<>(0, 0);
        final Pair<Integer, Integer> cornerDL = new Pair<>(WIDTH - 1, 0);
        final Pair<Integer, Integer> cornerUR = new Pair<>(0, HEIGHT - 1);
        final Pair<Integer, Integer> cornerUL = new Pair<>(WIDTH - 1, HEIGHT - 1);
        final Pair<Integer, Integer> posCenter = new Pair<>(WIDTH / 2, HEIGHT / 2);
        wfc.setStaticShape(cornerDR, Set.of(shpDic.get("DR")));
        wfc.setStaticShape(cornerDL, Set.of(shpDic.get("DL")));
        wfc.setStaticShape(cornerUR, Set.of(shpDic.get("UR")));
        wfc.setStaticShape(cornerUL, Set.of(shpDic.get("UL")));
        wfc.setStaticShape(posCenter, Set.of(shpDic.get("UDLR")));
        wfc.generateShapeMap();
        // Check if the shapes are an actual shape
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                final Pair<Integer, Integer> pos = new Pair<>(i, j);
                assertTrue(shpDic.containsValue(wfc.getShapeAt(pos)));
            }
        }
        // Check if the static shapes are correct
        for (int i = 1; i < WIDTH - 1; i++) {
            final Pair<Integer, Integer> posTop = new Pair<>(i, 0);
            final Pair<Integer, Integer> posBottom = new Pair<>(i, HEIGHT - 1);
            assertTrue(Set.of(shpDic.get("DLR"), shpDic.get("DL"), shpDic.get("DR")).contains(wfc.getShapeAt(posTop)));
            assertTrue(Set.of(shpDic.get("ULR"), shpDic.get("UL"), shpDic.get("UR")).contains(wfc.getShapeAt(posBottom)));
        }
        for (int j = 1; j < HEIGHT - 1; j++) {
            final Pair<Integer, Integer> posLeft = new Pair<>(0, j);
            final Pair<Integer, Integer> posRight = new Pair<>(WIDTH - 1, j);
            assertTrue(Set.of(shpDic.get("UDR"), shpDic.get("UR"), shpDic.get("DR")).contains(wfc.getShapeAt(posLeft)));
            assertTrue(Set.of(shpDic.get("UDL"), shpDic.get("UL"), shpDic.get("DL")).contains(wfc.getShapeAt(posRight)));
        }
        assertEquals(shpDic.get("DL"), wfc.getShapeAt(cornerDL));
        assertEquals(shpDic.get("DR"), wfc.getShapeAt(cornerDR));
        assertEquals(shpDic.get("UL"), wfc.getShapeAt(cornerUL));
        assertEquals(shpDic.get("UR"), wfc.getShapeAt(cornerUR));
        assertEquals(shpDic.get("UDLR"), wfc.getShapeAt(posCenter));
    }
}
