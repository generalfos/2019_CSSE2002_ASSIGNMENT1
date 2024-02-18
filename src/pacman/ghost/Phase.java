package pacman.ghost;

/**
 * Phase defines the different phases a ghost can be in.
 */
public enum Phase {
    CHASE(20),
    FRIGHTENED(30),
    SCATTER(10);

    private int duration;

    Phase(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the duration of the phase.
     *
     * @return the duration of the phase.
     */
    public int getDuration() {
        return duration;
    }
}
