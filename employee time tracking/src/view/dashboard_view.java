package view;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import model.employee_bp;
import model.history_model;
import model.modelbp;


public class dashboard_view{
    Scanner scanner;
    public dashboard_view(){
        scanner = new Scanner(System.in);
    }
    public String select_option_input(){
        try{
            System.out.print("Select_option: ");
            String option = scanner.nextLine();
            return option;
        } catch(Exception e){
            return null;
        }
            
    }
    public void emp_delete_msg(){
        System.out.println("Employee_Deleted");
    }
    public void option_list(){
        System.out.println("1) Add Time    2) History    3) Edit Employee_datail    4) Add_employee    5) delete_employee    6) Get Salary");
    }
    public void employee_not_exist_msg(){
        System.out.println("Employee is not Exist");
    }
    public String select_emp_delete(){
        System.out.print("Selected Employee to Delete || type (e) to cancel: ");
        return scanner.nextLine();
    }
    public void display_employees(ResultSet emp){
        try {
            while(emp.next()){
                int id = emp.getInt("ID");
                String name = emp.getString("name");
                System.out.println("Employee No. "+id);
                System.out.println("Name: "+ name);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public String select_emp(){
        try{
        System.out.print("Select_employee_add || type (p) to processed || (e) type for exit: ");
            String selected_emp = scanner.nextLine();
            return selected_emp;
        }catch(java.util.InputMismatchException e){
            System.out.println("invalid input");
            return "";
        }
    }
    public void show_history(ResultSet history) throws SQLException{
        while (history.next()) {
            String name = history.getString("name");
            String event = history.getString("event");
            Date date_event = history.getDate("date_event");
            // Create a SimpleDateFormat object with the desired date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM-dd-yyyy");
            // Format the date using the SimpleDateFormat
            String formattedDate = dateFormat.format(date_event);
            System.out.println(formattedDate + " || Name: " + name + " " + event);

        }
        System.out.print("Press Enter to Exit");
        scanner.nextLine();
    }
    public void display_overall_salary_emps(ResultSet salaries) throws SQLException{
        if(salaries.next()){
            System.out.println("                                    OVER_ALL_SALARY        P"+salaries.getString("salaries"));
            System.out.println();
        }
        
    }
    public void select_emp_datail_edit(){
        System.out.println("Select employee datail:");
    }
    public void employee_added_msg(){
        System.out.println("Employees Added Successfuly");
    }
    public void time_add_fail_msg(String name){
        System.out.println("Employee addtime failed "+name);
    }
    public void time_add_suc_msg(String name){
        System.out.println("Employee addtime successfuly "+name);
    }
    public void Employee_datail_updated(){
        System.out.println("Employee datail update successfuly");
    }
    public void Employee_deleted_messege(){
        System.out.println("Employee Deleted Successfuly");
    }
    public void add_emp_view(){

    }
    public int addtime(){
        System.out.print("hour_time : ");
            return scanner.nextInt();
    }
    public void time_added(){
        System.out.println("Employee time added");
    }
    public void time_add_faild(){
        System.out.println("Employee add_time fail");
    }
    public void enter_to_continue(){
        System.out.println("Press ENTER to continue");
        String temp = scanner.nextLine();
    }
    public void display_selected_employee(ArrayList<employee_bp> list_of_employee){ 
        for (model.employee_bp employee : list_of_employee){
            System.out.println(list_of_employee.indexOf(employee)+1+")"+employee.name);
        }
    }
    public String delete_selected_emp_des(){
        System.out.println("Type (p) to proccessed || Select id want to delete || Type (e) for exit");
        return scanner.nextLine();
    }
    public double time_add_employee_selected(){
        System.out.println("Time: ");
        return scanner.nextDouble();
    }
    public void selected_emp_addtime_already_exist_msg(){
        System.out.println("Employee Add Failed OR Employee is Already Exist");
    }
    public void selected_employee_not_exist(){
        System.out.println("Employee not exist in List");
    }
    public String choose_emp_salary(){
        try{
            System.out.print("Select_employee || type (p) to processed || (e) type for exit: ");
                String selected_emp = scanner.nextLine();
                return selected_emp;
            }catch(java.util.InputMismatchException e){
                System.out.println("invalid input");
                return "";
            }
    }
    public void show_emp_salary(String name, double salary){
        System.out.println(name+": P"+salary);
    }
    public void Enter_exit(){
        System.out.print("Type Enter to Exit: ");
        scanner.nextLine();
    }
    public String update_attributes_selection(){
        System.out.println("                             Select employee attribite to update");
        System.out.println("1)Name    2)Rate_per_hour   3)Hour_balance    4)Role");
        return scanner.nextLine();
    }
    public String Set_emp_name(){
        System.out.print("Set-Name: ");
        return scanner.nextLine();
    }
    public String Set_emp_rate_per_hour(){
        System.out.print("Set-Rate_per_hour: ");
        return scanner.nextLine();
    }
    public String Set_emp_role(){
        System.out.print("Set-Role: ");
        return scanner.nextLine();
    }
    public String Set_emp_hour_balance(){
        System.out.print("Set-hour_balance: ");
        return scanner.nextLine();
    }
    public String[] create_emp(){
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Type of Word: ");
        String role = scanner.nextLine();
        System.out.println("Rate_per_hour: ");
        String rate_per_hour = scanner.nextLine();
        return new String[]{name,role,rate_per_hour};
    }
    public void employee_created_suc_msg(){
        System.out.println("Employee created successfuly Type press (ENTER to continue");
        scanner.nextLine();
    }
}

