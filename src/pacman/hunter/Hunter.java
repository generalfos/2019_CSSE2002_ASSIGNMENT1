package pacman.hunter;

import pacman.game.Entity;
import pacman.ghost.Ghost;
import pacman.ghost.Phase;
import pacman.util.Direction;
import pacman.util.Position;
import static pacman.util.Direction.*;


/**
 * Hunters are entities which are controlled by the player to clear the board and win the game.
 */
public abstract class Hunter extends Entity {

    private Position position;
    private Direction direction;
    private Boolean specialUsed;
    private Boolean isAlive;
    private int specialDuration;

    /**
     * Creates a Hunter setting the hunter to be alive with the following conditions: The hunter has not used
     * it's special yet. The hunter also does not have its special active.
     * This hunter has a position of (0, 0) with a direction of UP.
     */
    public Hunter() {
        reset();
    }

    /**
     * Creates a Hunter where the following attributes are the same between this hunter and the original:
     * - Dead/Alive status
     * - Whether the hunter has used its special ability yet.
     * - The duration remaining of the special ability.
     * - The position and direction.
     *
     * @param original - hunter to copy.
     */
    public Hunter(Hunter original) {
        isAlive = !(original.isDead());
        position = original.getPosition();
        direction = original.getDirection();
        specialUsed = original.specialUsed;
        specialDuration = original.specialDuration;
    }

    /**
     * Tells if the hunter is dead.
     *
     * @return true if dead, false otherwise.
     */
    public boolean isDead() {
        return !isAlive;
    }

    /**
     * Activates the hunter's special if the hunter hasn't already used its special before.
     * If the hunter has already used its special then do not change the special duration.
     * If the duration for the special is greater than zero then use the hunter's special
     *  and set the special's duration to the given duration.
     * If the duration for the special is zero or lower than do not change the special duration
     *  and do not use up the hunter's special.
     *
     * @param duration - to activate the special for.
     */
    public void activateSpecial(int duration) {
        if (!specialUsed) {
            if (duration > 0) {
                specialUsed = true;
                specialDuration = duration;
            }
        }
    }

    /**
     * Gets how many ticks of our special ability is remaining.
     *
     * @return the amount of ticks remaining for the special.
     */
    public int getSpecialDurationRemaining() {
        return specialDuration;
    }

    /**
     * Checks if the special is currently active.
     *
     * @return true if the special ability has a duration remaining that is greater than 0 ticks.
     */
    public boolean isSpecialActive() {
        if (specialDuration > 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the hunter is at the same position of the ghost.
     * If the ghost and hunter do have the same position then
     * if the ghost is Phase.FRIGHTENED the ghost is killed Ghost.kill() otherwise the ghost kills the hunter.
     * If the ghost and hunter are not at the same position then do nothing.
     *
     * @param ghost - to check if were colliding with.
     * @throws NullPointerException - if ghost is null
     */
    public void hit(Ghost ghost) throws NullPointerException {
        if (ghost.getPosition() == this.getPosition()) {
            // Determines if ghost is in phase FRIGHTENED
            if (ghost.getPhase() == Phase.FRIGHTENED) {
                ghost.kill();
            }
            else {
                // Kills Hunter
                isAlive = false;
            }
        }
    }

    /**
     * Resets this hunter to be:
     * - Alive
     * - With a special that has not been used yet
     * - A special that is not active ( duration of 0 )
     * - With a Direction of Direction.UP
     * - With a Position of ( 0, 0 )
     */
    public void reset() {
        isAlive = true;
        position = new Position(0,0);
        direction = UP;
        specialUsed = false;
        specialDuration = 0;
    }
}
