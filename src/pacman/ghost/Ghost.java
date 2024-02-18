package pacman.ghost;

import pacman.game.Entity;
import pacman.util.Direction;
import pacman.util.Position;
import static pacman.ghost.Phase.*;
import static pacman.util.Direction.*;

/**
 * An abstract Ghost which is a game entity.
 */
public abstract class Ghost extends Entity {

    private Phase phase;
    private int phaseDuration;
    private Position position;
    private Direction direction;
    private boolean isAlive;

    /**
     * Creates a ghost which is alive and starts in the SCATTER phase with a duration of Phase.SCATTER.duration().
     * This ghost also has a default position of (0, 0) and a default direction of facing up.
     */
    public Ghost() {
        reset();
    }

    /**
     * Sets the Ghost Phase and its duration overriding any current phase information.
     * If Phase is null then no changes are made.
     * If the duration is less than zero then the duration is set to 0.
     *
     * @param newPhase the ghost's new phase
     * @param duration the number of ticks the new phase lasts for
     */
    public void setPhase(Phase newPhase, int duration) {
        if (newPhase != null) {
            phase = newPhase;
            if (duration < 0) {
                phaseDuration = 0;
            }
            else {
                phaseDuration = duration;
            }
        }
    }

    /**
     * Get the phase that the ghost currently is in.
     *
     * @return the current phase
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * Gets the phase info of the ghost.
     *
     * @return the phase and duration formatted as such: "PHASE:DURATION".
     */
    public String phaseInfo() {
        return String.format("%s:%d", phase, phaseDuration);
    }

    /**
     * Gets the ghosts type.
     *
     * @return this ghosts type
     */
    public abstract GhostType getType();

    /**
     * Gets the ghosts colour.
     *
     * @return hex version of the ghosts colour.
     */
    public abstract String getColour();

    /**
     * Checks if this ghost is dead.
     *
     * @return true if dead, false otherwise.
     */
    public boolean isDead() {
        return !isAlive;
    }

    /** Kills this ghost by setting its status to isDead. */
    public void kill() {
        isAlive = false;
    }

    /**
     * Resets the ghost back to an initial state where:
     * - It is alive
     * - With a Phase of SCATTER with duration SCATTER.getDuration()
     * - Facing in the Direction.UP
     * - With a Position of ( 0, 0 )
     */
    public void reset() {
        phase = SCATTER;
        position = new Position(0,0);
        direction = UP;
        isAlive = true;
    }


}
