package com.xinchen.wordguessing.model;

import com.xinchen.wordguessing.entities.Player;
import com.xinchen.wordguessing.common.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Interact with SQlite to load player history and update player score.
 */
public class LeaderBoard {
    private Player _player;
    private static Connection _connection;
    private List<Player> _allPlayers;

    /**
     * Start SQlite connection.
     * Load all players record.
     */
    public LeaderBoard(){
        _connection = DBUtil.getConnection();
        _allPlayers = new ArrayList<>();
        allPlayersRecord();
    }

    /**
     * Record of all Players are stored in ArrayList.
     */
    public void allPlayersRecord() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Players";
        try {
            ps = _connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Player player = new Player("new");
                player.set_id(rs.getInt("Id"));
                player.set_name(rs.getString("Name"));
                player.set_score(rs.getInt("Score"));
                _allPlayers.add(player);

            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.freeConnection(_connection);
        }
    }

    /**
     * Retrieve player record if exist, otherwise create record for new player.
     * @param userName
     * @return player with or without history record loaded.
     */
    public Player getPlayerRecord(String userName) {
        boolean newPlayer = true;
        for (Player player :_allPlayers){
            if (player.get_name().equals(userName)){
                _player = player;
                newPlayer = false;
                break;
            }
        }
        if (newPlayer) {
            _player = new Player(userName);
            insertPlayerRecord();
        }
        return _player;
    }

    /**
     * Update player record in SQlite db.
     */
    public void updatePlayerRecord() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement ps = null;
        //int result = 0;

        String query = "UPDATE players "
                + "SET Score = ? "
                + "WHERE Name = ?";
        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, _player.get_score());
            ps.setString(2, _player.get_name());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
            //result = 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.freeConnection(connection);
        }
    }

    /**
     * Insert new player record in SQlite db.
     */
    public void insertPlayerRecord() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement ps = null;
        //int result = 0;

        String query = "INSERT INTO players "
                + "(Name, Score) "
                + "VALUES (?, ?)";

        try {
            ps = connection.prepareStatement(query);
            //ps.setInt(1, _player.get_id());
            ps.setString(1, _player.get_name());
            ps.setInt(2, _player.get_score());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            //result = 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.freeConnection(connection);
        }
    }

    /**
     * Sort all players by score.
     * @return Sorted list of all players.
     */
    public List<Player> getSortedPlayers(){
        Collections.sort(_allPlayers);
        return _allPlayers;
    }

}
