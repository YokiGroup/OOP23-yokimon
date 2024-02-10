package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.PairImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WaveFunctionCollapseTest {

    private Set<Set<WfcShapeDirection>> allShapes;

    @BeforeEach
    public void init() {
        allShapes = new HashSet<>();
        allShapes.add(Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        allShapes.add(Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        allShapes.add(Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        allShapes.add(Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT));
        allShapes.add(Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT));
        allShapes.add(Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT));
        allShapes.add(Set.of(WfcShapeDirection.UP, WfcShapeDirection.RIGHT));
        allShapes.add(Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT));
        allShapes.add(Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT));
        allShapes.add(Set.of(WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT));
        allShapes.add(Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN));
    }

    @Test
    void getShapeAt() {
        final WaveFunctionCollapse wfc = new WaveFunctionCollapseImpl(new PairImpl<>(10, 10), allShapes);
        wfc.generateShapeMap();
        for (int j = 9; j >= 0; j--) {
            for (int i = 0; i < 10; i++) {
                Set<WfcShapeDirection> shape = wfc.getShapeAt(new PairImpl<>(i, j));
                assertTrue(allShapes.contains(shape));
                System.out.print("[");
                if (shape.contains(WfcShapeDirection.UP)) {
                    System.out.print("↑");
                } else {
                    System.out.print(" ");
                }
                if (shape.contains(WfcShapeDirection.DOWN)) {
                    System.out.print("↓");
                } else {
                    System.out.print(" ");
                }
                if (shape.contains(WfcShapeDirection.LEFT)) {
                    System.out.print("←");
                } else {
                    System.out.print(" ");
                }
                if (shape.contains(WfcShapeDirection.RIGHT)) {
                    System.out.print("→");
                } else {
                    System.out.print(" ");
                }
                System.out.print("]");
            }
            System.out.println(" ");
        }
    }

    @Test
    void setStaticShape() {

    }

    @Test
    void generateShapeMap() {

    }
}
