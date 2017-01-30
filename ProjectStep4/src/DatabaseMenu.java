
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class DatabaseMenu extends JFrame{

	public DatabaseMenu()
	{
		JMenuBar	menuBar = new JMenuBar();
		
		JMenu propertyMenu = new JMenu( "Forms" );
		propertyMenu.setMnemonic( 'P' );

		// Create property items
		JMenuItem customerItem = new JMenuItem("Customer Form");
		JMenuItem productItem = new JMenuItem("Product Form"); 
		JMenuItem ordersItem = new JMenuItem("Order Form");
		JMenuItem categoryItem = new JMenuItem("Category Form");
		
		
		propertyMenu.add(customerItem);
		propertyMenu.add(productItem);
		propertyMenu.add(ordersItem);
		propertyMenu.add(categoryItem);
		
		
		
		menuItemActionListener menuAL = new menuItemActionListener();
		customerItem.addActionListener(menuAL);
		productItem.addActionListener(menuAL);
		ordersItem.addActionListener(menuAL);
		categoryItem.addActionListener(menuAL);
		
		menuBar.add(propertyMenu);
		
		add(menuBar);
								
		
	}
	public static void main(String[] args) {
		DatabaseMenu myApp = new DatabaseMenu();
		myApp.setTitle("Database Menu");
		myApp.setSize(300,100);
		myApp.setLocation(200,100);
		myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myApp.setVisible(true);


	}

	class menuItemActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String text = ((JMenuItem)e.getSource()).getText();
			
			System.out.println(text);
			if (text == "Customer Form")
			{
				CustomerForm cForm = new CustomerForm();
				cForm.setTitle("Customer Form");
				cForm.setSize(500,200);
				cForm.setVisible(true);
		
			}
			else if (text == "Product Form")
			{
				InputForm iForm = new InputForm();
				iForm.setTitle("Product Form");
				iForm.setSize(500,200);
				iForm.setVisible(true);
		
			}
			else if (text == "Order Form")
			{
				OrderForm oForm = new OrderForm();
				oForm.setTitle("Order Form");
				oForm.setSize(500,200);
				oForm.setVisible(true);
		
			}
			else if (text == "Category Form")
			{
				CategoryForm caForm = new CategoryForm();
				caForm.setTitle("Category Form");
				caForm.setSize(500,200);
				caForm.setVisible(true);
		
			}
			
				}
	}
	
	class NewFrame extends JFrame implements ActionListener

		{

		    public NewFrame()
		    {

		        JButton open = new JButton("New Window");	
		        open.addActionListener(this);
		        add(open);
		        setVisible(true);
		    }
	
		 
		    public void actionPerformed(ActionEvent event)
	
		    {
		        new NewFrame();
		    }
	
		}
}


