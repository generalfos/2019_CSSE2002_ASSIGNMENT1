package pacman.hunter;

/**
 * A Speedy hunter that has a special ability that allows the hunter to travel twice as fast.
 */
public class Speedy extends Hunter {

    /**
     * Creates a Speedy Hunter with its special ability.
     */
    public Speedy() {
        super();
    }

    /**
     * Creates a Speedy Hunter by copying the internal state of another hunter.
     *
     * @param original - hunter to copy from
     */
    public Speedy(Hunter original) {
        super(original);
    }

}
