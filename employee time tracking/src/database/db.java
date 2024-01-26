package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db {
    Connection conn;
    java.sql.Statement pst;
    public db() throws ClassNotFoundException, SQLException{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        this.conn = DriverManager.getConnection("jdbc:ucanaccess://ETTDB.accdb");
        this.pst = conn.createStatement();
        
    }

    public void NRstatement(String statement) throws SQLException{
        ((java.sql.Statement) this.pst).executeUpdate(statement);
    }
    public ResultSet Rstatement(String statement) throws SQLException{
        ResultSet result = ((java.sql.Statement) this.pst).executeQuery(statement);
        return result;
    }
}
