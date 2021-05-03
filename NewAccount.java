
/* This program is for opening a new account in bank application */

package project.newaccount;
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


public class NewAccount 
{
	//creation of frame
	JFrame frame_newaccount;
	
	//accountnumber
	static long accountnumbernum;
	
	//declaration of buttons
	JButton btn_submit,btn_dgb_yes,btn_dgb_no,btn_clear,btn_exit,b;
	
	//declaration of textfields
	JTextField tf_name,tf_fathersname,tf_mothersname,tf_dob_year,tf_mobilenumber,tf_emailid;
	
	//declaration of textareas
	JTextArea tf_address,display;
	
	//declaration for connection and statemnet to connect to ojdbc
	Connection connection;
	Statement statement;
	
	//declaration dialog box
	JDialog dg_focus;
	
	//declaration of calendar
	Calendar calendar;
	
	//constructor of newaccount
	public NewAccount() 
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
	}
	//end of constructor
	
	//global variables
	int MAX_VALID_YR = 9999; 
    int MIN_VALID_YR = 0000; 
  
    //function for checking if year is a leap year or ot
    boolean isLeap(int year) 
    {  
        return (((year % 4 == 0) &&  
                 (year % 100 != 0)) ||  
                 (year % 400 == 0)); 
    } 
  
    //function for checking if the given date is valid or not
    boolean isValidDate(int d, String mo,int y) 
    { 
		int m=0;
		if(mo=="January")
			m=1;
		else if(mo=="February")
			m=2;
		else if(mo=="March")
			m=3;
		else if(mo=="April")
			m=4;
		else if(mo=="May")
			m=5;
		else if(mo=="June")
			m=6;
		else if(mo=="July")
			m=7;
		else if(mo=="August")
			m=8;
		else if(mo=="September")
			m=9;
		else if(mo=="October")
			m=10;
		else if(mo=="November")
			m=11;
		else if(mo=="December")
			m=12;
		
        if (y > MAX_VALID_YR ||  
            y < MIN_VALID_YR) 
            return false; 
        if (m < 1 || m > 12) 
            return false; 
        if (d < 1 || d > 31) 
            return false; 
  
        if (m == 2)  
        { 
            if (isLeap(y)) 
                return (d <= 29); 
            else
                return (d <= 28); 
        } 
        if (m == 4 || m == 6 ||  
            m == 9 || m == 11) 
            return (d <= 30); 
        return true; 
    } 
	
	//method buildGUI
	public void buildGUI(String args[]) 
	{	
		//creation of frame
		frame_newaccount = new JFrame();
		
		frame_newaccount.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			frame_newaccount.dispose();                           //closing of frame
			
		  }
		});

		//to display current date and day
		calendar = Calendar.getInstance();

		frame_newaccount.setExtendedState(frame_newaccount.getExtendedState() | frame_newaccount.MAXIMIZED_BOTH);      //for maximizing window

		//combobox for gender
		JComboBox cb_gender=new JComboBox();
		cb_gender.addItem(" ");
		cb_gender.addItem("M");
		cb_gender.addItem("F");
		
		//string array to hold months
		String month[]={" ","January","February","March","April","May","June","July","August","September","October","November","December"};
		//string array to hold days
		String day[]={" ","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		
		//combobox for day
		JComboBox cb_day=new JComboBox(day);
		
		//combobox for months
		JComboBox cb_month=new JComboBox(month);

		btn_submit=new JButton("Submit");
		btn_clear=new JButton("Clear");
		btn_exit=new JButton("Exit");
		
		tf_name=new JTextField(35);
		tf_fathersname=new JTextField(35);       
		tf_mothersname=new JTextField(35);
		tf_dob_year=new JTextField(4);          //dob year
		tf_mobilenumber=new JTextField(35);
		tf_emailid=new JTextField(35);
		tf_address=new JTextArea(10,100);
		display=new JTextArea(500,50);

		display.setEditable(false);
		
        JPanel p_cal = new JPanel();              //to display date and day at right top corner
		
		//panels for holding the components
        JPanel p_name = new JPanel();
		JPanel p_fathersname = new JPanel();
		JPanel p_mothersname = new JPanel();
		JPanel p_gender = new JPanel();
		JPanel p_dob = new JPanel();
		JPanel p_dob2=new JPanel();
		JPanel p_mobilenumber = new JPanel();
		JPanel p_emailid = new JPanel();
		JPanel p_address = new JPanel();
		JPanel p_submit_clear_exit = new JPanel();
		JPanel p_display = new JPanel();
		
		//setting layout for the panels
		p_cal.setLayout(new GridLayout(1,2));
		p_name.setLayout(new GridLayout(1,2));
		p_fathersname.setLayout(new GridLayout(1,2));
		p_mothersname.setLayout(new GridLayout(1,2));
		p_gender.setLayout(new GridLayout(1,2));
		p_dob.setLayout(new GridLayout(1,1));
		p_dob2.setLayout(new GridLayout(1,1));
		p_mobilenumber.setLayout(new GridLayout(1,2));
		p_emailid.setLayout(new GridLayout(1,2));
		p_address.setLayout(new GridLayout(1,2));
		p_submit_clear_exit.setLayout(new GridLayout(1,1));
		p_display.setLayout(new GridLayout(1,1));
		
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
		
		//adding labels, text field, combobox to the panels
		p_name.add(new JLabel("Full Name  "));
		p_name.add(tf_name);
		p_fathersname.add(new JLabel("Father's Name  "));
		p_fathersname.add(tf_fathersname);
		p_mothersname.add(new JLabel("Mother's Name  "));
		p_mothersname.add(tf_mothersname);
		p_gender.add(new JLabel("Gender  "));
		p_gender.add(cb_gender);
		p_dob.add(new JLabel("Date of birth(dd-mm-yyyy) "));
		p_dob2.add(cb_day);
		p_dob2.add(cb_month);
		p_dob2.add(tf_dob_year);
		p_mobilenumber.add(new JLabel("Mobile number  "));
		p_mobilenumber.add(tf_mobilenumber);
		p_emailid.add(new JLabel("E-Mail Id  "));
		p_emailid.add(tf_emailid);
		p_address.add(new JLabel("Address"));
		p_address.add(tf_address);

		//adding buttons to panels
		p_submit_clear_exit.add(btn_submit);
		p_submit_clear_exit.add(btn_clear);
		p_submit_clear_exit.add(btn_exit);

		p_display.add(display);

		//setting bounds to the panels
		p_cal.setBounds(1700,75,750,20);
		p_name.setBounds(200,150,1000,20);
		p_fathersname.setBounds(200,200,1000,20);
		p_mothersname.setBounds(200,250,1000,20);
		p_gender.setBounds(200,300,1000,20);
		p_dob.setBounds(200,350,500,20);
		p_dob2.setBounds(700,350,500,20);
		p_mobilenumber.setBounds(200,400,1000,20);
		p_emailid.setBounds(200,450,1000,20);
		p_address.setBounds(200,500,1000,150);
       	p_submit_clear_exit.setBounds(200,700,1000,20);
		p_display.setBounds(500,800,750,175);  		
		
		//adding layout to the frame
		frame_newaccount.setLayout(null);

		//adding panels to the frame
		frame_newaccount.add(p_cal);
		frame_newaccount.add(p_name);
		frame_newaccount.add(p_fathersname);
		frame_newaccount.add(p_mothersname);
		frame_newaccount.add(p_gender);
		frame_newaccount.add(p_dob);
		frame_newaccount.add(p_dob2);
		frame_newaccount.add(p_mobilenumber);
		frame_newaccount.add(p_emailid);
		frame_newaccount.add(p_address);
		frame_newaccount.add(p_submit_clear_exit);
		frame_newaccount.add(p_display);
		
		//when clear button is presssed
		btn_clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//dailog box to confirm whether to clear data or not
				JDialog dg_confirmation=new JDialog(frame_newaccount,"Confirmation",true);
				dg_confirmation.add(new JLabel("Are you sure you want to clear the data?"));
				btn_dgb_yes = new JButton ("YES");
				btn_dgb_no = new JButton ("NO");
				dg_confirmation.add(btn_dgb_yes);
				dg_confirmation.add(btn_dgb_no);
								
				dg_confirmation.setLayout( new FlowLayout() );  
	
				//when yes button is pressed
				btn_dgb_yes.addActionListener ( new ActionListener()  
				{  
					public void actionPerformed( ActionEvent e )  
					{  
												//setting all to null
												tf_name.setText("");
												tf_fathersname.setText("");
												tf_mothersname.setText("");
												cb_gender.setSelectedItem(" ");
												cb_day.setSelectedItem(" ");
												cb_month.setSelectedItem(" ");
												tf_dob_year.setText("");
												tf_mobilenumber.setText("");
												tf_emailid.setText("");
												tf_address.setText("");
												display.setText("");
												dg_confirmation.setVisible(false);	
        			}
       			});   
								
				//when no button is pressed
				btn_dgb_no.addActionListener ( new ActionListener()  
				{  
								            public void actionPerformed( ActionEvent e )  
							        	    { 
												dg_confirmation.setVisible(false);
									 
											}
				});
        		dg_confirmation.setSize(300,100);
				dg_confirmation.setLocationRelativeTo(null);
   				dg_confirmation.setVisible(true);
				btn_dgb_yes.requestFocus();
			}
		});

		//when exit button is pressed
		btn_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//dailog box to confirm whether to exit data or not
				JDialog dg_confirmation=new JDialog(frame_newaccount,"Confirmation",true);
								dg_confirmation.add(new JLabel("    Are you sure you want to exit?    "));
								btn_dgb_yes = new JButton ("YES");
								btn_dgb_no = new JButton ("NO");
								dg_confirmation.add(btn_dgb_yes);
								dg_confirmation.add(btn_dgb_no);
								
								dg_confirmation.setLayout( new FlowLayout() );  
								
								//when yes button is pressed
								btn_dgb_yes.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    {  
													dg_confirmation.setVisible(false);
													//closing of the frame
													frame_newaccount.dispose();
												
        								    }  
       								 });   
									 
									//when no button is pressed
									btn_dgb_no.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    { 
												dg_confirmation.setVisible(false);
									 
											}
									});
    	    							
        							dg_confirmation.setSize(500,100);
									dg_confirmation.setLocationRelativeTo(null);
   								dg_confirmation.setVisible(true);
								btn_dgb_yes.requestFocus();
				
				
			}
		});
		
		
		//start of submit class
		class submit
		{
            void submitbutton() 
            { 
                try 
				{
					//to take count of accounts in database to initialize account number
					int count=0;
					
					//resultset 
					ResultSet rs2 = statement.executeQuery("select * from NewAccount");
					
					while(rs2.next())  
							count++;
					rs2.close();
					
					//if there is no data in the table
					if(count==0)
					{
						accountnumbernum=1;
					}
					//if there is data in the table 
					else
					{
						Long data=Long.parseLong("0");
						String query = "select max(accountnumber) from NewAccount";
						ResultSet rs = statement.executeQuery(query);
						while(rs.next())
						{
							
								//System.out.println(rs.getLong("max(accountnumber)"));	
								data=rs.getLong("max(accountnumber)");
						}
						
						//increase in account number to have unique accout number
						accountnumbernum=data+1;
						rs.close();
					}
					display.setText("");
					
					//taking values from the text fields
				    String str_name=tf_name.getText().toString();
					String str_fathersname=tf_fathersname.getText().toString();
					String str_mothersname=tf_mothersname.getText().toString();
					String str_gender=cb_gender.getSelectedItem().toString();
					String str_mon=(cb_month.getSelectedItem().toString()).substring(0,3);
					String str_year=(tf_dob_year.getText().toString()).substring(2,4);
					String str_dob=cb_day.getSelectedItem().toString()+"-"+str_mon+"-"+str_year;
					try
					{
						long lng_mobilenumber=Long.parseLong(tf_mobilenumber.getText().toString());
					}
					catch(Exception eqqqqq)
					{
						display.setText("");
					}
					String str_emailid=tf_emailid.getText().toString();
					String str_address=tf_address.getText().toString();
					 
					//length of mobile number
            		int len_mobilenumber = tf_mobilenumber.getText().length();
							
					//length of dob year
            		int len_dobyear = tf_dob_year.getText().length();

					//if any of the text field is empty and submit button is clicked it displays the error to fill the data in the error box below
					if(str_name.isEmpty() ||  str_fathersname.isEmpty() ||  str_mothersname.isEmpty() ||  str_gender==" "  ||  cb_day.getSelectedItem().toString()==" " ||  cb_month.getSelectedItem().toString()==" "|| tf_dob_year.getText().toString().isEmpty() || len_mobilenumber==0 ||  str_emailid.isEmpty() ||  str_address.isEmpty() ) 
					{
						if(str_name.isEmpty())  
						{
							display.append("Please fill the details - Full Name\n");
						}
						if(str_fathersname.isEmpty())
						{
							display.append("Please fill the details - Father's name\n");
						}
						if(str_mothersname.isEmpty())
						{
							display.append("Please fill the details - Mother's name\n");
						}
						if(str_gender==" " )
						{
							display.append("Please fill the details - Gender\n");
						}
						if(cb_day.getSelectedItem().toString()==" " )
						{
							display.append("Please fill the details - dob(day)\n");
						}
						if(cb_month.getSelectedItem().toString()==" " )
						{
							display.append("Please fill the details - dob(month)\n");
						}
						if(tf_dob_year.getText().toString().isEmpty())
						{
							display.append("Please fill the details - dob(year)\n");
						}
						if(len_mobilenumber==0)
						{
							display.append("Please fill the details - mobile number\n");
						}
						if(str_emailid.isEmpty())
						{
							display.append("Please fill the details - email id\n");
						}
						if( str_address.isEmpty())
						{
							display.append("Please fill the details - address\n");
						}
					}
					//if all the text fields are filled
					else
					{ 
						//if email contains '@' and '.com' and length of mobile number is 10 and length of dob-year is 4 and is a valid year
						if(tf_emailid.getText().toString().contains("@")  && (tf_emailid.getText().toString().contains(".com") || tf_emailid.getText().toString().contains(".COM"))  
						&& len_mobilenumber==10  
						&& len_dobyear==4  
						&& isValidDate(Integer.parseInt(cb_day.getSelectedItem().toString()),cb_month.getSelectedItem().toString(),Integer.parseInt(tf_dob_year.getText().toString())))
						
						{ 
							long lng_mobilenumber=Long.parseLong(tf_mobilenumber.getText().toString());
							//dialog box to confirm to submit the data or not
								JDialog dg_confirmation=new JDialog(frame_newaccount,"Confirmation",true);
								dg_confirmation.add(new JLabel("Are you sure you want to submit the data?"));
								btn_dgb_yes = new JButton ("YES");
								btn_dgb_no = new JButton ("NO");
								dg_confirmation.add(btn_dgb_yes);
								dg_confirmation.add(btn_dgb_no);
								
								dg_confirmation.setLayout( new FlowLayout() );  
								
								btn_dgb_yes.requestFocus();
								
								//when yes button is clicked and he wants to insertthe data
								btn_dgb_yes.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    {  
												dg_confirmation.setVisible(false);
												
												try
												{
												//to collect data and insert into the table	
												PreparedStatement pst = connection.prepareStatement("insert into newaccount(accountnumber,name,fathersname,mothersname,gender,dob,mobilenumber,emailid,address,accountstatus,amount) values(?,?,?,?,?,?,?,?,?,?,?)",statement.RETURN_GENERATED_KEYS);
								
												pst.setLong(1,accountnumbernum);
												pst.setString(2,str_name);
												pst.setString(3,str_fathersname);
												pst.setString(4,str_mothersname);
												pst.setString(5,str_gender);
												pst.setString(6,str_dob);
												pst.setLong(7,lng_mobilenumber);
												pst.setString(8,str_emailid);
												pst.setString(9,str_address);
												pst.setString(10,"o");
												pst.setDouble(11,0.0);

												int rowAffected = pst.executeUpdate();
												
												//if there is a row
												if(rowAffected==1)
												{
													//dialog box to show the account number
													JDialog dg_accountcreated=new JDialog(frame_newaccount,"Account created",true);
													dg_accountcreated.add(new JLabel("New Account Created for "+tf_name.getText().toString()+". Your Account number is "+accountnumbernum));
													accountnumbernum++;
													b = new JButton ("OK");
													dg_accountcreated.add(b);
													dg_accountcreated.setLayout( new FlowLayout() );  
													
													b.addActionListener ( new ActionListener()  
														{  
																public void actionPerformed( ActionEvent e )  
																{  
																	dg_accountcreated.setVisible(false);  
																	tf_name.requestFocus();
																}  
														 });   
														 b.addKeyListener(new KeyAdapter()
															{
																public void keyPressed(KeyEvent kk)
																{
																	dg_accountcreated.setVisible(false); 
																	tf_name.requestFocus();
																}
																
															});	
															   
														dg_accountcreated.setSize(400,100);
														dg_accountcreated.setLocationRelativeTo(null);
													dg_accountcreated.setVisible(true);
													b.requestFocus();
													
													//setting all textfield values to null
													tf_name.setText("");
													tf_fathersname.setText("");
													tf_mothersname.setText("");
													cb_gender.setSelectedItem(" ");
													cb_day.setSelectedItem(" ");
													cb_month.setSelectedItem(" ");
													tf_dob_year.setText("");
													
													tf_mobilenumber.setText("");
													tf_emailid.setText("");
													tf_address.setText("");
													display.setText("");
													//commit statement
													try
													{
														connection.commit();
													}
													catch(Exception t)
													{
													}
												}
												}
												catch(Exception eeee)
												{
													eeee.printStackTrace();
												}
        								    }  
       								 });   
									 //when no button is clicked
									btn_dgb_no.addActionListener ( new ActionListener()  
						        	{  
								            public void actionPerformed( ActionEvent e )  
							        	    { 
												dg_confirmation.setVisible(false);
									 
											}
									});
    	    							
        							dg_confirmation.setSize(300,100);
									dg_confirmation.setLocationRelativeTo(null);
   								dg_confirmation.setVisible(true);
								
						
						}
						//if any of these becomes false (email contains '@' and '.com' and length of mobile number is 10 and length of dob-year is 4 and is a valid year)
						else
						{
								//length of mobile number is not 10
								if(len_mobilenumber!=10)
								{
									display.append("Enter a valid mobile number\n");
								}
								//length of dob-year is not 4
								if(len_dobyear!=4 )
								{
									display.append("Enter a valid dob\n");
								}
								//if date is not valid
								if(!isValidDate(Integer.parseInt(cb_day.getSelectedItem().toString()),cb_month.getSelectedItem().toString(),Integer.parseInt(tf_dob_year.getText().toString())))
								{
									display.append("Enter a valid dob\n");
								}
								//if email doesn't contain '@' or '.com' 
								if(tf_emailid.getText().toString().contains("@")==false  || tf_emailid.getText().toString().contains(".com")==false)
								{
										display.append("Enter a valid emailid\n");
								}
						}
					} 
				}
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
				catch(NullPointerException exx)
				{
					display.append("Please fill the details1\n");
				}
				catch(Exception eeeee)
				{
					eeeee.printStackTrace();
				}
            }; 
		}	
		//end of submit class
		
		
		//when submit button is pressed
		btn_submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				new submit().submitbutton();                  //calling of submit class to check for all the data is correct or not
			}
		});	
		
		btn_submit.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke) 
			{
				new submit().submitbutton();                 //calling of submit class to check for all the data is correct or not
			}
			
		});
		
		//adding key listener so that it doesnot take numbers as input
		tf_name.addKeyListener(new KeyAdapter() 
		{
         	public void keyPressed(KeyEvent ke) 
			{
				int len_name = tf_name.getText().length();
            	if ((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z' ) || (ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z' ) )  
				{
               				tf_name.setEditable(true);
            	} 
				else if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' )  
				{
               				tf_name.setEditable(false);
            	}
				else if( ke.getKeyChar()==10 || ke.getKeyChar()==11)               //enter is pressed
				{
					if(len_name==0)
						
						display.requestFocus();
					
					else
						tf_fathersname.requestFocus();
				}
				//if backspace or space is pressed	
				else if( ke.getKeyChar()==8 || ke.getKeyChar()==32)
				{
               				tf_name.setEditable(true);
            	} 
				else 
				{
               		tf_name.setEditable(false);
            	}
				//length doesn't exceed 35
				if (len_name>34)
				{
               				tf_name.setEditable(false);
            	}
				if(len_name>34 && ke.getKeyChar()==8) 
				{
               				tf_name.setEditable(true);
            	}
         	}
     	});
			 
		//adding focus listener to check data is correct when focus is lost
		tf_name.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent arg0) 
			{
				String str_name=tf_name.getText().toString();
				if(str_name.isEmpty() )
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Enter Full Name   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									tf_name.requestFocus();
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});

		//adding key listener so that it doesnot take numbers as input
		tf_fathersname.addKeyListener(new KeyAdapter() 
		{
         		public void keyPressed(KeyEvent ke) 
			{
            			
            			int len_fathersname = tf_fathersname.getText().length();
            			if ((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z' ) || (ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z' )  || ke.getKeyChar() == '.')  
				{
               				tf_fathersname.setEditable(true);
            			} 
				else if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' )  
				{
               				tf_fathersname.setEditable(false);
            			}
						
				else if( ke.getKeyChar()==10 || ke.getKeyChar()==9)
				{
					if(len_fathersname==0)
						
					display.requestFocus();
					
					else
						tf_mothersname.requestFocus();
					
				}
				else if( ke.getKeyChar()==8 || ke.getKeyChar()==32)
				{
               				tf_fathersname.setEditable(true);
            			} 
				else 
				{
               				tf_fathersname.setEditable(false);
            			}


				if (len_fathersname>34)
				{
               				tf_fathersname.setEditable(false);
            			}


				if(len_fathersname>34 && ke.getKeyChar()==8) 
				{
               				tf_fathersname.setEditable(true);
            			}
         		}
     		 });

		//adding focus listener to check data is correct when focus is lost
		tf_fathersname.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent arg0) 
			{
				String str_fathersname=tf_fathersname.getText().toString();
				if(tf_name.getText().toString().isEmpty())
				{
				}
				else
				{
				if(str_fathersname.isEmpty() )
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Enter father's name   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									tf_fathersname.requestFocus();
								
					
				}
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});


		//adding key listener so that it doesnot take numbers as input
		tf_mothersname.addKeyListener(new KeyAdapter() 
		{
         		public void keyPressed(KeyEvent ke) 
			{
            			
            			int len_mothersname = tf_mothersname.getText().length();
            			if ((ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z' ) || (ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z' )  || ke.getKeyChar() == '.')  
				{
               				tf_mothersname.setEditable(true);
            			} 
				else if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' )  
				{
               				tf_mothersname.setEditable(false);
            			}
						
				else if( ke.getKeyChar()==10 )
				{
					
					if(len_mothersname==0)
						
					display.requestFocus();
					
					else
						cb_gender.requestFocus();
					
				}		
				
				else if( ke.getKeyChar()==8 || ke.getKeyChar()==32)
				{
               				tf_mothersname.setEditable(true);
            			} 
				else 
				{
               				tf_mothersname.setEditable(false);
            			}


				if (len_mothersname>34)
				{
               				tf_mothersname.setEditable(false);
            			}


				if(len_mothersname>34 && ke.getKeyChar()==8) 
				{
               				tf_mothersname.setEditable(true);
            			}
         		}
     		 });
			 
		//adding focus listener to check data is correct when focus is lost
		tf_mothersname.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent arg0) 
			{
				String str_mothersname=tf_mothersname.getText().toString();
				if(tf_fathersname.getText().toString().isEmpty())
				{
				}
				else
				{
				if(str_mothersname.isEmpty() )
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Enter mother's name   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									tf_mothersname.requestFocus();
								
					
				}
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});	 
			 
		//adding key listener so that it doesnot take numbers as input
		cb_gender.addKeyListener(new KeyAdapter() 

		{
         		public void keyPressed(KeyEvent ke) 
			{
				if( ke.getKeyChar()==10 )
				{
					if(cb_gender.getSelectedItem()==" "){
						display.requestFocus();
						
					}
					else{
						cb_day.requestFocus();
					}
				}
				
				
			}
		});
		
		//adding focus listener to check data is correct when focus is lost
		cb_gender.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent arg0) 
			{
				if(tf_mothersname.getText().toString().isEmpty())
				{
				}
				else
				{
				
				if(cb_gender.getSelectedItem()==" ")
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Select gender   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									cb_gender.requestFocus();
					
				}
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});	 
		
		//adding key listener so that it doesnot take alphabets as input
		cb_day.addKeyListener(new KeyAdapter() 
		{
         		public void keyPressed(KeyEvent ke) 
			{
				if( ke.getKeyChar()==10 )
				{
					if(cb_day.getSelectedItem()==" ")
					{
						display.requestFocus();
					}
					else{
						cb_month.requestFocus();
					}
				}
				
				
			}
		});
		
		//adding focus listener to check data is correct when focus is lost
		cb_day.addFocusListener(new FocusListener()
		{
			
			public void focusLost(FocusEvent arg0) 
			{
				if(cb_gender.getSelectedItem()==" ")
				{
				}
				else
				{
				
				if(cb_day.getSelectedItem()==" ")
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Select day   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									cb_day.requestFocus();
								
					
				}
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});
		
		//adding key listener so that it doesnot take alphabets as input
		cb_month.addKeyListener(new KeyAdapter() 

		{
         		public void keyPressed(KeyEvent ke) 
			{
				if( ke.getKeyChar()==10 )
				{
					if(cb_month.getSelectedItem()==" ")
					{
						display.requestFocus();
					}
					else{
						tf_dob_year.requestFocus();
					}
				}
				
				
			}
		});
		
		//adding focus listener to check data is correct when focus is lost
		cb_month.addFocusListener(new FocusListener()
		{
			
			public void focusLost(FocusEvent arg0) 
			{
				if(cb_day.getSelectedItem()==" ")
				{
				}
				else
				{
				
				if(cb_month.getSelectedItem()==" ")
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Select month   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									cb_month.requestFocus();
								
					
				}
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});
		
		//adding key listener so that it doesnot take aplhabets as input
		tf_dob_year.addKeyListener(new KeyAdapter() 
		{
         	public void keyPressed(KeyEvent ke) 
			{		
            	int len_dob_year = tf_dob_year.getText().length();
				
					if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' )  
					{
								tf_dob_year.setEditable(true);
					} 
					else if( ke.getKeyChar()==8)
					{
								tf_dob_year.setEditable(true);
							} 
							
					else if (ke.getKeyChar()==10) 
					{
						if(Integer.parseInt(tf_dob_year.getText().toString())>=(calendar.get(Calendar.YEAR)-10))
						{
									JDialog dg_invalidyear=new JDialog(frame_newaccount,"Error",true);
									dg_invalidyear.add(new JLabel("Enter a valid year"));
									b = new JButton ("OK");
									dg_invalidyear.add(b);
									
									dg_invalidyear.setLayout( new FlowLayout() );  
															
															b.addActionListener ( new ActionListener()  
																{  
																		public void actionPerformed( ActionEvent e )  
																		{  
																			dg_invalidyear.setVisible(false);  
																			tf_dob_year.requestFocus();
																		}  
																 });   
																 b.addKeyListener(new KeyAdapter()
																	{
																		public void keyPressed(KeyEvent kk)
																		{
																			dg_invalidyear.setVisible(false); 
																			tf_dob_year.requestFocus();
																		}
																		
																	});	
																	   
																dg_invalidyear.setSize(300,100);
																dg_invalidyear.setLocationRelativeTo(null);
															dg_invalidyear.setVisible(true);
															b.requestFocus();
							}
							else{
								if(len_dob_year!=4)
								{
									display.requestFocus();
									
								}
								else{
									tf_mobilenumber.requestFocus();
								}
							}
						
						
					}
					else 
					{
								tf_dob_year.setEditable(false);
							}
					if (len_dob_year>3)
					{
								tf_dob_year.setEditable(false);
							}
					if(len_dob_year>3 && ke.getKeyChar()==8) 
					{
								tf_dob_year.setEditable(true);
							}
					}
				
     		 });
			 
		//adding focus listener to check data is correct when focus is lost
		tf_dob_year.addFocusListener(new FocusListener()
		{
			
			public void focusLost(FocusEvent arg0) 
			{
				if(Integer.parseInt(tf_dob_year.getText().toString())>=(calendar.get(Calendar.YEAR)-10))
				{
									JDialog dg_invalidyear=new JDialog(frame_newaccount,"Error",true);
									dg_invalidyear.add(new JLabel("Enter a valid year"));
									b = new JButton ("OK");
									dg_invalidyear.add(b);
									
									dg_invalidyear.setLayout( new FlowLayout() );  
															
															b.addActionListener ( new ActionListener()  
																{  
																		public void actionPerformed( ActionEvent e )  
																		{  
																			dg_invalidyear.setVisible(false);  
																			tf_dob_year.requestFocus();
																		}  
																 });   
																 b.addKeyListener(new KeyAdapter()
																	{
																		public void keyPressed(KeyEvent kk)
																		{
																			dg_invalidyear.setVisible(false); 
																			tf_dob_year.requestFocus();
																		}
																		
																	});	
																	   
																dg_invalidyear.setSize(300,100);
																dg_invalidyear.setLocationRelativeTo(null);
															dg_invalidyear.setVisible(true);
															b.requestFocus();
				}
				else
				{
						String str_dob_year=tf_dob_year.getText().toString();
						if(cb_month.getSelectedItem()==" ")
						{
						}
						else
						{
						
							if(str_dob_year.isEmpty())
							{
								dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
									dg_focus.add( new JLabel ("Enter year   ")); 
												dg_focus.setLayout( new FlowLayout() );  
								
												JButton be1 = new JButton ("OK");
					
												be1.addActionListener ( new ActionListener()  
													{  
															public void actionPerformed( ActionEvent ee )  
															{  
																dg_focus.setVisible(false);  
															}  
													 });  
													be1.addKeyListener(new KeyAdapter()
													{
														public void keyPressed(KeyEvent kkk)
														{
															dg_focus.setVisible(false); 
															
														}
														
													});										 
														dg_focus.add(be1);   
													dg_focus.setSize(300,100);
													dg_focus.setLocationRelativeTo(null);
												dg_focus.setVisible(true);
												be1.requestFocus();
												tf_dob_year.requestFocus();
											
								
							}
							else if(str_dob_year.length()!=4)
							{
								dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
									dg_focus.add( new JLabel ("Enter valid year   ")); 
												dg_focus.setLayout( new FlowLayout() );  
								
												JButton be1 = new JButton ("OK");
					
												be1.addActionListener ( new ActionListener()  
													{  
															public void actionPerformed( ActionEvent ee )  
															{  
																dg_focus.setVisible(false);  
															}  
													 });  
													be1.addKeyListener(new KeyAdapter()
													{
														public void keyPressed(KeyEvent kkk)
														{
															dg_focus.setVisible(false); 
															
														}
														
													});										 
														dg_focus.add(be1);   
													dg_focus.setSize(300,100);
													dg_focus.setLocationRelativeTo(null);
												dg_focus.setVisible(true);
												be1.requestFocus();
												tf_dob_year.requestFocus();
							}
						}
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});
		
		//adding key listener so that it doesnot take alphabets as input
		tf_mobilenumber.addKeyListener(new KeyAdapter() 
		{
         		public void keyPressed(KeyEvent ke) 
			{
            			int len_mobilenumber = tf_mobilenumber.getText().length();
            			if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' )  
				{
               				tf_mobilenumber.setEditable(true);
            			} 
				else if( ke.getKeyChar()==8)
				{
               				tf_mobilenumber.setEditable(true);
            			} 
						
				else if (ke.getKeyChar()==10) {

			   
					if(len_mobilenumber!=10)
					{
						display.requestFocus();
						
					}
					else{
						tf_emailid.requestFocus();
					}
				}		
				
				else 
				{
               				tf_mobilenumber.setEditable(false);
            			}
				if (len_mobilenumber>9)
				{
               				tf_mobilenumber.setEditable(false);
            			}
				if(len_mobilenumber>9 && ke.getKeyChar()==8) 
				{
               				tf_mobilenumber.setEditable(true);
            			}
         		}
     		 });
			 
		//adding focus listener to check data is correct when focus is lost
		tf_mobilenumber.addFocusListener(new FocusListener()
		{
			
			public void focusLost(FocusEvent arg0) 
			{
				String str_mobilenumber=tf_mobilenumber.getText().toString();
				if(tf_dob_year.getText().toString().isEmpty() || tf_dob_year.getText().toString().length()!=4)
				{
				}
				else
				{
				
				if(str_mobilenumber.isEmpty() )
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Enter mobile number   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									tf_mobilenumber.requestFocus();
								
					
				}
				else if(str_mobilenumber.length()!=10)
				{
					dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
						dg_focus.add( new JLabel ("Enter valid mobile number   ")); 
									dg_focus.setLayout( new FlowLayout() );  
					
									JButton be1 = new JButton ("OK");
		
									be1.addActionListener ( new ActionListener()  
										{  
												public void actionPerformed( ActionEvent ee )  
												{  
													dg_focus.setVisible(false);  
												}  
										 });  
										be1.addKeyListener(new KeyAdapter()
										{
											public void keyPressed(KeyEvent kkk)
											{
												dg_focus.setVisible(false); 
												
											}
											
										});										 
											dg_focus.add(be1);   
										dg_focus.setSize(300,100);
										dg_focus.setLocationRelativeTo(null);
									dg_focus.setVisible(true);
									be1.requestFocus();
									tf_mobilenumber.requestFocus();
				}
				}
			}
			@Override
        public void focusGained(FocusEvent arg0) {

        }
      		
		});


		//adding key listener
		tf_emailid.addKeyListener(new KeyAdapter() 
		{
         	public void keyPressed(KeyEvent ke) 
			{
            	int len_emailid = tf_emailid.getText().length();
				if(ke.getKeyChar()==32 || ( ke.getKeyChar()==64 && len_emailid==0))
				{
               				tf_emailid.setEditable(false);
            			}
				else if (ke.getKeyChar()==10) 
				{
					if(tf_emailid.getText().toString().contains("@")==false  ||  tf_emailid.getText().toString().contains(".com")==false ||  tf_emailid.getText().toString().isEmpty())
					{
						
						display.requestFocus();
					}
					else{
						tf_address.requestFocus();
					}
				}		
				else 
				{
               				tf_emailid.setEditable(true);
            			}
				if (len_emailid>34)
				{
               				tf_emailid.setEditable(false);
            			}
				if(len_emailid>34 && ke.getKeyChar()==8) 
				{
               				tf_emailid.setEditable(true);
            			}
         		}
     		 });
			 
		//adding focus listener to check data is correct when focus is lost
		tf_emailid.addFocusListener(new FocusListener()
		{
			
			public void focusLost(FocusEvent arg0) 
			{
				String str_emailid=tf_emailid.getText().toString();
				if(tf_mobilenumber.getText().toString().isEmpty() || tf_mobilenumber.getText().toString().length()!=10)
				{
				}
				else
				{
					if(str_emailid.isEmpty())
					{
						dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
							dg_focus.add( new JLabel ("Enter emailid   ")); 
										dg_focus.setLayout( new FlowLayout() );  
						
										JButton be1 = new JButton ("OK");
			
										be1.addActionListener ( new ActionListener()  
											{  
													public void actionPerformed( ActionEvent ee )  
													{  
														dg_focus.setVisible(false);  
													}  
											 });  
											be1.addKeyListener(new KeyAdapter()
											{
												public void keyPressed(KeyEvent kkk)
												{
													dg_focus.setVisible(false); 
													
												}
												
											});										 
												dg_focus.add(be1);   
											dg_focus.setSize(300,100);
											dg_focus.setLocationRelativeTo(null);
										dg_focus.setVisible(true);
										be1.requestFocus();
										tf_emailid.requestFocus();
									
						
					}
					else
					{
					
						if((tf_emailid.getText().toString().contains("@") && tf_emailid.getText().toString().contains(".com") ) || (tf_emailid.getText().toString().contains("@") && tf_emailid.getText().toString().contains(".COM")))
						{
						}
						
						else 
						{
							dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
								dg_focus.add( new JLabel ("Enter valid emailid   ")); 
											dg_focus.setLayout( new FlowLayout() );  
							
											JButton be1 = new JButton ("OK");
				
											be1.addActionListener ( new ActionListener()  
												{  
														public void actionPerformed( ActionEvent ee )  
														{  
															dg_focus.setVisible(false);  
														}  
												 });  
												be1.addKeyListener(new KeyAdapter()
												{
													public void keyPressed(KeyEvent kkk)
													{
														dg_focus.setVisible(false); 
														
													}
													
												});										 
													dg_focus.add(be1);   
												dg_focus.setSize(300,100);
												dg_focus.setLocationRelativeTo(null);
											dg_focus.setVisible(true);
											be1.requestFocus();
											tf_emailid.requestFocus();
						}
					}
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {

			}
      		
		});
			 
		
		//adding key listener 
		tf_address.addKeyListener(new KeyAdapter() 
		{
         		public void keyPressed(KeyEvent ke) 
			{
            			
            	int len_address = tf_address.getText().length();
				
				if(ke.getKeyChar()==10 && len_address==0)
				{
					tf_address.setEditable(false);
				}
				else
				{
					tf_address.setEditable(true);
				}
				
            			
				if (len_address>99)
				{
               				tf_address.setEditable(false);
            			}


				if(len_address>99 && ke.getKeyChar()==8) 
				{
               				tf_address.setEditable(true);
            			}
         		}
     		 });
			 
		//adding focus listener to check data is correct when focus is lost
		tf_address.addFocusListener(new FocusListener()
		{
			
			public void focusLost(FocusEvent arg0) 
			{
				String str_address=tf_address.getText().toString();
				if((tf_emailid.getText().toString().isEmpty()) ||
				
					(tf_emailid.getText().toString().contains("@") &&( tf_emailid.getText().toString().contains(".COM")==false)) ||
					(tf_emailid.getText().toString().contains("@") &&( tf_emailid.getText().toString().contains(".COM")==false)) || 
					(tf_emailid.getText().toString().contains("@")==false &&( tf_emailid.getText().toString().contains(".com"))) || 
					(tf_emailid.getText().toString().contains("@")==false &&( tf_emailid.getText().toString().contains(".COM"))) ||
					(tf_emailid.getText().toString().contains("@")==false &&( tf_emailid.getText().toString().contains(".com")==false))  ||
					(tf_emailid.getText().toString().contains("@")==false &&( tf_emailid.getText().toString().contains(".COM")==false))   )
				{
					
				}
				else
				{
					if(str_address.isEmpty())
					{
						dg_focus=new JDialog(frame_newaccount,"New Account errors",true);
							dg_focus.add( new JLabel ("Enter address   ")); 
										dg_focus.setLayout( new FlowLayout() );  
						
										JButton be1 = new JButton ("OK");
			
										be1.addActionListener ( new ActionListener()  
											{  
													public void actionPerformed( ActionEvent ee )  
													{  
														dg_focus.setVisible(false);  
													}  
											 });  
											be1.addKeyListener(new KeyAdapter()
											{
												public void keyPressed(KeyEvent kkk)
												{
													dg_focus.setVisible(false); 
													
												}
												
											});										 
												dg_focus.add(be1);   
											dg_focus.setSize(300,100);
											dg_focus.setLocationRelativeTo(null);
										dg_focus.setVisible(true);
										be1.requestFocus();
										tf_address.requestFocus();
									
						
					}
				}
				
			}
			//override method
			@Override
			public void focusGained(FocusEvent arg0) 
			{

			}
      		
		});
		
		//setting title to frame and making the frame visible
		frame_newaccount.setTitle("New Account");
		frame_newaccount.setVisible(true);
	}

	private void displaySQLErrors(SQLException e) 
	{
		display.append("\nSQLException: " + e.getMessage() + "\n");
		display.append("SQLState:     " + e.getSQLState() + "\n");
		display.append("VendorError:  " + e.getErrorCode() + "\n");
	}

}