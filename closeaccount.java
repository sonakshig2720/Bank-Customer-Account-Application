
/* This program is for closing an account in bank application */

package project.delacc;
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

public class closeaccount
{
	//declaration of frame, buttons, textfield, panels and labels
	JFrame frame_closeaccount;
	
	JPanel p_accountnumber,p_submitbtn;
	
	JTextField tf_accountnumber;
	
	JLabel lbl_dis_accountnumber,lbl_dis_name,lbl_dis_fathersname,lbl_dis_mothersname,lbl_dis_gender,lbl_dis_dob,lbl_dis_mobilenumber,lbl_dis_emailid,lbl_dis_address;
	JPanel p_dis_details,p_dis_account,p_dis_name,p_dis_fathersname,p_dis_mothersname,p_dis_gender,p_dis_dob,p_dis_mobilenumber,p_dis_emailid,p_dis_address;
	
	JButton btn_close,yes,no,ok;
	
	String acc_status="";
	
	//declaration for connection and statemnet to connect to ojdbc
	Connection connection;
	Statement statement;
	
	Calendar calendar;
	
	Double amm;
	
	//constructor
	public closeaccount()
	{
		try 
		{
		    //connecting to ojdbc using username and password
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sonas123");
      		statement = connection.createStatement();
		} 
		//catching of sql exception when connection didn't happen
		catch (SQLException connectException) 
		{
		  System.out.println(connectException.getMessage());
		  System.out.println(connectException.getSQLState());
		  System.out.println(connectException.getErrorCode());
		  System.exit(1);
		}
		
		calendar = Calendar.getInstance();
		JPanel p_cal = new JPanel(); 
		p_cal.setLayout(new GridLayout(1,2));
		//to set the the date and day
		String day_of_week="";
		int cc=calendar.get(Calendar.DAY_OF_WEEK);
		if(cc==1)
			day_of_week="Sunday";
		else if(cc==2)
			day_of_week="Monday";
		else if(cc==3)
			day_of_week="Tuesday";
		else if(cc==4)
			day_of_week="Wednesday";
		else if(cc==5)
			day_of_week="Thursday";
		else if(cc==6)
			day_of_week="Friday";
		else if(cc==7)
			day_of_week="Saturday";

		String time=calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR)+"    "+day_of_week;
		JLabel cal=new JLabel(time);
		p_cal.add(cal);
		p_cal.setBounds(1700,75,750,20);
		
		//creation of panels to hold components of account details
		
		p_dis_details=new JPanel();
		p_dis_account=new JPanel();
		p_dis_name=new JPanel();
		p_dis_fathersname=new JPanel();
		p_dis_mothersname=new JPanel();
		p_dis_gender=new JPanel();
		p_dis_dob=new JPanel();
		p_dis_mobilenumber=new JPanel();
		p_dis_emailid=new JPanel();
		p_dis_address=new JPanel();
		
		//setting layout to the panel
		p_dis_details.setLayout(new GridLayout(1,1));
		p_dis_account.setLayout(new GridLayout(1,1));
		p_dis_name.setLayout(new GridLayout(1,1));
		p_dis_fathersname.setLayout(new GridLayout(1,1));
		p_dis_mothersname.setLayout(new GridLayout(1,1));
		p_dis_gender.setLayout(new GridLayout(1,1));
		p_dis_dob.setLayout(new GridLayout(1,1));
		p_dis_mobilenumber.setLayout(new GridLayout(1,1));
		p_dis_emailid.setLayout(new GridLayout(1,1));
		p_dis_address.setLayout(new GridLayout(1,1));
		
		//setting bounds to the panels
		p_dis_details.setBounds(400,200,1000,20);
		p_dis_account.setBounds(400,250,1000,20);
		p_dis_name.setBounds(400,300,1000,20);
		p_dis_fathersname.setBounds(400,350,1000,20);
		p_dis_mothersname.setBounds(400,400,1000,20);
		p_dis_gender.setBounds(400,450,1000,20);
		p_dis_dob.setBounds(400,500,1000,20);
		p_dis_mobilenumber.setBounds(400,550,1000,20);
		p_dis_emailid.setBounds(400,600,1000,20);
		p_dis_address.setBounds(400,650,1000,20);
		
		//creation a frame
		frame_closeaccount = new JFrame();
		
		//creation text field
		tf_accountnumber=new JTextField(35);
		
		//creation of submit button
		btn_close=new JButton("close");
		
		//creation of panels
		p_accountnumber = new JPanel();
		p_submitbtn = new JPanel();
		
		//creation of labels
		lbl_dis_accountnumber=new JLabel();
		lbl_dis_name=new JLabel();
		lbl_dis_fathersname=new JLabel();
		lbl_dis_mothersname=new JLabel();
		lbl_dis_gender=new JLabel();
		lbl_dis_dob=new JLabel();
		lbl_dis_mobilenumber=new JLabel();
		lbl_dis_emailid=new JLabel();
		lbl_dis_address=new JLabel();
		
