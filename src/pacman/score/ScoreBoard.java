package pacman.score;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

/**
 * ScoreBoard contains previous scores and the current score of the PacmanGame.
 * A score is a name and value that a valid name only contains the following characters:
 * - A to Z
 * - a to z
 * - 0 to 9
 * and must have a length greater than 0.
 * The values is an integer that is equal to or greater than 0.
 */
public class ScoreBoard {

    private int score;
    private Map<String, Integer> scoreboard = new TreeMap<String, Integer>();
    private Map<Integer, String> scoreboardByScore = new TreeMap<Integer, String>(Collections.reverseOrder());

    /**
     * Creates a score board that has no entries and a current score of 0.
     */
    public ScoreBoard() {
        score = 0;
    }

    /**
     * Gets the stored entries ordered by Name in lexicographic order.
     * The format of the list should be:
     * - [name1 : score1. name2 : score2]
     *
     * @return List of scores formatted as "NAME : VALUE" omin the order described above or an empty list if no entries are stored.
     */
    public List<String> getEntriesByName() {
        List<String> list = new ArrayList<String>();
        // Iterate over scoreboard and add elements to a return list.
        scoreboard.forEach((k, v) -> list.add(String.format("%s : %d", k, v)));
        return list;
    }

    /**
     * Gets the stored entries ordered by the score in descending order ( 9999 first then 9998 and so on ...)
     * then in lexicographic order of the name if the scores match. The format should match the one described in
     * getEntriesByName().
     *
     * @return List of scores formatted as "NAME : VALUE" in the order described above or an empty list if no entries are stored.
     */
    public List<String> getEntriesByScore() {
        sortByScore();
        List<String> list = new ArrayList<String>();
        // Iterate over scoreboardByScore and add elements to a return list.
        scoreboardByScore.forEach((k, v) -> list.add(String.format("%s : %d", v, k)));
        return list;
    }

    private void sortByScore() {
        // Iterate over key, value pairs of the main scoreboard and put them in scoreboardByScore with key and value
        // swapped.
        scoreboard.forEach((k,v) -> scoreboardByScore.put(v, k));
    }

    /**
     * Sets the score for the given name if:
     * - name is not null
     * - name is a valid score name
     * - score is equal to or greater than zero.
     * This overrides any score stored for the given name if name and score are valid.
     *
     * @param name the name of the scorer.
     * @param score the score to be set to the scorer.
     */
    public void setScore(String name, int score) {
        if (nameValid(name)) {
            if (score >= 0) {
                // Puts name and score inside scoreboard, replacing any current pair with key equal to name.
                if (scoreboard.containsKey(name)) {
                    scoreboard.replace(name, score);
                } else {
                    scoreboard.put(name, score);
                }
            }
        }
    }


    private static boolean nameValid(String name) {
        if (name != null) {
            if (name.length() > 0) {
                // Iterates over characters of the name and checking if the character is not a letter or digit.
                for (int i = 0; i < name.length(); i++) {
                    if (!(Character.isLetterOrDigit(name.charAt(i)))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }

    /**
     * Sets a collection of scores if "scores" is not null, otherwise no scores are modified.
     * For each score contained in the scores if:
     * - name is not null
     * - name is a valid score name
     * - score is equal to or greater than zero.
     * then the score will be set and override any stored score for the given name, otherwise it will be skipped.
     *
     * @param scores to add to scoreboard.
     */
    public void setScores(Map<String, Integer> scores) {
        if (scores != null) {
            /* Loop over elements of the map calling setScore for each */
            scores.forEach((k, v) -> setScore(k, v));
        }
    }

    /**
     * Increases the score if the given additional is greater than 0.
     * No change to the current score if additional is less than or equal to 0.
     * @param additional integer to add to score
     */
    public void increaseScore(int additional) {
        if (additional > 0) {
            score += additional;
        }
    }

    /**
     * Get the current score.
     *
     * @return the current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the current score to 0.
     */
    public void reset() {
        score = 0;
    }
}
