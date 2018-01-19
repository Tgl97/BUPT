package homework4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class _14211168_÷‹“¢∆Â_4_CommisionFrame extends JFrame implements ActionListener
{
	public _14211168_÷‹“¢∆Â_4_EmployeeMenu main = null;
	
	protected JTextField first_text = new JTextField(15);
	protected JTextField last_text = new JTextField(15);
	protected JTextField Sales_text = new JTextField(15);
	protected JTextField Rate_text = new JTextField(15);
	protected JTextField Salary_text = new JTextField(15);
	
	protected JButton OK_Button = new JButton("OK");
	protected JButton RESET_Button = new JButton("RESET");
	
	private JLabel message = new JLabel();
	private JLabel firstname = new JLabel("firstname:");
	private JLabel lastname = new JLabel("lastname:");
	private JLabel Sales = new JLabel("grossSales:");
	private JLabel Rate = new JLabel("commissionRate:");
	private JLabel Salary = new JLabel("baseSalary:");
	
	private JPanel p = new JPanel(new GridLayout(6,2,10,10));
		
	public _14211168_÷‹“¢∆Â_4_CommisionFrame(int i,_14211168_÷‹“¢∆Â_4_EmployeeMenu main)
	{
		 //in order to send the data to the main frame
		 this.main = main; 
		 setTitle("Input"); 
		 setLayout(new FlowLayout());
		 
		 //closing the subframe will not close the whole program
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setSize(400,300);
		 setLocation(800,400);
		 
		 if (i == 0)	//create the subframe CommisionFrame
		 {
			 message.setText("Please input the info of the CommisionEmployee:");
		 }
		 else			//create the subframe BasePlusCommisionEmployeeFrame
		 {
			 message.setText("Please input the info of the BasePlusCommisionEmployee:");
		 }
		 
		 //add the label to the frame
		 getContentPane().add(message);
		 //add the labels,textfields and buttons to the panel
		 p.add(firstname);
		 p.add(first_text);
		 p.add(lastname);
		 p.add(last_text);
		 p.add(Sales);
		 p.add(Sales_text);
		 p.add(Rate);
		 p.add(Rate_text);
		 
		 if (i == 1)
		 {
			 p.add(Salary);
			 p.add(Salary_text);
		 }
		 
		 p.add(OK_Button);
		 p.add(RESET_Button);
		 //add panel to the frame
		 getContentPane().add(p);
		 
		 OK_Button.addActionListener(this);
		 RESET_Button.addActionListener(this);
		 setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == OK_Button)
		{
			String firstname,lastname,rate,sales;
			_14211168_÷‹“¢∆Â_4_CommisionEmployee CEmployee = new _14211168_÷‹“¢∆Â_4_CommisionEmployee(this.main.getNum());
			firstname = first_text.getText();
			lastname = last_text.getText();
			sales = Sales_text.getText();
			rate = Rate_text.getText();
			CEmployee.setfirstName(firstname);
			CEmployee.setlastName(lastname);
			CEmployee.setcommissionRate(Double.parseDouble(rate));
			CEmployee.setgrossSales(Double.parseDouble(sales));
			this.main.Cemployees.addElement(CEmployee);
			this.main.addNum();
		}
		
		if (e.getSource() == RESET_Button)
		{
			if (first_text.getText() != null)
			{
				first_text.setText("");
			}
			if (last_text.getText() != null)
			{
				last_text.setText("");
			}
			if (Sales_text.getText() != null)
			{
				Sales_text.setText("");
			}
			if (Rate_text.getText() != null)
			{
				Rate_text.setText("");
			}
		}
	}
}