		//adding labels to panels
		p_dis_details.add(new JLabel("Your Details:"));
		p_dis_account.add(new JLabel("Account Number"));
		p_dis_name.add(new JLabel("Name"));
		p_dis_fathersname.add(new JLabel("Father's Name"));
		p_dis_mothersname.add(new JLabel("Mother's Name"));
		p_dis_gender.add(new JLabel("Gender"));
		p_dis_dob.add(new JLabel("Date of Birth"));
		p_dis_mobilenumber.add(new JLabel("Mobile Number"));
		p_dis_emailid.add(new JLabel("Email ID"));
		p_dis_address.add(new JLabel("Address"));
		
		p_dis_account.add(lbl_dis_accountnumber);
		p_dis_name.add(lbl_dis_name);
		p_dis_fathersname.add(lbl_dis_fathersname);
		p_dis_mothersname.add(lbl_dis_mothersname);
		p_dis_gender.add(lbl_dis_gender);
		p_dis_dob.add(lbl_dis_dob);
		p_dis_mobilenumber.add(lbl_dis_mobilenumber);
		p_dis_emailid.add(lbl_dis_emailid);
		p_dis_address.add(lbl_dis_address);
										  
		//setting grid layout to panel
		p_accountnumber.setLayout(new GridLayout(1,1));
		p_accountnumber.add(new JLabel("Account Number: "));
		p_accountnumber.add(tf_accountnumber);
		
		p_submitbtn.setLayout(new GridLayout(1,1));
		p_submitbtn.add(btn_close);
		
		//setting bound to panels
		p_accountnumber.setBounds(400,100,1000,20);
		p_submitbtn.setBounds(800,800,100,20);
		
		//adding panels to frame
		frame_closeaccount.add(p_cal);
		frame_closeaccount.add(p_accountnumber);
		frame_closeaccount.add(p_submitbtn);
		
