import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class CategoryForm extends JFrame{

	Connection con;
	Statement stmt;
	ResultSet rs;
	
	//Display values from the database
	JTextField catCodeBox = new JTextField(50);
	JTextField catNameBox = new JTextField(50);
	//Scroll buttons
	JButton firstBtn = new JButton("First");
	JButton nextBtn = new JButton("Next");
	JButton lastBtn = new JButton("Last");
	JButton prevBtn = new JButton("Previous");
	
	public static void main(String[] args)
	{
		CategoryForm dbForm = new CategoryForm();
		dbForm.setTitle("Database Form");
		dbForm.setSize(500,200);
		dbForm.setVisible(true);
	}
	
	public CategoryForm(){
		
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
		
		p1.add(new JLabel("Category ID"));
		catCodeBox.setText("0");
		p1.add(catCodeBox);
		p1.add(new JLabel("Name"));
		catNameBox.setText(" ");
		p1.add(catNameBox);
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
			    String sql = "Select * from Category";
			    rs = stmt.executeQuery(sql);
			
			    //Move the cursor to the first record and get the data
			    rs.next();
			    
			    displayRowValues();
			       	
			    //con.close();

				} catch (Exception ex) { ex.printStackTrace();}

	}
	
	public void displayRowValues(){
		
		try{
			int id_col = rs.getInt("cat_id");
			String cat_id = Integer.toString(id_col);
			catCodeBox.setText(cat_id);
	    
			String cname_col = rs.getString("cat_name");
			catNameBox.setText(cname_col);

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
					JOptionPane.showMessageDialog(CategoryForm.this,"End of File");}
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
					JOptionPane.showMessageDialog(CategoryForm.this,"Begining of File");}
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
					JOptionPane.showMessageDialog(CategoryForm.this,"End of File");}
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
					JOptionPane.showMessageDialog(CategoryForm.this,"End of File");}
			} catch(Exception nextE){nextE.printStackTrace();}		
		}
	}


}