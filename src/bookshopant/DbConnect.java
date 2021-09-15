package bookshopant;

import java.sql.*;

public class DbConnect {

    Connection conn;
    Statement s;
    public DbConnect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql:///canteen","root","");
            s=conn.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        new DbConnect();
    }
}
