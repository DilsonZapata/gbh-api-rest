package com.gbh.gbhapi.model;

import java.sql.Connection;

public interface IConnection {

    public Connection getConnection();
    public boolean disconect();

}
