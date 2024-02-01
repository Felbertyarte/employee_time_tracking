/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.Scanner;

import model.user;
/**
 *
 * @author PCTRENDS
 */
public class passcode_view {
    Scanner scanner;
    public passcode_view() {
        this.scanner = new Scanner(System.in);
    }
    public String passcode_attempt(){
        try{
            System.out.println("Enter Your Passcode: ");
            return scanner.nextLine();
        }catch(Exception e){
            System.out.println("Error reading passcode input. Please try again.");
            scanner.nextLine();  // Consume the extra newline character
            return passcode_attempt();  // Retry passcode entry
        }
    }
    public void passcode_fail_messege(){
        System.out.println("incorrect passcode");
    }
}
