package pacman.ghost;

public class Inky extends Ghost {
    
    public Inky() {
        super();
    }

    /**
     * Get Inky's colour.
     *
     * @return "#7aa6da"
     */
    public String getColour() {
        return "#7aa6da";
    }

    /**
     * Get Inky's type/name.
     *
     * @return INKY
     */
    public GhostType getType() {
        return GhostType.INKY;
    }
}
