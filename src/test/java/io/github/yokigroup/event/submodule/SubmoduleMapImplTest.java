package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SubmoduleMapImplTest {
    private SubmoduleMap map;
    private PartySubmodule pSub;
    private FightSubmodule fSub;
    private Set<Submodule> subModuleTestSet;

    @BeforeEach
    void setUp() {
        MessageHandler handler = new MessageHandler() {
            // handle implementation do not matter for this test.
            @Override
            public <T extends Submodule> void handle(final Class<T> subModuleType, final Consumer<T> handler) {
                return;
            }

            @Override
            public <T extends Submodule, E> E handle(Class<T> subModuleType, Function<T, E> handler) {
                return null;
            }
        };

        map = new SubmoduleMapImpl();
        pSub = new PartySubmodule(handler);
        fSub = new FightSubmodule(handler);
        subModuleTestSet = Set.of(pSub, fSub);
    }

    private void retrievalAsserts() {
        assertEquals(Optional.of(pSub), map.get(pSub.getClass()));
        assertEquals(Optional.of(fSub), map.get(fSub.getClass()));
        assertEquals(Optional.empty(), map.get(PlayerCharacterSubmodule.class));
    }

    @Test
    void registerAndGet() {
        assertTrue(map.register(pSub));
        assertTrue(map.register(fSub));
        assertFalse(map.register(fSub));
        retrievalAsserts();
    }

    @Test
    void addAll() {
        map.registerAll(subModuleTestSet);
        retrievalAsserts();
    }

    @Test
    void subModuleSet() {
        map.registerAll(subModuleTestSet);
        assertEquals(subModuleTestSet, map.subModuleSet());
    }
}
