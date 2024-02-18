package pacman.score;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ScoreBoardTest {

    static ScoreBoard s1;
    static ScoreBoard s2;
    static ScoreBoard s3;
    static ScoreBoard s4;

    @Before
    public void setUp() throws Exception {
        s1 = new ScoreBoard();
        s1.increaseScore(10);
        s2 = new ScoreBoard();
        s3 = new ScoreBoard();
        s4 = new ScoreBoard();
    }

    @After
    public void tearDown() throws Exception {
        s1 = null;
        s2 = null;
        s3 = null;
        s4 = null;
    }

    @Test
    public void getEntriesByName() {
        // Case 1 - Single Case
        List<String> e1 = new ArrayList<String>(List.of("a : 1"));
        s1.setScore("a", 1);
        assertEquals(e1, s1.getEntriesByName());

        // Case 2 - Elements entered without lexicographical order
        List<String> e2 = new ArrayList<String>(List.of("a : 50", "b : 10", "c : 40"));
        s2.setScore("a", 50);
        s2.setScore("c", 40);
        s2.setScore("b", 10);
        assertEquals(e2, s2.getEntriesByName());
    }

    @Test
    public void getEntriesByScore() {
        // Case 1 - Single Case
        List<String> e1 = new ArrayList<String>(List.of("a : 1"));
        s1.setScore("a", 1);
        assertEquals(e1, s1.getEntriesByScore());

        // Case 2 - Elements entered in descending score order
        List<String> e2 = new ArrayList<String>(List.of("a : 50", "c : 40", "b : 10"));
        s2.setScore("a", 50);
        s2.setScore("c", 40);
        s2.setScore("b", 10);
        assertEquals(e2, s2.getEntriesByScore());

        // Case 3 - Elements not entered in descending score order
        List<String> e3 = new ArrayList<String>(List.of("a : 50", "c : 40", "b : 10"));
        s3.setScore("a", 50);
        s3.setScore("b", 10);
        s3.setScore("c", 40);
        assertEquals(e3, s3.getEntriesByScore());
    }

    @Test
    public void setScore() {
        // Case 1 - Single Case
        List<String> e1 = new ArrayList<String>(List.of("a : 1"));
        s1.setScore("a", 1);
        assertEquals(e1, s1.getEntriesByName());

        // Case 2 - Invalid Name
        List<String> e2 = new ArrayList<String>();
        s2.setScore("e53.{", 1);
        assertEquals(e2, s2.getEntriesByName());

        // Case 3 - Invalid Score
        List<String> e3 = new ArrayList<String>();
        s3.setScore("a", -495);
        assertEquals(e2, s3.getEntriesByName());

        // Case 4 - Pre-existing value
        List<String> e4 = new ArrayList<String>(List.of("a : 50"));
        s4.setScore("a", 1);
        s4.setScore("a", 50);
        assertEquals(e4, s4.getEntriesByName());
    }

    @Test
    public void setScores() {
        // Case 1 - Single Case
        List<String> e1 = new ArrayList<String>(List.of("a : 1"));
        Map<String, Integer> scores1 = new HashMap<String, Integer>();
        scores1.put("a", 1);
        s1.setScores(scores1);
        assertEquals(e1, s1.getEntriesByName());

        // Case 2 - Single Element with Invalid Name
        List<String> e2 = new ArrayList<String>(List.of("a : 1", "b : 1"));
        Map<String, Integer> scores2 = new HashMap<String, Integer>();
        scores2.put("b", 1);
        scores2.put("e53.{", 1);
        scores2.put("a", 1);
        s2.setScores(scores2);
        assertEquals(e2, s2.getEntriesByName());

        // Case 3 - Single Element with Invalid Score
        List<String> e3 = new ArrayList<String>(List.of("b : 20"));
        Map<String, Integer> scores3 = new HashMap<String, Integer>();
        scores3.put("b", 20);
        scores3.put("a", 1);
        scores3.put("a", -495);
        s3.setScores(scores3);
        assertEquals(e3, s3.getEntriesByName());

        // Case 4 - Multiple invalid elements
        List<String> e4 = new ArrayList<String>(List.of("a : 495"));
        Map<String, Integer> scores4 = new HashMap<String, Integer>();
        scores4.put("t5$5@", -1);
        scores4.put("e%^7;", 1);
        scores4.put("a", -9);
        scores4.put("a", 495);
        s4.setScores(scores4);
        assertEquals(e4, s4.getEntriesByName());
    }

    @Test
    public void increaseScore() {
        // Case 1
        s1.increaseScore(10);
        assertEquals(20, s1.getScore());

        // Case 2
        s2.increaseScore(-10);
        assertEquals(0, s2.getScore());

        // Case 3
        s3.increaseScore(0);
        assertEquals(0, s3.getScore());
    }

    @Test
    public void getScore() {
        assertEquals(10, s1.getScore());
        assertEquals(0, s2.getScore());
    }

    @Test
    public void reset() {
        s1.reset();
        assertEquals(0, s1.getScore());
        s2.reset();
        assertEquals(0, s2.getScore());
    }
}