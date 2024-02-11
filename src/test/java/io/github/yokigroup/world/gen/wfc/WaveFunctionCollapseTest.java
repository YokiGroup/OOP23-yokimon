package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.PairImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

// import static org.junit.jupiter.api.Assertions.*;

class WaveFunctionCollapseTest {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private WaveFunctionCollapse wfc;
    private Map<String, Set<WfcShapeDirection>> shapeDict;

    @BeforeEach
    public void init() {
        shapeDict = new HashMap<>();
        shapeDict.put("UDLR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shapeDict.put("DLR", Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shapeDict.put("ULR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shapeDict.put("UDL", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT));
        shapeDict.put("UDR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT));
        shapeDict.put("UL", Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT));
        shapeDict.put("UR", Set.of(WfcShapeDirection.UP, WfcShapeDirection.RIGHT));
        shapeDict.put("DL", Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT));
        shapeDict.put("DR", Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT));
        shapeDict.put("LR", Set.of(WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        shapeDict.put("UD", Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN));
        wfc = new WaveFunctionCollapseImpl(new PairImpl<>(WIDTH, HEIGHT), new HashSet<>(shapeDict.values()));
    }

    private void printWfc() {
        for (int j = WIDTH - 1; j >= 0; j--) {
            for (int i = 0; i < HEIGHT; i++) {
                Set<WfcShapeDirection> shape = wfc.getShapeAt(new PairImpl<>(i, j));
                if (Objects.equals(shape, shapeDict.get("UDLR"))) {
                    System.out.print("━╋━");
                } else if (Objects.equals(shape, shapeDict.get("DLR"))) {
                    System.out.print("━┳━");
                } else if (Objects.equals(shape, shapeDict.get("ULR"))) {
                    System.out.print("━┻━");
                } else if (Objects.equals(shape, shapeDict.get("UDL"))) {
                    System.out.print("━┫ ");
                } else if (Objects.equals(shape, shapeDict.get("UDR"))) {
                    System.out.print(" ┣━");
                } else if (Objects.equals(shape, shapeDict.get("UL"))) {
                    System.out.print("━┛ ");
                } else if (Objects.equals(shape, shapeDict.get("UR"))) {
                    System.out.print(" ┗━");
                } else if (Objects.equals(shape, shapeDict.get("DL"))) {
                    System.out.print("━┓ ");
                } else if (Objects.equals(shape, shapeDict.get("DR"))) {
                    System.out.print(" ┏━");
                } else if (Objects.equals(shape, shapeDict.get("LR"))) {
                    System.out.print("━━━");
                } else if (Objects.equals(shape, shapeDict.get("UD"))) {
                    System.out.print(" ┃ ");
                }
            }
            System.out.println(" ");
        }
    }

    @Test
    void getShapeAt() {
        wfc.generateShapeMap();
        printWfc();
        // TODO: complete the test
    }

    @Test
    void setStaticShape() {
        for (int i = 1; i < WIDTH - 1; i++) {
            wfc.setStaticShape(new PairImpl<>(i, 0), Set.of(shapeDict.get("ULR"), shapeDict.get("UL"), shapeDict.get("UR")));
            wfc.setStaticShape(new PairImpl<>(i, HEIGHT - 1), Set.of(shapeDict.get("DLR"), shapeDict.get("DL"), shapeDict.get("DR")));
        }
        for (int j = 1; j < HEIGHT - 1; j++) {
            wfc.setStaticShape(new PairImpl<>(0, j), Set.of(shapeDict.get("UDR"), shapeDict.get("UR"), shapeDict.get("DR")));
            wfc.setStaticShape(new PairImpl<>(WIDTH - 1, j), Set.of(shapeDict.get("UDL"), shapeDict.get("UL"), shapeDict.get("DL")));
        }
        wfc.setStaticShape(new PairImpl<>(0, 0), Set.of(shapeDict.get("UR")));
        wfc.setStaticShape(new PairImpl<>(WIDTH - 1, 0), Set.of(shapeDict.get("UL")));
        wfc.setStaticShape(new PairImpl<>(0, HEIGHT - 1), Set.of(shapeDict.get("DR")));
        wfc.setStaticShape(new PairImpl<>(WIDTH - 1 , HEIGHT - 1), Set.of(shapeDict.get("DL")));
        wfc.setStaticShape(new PairImpl<>(WIDTH / 2 - 1, HEIGHT / 2 - 1), Set.of(shapeDict.get("UDLR")));
        wfc.generateShapeMap();
        printWfc();
        // TODO: complete the test
    }
}
