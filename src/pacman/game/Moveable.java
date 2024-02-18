package pacman.game;

import pacman.util.Direction;
import pacman.util.Position;

/**
 * An object that can move with a current position and a direction.
 */
public interface Moveable {

    /**
     * Gets the direction that this Moveable is facing.
     *
     * @return the current direction of the Movable.
     */
    Direction getDirection();

    /**
     * Gets the current position of the Moveable.
     *
     * @return current position.
     */
    Position getPosition();

    /**
     * Sets the direction of the entity, if direction is null the direction is not set and remains the same.
     *
     * @param direction - to be set.
     */
    void setDirection(Direction direction);

    /**
     * Sets the position of the entity, if position is null the position is not set.
     *
     * @param position - to set to the Moveable.
     */
    void setPosition(Position position);
}
