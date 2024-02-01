/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author PCTRENDS
 */
public class passcode_model extends database.db {
    public passcode_model() throws ClassNotFoundException, SQLException{
    }

    
    public boolean passcode_attemp_succ(String passcode){
        String SF = String.format("select * from user_table where passcode = %s", passcode);
            try (ResultSet rs = Rstatement(SF)) {
                if (!rs.next()){
                    return false;
                }
                else{
                    return true;
                }
            } catch (SQLException e) {
                return false;
            }    
    }
    public boolean update_user_passcode_succ(String id,String passcode){
        String SF = String.format("update user_table set passcode = %s where ID = %s",passcode,id);
        System.out.println(SF);
        try {
            NRstatement(SF);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean create_user_succ(String username,String passcode){
        String statement = String.format("insert into user_table(name,passcode)values(%s,%s)",username,passcode);
        try {
            NRstatement(statement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    public ResultSet get_user() throws SQLException{
        return Rstatement("select * from user_table");
    }
}
