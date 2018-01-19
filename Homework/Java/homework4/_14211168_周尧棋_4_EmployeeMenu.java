package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class _14211168_周尧棋_4_EmployeeMenu extends JFrame implements ActionListener
{
	//创建顶上的菜单条
	private JMenuBar bar = new JMenuBar();
	//创建EmployeeInfoInput和Search两个一级菜单
	private JMenu EmployeeInfoInput = new JMenu("EmployeeInfoInput");
	private JMenu Search = new JMenu("Search");
	//分别创建menu item（二级菜单）
	private JMenuItem CommisionEm_Menu = new JMenuItem("CommisionEmployee");
	private JMenuItem BasePlusCommisionEm_Menu = new JMenuItem("BasePlusCommisionEmployee");
	private JMenuItem EarningSearch_Menu = new JMenuItem("AverageEarningSearch");
	
	private JLabel message = new JLabel();
	private JLabel average = new JLabel();
	private JPanel p = new JPanel(new GridLayout(1,2,20,10));
	
	int num = 0;
	Vector<_14211168_周尧棋_4_CommisionEmployee> Cemployees = new Vector<_14211168_周尧棋_4_CommisionEmployee>();
	Vector<_14211168_周尧棋_4_BasePlusCommisionEmployee> Bemployees = new Vector<_14211168_周尧棋_4_BasePlusCommisionEmployee>();
	//CommisionFrame cEm_frame = new CommisionFrame(0,EmployeeMenu.this);
	//BasePlusCommisionEmployeeFrame bEm_frame = new BasePlusCommisionEmployeeFrame(EmployeeMenu.this);
	
	public _14211168_周尧棋_4_EmployeeMenu()
	{
		//add labels to the panel
		p.add(message);
		p.add(average);
		
		//add the panel to the frame
		getContentPane().add(p);
		
		//add menu items to the EmployeeInfoInput menu
		EmployeeInfoInput.add(CommisionEm_Menu);
		EmployeeInfoInput.add(BasePlusCommisionEm_Menu);
		Search.add(EarningSearch_Menu);
		
		// add the menus to the menu bar
		bar.add(EmployeeInfoInput);
		bar.add(Search);
		
		//add the menu bar to the frame
		setJMenuBar(bar);
		
		//add listeners to each menu item
		CommisionEm_Menu.addActionListener(this);
		BasePlusCommisionEm_Menu.addActionListener(this);
		EarningSearch_Menu.addActionListener(this);
		
	    // select a GridLayout
		setLayout(new FlowLayout());

		// choose settings for the frame and make it visible
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(500,200);
	    setLocation(400,300);
	    setVisible(true);
	}
	
	public void addNum()
	{
		num ++;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == CommisionEm_Menu)
		{
			_14211168_周尧棋_4_CommisionFrame cEm_frame = new _14211168_周尧棋_4_CommisionFrame(0,_14211168_周尧棋_4_EmployeeMenu.this);
			cEm_frame.setVisible(true);
		}
		if (e.getSource() == BasePlusCommisionEm_Menu)
		{
			_14211168_周尧棋_4_BasePlusCommisionEmployeeFrame bEm_frame = new _14211168_周尧棋_4_BasePlusCommisionEmployeeFrame(_14211168_周尧棋_4_EmployeeMenu.this);
			bEm_frame.setVisible(true);
		}
		if (e.getSource() == EarningSearch_Menu)
		{
			message.setText("The average salary of employees is:");
			double sum = 0;
			int i,j;
			int num1 = this.Cemployees.size();
			int num2 = this.Bemployees.size();
			for (i = 0; i < num1; i ++)
			{
				sum += this.Cemployees.get(i).earning();
			}
			
			for (i = 0; i < num2; i ++)
			{
				sum += this.Bemployees.get(i).earning();
			}
			
			double ave = sum / (num1 + num2);
			average.setText(Double.toString(ave));
			
			System.out.println("The sum of the salaries: " + sum + "\n");
			System.out.println("The number of the employees: "+ (num1+num2) + "\n");
			System.out.println("The average salary of employees is: " + ave + "\n");
			for (j = 0; j < num1; j ++)
			{
				System.out.println(this.Cemployees.get(j).toString());
			}
			for (j = 0; j < num2; j ++)
			{
				System.out.println(this.Bemployees.get(j).toString());
			}
			
		}
	}
	
	public static void main(String arg[])
	{
		_14211168_周尧棋_4_EmployeeMenu M = new _14211168_周尧棋_4_EmployeeMenu();
		//set the title of the frame
		M.setTitle("Employee");
		
	}

}
