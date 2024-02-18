package pacman.util;

/**
 * A position used to represent objects in a 2d space.
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructor: creates a position at the given x and y coordinates.
     *
     * @param x - coordinate
     * @param y - coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X coordinate.
     *
     * @return the X coordinate of the position.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate.
     *
     * @return the Y coordinate of the position.
     */
    public int getY() {
        return y;
    }

    /**
     * Adds two positions together.
     *
     * @param other a position to add to this one.
     * @return This position + other.
     */
    public Position add(Position other) {
        int new_x = x + other.getX();
        int new_y = y + other.getY();
        return new Position(new_x, new_y);
    }

    /**
     * Calculates the Euclidean distance from this point to the given other point.
     *
     * @param other point used to calculate the euclidean distance.
     * @return the euclidean distance.
     */
    public double distance(Position other) {
        return java.lang.Math.sqrt((x-other.getX())^2 + (y-other.getY())^2);
    }

    /**
     * Multiplies by a factor on the x and y axis.
     *
     * @param factor - to multiply the x and y coordinates by.
     * @return a new position with the x coord scaled by factor and y coord scaled by factor.
     */
    public Position multiply(int factor) {
        return new Position(this.getX()*factor, this.getY()*factor);
    }

    /**
     * Checks if two positions are equal.
     *
     * @param other - object to compare against.
     * @return true if x == this.x and also y == this.y, false otherwise.
     */
    public boolean equals(Object other) {
        // Determines if other is a position.
        if (other instanceof Position) {
            return equals((Position) other);
            }
        return false;
    }

    private boolean equals(Position other) {
        if (x == other.getX()) {
            if (y == other.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the hash of the position.
     *
     * @return hash of this position.
     */
    public int hashCode() {
        return x + y;
    }
}
