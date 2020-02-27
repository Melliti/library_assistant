package library.assistant.database;

import library.DBConfig;

import javax.swing.*;
import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler handler;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    public DatabaseHandler() {
        createConnection();
        setupBookTable();
        setupMemberTable();
    }

    void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL, DBConfig.user, DBConfig.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupMemberTable() {
        String TABLE_NAME = "MEMBER";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists.");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME
                        + "            (id varchar(200) primary key,\n"
                        + "             name varchar(200),\n"
                        + "             mobile varchar(20),\n"
                        + "             email varchar(100)"
                        + ")");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + "--- setupDatabase");
            e.printStackTrace();
        }
    }

    void setupBookTable() {
        String TABLE_NAME = "BOOK";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists.");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME
                    + "            (id varchar(200) primary key,\n"
                    + "             title varchar(200),\n"
                    + "             author varchar(200),\n"
                    + "             publisher varchar(100),\n"
                    + "             isAvail boolean default true)");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + "--- setupDatabase");
            e.printStackTrace();
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception ar execQuery:DataHandler " + e.getMessage());
            return null;
        } finally {
        }
        return result;
    }

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.err.println("Exception at execQuery:dataHandler" + e.getLocalizedMessage());
            return false;
        }
    }
}
