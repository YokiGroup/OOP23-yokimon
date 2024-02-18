package io.github.yokigroup.event.submodule;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.FightSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.PartySubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.view.observer.ModelObserverImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SubmoduleMapImplTest {
    private SubmoduleMap map;
    private PartySubmoduleAbs pSub;
    private FightSubmoduleAbs fSub;
    private Set<Submodule> subModuleTestSet;

    @BeforeEach
    void setUp() {
        MessageHandler handler = new GameMessageHandler();
        ModelObserver obs = new ModelObserverImpl(null);

        map = new SubmoduleMapImpl();
        // FIXME init these submodules
        pSub = new PartySubmodule(handler, obs);
        fSub = new FightSubmodule(handler, obs);
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
