/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import model.passcode_model;
import model.salary_model;
import model.user;
import view.passcode_view;
import view.dashboard_view;
import model.employee_model;
import model.history_model;
import model.modelbp;




import java.sql.SQLException;


import controller.passcode_controller;
import database.db;
import view.view_bp;

/**
 *
 * @author PCTRENDS
 */
public class main {
    /**
     * @param args
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        model.employee_model e =  new employee_model();

        passcode_view Passcode_view = new passcode_view();
        dashboard_view Dashboard_view = new dashboard_view();
        view_bp view = new view_bp(Passcode_view, Dashboard_view);

        model.user userbp;
        model.passcode_model Passcode_model = new passcode_model();
        model.employee_model Employee_model = new employee_model();
        model.salary_model Salary_model = new salary_model();
        model.history_model History_model = new history_model();

        modelbp model = new modelbp(Employee_model,Passcode_model,History_model,Salary_model);
        passcode_controller Passcode_controller = new passcode_controller(view,model);
        
    }
}

