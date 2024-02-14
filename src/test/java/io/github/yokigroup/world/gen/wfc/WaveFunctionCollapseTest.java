package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
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
    private Map<String, Set<WfcShapeDirection>> shpDic;

    @BeforeEach
    public void init() {
        shpDic = new HashMap<>();
        shpDic.put("UDLR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shpDic.put("DLR", Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shpDic.put("ULR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shpDic.put("UDL", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT));
        shpDic.put("UDR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT));
        shpDic.put("UL", Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT));
        shpDic.put("UR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.RIGHT));
        shpDic.put("DL", Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT));
        shpDic.put("DR", Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT));
        shpDic.put("LR", Set.of(WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shpDic.put("UD", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN));
        wfc = new WaveFunctionCollapseImpl(new Pair<>(WIDTH, HEIGHT), new HashSet<>(shpDic.values()));
    }

<<<<<<< HEAD
    private void printWfc() {
        for (int j = WIDTH - 1; j >= 0; j--) {
            for (int i = 0; i < HEIGHT; i++) {
                Set<WfcShapeDirection> shape = wfc.getShapeAt(new Pair<>(i, j));

            }
            System.out.println(" ");
        }
    }

=======
>>>>>>> master
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
