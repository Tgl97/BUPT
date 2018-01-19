package homework4;

import java.awt.event.*;

public class _14211168_÷‹“¢∆Â_4_BasePlusCommisionEmployeeFrame extends _14211168_÷‹“¢∆Â_4_CommisionFrame
{	
	_14211168_÷‹“¢∆Â_4_BasePlusCommisionEmployeeFrame(_14211168_÷‹“¢∆Â_4_EmployeeMenu main)
	{
		super(1,main);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.OK_Button)
		{
			String firstname,lastname,rate,sales;
			_14211168_÷‹“¢∆Â_4_BasePlusCommisionEmployee BEmployee = new _14211168_÷‹“¢∆Â_4_BasePlusCommisionEmployee(this.main.getNum());
			firstname = first_text.getText();
			lastname = last_text.getText();
			sales = Sales_text.getText();
			rate = Rate_text.getText();
			BEmployee.setfirstName(firstname);
			BEmployee.setlastName(lastname);
			BEmployee.setcommissionRate(Double.parseDouble(rate));
			BEmployee.setgrossSales(Double.parseDouble(sales));
			this.main.Bemployees.addElement(BEmployee);
			this.main.addNum();
		}
		
		if (e.getSource() == this.RESET_Button)
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
			if (Salary_text.getText() != null)
			{
				Salary_text.setText("");
			}
		}
	}
}
