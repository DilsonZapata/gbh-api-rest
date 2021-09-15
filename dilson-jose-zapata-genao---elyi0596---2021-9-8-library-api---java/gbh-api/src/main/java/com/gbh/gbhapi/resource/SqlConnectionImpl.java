package com.gbh.gbhapi.resource;

import com.gbh.gbhapi.model.IConnection;

import javax.annotation.Resource;
import javax.naming.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class SqlConnectionImpl implements IConnection {


    @Override
    public Connection getConnection() {


        try {


            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbGbh");
            return ds.getConnection();

//            InitialContext ctx = new InitialContext();
//            NamingEnumeration<NameClassPair> list = ctx.list("");
//            while (list.hasMore()) {
//                System.out.println(list.next().getName());
//            }

//            Context initContext = new InitialContext();
//            DataSource ds = (DataSource) initContext.lookup("jdbcPoolGbh");
//            return ds.getConnection();


        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public boolean disconect() {
        return false;
    }
}
