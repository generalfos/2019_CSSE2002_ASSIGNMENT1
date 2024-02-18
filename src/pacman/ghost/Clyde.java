package pacman.ghost;

public class Clyde extends Ghost {
    public Clyde() {
        super();
    }

    /**
     * Get Clydes colour.
     *
     * @return "#e78c45"
     */
    public String getColour() {
        return "#e78c45";
    }

    /**
     * Get Clydes type/name.
     *
     * @return CLYDE
     */
    public GhostType getType() {
        return GhostType.CLYDE;
    }
}
