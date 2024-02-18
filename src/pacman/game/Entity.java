package pacman.game;
import pacman.util.Direction;
import pacman.util.Position;
import pacman.util.Direction.*;

import static pacman.util.Direction.*;

/**
 * A entity is the animated objects in the game that can traverse the game board and interact with other entities
 */
public abstract class Entity implements Moveable {

    private Position position;
    private Direction direction;

    /**
     * Creates an entity that is at the given position facing in the given direction.
     * If the position is null then the position will be the same as the default position ( 0, 0 ).
     * If the direction is null then the direction will be the same as the default ( UP ).
     *
     * @param position the initial position of the entity
     * @param direction the initial direction of the entity
     */
    public Entity(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;

        // Handle Null Cases (Fallback to Default Values)
        if (position == null) {
            this.position = new Position(0,0);
        }
        if (direction == null) {
            this.direction = UP;
        }
    }

    /**
     * Creates an entity that is at position (0, 0) and is facing UP.
     */
    public Entity() {
        position = new Position(0,0);
        direction =  UP;
    }

    /**
     * Gets the direction that this Moveable is facing.
     *
     * @return the current direction of movable
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the current position of the Moveable.
     *
     * @return the current position of movable.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the direction of the entity, if direction is null the direction is not set and remains the same.
     *
     * @param direction the new direction.
     */
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    /**
     * Sets the position of the entity, if position is null the position is not set.
     *
     * @param position the new position of movable
     */
    public void setPosition(Position position) {
        if (position != null) {
            this.position = position;
        }
    }
}
