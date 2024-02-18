package pacman.ghost;

public class Blinky extends Ghost {

    public Blinky() {
        super();
    }

    /**
     * Get Blinkys colour.
     *
     * @return "#d54e53"
     */
    public String getColour() {
        return "#d54e53";
    }

    /**
     * Get Blinkys type/name.
     *
     * @return BLINKY
     */
    public GhostType getType() {
        return GhostType.BLINKY;
    }
}
