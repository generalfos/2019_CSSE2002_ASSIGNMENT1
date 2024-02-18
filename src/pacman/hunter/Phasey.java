package pacman.hunter;

import pacman.ghost.Ghost;
import pacman.ghost.Phase;

/**
 * A Phasey hunter with a special ability that allows the hunter to travel through ghosts temporarily without dieing.
 */
public class Phasey extends Hunter {

    /**
     * Creates a Phasey Hunter with its special ability.
     */
    public Phasey() {
        super();
    }

    /**
     * Creates a Phasey Hunter by copying the internal state of another hunter.
     *
     * @param original - hunter to copy from
     */
    public Phasey(Hunter original) {
        super(original);
    }

    /**
     * If Phasey's special is active and if a ghost is not
     * Phase.FRIGHTENED then we travel through the ghost without killing them or them killing us.
     * Otherwise we behave as a normal Hunter.
     *
     * @param ghost - to check if we are colliding with.
     */
    @Override
    public void hit(Ghost ghost) {
        if (this.isSpecialActive()) {
            if (ghost.getPhase() != Phase.FRIGHTENED) {}
        }
        else {
            super.hit(ghost);
        }
    }
}
