package io.github.yokigroup.world.entity;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.Updatable;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.Sprite;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Objects;

/**
 * Entity class represents a generic entity in the game world.
 * It provides methods to manage and manipulate entities.
 */
public abstract class Entity extends Updatable implements Sprite {
    private final String resourceURL;
    private final Vector2 dimensions;
    private Position pos;
    private final Hitbox hitBox;
    private final MessageHandler messageHandler;
    private static final int ENTITY_DRAW_PRIORITY = 1;
    /**
     * Path for the textures.
     */
    protected static final String ROOT_URL = "io/github/yokigroup/view/textures/";

    /**
     * Constructs an Entity object with the specified attributes.
     *
     * @param pos            The position of the Entity
     * @param hitBox         The hitBox of the Entity
     * @param messageHandler handler for the events
     * @param dimensions     dimensions of the entity
     * @param resourceURL    url of the entity image (starting at {@link Entity#ROOT_URL})
     */
    public Entity(final Position pos, final Hitbox hitBox, final MessageHandler messageHandler,
                  final Vector2 dimensions, final String resourceURL) {
        Objects.requireNonNull(hitBox, "HIT-BOX passed to Entity was null");
        this.messageHandler = messageHandler;
        this.hitBox = hitBox.copyOf();
        this.setPos(pos);
        this.dimensions = dimensions;
        this.resourceURL = ROOT_URL + resourceURL;
    }

    /**
     * This method return a string with the Entity ResourceURL used to display his sprite in the view.
     *
     * @return String
     */
    protected String getResourceURL() {
        return resourceURL;
    }

    /**
     * This method return the SpriteData to display his sprite in the view.
     *
     * @return SpriteData
     */
    @Override
    public SpriteData getSpriteData() {
        return new SpriteData(getResourceURL(), pos.getPosition(), dimensions, ENTITY_DRAW_PRIORITY);
    }

    /**
     * return the current position of the entity.
     *
     * @return Position, X e Y
     */
    public final Position getPos() {
        return pos.copyOf();
    }

    /**
     * return the messageHandler of this class.
     *
     * @return MessageHandler
     */
    public final MessageHandler getMessageHandler() {
        return this.messageHandler;
    }

    /**
     * Set a new position for the entity, if it's valid it changes it.
     *
     * @param pos new position
     */
    public final void setPos(final Position pos) {
        Objects.requireNonNull(pos, "Pos passed to the entity was null");
        if (pos.isValid()) {
            this.pos = pos.copyOf();
            this.hitBox.setPosition(pos.getPosition());
        }

    }

    /**
     * Return the hitBox of the current Entity.
     *
     * @return This hitBox
     */
    public Hitbox getHitBox() {
        return this.hitBox.copyOf();
    }

    /**
     * Control if the entity is equal to another.
     *
     * @param o Entity
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Entity entity = (Entity) o;
        return Objects.equals(pos, entity.pos) && Objects.equals(hitBox, entity.hitBox);
    }

    /**
     * Return and hash code of the entity.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(pos, hitBox);
    }
}
