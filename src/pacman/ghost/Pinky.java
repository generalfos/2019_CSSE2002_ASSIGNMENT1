package pacman.ghost;

public class Pinky extends Ghost{

    public Pinky() {
        super();
    }

    /**
     * Get Pinky's colour.
     *
     * @return "#c397d8"
     */
    public String getColour() {
        return "#c397d8";
    }

    /**
     * Get Pinky's type/name.
     *
     * @return PINKY
     */
    public GhostType getType() {
        return GhostType.PINKY;
    }
}
