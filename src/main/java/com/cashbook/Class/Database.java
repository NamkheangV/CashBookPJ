package com.cashbook.Class;
import java.sql.*;


public class Database {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String url = "jdbc:mysql://localhost:3306/cashbook";
    private String username = "root";
    private String password = "";

    public void connectDB()throws SQLException {
        try{
            con = DriverManager.getConnection(url,username,password);
        }
        catch(Exception e){

        }
    }

    public void DisconnectDB()throws SQLException {
        try{
            con.close();
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    public boolean execute(String SQL)throws SQLException {
        try{
            connectDB();
            st = con.createStatement();
            st.execute(SQL);
            DisconnectDB();
            return true;
        }
        catch(Exception e){
            System.out.print(e.getMessage());
            return false;
        }

    }

    public ResultSet getResultSet(String SQL)throws SQLException {
        try{
            connectDB();
            st = con.createStatement();
            rs = st.executeQuery(SQL);
            return rs;
        }
        catch(Exception e){
            return null;
        }
    }

    public ResultSet execQuery(String sql) {
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return rs;
    }
}
