package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class salary_model extends database.db{
    ArrayList<model.salary_bp> salary_list;
    public salary_model() throws ClassNotFoundException, SQLException{
        salary_list = new ArrayList<>();
    }
    public ResultSet get_all_employee_salary() throws SQLException{
        //Rstatement("select sum(salary) as salaries from salary_table");
        return Rstatement("select sum(rate_per_hour * hour_balance) as salaries from Employee_table");
    }
    public void set_salary_in_list(salary_bp salary){
        this.salary_list.add(salary);
    }
    public ArrayList<salary_bp> get_salary(){
        return salary_list;
    }
    public ResultSet get_individial_salary(int emp_ID) throws SQLException{
        return Rstatement(String.format("SELECT name, sum(rate_per_hour * hour_balance) as salary FROM Employee_table where ID = %d GROUP BY name", emp_ID));
    }
    public void clear_salary(int emp_ID) throws SQLException{
        NRstatement(String.format("update Employee_table set hour_balance = 0 where ID = %d", emp_ID));
    }

    
}