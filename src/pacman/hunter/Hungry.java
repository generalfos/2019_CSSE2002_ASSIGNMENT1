package pacman.hunter;

import pacman.ghost.Ghost;

/**
 * A Hungry hunter that has a special ability that allows the hunter to eat ghosts
 * temporarily without them being in a Frightened state.
 */
public class Hungry extends Hunter {

    /**
     * Creates a Hungry Hunter with its special ability.
     */
    public Hungry() {
        super();
    }

    /**
     * Creates a Hungry Hunter by copying the internal state of another hunter.
     *
     * @param original hunter to copy from
     */
    public Hungry(Hunter original) {
        super(original);
    }

    /**
     * If Hungry's special is active then if we are in the same tile of a ghost,
     * that ghost will be killed. Otherwise it behaves as a normal Hunter.
     *
     * @param ghost - to check if were colliding with.
     */
    public void hit(Ghost ghost) {
        if (this.isSpecialActive()) {
            if (ghost.getPosition() == this.getPosition()) {
                ghost.kill();
            }
        } else {
            super.hit(ghost);
        }

    }
}
