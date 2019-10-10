package com.xinchen.wordguessing.common;

import java.sql.*;

/**
 * Database Utils
 */
public class DBUtil {
    /**
     * Get SQlite connection to PlayerRecord.db.
     * @return SQlite connection to PlayerRecord.db.
     */
    public static Connection getConnection() {
        try {
            String url = "jdbc:sqlite:./PlayerRecord.db";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Close database connection.
     * @param c database connection.
     */
    public static void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Close prepared statements.
     * @param ps db prepared statement.
     */
    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Close db result set.
     * @param rs db result set.
     */
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }


}
