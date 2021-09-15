package com.gbh.gbhapi.resource;

import com.gbh.gbhapi.model.IConnection;
import java.sql.*;


public class DbAccess {

    private static DbAccess dbAccess = null;


    private DbAccess() {

    }

    public static DbAccess getDbAccess() {
        if (dbAccess == null)
            dbAccess = new DbAccess();
        
        return dbAccess;
    }


    public IConnection getConnection(String DbKey) {

        switch (DbKey) {
            case "SQL":
                return new SqlConnectionImpl();
            case "MySQL":
                return new MySqlConnectionImpl();
            default:
                return null;
        }

    }

    public ResultSet getSelect(Connection conn, String query) throws SQLException {
        return conn.createStatement().executeQuery(query);
    }


}
