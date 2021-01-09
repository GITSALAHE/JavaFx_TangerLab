package com.guserinterfaces.config;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DB extends Config{
    public Config configuration;
    public DB(){
        configuration = new Config("jdbc:mysql://localhost/tangerlab", "root", "");
    }
    public ResultSet selectAll(String table) throws SQLException{
        String sqlString = "SELECT * FROM  "+table;
        Statement stmt = this.configuration.connected().createStatement();
        ResultSet res = stmt.executeQuery(sqlString);
        return res;
    }
    public ResultSet selectOne(String table, String conditionName, String condition) throws SQLException{
        String sqlString = "SELECT * FROM  "+table+" WHERE "+conditionName+"="+condition;
        Statement stmt = this.configuration.connected().createStatement();
        ResultSet res = stmt.executeQuery(sqlString);
        return res;
    }

    public void createAndUpdateAndDelete(String sql) throws SQLException{
        Statement stmt = this.configuration.connected().createStatement();
        int res = stmt.executeUpdate(sql);
    }
    
}
