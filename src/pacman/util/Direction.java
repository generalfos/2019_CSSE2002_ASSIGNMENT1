package pacman.util;

/**
 * Direction represents directions in a 2D plane. Each direction stores a relative position.
 */
public enum Direction {
    LEFT(new Position(-1, 0)),
    RIGHT(new Position(1, 0)),
    UP(new Position(0, -1)),
    DOWN(new Position(-1, 0));

    private Position offset;

    /**
     * Constructor - Generates a position with relative position.
     *
     * @param position relative position.
     */
    Direction(Position position) {
        offset = position;
    }

    /**
     * Gets the opposite direction to this direction.
     *
     * @return the opposite direction e.g. up returns down, left returns right.
     * If no opposite direction exists then return this direction.
     */
    public Direction opposite() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                return this;
        }
    }

    /**
     * Gets the offset associated with this direction.
     *
     * @return relative position offset.
     */
    public Position offset() {
        return offset;
    }
}
