// 
// Decompiled by Procyon v0.5.36
// 

package com.company.dao.inter;

import java.sql.DriverManager;
import java.sql.Connection;
import com.mysql.jdbc.Driver;


public abstract class AbstractDAO
{
    public Connection connection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        final String url = "jdbc:mysql://localhost:3306/resume";
        final String username = "root";
        final String password = "ilqar2002";
        final Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
