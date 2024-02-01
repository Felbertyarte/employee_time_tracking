package controller;
import view.view_bp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.employee_bp;
import model.modelbp;


public class passcode_controller{
    model.modelbp model;
    view_bp view;
    public passcode_controller(view_bp view,modelbp model) throws SQLException{
        this.model = model;
        this.view = view;
        this.check_user_exist();

    }
    protected void check_user_exist() throws SQLException{
        ResultSet user_exist = model.PASSCODE_MODEL.get_user();
        if (!user_exist.next()){
            System.out.println("user_is_not exist");
        }
        else{
            this.passcode_attempt();
        }
    }
    public void passcode_attempt() throws SQLException{
        String passcode = this.view.PASSCODE_VIEW.passcode_attempt();
        boolean passcode_attem_suc = this.model.PASSCODE_MODEL.passcode_attemp_succ(passcode);
        if (!passcode_attem_suc){
            this.view.PASSCODE_VIEW.passcode_fail_messege();
            this.passcode_attempt();
        }
        else{
            this.goto_dashboard();
        }

    }
    public void Edit_employee_attributes() throws SQLException{
        ResultSet rs = this.model.EMPLOYEE_MODEL.get_all_employee();
        this.view.DASHBOARD_VIEW.display_employees(rs);
        String selected_emp_id = this.view.DASHBOARD_VIEW.select_emp();
        if (!isNumeric(selected_emp_id)){
            if(selected_emp_id.equals("e")){
                this.goto_dashboard();
            }else{
                this.Edit_employee_attributes();
            }
        }
        else{
            boolean employee_exist = this.model.EMPLOYEE_MODEL.employee_exist(selected_emp_id);
            if(!employee_exist){
                this.view.DASHBOARD_VIEW.employee_not_exist_msg();
                this.Edit_employee_attributes();
            }
            else{
                String select_attributes = this.view.DASHBOARD_VIEW.update_attributes_selection();
                
            switch (select_attributes){
                case "1":
                    String name = this.view.DASHBOARD_VIEW.Set_emp_name();
                    this.model.EMPLOYEE_MODEL.set_emp_name(selected_emp_id,name);
                    break;
                case "2":
                    String rate_per_hour = this.view.DASHBOARD_VIEW.Set_emp_rate_per_hour();
                    if (!isNumeric(rate_per_hour)){
                        this.Edit_employee_attributes();
                    }
                    else{
                        this.model.EMPLOYEE_MODEL.set_emp_rate_per_hour(selected_emp_id,rate_per_hour);
                    }
                    break;
                case "3":
                    String hour_balace = this.view.DASHBOARD_VIEW.Set_emp_hour_balance();
                    if (!isNumeric(hour_balace)){
                        this.Edit_employee_attributes();
                    }
                    this.model.EMPLOYEE_MODEL.set_emp_hour_balance(selected_emp_id,hour_balace);
                    break;
                case "4":
                    String role = this.view.DASHBOARD_VIEW.Set_emp_role();
                    this.model.EMPLOYEE_MODEL.set_emp_role(selected_emp_id,role);//String ID, String role
                    break;
                case "e":
                    this.Edit_employee_attributes();
                    break;
                default:
                this.Edit_employee_attributes();
            }
            }
            
        }

    }
    public void add_emp() throws SQLException{
        String[] emp = this.view.DASHBOARD_VIEW.create_emp();
        if (!isNumeric(emp[2])){
            add_emp();
        }
        else{      
            this.model.EMPLOYEE_MODEL.create_emp(emp[0], emp[2], emp[1]);
            this.view.DASHBOARD_VIEW.employee_created_suc_msg();
            this.view.DASHBOARD_VIEW.enter_to_continue();
            this.goto_dashboard();
        }
    }
    public void delete_emp() throws SQLException{
        ResultSet rs = this.model.EMPLOYEE_MODEL.get_all_employee();
        this.view.DASHBOARD_VIEW.display_employees(rs);
        String selected_emp_id =this.view.DASHBOARD_VIEW.select_emp_delete();
        if (!isNumeric(selected_emp_id)){
            if (selected_emp_id.equals("e")){
                this.goto_dashboard();
            }
        }
        else{
            this.model.EMPLOYEE_MODEL.delete_employee(selected_emp_id);
            this.view.DASHBOARD_VIEW.emp_delete_msg();
            this.view.DASHBOARD_VIEW.enter_to_continue();
            this.goto_dashboard();

            
        }
    }
    public void goto_dashboard() throws SQLException{
        this.view.DASHBOARD_VIEW.display_overall_salary_emps(this.model.SALARY_MODEL.get_all_employee_salary());
        this.view.DASHBOARD_VIEW.option_list();
        String option = this.view.DASHBOARD_VIEW.select_option_input();
        switch (option) {
            case "1":
                this.Add_time();
                break;
            case "2":
                this.show_history();
                break;
            case "3":
                this.Edit_employee_attributes();
                break;
            case "4":
                this.add_emp();
                break;
            case "5":
                this.delete_emp();
                break;
            case "6":
                this.get_salary();
                break;
            default:
            this.goto_dashboard();
                break;
        }
    }
    public void addtime_proccessed_delete_emp() throws SQLException{
        ArrayList<employee_bp> emp = model.EMPLOYEE_MODEL.get_all_selected_employee();
        this.view.DASHBOARD_VIEW.display_selected_employee(emp);
        String delete_des = this.view.DASHBOARD_VIEW.delete_selected_emp_des();
        if (delete_des.equals("p")){
            double time = this.view.DASHBOARD_VIEW.time_add_employee_selected();
            for (employee_bp employee:emp){
                boolean addtime_succ = model.EMPLOYEE_MODEL.employee_addtime_suc(employee.ID, time);
                if (!addtime_succ){
                    this.view.DASHBOARD_VIEW.time_add_fail_msg(employee.name);
                    this.Add_time();
                }
                else{
                    this.view.DASHBOARD_VIEW.time_add_suc_msg(employee.name);
                }
            }
            this.goto_dashboard();
            
        }else if(delete_des.equals("e")){
            this.Add_time();
        }
        else{
            try{
                model.EMPLOYEE_MODEL.get_all_selected_employee().remove(Integer.parseInt(delete_des)-1);
                this.addtime_proccessed_delete_emp();
            }catch(java.lang.IndexOutOfBoundsException e){
                this.view.DASHBOARD_VIEW.selected_employee_not_exist();
                this.addtime_proccessed_delete_emp();
            }catch(java.lang.NumberFormatException e){
                this.addtime_proccessed_delete_emp();
            }
            

        }
    }
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    
    public void Add_time(){//For ADDTIME
        try {
            ResultSet rs = this.model.EMPLOYEE_MODEL.get_all_employee();
            this.view.DASHBOARD_VIEW.display_employees(rs);
            String selected_emp_id = this.view.DASHBOARD_VIEW.select_emp();
            if (!isNumeric(selected_emp_id)){
                if(selected_emp_id.equals("")||selected_emp_id.equals(" ")||selected_emp_id.equals("  ")){
                    this.Add_time();
                }
                else if(selected_emp_id.equals("e")){
                    this.goto_dashboard();
                }
                else if(selected_emp_id.equals("p")){
                    this.addtime_proccessed_delete_emp();
                }  
                else{//if user is pinataka input
                    this.Add_time();
                }
            }
            else{
                boolean employee_add_in_arraylist_suc = model.EMPLOYEE_MODEL.employee_selected_AT(selected_emp_id);
                if (!employee_add_in_arraylist_suc){
                    this.view.DASHBOARD_VIEW.selected_emp_addtime_already_exist_msg();
                    this.Add_time();
                }
                else{
                    this.Add_time();
                }
            }
            


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void show_history() throws SQLException{
        this.view.DASHBOARD_VIEW.show_history(model.HISTORY_MODEL.get_all_history());
        this.goto_dashboard();
    }   

    public void get_salary_proccessed_delete_emp() throws SQLException{
        ArrayList<employee_bp> emp = model.EMPLOYEE_MODEL.get_all_selected_emp_get_salary();
        this.view.DASHBOARD_VIEW.display_selected_employee(emp);
        String delete_des = this.view.DASHBOARD_VIEW.delete_selected_emp_des();
        if (delete_des.equals("p")){
            for(employee_bp employee : emp){//emps in array
                ResultSet employee_salary  = this.model.SALARY_MODEL.get_individial_salary(employee.ID);
                if (employee_salary.next()) {
                    String name = employee_salary.getString("name");
                    double salary = employee_salary.getDouble("salary");
                    this.view.DASHBOARD_VIEW.show_emp_salary(name, salary);
                    this.model.SALARY_MODEL.clear_salary(employee.ID);
                    
                }

            }
            this.view.DASHBOARD_VIEW.Enter_exit();

            
        }else if(delete_des.equals("e")){
            this.get_salary();
        }
        else{
            try{
                model.EMPLOYEE_MODEL.get_all_selected_emp_get_salary().remove(Integer.parseInt(delete_des)-1);
                this.get_salary_proccessed_delete_emp();
            }catch(java.lang.IndexOutOfBoundsException e){
                this.view.DASHBOARD_VIEW.selected_employee_not_exist();
                this.get_salary_proccessed_delete_emp();
            }catch(java.lang.NumberFormatException e){
                this.get_salary_proccessed_delete_emp();
            }
            

        }
    }

    public void get_salary(){
        try (ResultSet rs = this.model.EMPLOYEE_MODEL.get_all_employee()) {
            this.view.DASHBOARD_VIEW.display_overall_salary_emps(this.model.SALARY_MODEL.get_all_employee_salary());
            this.view.DASHBOARD_VIEW.display_employees(rs);
            String selected_emp = this.view.DASHBOARD_VIEW.choose_emp_salary();
            if (!isNumeric(selected_emp)){
                if (selected_emp.equals("p")){
                    this.get_salary_proccessed_delete_emp();
                }
                else if(selected_emp.equals("e")){
                    this.goto_dashboard();
                }else{
                    this.Add_time();
                }
            }
            else{
                boolean emp_add_in_get_salary_arr_succ = this.model.EMPLOYEE_MODEL.employee_selected_get_salary(selected_emp);
                if (!emp_add_in_get_salary_arr_succ){
                    this.view.DASHBOARD_VIEW.selected_emp_addtime_already_exist_msg();
                    this.get_salary();
                }
                else{
                    this.get_salary();
                }
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
