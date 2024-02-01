package model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class history_model extends database.db {
    
    public history_model() throws ClassNotFoundException, SQLException {
        super();
        
    }

    public ResultSet get_all_history() throws SQLException{
        return Rstatement("SELECT event,date_event,name from History_table inner join Employee_table on History_table.employee_id = Employee_table.ID");
    }
}
