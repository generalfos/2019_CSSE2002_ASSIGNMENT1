package pacman.board;

import pacman.util.Position;

import java.util.Map;

import static pacman.board.BoardItem.*;
/**
 * PacmanBoard - represents a pacman board containing BoardItems.
 * This board can be any size and is set out as a grid with each space containing one BoardItem.
 * A pacman board is by default surronded by BoardItem.WALL with every other space containing BoardItem.NONE.
 *
 * @author Joel Foster
 * */
public class PacmanBoard {

    private int width;
    private int height;
    private int board_size;
    private Character[][] board;

    /**
     * Default constructor. Initialises a new pacman board by passing it its height and width.
     *
     * @param width the width of the pacman board.
     * @param height the height of the pacman board.
     * @throws IllegalArgumentException
     */
    public PacmanBoard(int width, int height) throws IllegalArgumentException {
        this.width = width;
        this.height = height;
        board = defaultBoard();
        board_size = width * height;
    }

    /**
     * Constructor which takes a pre-existing Pacman Board and produces a deep copy of the provided board.
     * This copy shares the width, height and board of the old board. Changes to one board do not affect the copy.
     *
     * @param other the pacman board to duplicate
     * @throws NullPointerException
     */
    public PacmanBoard(PacmanBoard other) throws NullPointerException {
        width = other.getWidth();
        height = other.getHeight();
        board = other.getBoardArray();
    }

    private Character[][] defaultBoard() {
        // Initialises a new board array.
        Character[][] newboard = new Character[height][width];
        // Iterates over all non edge array elements setting them to BoardItem.NONE
        for (int i = 1; i < height-1; i++) {
            for (int j = 1; j < width-1; j++) {
                newboard[i][j] = '0';
            }
        }
        // Iterates over all edge array elements setting them to BoardItem.WALL. Iteration follows 'L' form.
        for (int i = 0; i < height; i++) {
            newboard[i][width-1] = 'X';
            newboard[i][0] = 'X';
        }
        for (int i = 0; i < width; i++) {
            newboard[height-1][i] = 'X';
            newboard[0][i] = 'X';
        }
        return newboard;
    }

    /**
     * Tries to eat a dot off of the board and returns the item it tried to eat.
     * If a BoardItem.DOT is eaten it is replaced with a BoardItem.NONE.
     * If a BoardItem.BIG_DOT is eaten it is replaced with a BoardItem.BIG_DOT_SPAWN.
     * If the item is any other BoardItem, do nothing and return the item.
     *
     * @param position the position of the BoardItem to eat
     * @return the item that was originally at position before trying to eat.
     */
    public BoardItem eatDot(Position position) {
        BoardItem item = this.getEntry(position);
        // Handles DOT and BIG_DOT cases
        if (item == DOT) {
            this.setEntry(position, NONE);
        } else if (item == BIG_DOT) {
            this.setEntry(position, BIG_DOT_SPAWN);
        }
        return item;
    }

    /**
     * Returns what item the board has on a given position.
     *
     * @param position the position of the item to be checked.
     * @return BoardItem at the given location.
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public BoardItem getEntry(Position position) throws IndexOutOfBoundsException, NullPointerException {
        // Handles the null case
        if (position == null) {
            throw new NullPointerException();
        }
        return BoardItem.getItem(board[position.getX()][position.getY()]);
    }

    /**
     * Gets the height of the board.
     *
     * @return the height of the board.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the spawn position for the ghosts.
     *
     * @return the position of the ghost spawn or null if none are found.
     * @requires board to contain 0 or 1 GHOST_SPAWN's.
     */
    public Position getGhostSpawn() {
        return getBoardItemLocation(GHOST_SPAWN);
    }

    /**
     * Get the spawn position for pacman.
     *
     * @return the postiion of the pacman spawn or null if none are found.
     * @requires board to contain 0 or 1 PACMAN_SPAWN's.
     */
    public Position getPacmanSpawn() {
        return getBoardItemLocation(PACMAN_SPAWN);
    }

    private Position getBoardItemLocation(BoardItem item) {
        // Iterates over the entire board
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Position board_position = new Position(i, j);
                // Determines if current selected position contains the item and returns the position if it does.
                if (getEntry(board_position) == item) {
                    return board_position;
                }
            }
        }
        return null;
    }

    /**
     * Gets the width of the board.
     *
     * @return width of the game board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Checks if the board contains any pickup items.
     *
     * @return true if the board does not contain any DOT's or BIG_DOT's, false otherwise.
     */
    public boolean isEmpty() {
        // Iterates over the entire board
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Position board_position = new Position(i, j);
                // Checks if current position contains BoardItem.DOT or BoardItem.BIG_DOT
                if (getEntry(board_position) == DOT || getEntry(board_position) == BIG_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Resets the board to place a DOT in every position that has no item ( NONE BoardItem )
     * and respawns BIG_DOT's in the BIG_DOT_SPAWN locations.
     * Leaves walls, pacman spawns and ghost spawns intact.
     */
    public void reset() {
        // Iterates over the entire board
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Position board_position = new Position(i, j);
                if (getEntry(board_position) == NONE) {
                    setEntry(board_position, DOT);
                } else if (getEntry(board_position) == BIG_DOT_SPAWN) {
                    setEntry(board_position, BIG_DOT);
                }
            }
        }
    }

    /**
     * Sets a tile on the board to an item.
     * If the item to be set is a BoardItem.PACMAN_SPAWN and the board already contains a BoardItem.PACMAN_SPAWN
     * then the old BoardItem.PACMAN_SPAWN should be made a BoardItem.NONE.
     * If the item to be set is a BoardItem.GHOST_SPAWN and the board already contains a BoardItem.GHOST_SPAWN
     * then the old BoardItem.GHOST_SPAWN should be made a BoardItem.NONE.
     *
     * @param position the position to place the item.
     * @param item the board item that is to be placed at the position.
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public void setEntry(Position position, BoardItem item)
    throws IndexOutOfBoundsException, NullPointerException {
        if (item == PACMAN_SPAWN) {
            Position pacspawn = getPacmanSpawn();
            // Removes any PacmanSpawns if any currently exist
            if (pacspawn != null) {
                this.setEntry(pacspawn, NONE);
            }
        }
        if (item == GHOST_SPAWN) {
            Position ghost_spawn = getGhostSpawn();
            // Removes any GhostSpawns if any currently exist
            if (ghost_spawn != null) {
                this.setEntry(ghost_spawn, NONE);
            }
        }
        // Converts BoardItem into character representative and places it on the board.
        if (item != null) {
            board[position.getX()][position.getY()] = convertEntry(item);
        } else {
            throw new NullPointerException();
        }
    }
    /* Converts entry of BoardItem to its representative character */
    private static char convertEntry(BoardItem item) {
        final Map<BoardItem, Character> DICT = Map.of(NONE, '0', WALL, 'X', DOT, '1', BIG_DOT, 'B',
                BIG_DOT_SPAWN, 'b', GHOST_SPAWN, '$', PACMAN_SPAWN, 'P');
        return DICT.get(item);
    }

    /* Returns a clone of the current board */
    private Character[][] getBoardArray() {
        return board.clone();
    }
}
