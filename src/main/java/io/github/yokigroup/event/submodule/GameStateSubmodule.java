package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.GameStateSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;

import java.util.Map;
import java.util.Set;

/**
 * Submodule containing information about the game's current state, and deactivates submodules not \
 * inherent to the current state.
 *
 * @author Giovanni Paone
 */
public final class GameStateSubmodule extends GameStateSubmoduleAbs {
    private GameState currentState = GameState.WORLD;
    private final Publisher<RenderState> renderStatePublisher = new PublisherImpl<>();
    private final Map<GameState, Set<Class<? extends Submodule>>> submoduleStateMap = Map.of(
            GameState.FIGHT, Set.of(
            ),
            GameState.WORLD, Set.of(
                    PlayerCharacterSubmodule.class,
                    GameMapSubmodule.class
            ),
            GameState.GAMEOVER, Set.of(
            ),
            GameState.VICTORY, Set.of(
            )
    );

    /**
     * @param handler  MessageHandler to call in order to query other submodules.
     * @param modelObs {@link ModelObserver} used to instantiate the submodule
     */
    public GameStateSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
        modelObs.addStateChangePublisher(renderStatePublisher);
    }

    private void deactivateSubmodule(final Class<? extends Submodule> submodule) {
        handler().handle(submodule, Submodule::deactivate);
    }

    private void activateSubmodule(final Class<? extends Submodule> submodule) {
        handler().handle(submodule, Submodule::activate);
    }

    @Override
    public void setGameState(final GameState state) {
        if (currentState != state) {
            submoduleStateMap.entrySet().stream()
                    .filter(e -> e.getKey() != state)
                    .flatMap(e -> e.getValue().stream())
                    .forEach(this::deactivateSubmodule);
            submoduleStateMap.get(state).forEach(this::activateSubmodule);
        }
        this.currentState = state;
        renderStatePublisher.notifyObservers(
                switch (currentState) {
                    case WORLD -> RenderState.WORLD;
                    case FIGHT -> RenderState.FIGHT;
                    case GAMEOVER -> RenderState.DEATH;
                    case VICTORY -> RenderState.VICTORY;
                }
        );
    }

    @Override
    public GameState getGameState() {
        return currentState;
    }

    @Override
    protected void updateCode(final double delta) {
    }
}
