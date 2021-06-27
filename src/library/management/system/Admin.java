/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;

/**
 *
 * @author KDHR
 */
public class Admin extends JFrame implements ActionListener {
 JLabel l1,l2,l3,l4,l5,l6,l7;
 JButton b1,b2,b3,b4,b5;   
	public static void main(String[] args) {
            new Admin().setVisible(true);
	}    
        
        public Admin(){
                super("IIC Smart Library v1.1   -Admin");
            setBounds(350, 35, 800, 700);
        setLayout(null);
       l1 = new JLabel("");
         ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/Admin.jpg"));
                Image i3 = i1.getImage().getScaledInstance(800, 700,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                l1 = new JLabel(i2);
                l1.setBounds(0, 0, 800, 700);
		add(l1);
   
                
       	l2 = new JLabel("Manage User Accounts");
	l2.setForeground(Color.RED);
	l2.setFont(new Font("Tahoma", Font.BOLD, 14));
	l2.setBounds(99, 234, 195, 26);
	l1.add(l2);
           
        b1 = new JButton("Accounts");
	b1.addActionListener(this);
	b1.setFont(new Font("Tahoma", Font.BOLD, 13));
	b1.setBounds(600, 234, 170, 26);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.RED);
	l1.add(b1);
        
        l3 = new JLabel("Manage Student Details");
	l3.setForeground(Color.RED);
	l3.setFont(new Font("Tahoma", Font.BOLD, 14));
	l3.setBounds(99, 269, 195, 26);
	l1.add(l3);
           
        b2 = new JButton("Student Details");
	b2.addActionListener(this);
	b2.setFont(new Font("Tahoma", Font.BOLD, 13));
	b2.setBounds(600, 269, 170, 26);
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.RED);
	l1.add(b2);
        
        l4 = new JLabel("Manage Book Details");
	l4.setForeground(Color.RED);
	l4.setFont(new Font("Tahoma", Font.BOLD, 14));
	l4.setBounds(99, 304, 190, 26);
	l1.add(l4);
           
        b3 = new JButton("Book Details");
	b3.addActionListener(this);
	b3.setFont(new Font("Tahoma", Font.BOLD, 13));
	b3.setBounds(600, 304, 170, 26);
        b3.setBackground(Color.WHITE);
        b3.setForeground(Color.RED);
	l1.add(b3);
        
        l5 = new JLabel("Manage Book Transactions");
	l5.setForeground(Color.RED);
	l5.setFont(new Font("Tahoma", Font.BOLD, 14));
	l5.setBounds(99, 339, 190, 26);
	l1.add(l5);
           
        b4 = new JButton("Transaction Records");
	b4.addActionListener(this);
	b4.setFont(new Font("Tahoma", Font.BOLD, 13));
	b4.setBounds(600, 339, 170, 26);
        b4.setBackground(Color.WHITE);
        b4.setForeground(Color.RED);
	l1.add(b4);
        
        l6 = new JLabel("Enter Into System");
	l6.setForeground(Color.RED);
	l6.setFont(new Font("Tahoma", Font.BOLD, 14));
	l6.setBounds(99, 374, 190, 26);
	l1.add(l6);
           
        b5 = new JButton("Smart IIC v1.1");
	b5.addActionListener(this);
	b5.setFont(new Font("Tahoma", Font.BOLD, 13));
	b5.setBounds(600, 374, 170, 26);
        b5.setBackground(Color.WHITE);
        b5.setForeground(Color.RED);
	l1.add(b5);
        
        }
            public void actionPerformed(ActionEvent ae){
                        if(ae.getSource() == b1){
                this.setVisible(false);
                new Manage_Accounts().setVisible(true);
            }
            if(ae.getSource() == b2){
                this.setVisible(false);
                new studentReport().setVisible(true);
            }
            if(ae.getSource() == b3){
                this.setVisible(false);
                new BookRecords().setVisible(true);
            }
            if(ae.getSource() == b4){
                this.setVisible(false);
                new IRReport().setVisible(true);
            }
            if(ae.getSource() == b5){
                this.setVisible(false);
                new adminEPanel().setVisible(true);
            
            }
            
            }
}
