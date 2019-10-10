package com.xinchen.wordguessing.entities;


/**
 * Data Structure to represent a player.
 */
public class Player implements Comparable<Player> {
    private String _name;
    private int _id;
    private int _score;

    /**
     * Override compareTo method, compare by player.score.
     * @param otherPlayer to compare.
     * @return order (descending).
     */
    @Override
    public int compareTo(Player otherPlayer) {
        return (otherPlayer.get_score() - this.get_score());
    }

    /**
     * Construct player by name and set score to 0 for new player.
     * @param name gamer input.
     */
    public Player(String name) {
        _name = name;
        _score = 0;
    }

    /**
     * Get player name.
     * @return player name
     */
    public String get_name() {
        return _name;
    }

    /**
     * Set player name
     * @param _name
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * Set player id
     * @param _id
     */
    public void set_id(int _id) {
        this._id = _id;
    }

    /**
     * Get player score.
     * @return player score.
     */
    public int get_score() {
        return _score;
    }

    /**
     * Set player score.
     * @param _score
     */
    public void set_score(int _score) {
        this._score = _score;
    }

    /**
     * Add score earned to old score.
     * @param score
     */
    public void addScore(int score) {
        _score += score;
    }

    /**
     * multiply _score by difficulty
     * @param difficulty
     */
    public void multiplyScore (int difficulty) {
        _score *= difficulty;
    }
}