		frame_closeaccount.add(p_dis_details);
		frame_closeaccount.add(p_dis_account);
		frame_closeaccount.add(p_dis_name);
		frame_closeaccount.add(p_dis_fathersname);
		frame_closeaccount.add(p_dis_mothersname);
		frame_closeaccount.add(p_dis_gender);
		frame_closeaccount.add(p_dis_dob);
		frame_closeaccount.add(p_dis_mobilenumber);
		frame_closeaccount.add(p_dis_emailid);
		frame_closeaccount.add(p_dis_address);
		
		
		frame_closeaccount.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			frame_closeaccount.dispose();                               //closing of frame
		  }
		});
		
		frame_closeaccount.setExtendedState(frame_closeaccount.getExtendedState() | frame_closeaccount.MAXIMIZED_BOTH);         //for maximizing window
				
		//adding keylistener to account number textfield so that it doesn't take alphabets and length doesn't exceed 10
		tf_accountnumber.addKeyListener(new KeyAdapter() 
				{
					public void keyPressed(KeyEvent ke) 
					{
						
            			int len_accountnumber = tf_accountnumber.getText().length();
            			if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' )  
						{
               				tf_accountnumber.setEditable(true);
            			} 
						else if( ke.getKeyChar()==8)
						{
									tf_accountnumber.setEditable(true);
						} 
						else 
						{
									tf_accountnumber.setEditable(false);
						}
						if (len_accountnumber>9)
						{
									tf_accountnumber.setEditable(false);
						}
						if(len_accountnumber>9 && ke.getKeyChar()==8) 
						{
									tf_accountnumber.setEditable(true);
						}
						//if enter is pressed
						if(ke.getKeyChar()==10)
						{
							//if length is 0
							if(tf_accountnumber.getText().length()==0)
							{
														lbl_dis_accountnumber.setText("");
														lbl_dis_name.setText("");
														lbl_dis_fathersname.setText("");
														  lbl_dis_mothersname.setText("");
														  lbl_dis_gender.setText("");
														  lbl_dis_dob.setText("");
														  lbl_dis_mobilenumber.setText("");
														  lbl_dis_emailid.setText("");
														  lbl_dis_address.setText("");
														  
														//dialog box for error
														JDialog dg_error=new JDialog(frame_closeaccount,"Error",true);
															dg_error.add(new JLabel("Please enter account number"));
															ok = new JButton ("ok");
															dg_error.add(ok);
															
															dg_error.setLayout( new FlowLayout() ); 
															ok.addActionListener ( new ActionListener()  
																{  
																		public void actionPerformed( ActionEvent e )  
																		{ 
																			dg_error.setVisible(false);
																 
																		}
																});
																dg_error.setSize(300,100);
																dg_error.setLocationRelativeTo(null);
															
															dg_error.setVisible(true);
															ok.requestFocus();
							}
							//if length >0 
							else
							{
								try
								{
									//extracting the data of the account number and displaying the details
									String accno=tf_accountnumber.getText().toString();
										String sql = "select * from newaccount where accountnumber="+accno;
										ResultSet rs = statement.executeQuery(sql);
										  int count=0;
										  while(rs.next())
										  {
											  count++;
											  Long lng_accountnumber=rs.getLong("accountnumber");
											  String str_name=rs.getString("name");
											  String str_fathersname=rs.getString("fathersname");
											  String str_mothersname=rs.getString("mothersname");
											  String str_gender=rs.getString("gender");
											  String str_dob=rs.getString("dob").substring(0,11);
											  Long lng_mobilenumber=rs.getLong("mobilenumber");
											  String str_emailid=rs.getString("emailid");
											  String str_address=rs.getString("address");
											  acc_status=rs.getString("accountstatus");
											  amm=rs.getDouble("amount");
											  
											  //if account is open
											  if(acc_status.equals("o"))
											  {
												  lbl_dis_accountnumber.setText(lng_accountnumber.toString());
												  lbl_dis_name.setText(str_name);
												  lbl_dis_fathersname.setText(str_fathersname);
												  lbl_dis_mothersname.setText(str_mothersname);
												  lbl_dis_gender.setText(str_gender);
												  lbl_dis_dob.setText(str_dob);
												  lbl_dis_mobilenumber.setText(lng_mobilenumber.toString());
												  lbl_dis_emailid.setText(str_emailid);
												  lbl_dis_address.setText(str_address);
											  }
											  //if account is closed
											  else if(acc_status.equals("c"))
											{
												lbl_dis_accountnumber.setText("");
															lbl_dis_name.setText("");
															lbl_dis_fathersname.setText("");
															  lbl_dis_mothersname.setText("");
															  lbl_dis_gender.setText("");
															  lbl_dis_dob.setText("");
															  lbl_dis_mobilenumber.setText("");
															  lbl_dis_emailid.setText("");
															  lbl_dis_address.setText("");
															
															JDialog dg_accountclosed=new JDialog(frame_closeaccount,"Account closed",true);
																dg_accountclosed.add(new JLabel("Your account is already closed"));
																ok = new JButton ("ok");
																dg_accountclosed.add(ok);
																
																dg_accountclosed.setLayout( new FlowLayout() ); 
																
																ok.addActionListener ( new ActionListener()  
																	{  
																			public void actionPerformed( ActionEvent e )  
																			{ 
																				dg_accountclosed.setVisible(false);
																				tf_accountnumber.requestFocus();
																	 
																			}
																	});
																	dg_accountclosed.setSize(300,100);
																	dg_accountclosed.setLocationRelativeTo(null);
																	
																dg_accountclosed.setVisible(true);
																ok.requestFocus();
																break;
											}
											  btn_close.requestFocus();
										  }
										  rs.close();
										  //if account number is not valid
										  if(count==0)
											{
															lbl_dis_accountnumber.setText("");
															lbl_dis_name.setText("");
															lbl_dis_fathersname.setText("");
															  lbl_dis_mothersname.setText("");
															  lbl_dis_gender.setText("");
															  lbl_dis_dob.setText("");
															  lbl_dis_mobilenumber.setText("");
															  lbl_dis_emailid.setText("");
															  lbl_dis_address.setText("");
															  
															
															JDialog dg_error=new JDialog(frame_closeaccount,"Invalid account number",true);
																dg_error.add(new JLabel("Please enter valid account number"));
																ok = new JButton ("ok");
																dg_error.add(ok);
																
																dg_error.setLayout( new FlowLayout() ); 
																
																ok.addActionListener ( new ActionListener()  
																	{  
																			public void actionPerformed( ActionEvent e )  
																			{ 
																				dg_error.setVisible(false);
																	
																	 
																			}
																	});
																	dg_error.setSize(300,100);
																	dg_error.setLocationRelativeTo(null);
																	
																dg_error.setVisible(true);
																ok.requestFocus();
							
											}
								}
								catch(Exception eeee)
								{
									
								}
							}
						}
					}
				});
												
				//when close button is clicked
				btn_close.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ett) 
					{
						//if account is closed
						if(acc_status.equals("c"))
						{
															lbl_dis_accountnumber.setText("");
															lbl_dis_name.setText("");
															lbl_dis_fathersname.setText("");
															  lbl_dis_mothersname.setText("");
															  lbl_dis_gender.setText("");
															  lbl_dis_dob.setText("");
															  lbl_dis_mobilenumber.setText("");
															  lbl_dis_emailid.setText("");
															  lbl_dis_address.setText("");
															  
															//dialog box for account is already closed
															JDialog dg_accountclosed=new JDialog(frame_closeaccount,"Account closed",true);
																dg_accountclosed.add(new JLabel("Your account is already closed"));
																ok = new JButton ("ok");
																dg_accountclosed.add(ok);
																
																dg_accountclosed.setLayout( new FlowLayout() ); 
																
																ok.addActionListener ( new ActionListener()  
																	{  
																			public void actionPerformed( ActionEvent e )  
																			{ 
																				dg_accountclosed.setVisible(false);
																				tf_accountnumber.requestFocus();
																	 
																			}
																	});
																	dg_accountclosed.setSize(300,100);
																	dg_accountclosed.setLocationRelativeTo(null);
																	
																dg_accountclosed.setVisible(true);
																ok.requestFocus();
																
						}
						//if account is open
						else
						{
							if(amm>0)
							{
								JDialog dg_confirmaton=new JDialog(frame_closeaccount,"Confirmation",true);
								dg_confirmaton.add(new JLabel("You hava a balance of "+amm+" Are you sure yout want to close account?"));
								yes = new JButton ("YES");
								no = new JButton ("NO");
								dg_confirmaton.add(yes);
								dg_confirmaton.add(no);
								
								dg_confirmaton.setLayout( new FlowLayout() );  
								//if yes is pressed
								yes.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    {  
												try{
													
													String accno=tf_accountnumber.getText().toString();
													//upadating data of the accountumber
														String sql1 = "UPDATE newaccount set accountstatus='c' where accountnumber="+accno;
														statement.executeUpdate(sql1);
														String sql2 = "UPDATE newaccount set amount=0 where accountnumber="+accno;
														statement.executeUpdate(sql2);
														//setting values to null
														tf_accountnumber.setText("");
														
														lbl_dis_accountnumber.setText("");
															lbl_dis_name.setText("");
															lbl_dis_fathersname.setText("");
															  lbl_dis_mothersname.setText("");
															  lbl_dis_gender.setText("");
															  lbl_dis_dob.setText("");
															  lbl_dis_mobilenumber.setText("");
															  lbl_dis_emailid.setText("");
															  lbl_dis_address.setText("");
															  
														dg_confirmaton.setVisible(false);
													
													tf_accountnumber.requestFocus();
												}
												catch(Exception eeee)
													{
														
													}
													try
													{
														connection.commit();
													}
													catch(Exception t)
													{
													}
        								    }  
       								 });   
									 
									no.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    { 
												dg_confirmaton.setVisible(false);
											}
									});
    	    							
        							dg_confirmaton.setSize(300,100);
									dg_confirmaton.setLocationRelativeTo(null);
   								dg_confirmaton.setVisible(true);
								yes.requestFocus();
							}
							else
							{
								//confirmation for closing the account
								JDialog dg_confirmaton=new JDialog(frame_closeaccount,"Confirmation",true);
								dg_confirmaton.add(new JLabel("Are you sure you want to close the account?"));
								yes = new JButton ("YES");
								no = new JButton ("NO");
								dg_confirmaton.add(yes);
								dg_confirmaton.add(no);
								
								dg_confirmaton.setLayout( new FlowLayout() );  
								//if yes is pressed
								yes.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    {  
												try{
													
													String accno=tf_accountnumber.getText().toString();
													//upadating data of the accountumber
														String sql1 = "UPDATE newaccount set accountstatus='c' where accountnumber="+accno;
														statement.executeUpdate(sql1);
													//setting values to null
														tf_accountnumber.setText("");
														
														lbl_dis_accountnumber.setText("");
															lbl_dis_name.setText("");
															lbl_dis_fathersname.setText("");
															  lbl_dis_mothersname.setText("");
															  lbl_dis_gender.setText("");
															  lbl_dis_dob.setText("");
															  lbl_dis_mobilenumber.setText("");
															  lbl_dis_emailid.setText("");
															  lbl_dis_address.setText("");
															  
														dg_confirmaton.setVisible(false);
													
													tf_accountnumber.requestFocus();
												}
												catch(Exception eeee)
													{
														
													}
													try
													{
														connection.commit();
													}
													catch(Exception t)
													{
													}
        								    }  
       								 });   
									 
									no.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    { 
												dg_confirmaton.setVisible(false);
											}
									});
    	    							
        							dg_confirmaton.setSize(500,200);
									dg_confirmaton.setLocationRelativeTo(null);
   								dg_confirmaton.setVisible(true);
								yes.requestFocus();
							}
						}
					}
				});
			//setting title to frame and making the frame visible
		 frame_closeaccount.setLayout(null);
		 frame_closeaccount.setTitle("Close Account");
		frame_closeaccount.setVisible(true);
		
	}
}