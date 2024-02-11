package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.PairImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
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
                if (Objects.equals(shape, Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT))) {
                    System.out.print("╋");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT))) {
                    System.out.print("┳");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT))) {
                    System.out.print("┻");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.LEFT))) {
                    System.out.print("┫");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT))) {
                    System.out.print("┣");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.UP, WfcShapeDirection.LEFT))) {
                    System.out.print("┛");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.UP, WfcShapeDirection.RIGHT))) {
                    System.out.print("┗");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.LEFT))) {
                    System.out.print("┓");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.DOWN, WfcShapeDirection.RIGHT))) {
                    System.out.print("┏");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.LEFT, WfcShapeDirection.RIGHT))) {
                    System.out.print("━");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.UP, WfcShapeDirection.DOWN))) {
                    System.out.print("┃");
                }
                /*
                else if (Objects.equals(shape, Set.of(WfcShapeDirection.UP))) {
                    System.out.print("╹");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.DOWN))) {
                    System.out.print("╻");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.LEFT))) {
                    System.out.print("╸");
                } else if (Objects.equals(shape, Set.of(WfcShapeDirection.RIGHT))) {
                    System.out.print("╺");
                }
                */
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
