package pacman.hunter;

/**
 * A Phil hunter that has no special ability.
 */
public class Phil extends Hunter{

    /**
     * Creates a Phil Hunter
     */
    public Phil() {
        super();
    }

    /**
     * Creates a Phil hunter by copying the internal state of another hunter.
     *
     * @param original - hunter to copy from
     */
    public Phil(Hunter original) {
        super(original);
    }

    /**
     * Phil does not have a special.
     *
     * @return false
     */
    @Override
    public boolean isSpecialActive() {
        return false;
    }
}
