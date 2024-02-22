package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AltarTest {

    @Test
    void altarTest() {
        final MessageHandler testMeg = new AbsTestMessageHandler();
        testMeg.handle(AbsTestMessageHandler.TestSubmodule.class, map -> {
            for (final Entity entity : map.getEntitiesOnCurrentTile()) {

                if (entity instanceof Altar altar) {
                    testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        assertEquals(Altar.AltarState.POWERED, altar.getState());
                        altar.update();
                        assertEquals(Altar.AltarState.USED, altar.getState());
                        testMeg.handle(PartySubmodule.class, party -> {
                            assertEquals(1, party.listYokimons().size());
                        });
                    });
                }
            }
        });

    }

}
