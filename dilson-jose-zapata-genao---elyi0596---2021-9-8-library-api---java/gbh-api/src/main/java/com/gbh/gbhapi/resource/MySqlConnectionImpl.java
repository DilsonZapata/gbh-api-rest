package com.gbh.gbhapi.resource;

import com.gbh.gbhapi.model.IConnection;

import java.sql.Connection;

public class MySqlConnectionImpl implements IConnection {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public boolean disconect() {
        return false;
    }
}
