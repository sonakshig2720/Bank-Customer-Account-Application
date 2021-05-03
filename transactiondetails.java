
/* This program is for viewing transaction details of their bank application */

package project.transdet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.sql.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.*;
import java.io.*;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javafx.embed.swing.JFXPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import project.newaccount.*;
import java.text.*;

public class transactiondetails
{
	//declaration of frame, buttons, textfield, panels and labels
	JFrame frame_transdet,frame1;
	
	JPanel p_accountnumber,p_order,p_seetrans;
	JTextField tf_accountnumber;
	
	JRadioButton rb_order1,rb_order2;
	
	JButton ok,btn_seetrans,btn_export;
	
	static JTable table;
	 String from;

    String[] columnNames = {"Date", "Deposited Amount", "Withdrawn Amount", "Balance"};
	
	
	Calendar calendar;
	
	//declaration for connection and statemnet to connect to ojdbc
	Connection connection;
	Statement statement;
	PreparedStatement pst;
	
	//global variables
	int MAX_VALID_YR = 9999; 
    int MIN_VALID_YR = 0000; 
	
	
	
	public void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        FileWriter out = new FileWriter(file);
		BufferedWriter bw= new BufferedWriter(out); 
		
        for(int i=0; i < model.getColumnCount(); i++) {
            bw.write(model.getColumnName(i) + "\t");
        }
        bw.write("\n");
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
				if(j==0){
					String mydate=model.getValueAt(i,j).toString();
					try{
						Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(mydate);
						bw.write(d1+"\t");
					}
					catch(Exception exc)
					{
						exc.printStackTrace();
					}
				}
				else
				bw.write(model.getValueAt(i,j).toString()+"\t");
            }
            bw.write("\n");
        }
        bw.close();
        System.out.println("write out to: " + file);
		
		
    }
	
	boolean isLeap(int year) 
    {  
        return (((year % 4 == 0) &&  
                 (year % 100 != 0)) ||  
                 (year % 400 == 0)); 
    }
	
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
	
	
	//constructor
	public transactiondetails()
	{
		try 
		{
		    //connecting to ojdbc using username and password
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sonas123");
      		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} 
		//catching of sql exception when connection didn't happen
		catch (SQLException connectException) 
		{
		  System.out.println(connectException.getMessage());
		  System.out.println(connectException.getSQLState());
		  System.out.println(connectException.getErrorCode());
		  System.exit(1);
		}
		
		frame_transdet=new JFrame("Transaction details");
		
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
		
		//creation of textfields, textareas, panels
		tf_accountnumber=new JTextField(35);
		
		p_accountnumber=new JPanel();
		p_order=new JPanel();
		p_seetrans=new JPanel();
		
		btn_seetrans=new JButton("View Transactions");
		
		rb_order1=new JRadioButton("Latest to Old");
		rb_order2=new JRadioButton("Old to Latest");
		
		ButtonGroup bg_order=new ButtonGroup();  
		bg_order.add(rb_order1);
		bg_order.add(rb_order2);

		rb_order1.setSelected(true);
		
		//setting grid layout
		p_accountnumber.setLayout(new GridLayout(1,2)); 
		p_order.setLayout(new GridLayout(1,2));
		p_seetrans.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//adding labels
		p_accountnumber.add(new JLabel("Enter Account Number: "));
		
		p_order.add(new JLabel("Select order of transactions: "));
		p_order.add(rb_order1);
		p_order.add(rb_order2);
		
		p_seetrans.add(btn_seetrans);
		
		p_accountnumber.add(tf_accountnumber);
									
		//setting bounds
		p_accountnumber.setBounds(300,100,1000,20);
		p_order.setBounds(300,150,1000,20);
		p_seetrans.setBounds(300,250,1500,40);
		
		//adding panels to frame
		frame_transdet.add(p_accountnumber);
		frame_transdet.add(p_cal);
		frame_transdet.add(p_order);
		frame_transdet.add(p_seetrans);
		
		
		frame_transdet.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			frame_transdet.dispose();                               //closing of frame
			try
			{
				frame1.dispose();
			}
			catch(Exception ex)
			{
			}
		  }
		});
		
		frame_transdet.setExtendedState(frame_transdet.getExtendedState() | frame_transdet.MAXIMIZED_BOTH);    //for maximizing window
		
		
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
						//when enter is pressed
						if(ke.getKeyChar()==10)
						{
							//if length is 0
							if(tf_accountnumber.getText().length()==0)
							{
														JDialog dg_error=new JDialog(frame_transdet,"Error",true);
															dg_error.add(new JLabel("Please enter account number"));
															ok = new JButton ("ok");
															dg_error.add(ok);
															
															dg_error.setLayout( new FlowLayout() ); 
															ok.addActionListener ( new ActionListener()  
																{  
																		public void actionPerformed( ActionEvent e )  
																		{ 
																			dg_error.setVisible(false);
																			tf_accountnumber.requestFocus();
																		}
																});
																dg_error.setSize(300,100);
																dg_error.setLocationRelativeTo(null);
															
															dg_error.setVisible(true);
															ok.requestFocus();
															
							}
							else
							{
								btn_seetrans.requestFocus();
							}
						}
						
			}
			
		});
		
		btn_seetrans.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//if length is 0
							if(tf_accountnumber.getText().length()==0)
							{
														JDialog dg_error=new JDialog(frame_transdet,"Error",true);
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
							
							
							//if length>0
							else
							{
								try
								{
									//extracting the data of the account number and displaying the transaction details details
									String accno=tf_accountnumber.getText().toString();
										String sql = "select * from newaccount where accountnumber="+accno;
										ResultSet rs = statement.executeQuery(sql);
										int i = 0;
										  int count=0;
										  while(rs.next())
										  {
											  count++;
										  }
										  rs.close();
										  if(count==0)
											{
															JDialog dg_error=new JDialog(frame_transdet,"Invalid account number",true);
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
											else
											{
												
												  frame1=new JFrame("Transaction Details of account number "+accno);
													  frame1.addWindowListener(new WindowAdapter(){
													  public void windowClosing(WindowEvent e) 
													  {
														frame1.dispose();                               //closing of frame
													  }
													});

														frame1.setLayout(new BorderLayout());

														DefaultTableModel model = new DefaultTableModel();
														model.setColumnIdentifiers(columnNames);

														btn_export = new JButton("Download");

														table = new JTable();
														table.setDefaultEditor(Object.class, null);
														table.setModel(model);
														table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
														table.setFillsViewportHeight(true);
														
														
														JScrollPane scroll = new JScrollPane(table);
														scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
														
														DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
														rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
														table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
														table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
														table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

														String str_date = "";
														Double d_dep=0.0;
														Double d_with=0.0;
														
														String str_dep="0.0",str_with="0.0";
														
														double mybal=0;

														try 
														{
															//rb_order1 is selected
															if(rb_order1.isSelected())
															{
																pst = connection.prepareStatement("select * from transactions where accountnumber="+accno+" order by transactiondate asc");
														
															ResultSet rs2 = pst.executeQuery();

															while(rs2.next()) {
																
																str_date=rs2.getString("transactiondate");
																d_dep=rs2.getDouble("depositedamount");
																d_with=rs2.getDouble("withdrawnamount");
																
																if(d_dep==0.0)
																{
																	str_dep="-";
																}
																else
																{
																	str_dep=d_dep.toString();
																	mybal=mybal+d_dep;
																}
																if(d_with==0.0)
																{
																	str_with="-";
																}
																else
																{
																	str_with=d_with.toString();
																	mybal=mybal-d_with;
																}

																model.addRow(new Object[]{str_date,str_dep,str_with,mybal});
																i++;

															}
															rs2.close();
															TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
															table.setRowSorter(sorter);
															ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
															 
															int columnIndexToSort = 0;
															sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
															 
															sorter.setSortKeys(sortKeys);
															sorter.sort();
																
															}
														//rb_order2 is selected
														else if(rb_order2.isSelected())
														{
															pst = connection.prepareStatement("select * from transactions where accountnumber="+accno+" order by transactiondate asc");
														
															ResultSet rs2 = pst.executeQuery();

															while(rs2.next()) {
																
																str_date=rs2.getString("transactiondate");
																d_dep=rs2.getDouble("depositedamount");
																d_with=rs2.getDouble("withdrawnamount");
																
																if(d_dep==0.0)
																{
																	str_dep="-";
																}
																else
																{
																	str_dep=d_dep.toString();
																	mybal=mybal+d_dep;
																}
																if(d_with==0.0)
																{
																	str_with="-";
																}
																else
																{
																	str_with=d_with.toString();
																	mybal=mybal-d_with;
																}

																model.addRow(new Object[]{str_date,str_dep,str_with,mybal});
																i++;

															}
															rs2.close();
															TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
															table.setRowSorter(sorter);
															ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
															 
															int columnIndexToSort = 0;
															sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
															 
															sorter.setSortKeys(sortKeys);
															sorter.sort();
														}
															
														}
														catch(Exception eee)
														{
															eee.printStackTrace();
														}
														
													   frame1.add(scroll);
													   frame1.setSize(700,600);
														frame1.setVisible(true);
														frame1.getContentPane().add("South",btn_export);
													  frame1.setLocationRelativeTo(null);
													  
													  btn_export.addActionListener(new ActionListener()
														{
															public void actionPerformed(ActionEvent e) 
															{
																try {
																	transactiondetails exp = new transactiondetails();
																	exp.exportTable(table, new File("results.xls"));
																} catch (IOException ex) {
																	System.out.println(ex.getMessage());
																	ex.printStackTrace();
																}
																
															}
														});
													  
													  
												  }
												  if(i==0)
													{
																	JDialog dg_error=new JDialog(frame_transdet,"No transactions",true);
																		dg_error.add(new JLabel("You dont have any transactions"));
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
									eeee.printStackTrace();
								}
								
							}
				
			}
		});
		
		frame_transdet.setLayout(null);
		frame_transdet.setVisible(true);
	}

}