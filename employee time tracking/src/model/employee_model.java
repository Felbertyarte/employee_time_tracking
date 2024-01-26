package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.db;

public class employee_model {
    private database.db DB;
    public employee_model() throws ClassNotFoundException, SQLException{
        this.DB = new db();
        
    }
    public int get_len_employee() throws SQLException{
        int row_count = 0;
        ResultSet rs = DB.Rstatement("select count(*) as row_count from Employee_table");
        if (rs.next()){
            row_count = rs.getInt("row_count");
        }
        return row_count;
    }
    public ResultSet get_all_employee() throws SQLException{
        return this.DB.Rstatement("select * from Employee_table");
    }
}
