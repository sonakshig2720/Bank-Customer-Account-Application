
/*This program is startscreen of the bank application*/


package project.welcome;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.sql.*;
import java.util.*;
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.embed.swing.JFXPanel;
import project.newaccount.*;
import project.modmn.*;
import project.modei.*;
import project.modadd.*;
import project.delacc.*;
import project.dep.*;
import project.withd.*;
import project.transdet.*;


public class startscreen
{
	
	JFrame frame_startscreen;
	JLabel lbl_welcome;
	
	JMenu menu,sbm_modify,sbm_transactions;  
    JMenuItem mi_create,mi_mobile,mi_email,mi_address,mi_close,mi_deposit,mi_withdraw,mi_transactiondetails; 
	
	//constructor
	public void startscreen1(String[] args)
	{
		frame_startscreen = new JFrame();
		lbl_welcome=new JLabel("WELCOME",SwingConstants.CENTER);
		lbl_welcome.setFont(new Font("Serif", Font.PLAIN, 150));
		
		frame_startscreen.addWindowListener(new WindowAdapter()
		{
		  public void windowClosing(WindowEvent e) 
		  {
			frame_startscreen.dispose();                         //closing of frame_startscreen
		  }
		});

		frame_startscreen.add(lbl_welcome);
		frame_startscreen.setExtendedState(frame_startscreen.getExtendedState() | frame_startscreen.MAXIMIZED_BOTH);     //for maximizing window
		
		//creaton of menubar
		JMenuBar mb=new JMenuBar();
        menu=new JMenu("Menu");  
		sbm_modify=new JMenu("Modify Account Details"); 
		sbm_transactions=new JMenu("Transactions"); 
        
		//creation of menu items
        mi_create=new JMenuItem("Create New Account");  
        mi_mobile=new JMenuItem("Mobile number");
		mi_email=new JMenuItem("EmailId");
		mi_address=new JMenuItem("Address");
		mi_close=new JMenuItem("Close Account");
		mi_deposit=new JMenuItem("Deposit");
		mi_withdraw=new JMenuItem("Withdraw");
		mi_transactiondetails=new JMenuItem("Transaction Details");
		
		//adding of menu items to menu
        menu.add(mi_create);
		menu.add(sbm_modify);
		  
		sbm_modify.add(mi_mobile);
		sbm_modify.add(mi_email);
		sbm_modify.add(mi_address);
		menu.add(sbm_transactions);
		sbm_transactions.add(mi_deposit);
		sbm_transactions.add(mi_withdraw);
		menu.add(mi_transactiondetails);
		menu.add(mi_close);
			
        mb.add(menu); 
		
        frame_startscreen.setJMenuBar(mb);  
		  
		  
		mi_create.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			  NewAccount acc = new NewAccount();                   

				acc.buildGUI(args);                                //new account frame  
			}
		  });
		  
		  
		mi_mobile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				modifymn m=new modifymn();                        //modify mobilenumber frame
				
			}
		  });
		  
		mi_email.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				modifyei m=new modifyei();                       //modify emailid frame
			}
		  });
		  
		mi_address.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				modifyadd m=new modifyadd();                     //modify address frame
			}
		  });
		  
		   
		mi_close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				closeaccount m=new closeaccount();                //closing account frame
			}
		  });
		  
		mi_deposit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				deposit m=new deposit();                          //deposting amount frame
			}
		  });
		  
		mi_withdraw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				withdraw m=new withdraw();                       //withdrawing amount frame
			}
		  });
		  
		  mi_transactiondetails.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				transactiondetails t=new transactiondetails();                      //shwing transaction details frame
			}
		  });
		  
		frame_startscreen.setTitle("Welcome");
		frame_startscreen.setVisible(true);
	
	}
	//main function
	public static void main(String[] args) 
	{
		//calling of constructor
		startscreen s=new startscreen();
		s.startscreen1(args);
	}
}