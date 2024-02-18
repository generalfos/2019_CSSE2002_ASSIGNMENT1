package pacman.board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pacman.util.Position;

import static org.junit.Assert.*;
import static pacman.board.BoardItem.*;

public class PacmanBoardTest {

    PacmanBoard b1;
    Position origin;

    @Before
    public void setUp() throws Exception {
        b1 = new PacmanBoard(3, 3);
        origin = new Position(0,0);
    }

    @After
    public void tearDown() throws Exception {
        b1 = null;
        origin = null;
    }

    @Test()
    public void eatDot() {
        // Big Dot Case
        b1.setEntry(origin, BIG_DOT);
        BoardItem item1 = b1.eatDot(origin);
        assertEquals(BIG_DOT, item1);
        assertEquals(BIG_DOT_SPAWN, b1.getEntry(origin));

        // Dot Case
        b1.setEntry(origin, DOT);
        BoardItem item2 = b1.eatDot(origin);
        assertEquals(DOT, item2);
        assertEquals(NONE, b1.getEntry(origin));

        // Other BoardItem Case
        b1.setEntry(origin, WALL);
        BoardItem item3 = b1.eatDot(origin);
        assertEquals(WALL, item3);
        assertEquals(WALL, b1.getEntry(origin));
    }

    @Test(expected = NullPointerException.class)
    public void eatDotNull() {
        // Null Case
        b1.eatDot(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void eatDotIndex() {
        // Index Out Of Bounds Case
        b1.eatDot(new Position(-1, -1));
    }


    @Test
    public void getEntry() {
        // Boundary Case
        BoardItem e1 = WALL;
        assertEquals(e1, b1.getEntry(new Position(0,0)));

        // Inside Case
        BoardItem e2 = NONE;
        assertEquals(e2, b1.getEntry(new Position(1,1)));
    }

    @Test(expected = NullPointerException.class)
    public void getEntryNull() {
        // Null Case (Null Pointer Exception should be thrown)
        b1.getEntry(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getEntryIndex() {
        // Index Out Of Bounds Case
        b1.getEntry(new Position(-1, -1));
    }

    @Test
    public void getGhostSpawn() {
        // Case 1 - Board Contains No Ghost Spawns
        assertEquals(null, b1.getGhostSpawn());

        // Case 2 - Board Contains One Ghost Spawn
        b1.setEntry(origin, GHOST_SPAWN);
        assertEquals(origin, b1.getGhostSpawn());
    }

    @Test
    public void getHeight() {
        assertEquals(3, b1.getHeight());
    }

    @Test
    public void getPacmanSpawn() {
        // Case 1 - Board Contains No Pacman Spawns
        assertEquals(null, b1.getPacmanSpawn());

        // Case 2 - Board Contains One Pacman Spawn
        b1.setEntry(origin, PACMAN_SPAWN);
        assertEquals(origin, b1.getPacmanSpawn());
    }

    @Test
    public void getWidth() {
        assertEquals(3, b1.getWidth());
    }

    @Test
    public void isEmpty() {
        // Case 1 - Board is empty.
        assertEquals(true, b1.isEmpty());

        // Case 2 - Board is not empty.
        b1.setEntry(origin, DOT);
        assertEquals(false, b1.isEmpty());
    }

    @Test
    public void reset() {
        b1.reset();
        // Default - 3x3 case
        for (int i = 0; i < b1.getHeight(); i++) {
            for (int j = 0; j < b1.getWidth(); j++) {
                Position board_position = new Position(i, j);
                assertEquals(false, b1.getEntry(board_position) == NONE);
                assertEquals(false, b1.getEntry(board_position) == BIG_DOT_SPAWN);
            }
        }
    }

    @Test
    public void setEntry() {
        // Case 1 - Default BoardItem.
        b1.setEntry(origin, NONE);
        assertEquals(NONE, b1.getEntry(origin));

        // Case 2 - Pacman Spawn.
        b1.setEntry(origin, PACMAN_SPAWN);
        assertEquals(PACMAN_SPAWN, b1.getEntry(origin));
        assertEquals(origin, b1.getPacmanSpawn());

        // Case 3 - Ghost Spawn.
        b1.setEntry(origin, GHOST_SPAWN);
        assertEquals(GHOST_SPAWN, b1.getEntry(origin));
        assertEquals(origin, b1.getGhostSpawn());
    }

    @Test(expected = NullPointerException.class)
    public void setEntryPositionNull() {
        b1.setEntry(null, NONE);
    }

    @Test(expected = NullPointerException.class)
    public void setEntryItemNull() {
        b1.setEntry(origin, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setEntryNegativeIndex() {
        b1.setEntry(new Position(-1, -1), NONE);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setEntryOutOfBoundsIndex() {
        b1.setEntry(new Position(b1.getHeight(), b1.getWidth()), NONE);
    }
}