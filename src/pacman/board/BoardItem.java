package pacman.board;

/**
 * This enum defines different items which can be placed on a pacman board.
 * Each item has an associated character, score and is defined as pathable or not.
 */
public enum BoardItem {
    BIG_DOT('B', 15, true),
    BIG_DOT_SPAWN('b', 0, true),
    DOT('1', 10, true),
    GHOST_SPAWN('$', 0, true),
    NONE('0', 0, true),
    PACMAN_SPAWN('P', 0, true),
    WALL('X', 0, false);

    /* Associated characteristics */
    private char character;
    private int score;
    private boolean pathable;

    /**
     * Default constructor, initializes a new board item.
     * @param character the associated character value.
     * @param score the associated score.
     * @param pathable true if the board item is pathable, false otherwise.
     */
   BoardItem(char character, int score, boolean pathable) {
        this.character = character;
        this.score = score;
        this.pathable = pathable;
    }

    /**
     * Returns the associated character with the BoardItem.
     *
     * @return the associated character.
     */
    public char getChar() {
       return character;
    }

    /**
     * Returns the associated score of the BoardItem.
     *
     * @return score of the BoardItem.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns true if the BoardItem is pathable, false otherwise.
     *
     * @return true if the BoardItem is pathable, false otherwise.
     */
    public boolean getPathable() {
        return pathable;
    }

    /**
     * Returns the BoardItem associated with the provided character.
     *
     * @param key a character to retrieve the associated BoardItem.
     * @return a BoardItem associated with the given character.
     * @throws IllegalArgumentException
     */
    public static BoardItem getItem(char key) throws IllegalArgumentException {
        switch (key) {
            case '0':
                return NONE;
            case 'X':
                return WALL;
            case '1':
                return DOT;
            case 'B':
                return BIG_DOT;
            case 'b':
                return BIG_DOT_SPAWN;
            case '$':
                return GHOST_SPAWN;
            case 'P':
                return PACMAN_SPAWN;
            default:
                throw new IllegalArgumentException();
        }
    }

}
