package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class employee_model extends database.db{
    private ArrayList<model.employee_bp> selected_employee_list;
    private ArrayList<model.employee_bp> selected_employee_list_get_salary;
    public employee_model() throws ClassNotFoundException, SQLException{
        this.selected_employee_list = new ArrayList<>();
        this.selected_employee_list_get_salary = new ArrayList<>();
    }
    public boolean employee_addtime_suc(int ID,double time){
        try {
            NRstatement(String.format("update Employee_table set hour_balance = hour_balance + %f where ID = %s", time,ID));
            this.create_history(String.format("ADDTIME     %s Hours", time), Integer.toString(ID));
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
            
        }
    }
    public void delete_employee(String ID) throws SQLException{
        NRstatement(String.format("delete from Employee_table where ID = %s", ID));
    }
    public void create_emp(String name,String rate_per_hour,String role) throws SQLException{
        NRstatement(String.format("insert into Employee_table(name,rate_per_hour,role)values('%s',%s,'%s')", name,rate_per_hour,role));
    }
//--------------------for editing an attributes--------------------------
    public void set_emp_rate_per_hour(String ID, String rate_per_hour) throws SQLException{
        NRstatement(String.format("update Employee_table set rate_per_hour = '%s' where ID = '%s'", rate_per_hour,ID));
    }
    public void set_emp_name(String ID, String name) throws SQLException{
        NRstatement(String.format("update Employee_table set name = '%s' where ID = %s", name,ID));
    }
    public void set_emp_hour_balance(String ID, String hour_balance) throws SQLException{
        NRstatement(String.format("update Employee_table SET hour_balance = %s where ID = %s", hour_balance,ID));
    }
    public void set_emp_role(String ID, String role) throws SQLException{
        NRstatement(String.format("update Employee_table set role = '%s' where ID = %s", role,ID));
    }
//-----------------------------------------------------------
    public int get_len_employee() throws SQLException{
        int row_count = 0;
        ResultSet rs = Rstatement("select count(*) as row_count from Employee_table");
        if (rs.next()){
            row_count = rs.getInt("row_count");
        }
        return row_count;
    }
    public ResultSet get_all_employee() throws SQLException{
        return this.Rstatement("select * from Employee_table");
    }
    public boolean employee_exist(String id) throws SQLException{//employee_exist_checker
        ResultSet emp_exit = Rstatement(String.format("select * from Employee_table where ID = %s", id));
        if (!emp_exit.next()){
            return false;
        }
        else{
            return true;
        }
    }
    public ArrayList<employee_bp> get_all_selected_employee(){
        return selected_employee_list;
    }
    public ArrayList<employee_bp> get_all_selected_emp_get_salary(){
        return selected_employee_list_get_salary;
    }
    public void create_history(String event,String ID) throws SQLException{
        NRstatement(String.format("INSERT INTO History_table (event, date_event, employee_id)\r\n" + //
                "VALUES ('%s', Now(), %s);", event,ID));
    }
    public boolean employee_selected_AT(String id){//add time salary list
        try {
            if (!employee_exist(id)){
                return false;
            }
            else{
            ResultSet rs = Rstatement(String.format("select * from Employee_table where ID = %s", id));
            while (rs.next()) {
                String name = rs.getString("name");
                int ID = rs.getInt("ID");

                employee_bp newEmployee = new employee_bp(ID, name, 0.0, null, 0.0);
                if(selected_employee_list.contains(newEmployee)){
                    return false;
                }else{
                    this.selected_employee_list.add(new employee_bp(ID,name, 0.0, null, 0.0));
                    return true;
                }
                
            }
  
            //NRstatement(String.format("update Employee_table set hour_balance = hour_balance + %d where ID = '%s'", time,id));
            
            return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean employee_selected_get_salary(String id){//get salary emp list
        try {
            if (!employee_exist(id)){
                return false;
            }
            else{
            ResultSet rs = Rstatement(String.format("select * from Employee_table where ID = %s", id));
            while (rs.next()) {
                String name = rs.getString("name");
                int ID = rs.getInt("ID");

                employee_bp newEmployee = new employee_bp(ID, name, 0.0, null, 0.0);
                if(this.selected_employee_list_get_salary.contains(newEmployee)){
                    return false;
                }else{
                    this.selected_employee_list_get_salary.add(new employee_bp(ID,name, 0.0, null, 0.0));
                    return true;
                }
                
            }
  
            //NRstatement(String.format("update Employee_table set hour_balance = hour_balance + %d where ID = '%s'", time,id));
            
            return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
