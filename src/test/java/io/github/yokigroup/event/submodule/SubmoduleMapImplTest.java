package io.github.yokigroup.event.submodule;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.FightSubmodule;
import io.github.yokigroup.event.submodule.abs.PartySubmodule;
import io.github.yokigroup.event.submodule.abs.Submodule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

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
        MessageHandler handler = new GameMessageHandler();

        map = new SubmoduleMapImpl();
        pSub = new PartySubmoduleImpl(handler);
        fSub = new FightSubmoduleImpl(handler);
        subModuleTestSet = Set.of(pSub, fSub);
    }

    private void retrievalAsserts() {
        assertEquals(Optional.of(pSub), map.get(pSub.getClass()));
        assertEquals(Optional.of(fSub), map.get(fSub.getClass()));
        assertEquals(Optional.empty(), map.get(PlayerCharacterSubmoduleImpl.class));
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
