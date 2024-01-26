package controller;
import view.view_bp;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.modelbp;
public class passcode_controller{
    model.modelbp model;
    view_bp view;
    public passcode_controller(view_bp view,modelbp model) throws SQLException{
        this.model = model;
        this.view = view;
        this.check_user_exist();
        this.okay_button_add_mouse_listener();

    }
    protected void check_user_exist() throws SQLException{
        ResultSet user_exist = model.PASSCODE_MODEL.get_user();
        if (!user_exist.next()){
            System.out.println("user_is_not exist");
        }
        else{
            this.view.PASSCODE_VIEW.setVisible(true);
        }
    }

    public void okay_button_add_mouse_listener(){
        this.view.PASSCODE_VIEW.get_okay_button().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                try {
                    okay_button_event();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void okay_button_event() throws SQLException{
       byte[] byte_array_passscode = this.view.PASSCODE_VIEW.get_passcode_tf().getBytes();
        String passcode = new String(byte_array_passscode);
       boolean passcode_attemp_suc = model.PASSCODE_MODEL.passcode_attemp_succ("1234");
       if (passcode_attemp_suc != true){
            this.view.DASHBOARD_VIEW.setVisible(false);
       }else{
            this.view.DASHBOARD_VIEW.setVisible(true);
            this.view.PASSCODE_VIEW.setVisible(false);
       }
    }
}
