package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class dashboard_view extends javax.swing.JFrame {
    JPanel overall_container;
    JLabel text;
    public dashboard_view(){
        this.components_Setting();
        this.frameSetting();
    }
    protected void components_Setting(){
        //for main container
        this.overall_container = new JPanel();
        this.overall_container.setSize(1440, 900);
        this.overall_container.setBackground(new Color(34, 40, 49));
        this.overall_container.setLayout(new BorderLayout());
        //
        text = new JLabel("gjfdgfdg");
        this.overall_container.add(text);
        

    }

    protected void frameSetting(){
        super.setSize(1440, 900);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.add(overall_container);
    }

}
