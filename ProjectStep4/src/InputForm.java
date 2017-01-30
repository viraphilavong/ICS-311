/**
 * This file implements a form that takes one input (product code). The form then
 * reads and displays vendor information for the given prodcut code.
 * This is done by using the input value to construct SQL query. The query is 
 * executed and the output is displayed in the lower part of the form.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class InputForm extends JFrame{

	Connection con;
	Statement stmt;
	PreparedStatement pStmt;
	ResultSet rs;
	
	//Display values from the database
	JTextField productCodeBox = new JTextField(8);
	
	//Scroll buttons
	JButton submitBtn = new JButton("Submit");
	
	//Output Display
	JTextField productCategory = new JTextField(8);
	JTextField productTitle = new JTextField(8);
	JTextField productPrice = new JTextField(8);
	JTextField productDescription = new JTextField(8);
	JTextField productStock = new JTextField(8);
	
	public static void main(String[] args)
	{
		InputForm dbForm = new InputForm();
		dbForm.setTitle("Database Form");
		dbForm.setSize(500,200);
		dbForm.setVisible(true);
	}
	
	public InputForm(){
		
		initComponents();
		DoConnect();
	}
	
	public void initComponents(){
		

		
		JPanel p1 = new JPanel();
		GridLayout textFieldsLayout = new GridLayout(3,2);
		p1.setLayout(textFieldsLayout);
		
		JPanel p2 = new JPanel();
		GridLayout buttonsLayout = new GridLayout(3,2);
		p2.setLayout(buttonsLayout);
		
		p1.add(new JLabel("Product Code"));
		p1.add(productCodeBox);
		p1.add(submitBtn);
		add(p1,BorderLayout.NORTH);
		
		p2.add(new JLabel("Product Category"));
		productCategory.setText(" ");
		p2.add(productCategory);
		p2.add(new JLabel("Product Title"));
		productTitle.setText(" ");
		p2.add(productTitle);
		p2.add(new JLabel("Product Price"));
		productPrice.setText(" ");
		p2.add(productPrice);
		p2.add(new JLabel("Product Description"));
		productDescription.setText(" ");
		p2.add(productDescription);
		p2.add(new JLabel("Product Stock"));
		productStock.setText(" ");
		p2.add(productStock);
		
		add(p2,BorderLayout.SOUTH);
		
		
		
		submitListenerClass listener1 = new submitListenerClass();
		
		
		submitBtn.addActionListener(listener1);
		}
	
	public void DoConnect(){
		
		//Connecting to the database
		try {
			    Class.forName("com.mysql.jdbc.Driver");
			    con = DriverManager.getConnection("jdbc:mysql://localhost/ics311?user=root&password=ics311");
			    System.out.println("Connection Object Created : " + con);
			       		
			       		
			 
			   
			    String sqlStr = "Select prod_id, cat_id, title, title, V.price, prod_desc, stock ";
			    pStmt = con.prepareStatement(sqlStr);
			    
			    //rs.next();
			    
			   // displayRowValues();
			       	
			    //con.close();

				} catch (Exception ex) { ex.printStackTrace();}

	}
	//CONTINUE ON THIS PART, DONT FORGET TO CREATE THE SQL STRIGN ABOVE
	public void displayRowValues(){
		
		try{
			int id_col = rs.getInt("prod_id");
			String productCode = Integer.toString(id_col);
			productCodeBox.setText(productCode);
	    
			int cat_col = rs.getInt("cat_id");
			String catCode = Integer.toString(cat_col);
			productCategory.setText(catCode);
	    
			String title_col = rs.getString("title");
			productTitle.setText(title_col);
			
			double price_col = rs.getDouble("price");
			String price = Double.toString(price_col);
			productPrice.setText(price);
			
			String desc_col = rs.getString("prod_desc");
			productDescription.setText(desc_col);
			
			int stock_col = rs.getInt("stock");
			String stock = Integer.toString(stock_col);
			productStock.setText(stock);
		}catch (Exception e){e.printStackTrace();}
		
	}
	
	class submitListenerClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			try{
					String pCodeStr = productCodeBox.getText();
					int pCode = Integer.parseInt(pCodeStr);
					stmt = con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM product WHERE prod_id ="+pCode);
					System.out.println(stmt);
					rs.first();
					displayRowValues();
				}
			catch(Exception nextE){nextE.printStackTrace();}		
		}
	}
	
	
	
	
}

