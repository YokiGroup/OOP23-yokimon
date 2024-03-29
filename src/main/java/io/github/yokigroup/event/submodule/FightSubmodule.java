package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.fight.FightImpl;
import io.github.yokigroup.view.notification.AttackOutcomeNotification;
import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.FightSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.GameStateSubmoduleAbs;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.notification.AttackOutcomeNotificationImpl;
import io.github.yokigroup.view.notification.AttackSelectedNotificationImpl;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.GameMap;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Implementation of {@link FightSubmoduleAbs}.
 *
 * @author Giovanni Paone
 */
public final class FightSubmodule extends FightSubmoduleAbs {
    private Fight lastAnnouncedFight;
    private final Publisher<Fight> fightPub = new PublisherImpl<>();
    private final Publisher<SpriteData> backgroundPub = new PublisherImpl<>();
    private final Publisher<Notification> notificationPublisher = new PublisherImpl<>();
    private static final int LAST_PRIORITY = -100;

    /**
     * @param handler  MessageHandler to call in order to query other submodules.
     * @param modelObs the model Observer.
     */
    public FightSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
        Objects.requireNonNull(modelObs);
        modelObs.addFightPublisher(fightPub);
        modelObs.addSpritePublisher(RenderState.FIGHT, backgroundPub);
        modelObs.addNotificationPublisher(notificationPublisher);
        final Vector2 mapDim = Vector2Impl.castPair(GameMap.TILE_DIMENSIONS);
        final SpriteData battleBackground = new SpriteData(
                "io/github/yokigroup/view/textures/tiles/battle-forest.png",
                mapDim.scale(0.5),
                mapDim,
                LAST_PRIORITY
        );
        backgroundPub.notifyObservers(battleBackground);
    }

    @Override
    public void addEncounter(final List<Yokimon> enemyParty) {
        Objects.requireNonNull(enemyParty, "Enemy party was null");
        final int partyYokimonsNum = handler().handle(PartySubmodule.class, s -> {
            return s.listYokimons().size();
        });
        if (partyYokimonsNum == 0) {
            handler().handle(GameEndSubmodule.class, GameEndSubmodule::triggerBattleWithNoYokimonsGO);
        } else {
            handler().handle(GameStateSubmodule.class,
                    (Consumer<GameStateSubmodule>) s -> s.setGameState(GameStateSubmoduleAbs.GameState.FIGHT));
            lastAnnouncedFight = handler().handle(PartySubmodule.class, s -> {
                return new FightImpl(s.listYokimons(), enemyParty);
            });
            fightPub.notifyObservers(lastAnnouncedFight);
        }
    }

    @Override
    public Optional<Fight> getLastAnnouncedFight() {
        return Optional.ofNullable(lastAnnouncedFight);
    }

    private Fight getLastAnnouncedFightOrThrowException() {
        return getLastAnnouncedFight().orElseThrow(
                () -> new IllegalStateException("Player is not involved in a fight")
        );
    }

    @Override
    public void nextAttack() {
        final Fight currentFight = getLastAnnouncedFightOrThrowException();
        final List<Attack> attacks = currentFight.getCurrentMyYokimon().getAttacks();
        final int nextAttackIndex = (attacks.indexOf(currentFight.getSelectedAttack()) + 1) % attacks.size();
        currentFight.selectAttack(attacks.get(nextAttackIndex));
        notificationPublisher.notifyObservers(new AttackSelectedNotificationImpl(currentFight.getSelectedAttack()));
    }

    @Override
    public void prevAttack() {
        final Fight currentFight = getLastAnnouncedFightOrThrowException();
        final List<Attack> attacks = currentFight.getCurrentMyYokimon().getAttacks();
        int nextAttackIndex = attacks.indexOf(currentFight.getSelectedAttack()) - 1;
        nextAttackIndex = nextAttackIndex < 0 ? attacks.size() - 1 : nextAttackIndex;
        currentFight.selectAttack(attacks.get(nextAttackIndex));
        notificationPublisher.notifyObservers(new AttackSelectedNotificationImpl(currentFight.getSelectedAttack()));
    }

    @Override
    public void confirmAttack() {
        final Fight currentFight = getLastAnnouncedFightOrThrowException();
        fightPub.notifyObservers(currentFight);
        switch (currentFight.getState()) {
            case PLAYER_TURN ->
                    notificationPublisher.notifyObservers(new AttackOutcomeNotificationImpl(currentFight.attack(),
                            AttackOutcomeNotification.Attacker.PLAYER));
            case OPPONENT_TURN ->
                    notificationPublisher.notifyObservers(new AttackOutcomeNotificationImpl(currentFight.getAttacked(),
                            AttackOutcomeNotification.Attacker.ENEMY));
            default -> {
                throw new AssertionError("Fight state cannot be anything other than PLAYERTURN "
                        + "or OPPONENTTURN at this stage of the program");
            }
        }
        switch (currentFight.getState()) {
            case LOSE -> {
                handler().handle(GameEndSubmodule.class, GameEndSubmodule::triggerDeathGameGO);
            }
            case WIN -> {
                handler().handle(PartySubmodule.class,
                        (Consumer<PartySubmodule>) s -> s.setParty(currentFight.getPlayerParty()));
                if (handler().handle(GameMapSubmodule.class, GameMapSubmodule::areAllEnemiesSlain)) {
                    handler().handle(GameEndSubmodule.class, GameEndSubmodule::triggerVictory);
                } else {
                    handler().handle(GameStateSubmodule.class,
                            (Consumer<GameStateSubmodule>) s -> s.setGameState(GameStateSubmoduleAbs.GameState.WORLD));
                }
            }
            default -> {
            }
        }
        fightPub.notifyObservers(currentFight);
    }

}
