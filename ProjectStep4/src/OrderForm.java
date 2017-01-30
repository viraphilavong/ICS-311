import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;


public class OrderForm extends JFrame{

	Connection con;
	Statement stmt;
	ResultSet rs;
	
	//Display values from the database
	JTextField orderCodeBox = new JTextField(50);
	JTextField orderDateBox = new JTextField(50);
	JTextField cusCodeBox = new JTextField(50);
	JTextField cusNameBox = new JTextField(50);
	JTextField cusAddressBox = new JTextField(50);
	JTextField cusCCBox = new JTextField(50);
	JTextField cusPhoneBox = new JTextField(50);
	JTextField productIDBox = new JTextField(50);
	JTextField productTitleBox = new JTextField(50);
	JTextField productQuantBox = new JTextField(50);
	JTextField totalPriceBox = new JTextField(50);
	//Scroll buttons
	JButton firstBtn = new JButton("First");
	JButton nextBtn = new JButton("Next");
	JButton lastBtn = new JButton("Last");
	JButton prevBtn = new JButton("Previous");
	
	public static void main(String[] args)
	{
		OrderForm dbForm = new OrderForm();
		dbForm.setTitle("Database Form");
		dbForm.setSize(900,200);
		dbForm.setVisible(true);
	}
	
	public OrderForm(){
		initComponents();
		DoConnect();
	}
	
	public void initComponents(){
		
		JPanel p1 = new JPanel();
		GridLayout textFieldsLayout = new GridLayout(6,2);
		p1.setLayout(textFieldsLayout);
		
		JPanel p2 = new JPanel();
		GridLayout buttonsLayout = new GridLayout(1,4);
		p2.setLayout(buttonsLayout);
		
		p1.add(new JLabel("Order ID"));
		orderCodeBox.setText(" ");
		p1.add(orderCodeBox);
		p1.add(new JLabel("Order Date"));
		orderDateBox.setText(" ");
		p1.add(orderDateBox);
		p1.add(new JLabel("Customer ID"));
		cusCodeBox.setText(" ");
		p1.add(cusCodeBox);
		p1.add(new JLabel("Customer Name"));
		cusNameBox.setText(" ");
		p1.add(cusNameBox);
		p1.add(new JLabel("Customer Address"));
		cusAddressBox.setText(" ");
		p1.add(cusAddressBox);
		p1.add(new JLabel("Customer CC"));
		cusCCBox.setText(" ");
		p1.add(cusCCBox);
		p1.add(new JLabel("Customer Phone"));
		cusPhoneBox.setText(" ");
		p1.add(cusPhoneBox);
		p1.add(new JLabel("Product ID"));
		productIDBox.setText(" ");
		p1.add(productIDBox);
		p1.add(new JLabel("Product Title"));
		productTitleBox.setText(" ");
		p1.add(productTitleBox);
		p1.add(new JLabel("Product Quant"));
		productQuantBox.setText(" ");
		p1.add(productQuantBox);
		p1.add(new JLabel("Total Price"));
		totalPriceBox.setText(" ");
		p1.add(totalPriceBox);
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
			    String sql = "Select O.order_id, O.order_date, O.cus_id, C.cus_name, C.address, C.cc_num, C.phone, O.prod_id, P.title, O.prod_quant, O.total_price "
			    		+ "from orders O, customer C, product P "
			    		+ "where O.cus_id = C.cus_id and "
			    		+ "O.prod_id = P.prod_id";
			    rs = stmt.executeQuery(sql);
			
			    //Move the cursor to the first record and get the data
			    rs.next();
			    
			    displayRowValues();
			       	
			    //con.close();

				} catch (Exception ex) { ex.printStackTrace();}

	}
	
	public void displayRowValues(){
		
		try{
			int orderid_col = rs.getInt("order_id");
			String order_id = Integer.toString(orderid_col);
			orderCodeBox.setText(order_id);
	    
			String orderd_col = rs.getString("order_date");
			orderDateBox.setText(orderd_col);
			
			int cusid_col = rs.getInt("cus_id");
			String cus_id = Integer.toString(cusid_col);
			cusCodeBox.setText(cus_id);
			
			String cusn_col = rs.getString("cus_name");
			cusNameBox.setText(cusn_col);
			
			String cusa_col = rs.getString("address");
			cusAddressBox.setText(cusa_col);
			
			String cuscc_col = rs.getString("cc_num");
			cusCCBox.setText(cuscc_col);
			
			String cusp_col = rs.getString("phone");
			cusPhoneBox.setText(cusp_col);
			
			int prodid_col = rs.getInt("prod_id");
			String prod_id = Integer.toString(prodid_col);
			productIDBox.setText(prod_id);
			
			String prodt_col = rs.getString("title");
			productTitleBox.setText(prodt_col);
			
			int prodq_col = rs.getInt("prod_quant");
			String prodq = Integer.toString(prodq_col);
			productQuantBox.setText(prodq);
			
			double ordert_col = rs.getDouble("total_price");
			String total = Double.toString(ordert_col);
			totalPriceBox.setText(total);
			
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
					JOptionPane.showMessageDialog(OrderForm.this,"End of File");}
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
					JOptionPane.showMessageDialog(OrderForm.this,"Begining of File");}
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
					JOptionPane.showMessageDialog(OrderForm.this,"End of File");}
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
					JOptionPane.showMessageDialog(OrderForm.this,"End of File");}
			} catch(Exception nextE){nextE.printStackTrace();}		
		}
	}


}