/**
 * This files implements a form that navigates through
 * all the rows in the Customer table.
 * In order to run this form, make sure your connection
 * is set to connect to the correct database with the correct
 * password.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class CustomerForm extends JFrame{

	Connection con;
	Statement stmt;
	ResultSet rs;
	
	//Display values from the database
	JTextField cusCodeBox = new JTextField(50);
	JTextField cusFNameBox = new JTextField(50);
	JTextField cusAddressBox = new JTextField(50);
	JTextField cusCCBox = new JTextField(50);
	JTextField cusPhoneBox = new JTextField(50);
	
	//Scroll buttons
	JButton firstBtn = new JButton("First");
	JButton nextBtn = new JButton("Next");
	JButton lastBtn = new JButton("Last");
	JButton prevBtn = new JButton("Previous");
	
	
	
	public static void main(String[] args)
	{
		CustomerForm dbForm = new CustomerForm();
		dbForm.setTitle("Database Form");
		dbForm.setSize(500,200);
		dbForm.setVisible(true);
	}
	
	public CustomerForm(){
		
		initComponents();
		DoConnect();
	}
	
	public void initComponents(){
		

		
		JPanel p1 = new JPanel();
		GridLayout textFieldsLayout = new GridLayout(3,2);
		p1.setLayout(textFieldsLayout);
		
		JPanel p2 = new JPanel();
		GridLayout buttonsLayout = new GridLayout(1,4);
		p2.setLayout(buttonsLayout);
		
		p1.add(new JLabel("Customer ID"));
		cusCodeBox.setText("0");
		p1.add(cusCodeBox);
		p1.add(new JLabel("Name"));
		cusFNameBox.setText(" ");
		p1.add(cusFNameBox);
		p1.add(new JLabel("Address"));
		cusAddressBox.setText(" ");
		p1.add(cusAddressBox);
		p1.add(new JLabel("Credit Card"));
		cusCCBox.setText(" ");
		p1.add(cusCCBox);
		p1.add(new JLabel("Phone Number"));
		cusPhoneBox.setText(" ");
		p1.add(cusPhoneBox);
		add(p1,BorderLayout.NORTH);
		
		
		p2.add(firstBtn);
		p2.add(prevBtn);
		p2.add(nextBtn);
		p2.add(lastBtn);
		add(p2,BorderLayout.SOUTH);
		
		
		
		nextListenerClass listener1 = new nextListenerClass();
		prevListenerClass listener2 = new prevListenerClass();
		firstListenerClass listener3 = new firstListenerClass();
		lastListenerClass listener4 = new lastListenerClass();
		
		nextBtn.addActionListener(listener1);
		prevBtn.addActionListener(listener2);
		firstBtn.addActionListener(listener3);
		lastBtn.addActionListener(listener4);
	}
	
	public void DoConnect(){
		
		//Connecting to the database
		try {
			    Class.forName("com.mysql.jdbc.Driver");
			    con = DriverManager.getConnection("jdbc:mysql://localhost/ics311?user=root&password=ics311");
			    System.out.println("Connection Object Created : " + con);
			       		
			       		
			 
			    stmt = con.createStatement();
			    String sql = "Select * from Customer";
			    rs = stmt.executeQuery(sql);
			
			    //Move the cursor to the first record and get the data
			    rs.next();
			    
			    displayRowValues();
			       	
			    //con.close();

				} catch (Exception ex) { ex.printStackTrace();}

	}
	
	public void displayRowValues(){
		
		try{
			int id_col = rs.getInt("cus_id");
			String cus_id = Integer.toString(id_col);
			cusCodeBox.setText(cus_id);
	    
			String fname_col = rs.getString("cus_name");
			cusFNameBox.setText(fname_col);
	    
			String address_col = rs.getString("address");
			cusAddressBox.setText(address_col);
			
			String ccnum_col = rs.getString("cc_num");
			cusCCBox.setText(ccnum_col);
			
			String phone_col = rs.getString("phone");
			cusPhoneBox.setText(phone_col);
		}catch (Exception e){e.printStackTrace();}
	
		
		
	}
	
	class nextListenerClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try{
				
				if (rs.next()){
					
					displayRowValues();
					
					
				}else{
					JOptionPane.showMessageDialog(CustomerForm.this,"End of File");}
			} catch(Exception nextE){nextE.printStackTrace();}		
		}
	}
	
	
	
	class prevListenerClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try{
				
				if (rs.previous()){
					
					displayRowValues();
					
				}else{
					JOptionPane.showMessageDialog(CustomerForm.this,"Begining of File");}
			} catch(Exception nextE){nextE.printStackTrace();}		
		}
	}
	
	class firstListenerClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try{
				
				if (rs.first()){
					
					displayRowValues();
					
					
				}else{
					JOptionPane.showMessageDialog(CustomerForm.this,"End of File");}
			} catch(Exception nextE){nextE.printStackTrace();}		
		}
	}
	
	class lastListenerClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try{
				
				if (rs.last()){
					
					displayRowValues();
					
					
				}else{
					JOptionPane.showMessageDialog(CustomerForm.this,"End of File");}
			} catch(Exception nextE){nextE.printStackTrace();}		
		}
	}


}
