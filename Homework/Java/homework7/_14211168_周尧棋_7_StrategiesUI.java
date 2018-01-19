package homework7;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class _14211168_÷‹“¢∆Â_7_StrategiesUI extends JDialog implements ActionListener
{
	protected JButton View_Button = new JButton("View");
	protected JButton Add_Button = new JButton("Add");
	protected JButton Delete_Button = new JButton("Delete");
	protected JButton Update_Button = new JButton("Update");
	protected JButton Reset_Button1 = new JButton("Reset");
	protected JButton Reset_Button2 = new JButton("Reset");
	protected JButton Reset_Button3 = new JButton("Reset");
	
	protected JLabel title = new JLabel("Strategy Maintenance",SwingUtilities.CENTER);
	protected JLabel No1 = new JLabel("Strategy Number:      ",SwingUtilities.CENTER);
	protected JLabel No2 = new JLabel("Strategy Number:      ",SwingUtilities.CENTER);
	protected JLabel No3 = new JLabel("Strategy Number:      ",SwingUtilities.CENTER);
	protected JLabel Name1 = new JLabel("Strategy Name:      ",SwingUtilities.CENTER);
	protected JLabel Name3 = new JLabel("Strategy Name:      ",SwingUtilities.CENTER);
	protected JLabel Type1 = new JLabel("Strategy Type:      ",SwingUtilities.CENTER);
	protected JLabel Type3 = new JLabel("Strategy Type:      ",SwingUtilities.CENTER);
	protected JLabel Book1 = new JLabel("Applied Book Type:      ",SwingUtilities.CENTER);
	protected JLabel Book3 = new JLabel("Applied Book Type:      ",SwingUtilities.CENTER);
	protected JLabel Discount1 = new JLabel("Discount:      ",SwingUtilities.CENTER);
	protected JLabel Discount3 = new JLabel("Discount:      ",SwingUtilities.CENTER);
	protected JLabel Delete = new JLabel("Please input the number of the strategy you want to delete:");
	
	protected JTextField No_text1 = new JTextField();
	protected JTextField No_text2 = new JTextField();
	protected JTextField No_text3 = new JTextField();
	protected JTextField Name_text1 = new JTextField();
	protected JTextField Name_text3 = new JTextField();
	protected JTextField Type_text1 = new JTextField();
	protected JTextField Type_text3 = new JTextField();
	protected JTextField Book_text1 = new JTextField();
	protected JTextField Book_text3 = new JTextField();
	protected JTextField Discount_text1 = new JTextField();
	protected JTextField Discount_text3 = new JTextField();
	
	protected JPanel jp_center_left1 = new JPanel(new GridLayout(5,1,10,10));
	protected JPanel jp_center_left3 = new JPanel(new GridLayout(5,1,10,10));
	protected JPanel jp_center_right1 = new JPanel(new GridLayout(5,1,10,10));
	protected JPanel jp_center_right3 = new JPanel(new GridLayout(5,1,10,10));
	protected JPanel jp_center1 = new JPanel(new GridLayout(1,2,5,5));
	protected JPanel jp_center3 = new JPanel(new GridLayout(1,2,5,5));
	protected JPanel jp_south1 = new JPanel(new GridLayout(1,2,20,20));
	protected JPanel jp_south3 = new JPanel(new GridLayout(1,2,20,20));
	protected JPanel jp_add = new JPanel(new BorderLayout());
	protected JPanel jp_update = new JPanel(new BorderLayout());
	
	protected JPanel jp_delete_center = new JPanel(new GridLayout(2,2,10,10));
	protected JPanel jp_delete = new JPanel();
	
	String[] tableHead = {"Number", "Name", "Type", "Applied Book Type", "Discount"};
	final int table_high = 20;
	String[][] content = new String[table_high][5];
	final DefaultTableModel model = new DefaultTableModel(content,tableHead);
	protected JTable strategy_table;
	
	protected JTabbedPane choiceTabbedPane = new JTabbedPane(JTabbedPane.TOP);

	_14211168_÷‹“¢∆Â_7_Controller controller = null;
	
	_14211168_÷‹“¢∆Â_7_StrategiesUI(_14211168_÷‹“¢∆Â_7_Controller Ctrl)
	{
		this.controller = Ctrl;
		
		strategy_table = new JTable(model)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		HashMap<String,_14211168_÷‹“¢∆Â_7_IPricingStrategy> map;
		map = _14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap();
		Iterator<String> iter = map.keySet().iterator();
		int i = 0;
		while (iter.hasNext())
		{
			String key = (String)iter.next();
			_14211168_÷‹“¢∆Â_7_IPricingStrategy tmp = (_14211168_÷‹“¢∆Â_7_IPricingStrategy) map.get(key);
			model.setValueAt(tmp.getNo(), i, 0);
			model.setValueAt(tmp.getName(), i, 1);
			model.setValueAt(tmp.getType(), i, 2);
			model.setValueAt(tmp.getBook(), i, 3);
			model.setValueAt(tmp.getDiscount(), i, 4);
			i ++;
		}
		
		TableColumn column = null;
		int columns = strategy_table.getColumnCount();
		for (i = 0; i < columns; i ++)
		{
			column = strategy_table.getColumnModel().getColumn(i);
			column.setPreferredWidth(150);
		}
		strategy_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(strategy_table);
		scroll.setSize(300,200);
		choiceTabbedPane.addTab("View",scroll);
		
		Font font = new Font("Calibri",Font.BOLD,24);
		title.setFont(font);
		title.setBorder(BorderFactory.createEtchedBorder());
		getContentPane().add(title,BorderLayout.NORTH);
		
		font = new Font("Calibri",Font.BOLD,15);
		No1.setFont(font);
		Name1.setFont(font);
		Type1.setFont(font);
		Book1.setFont(font);
		Discount1.setFont(font);
		
		jp_center_left1.add(No1);
		jp_center_left1.add(Name1);
		jp_center_left1.add(Type1);
		jp_center_left1.add(Book1);
		jp_center_left1.add(Discount1);
		
		jp_center_right1.add(No_text1);
		jp_center_right1.add(Name_text1);
		jp_center_right1.add(Type_text1);
		jp_center_right1.add(Book_text1);
		jp_center_right1.add(Discount_text1);
		Border b1 = BorderFactory.createEmptyBorder(15,15,15,15);
		Border b2 = BorderFactory.createTitledBorder("Information:");
		jp_center1.setBorder(BorderFactory.createCompoundBorder(b1, b2));
		jp_center1.add(jp_center_left1);
		jp_center1.add(jp_center_right1);
		
		Add_Button.setBorder(BorderFactory.createRaisedBevelBorder());
		Reset_Button1.setBorder(BorderFactory.createRaisedBevelBorder());
		jp_south1.add(Add_Button);
		jp_south1.add(Reset_Button1);
		jp_add.add(jp_center1,BorderLayout.NORTH);
		jp_add.add(jp_south1,BorderLayout.CENTER);
		choiceTabbedPane.addTab("Add",jp_add);
		
		font = new Font("Calibri",Font.BOLD,15);
		No2.setFont(font);
		font = new Font("Calibri",Font.BOLD,18);
		Delete.setFont(font);
		Delete_Button.setBorder(BorderFactory.createRaisedBevelBorder());
		Reset_Button2.setBorder(BorderFactory.createRaisedBevelBorder());
		jp_delete_center.add(No2);
		jp_delete_center.add(No_text2);
		jp_delete_center.add(Delete_Button);
		jp_delete_center.add(Reset_Button2);
		jp_delete_center.setPreferredSize(new Dimension(300,80));
		jp_delete.add(Delete,BorderLayout.NORTH);
		jp_delete.add(jp_delete_center,BorderLayout.CENTER);
		choiceTabbedPane.addTab("Delete",jp_delete);
		
		font = new Font("Calibri",Font.BOLD,15);
		No3.setFont(font);
		Name3.setFont(font);
		Type3.setFont(font);
		Book3.setFont(font);
		Discount3.setFont(font);
		
		jp_center_left3.add(No3);
		jp_center_left3.add(Name3);
		jp_center_left3.add(Type3);
		jp_center_left3.add(Book3);
		jp_center_left3.add(Discount3);
		jp_center_right3.add(No_text3);
		jp_center_right3.add(Name_text3);
		jp_center_right3.add(Type_text3);
		jp_center_right3.add(Book_text3);
		jp_center_right3.add(Discount_text3);
		jp_center3.setBorder(BorderFactory.createCompoundBorder(b1, b2));
		jp_center3.add(jp_center_left3);
		jp_center3.add(jp_center_right3);
		
		Update_Button.setBorder(BorderFactory.createRaisedBevelBorder());
		Reset_Button3.setBorder(BorderFactory.createRaisedBevelBorder());
		jp_south3.add(Update_Button);
		jp_south3.add(Reset_Button3);
		jp_update.add(jp_center3,BorderLayout.NORTH);
		jp_update.add(jp_south3,BorderLayout.CENTER);
		choiceTabbedPane.addTab("Update",jp_update);
		
		getContentPane().add(choiceTabbedPane);
		
		setTitle("Strategy"); 
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(533,330);
		
		Add_Button.addActionListener(this);
		Delete_Button.addActionListener(this);
		Update_Button.addActionListener(this);
		Reset_Button1.addActionListener(this);
		Reset_Button2.addActionListener(this);
		Reset_Button3.addActionListener(this);
		choiceTabbedPane.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e) 
			{
				change(e);
			}
		});
	}
	
	private void change(ChangeEvent e)
	{
		JTabbedPane tab = (JTabbedPane)e.getSource();
		int select = tab.getSelectedIndex();
		if (select == 0)
		{
			HashMap<String,_14211168_÷‹“¢∆Â_7_IPricingStrategy> map;
			map = _14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap();
			Iterator<String> iter = map.keySet().iterator();
			int i = 0;
			while (iter.hasNext())
			{
				String key = (String)iter.next();
				_14211168_÷‹“¢∆Â_7_IPricingStrategy tmp = (_14211168_÷‹“¢∆Â_7_IPricingStrategy) map.get(key);
				model.setValueAt(tmp.getNo(), i, 0);
				model.setValueAt(tmp.getName(), i, 1);
				model.setValueAt(tmp.getType(), i, 2);
				model.setValueAt(tmp.getBook(), i, 3);
				model.setValueAt(tmp.getDiscount(), i, 4);
				i ++;
			}
		}
		else
		{
			for (int i = 0; i < table_high; i ++)
			{
				model.setValueAt(null, i, 0);
				model.setValueAt(null, i, 1);
				model.setValueAt(null, i, 2);
				model.setValueAt(null, i, 3);
				model.setValueAt(null, i, 4);
			}
		}
	}
	
	public void JudgeCompositeInput(String no, String name, int type, int book, String discount)
	{
		int cnt = 0;
		String strategies[] = discount.split("\\|");
		HashMap<String,_14211168_÷‹“¢∆Â_7_IPricingStrategy> map;
		map = _14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap();
		for (int i = 0; i < strategies.length; i ++)
		{
			if (map.containsKey(strategies[i]))
			{
				cnt ++;
			}
		}
		if (cnt == strategies.length)
		{
			controller.addCompositeStrategy(no,name,type,book,strategies);
		}
		else
		{
			String message1 = "Some of the substrategies don't exit!";
			JOptionPane.showMessageDialog(null, message1, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == Add_Button)
		{
			try
			{
				if (No_text1.getText().equals("")|| Name_text1.getText().equals("") || Type_text1.getText().equals("")
						|| Book_text1.getText().equals("") || Discount_text1.getText().equals(""))
				{
					throw new InputException();
				}
				else
				{
					String no = No_text1.getText();
					String name = Name_text1.getText();
					int type = Integer.parseInt(Type_text1.getText());
					int book = Integer.parseInt(Book_text1.getText());
					String discount = Discount_text1.getText();
					if (_14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().containsKey(no))
					{
						throw new ExitException("The strategy already exits!");
					}
					else
					{
						if (type == 1 || type == 2)
						{
							controller.addSimpleStrategy(no,name,type,book,discount);
						}
						else
						{
							JudgeCompositeInput(no,name,type,book,discount);
						}
					}
				}
				
			}
			
			catch (InputException i)
			{
				String message1 = "The input information was not complete!";
				JOptionPane.showMessageDialog(null, message1, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			catch (ExitException ee)
			{
				String message = "The strategy already exits!\nThe option may change the info of the strategy!\nContinue?";
				int choice = JOptionPane.showConfirmDialog(null,message,"Attention!",JOptionPane.YES_NO_OPTION);
				String no = No_text1.getText();
				String name = Name_text1.getText();
				int type = Integer.parseInt(Type_text1.getText());
				int book = Integer.parseInt(Book_text1.getText());
				String discount = Discount_text1.getText();
				if (choice == 0)
				{
					if (type == 1 || type == 2)
					{
						controller.addSimpleStrategy(no,name,type,book,discount);
					}
					else
					{
						JudgeCompositeInput(no,name,type,book,discount);
					}
				}
			}
			
		}
		
		if (e.getSource() == Delete_Button)
		{
			try
			{
				if (No_text2.getText().equals(""))
				{
					throw new InputException();
				}
				String no = No_text2.getText();
				if (!_14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().containsKey(no))
				{
					throw new ExitException("The strategy doesn't exit!");
				}
				else
				{
					controller.deleteStrategy(no);
				}
			}
			
			catch (InputException ex)
			{
				String message = "The input information was not complete!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			catch (ExitException ee)
			{
				String message = "The strategy doesn't exit!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource() == Update_Button)
		{
			try
			{
				if (No_text3.getText().equals("") || Name_text3.getText().equals("") || Type_text3.getText().equals("")
						|| Book_text3.getText().equals("") || Discount_text3.getText().equals(""))
				{
					throw new InputException();
				}
				String no = No_text3.getText();
				String name = Name_text3.getText();
				int type = Integer.parseInt(Type_text3.getText());
				int book = Integer.parseInt(Book_text3.getText());
				String discount = Discount_text3.getText();
				if (!_14211168_÷‹“¢∆Â_7_PricingStrategyFactory.getInstance().getStrategyCatalog().getHashMap().containsKey(no))
				{
					throw new ExitException("The strategy doesn't exit!");
				}
				else
				{
					controller.updateStrategy(no,name,type,book,discount);
				}
			}
			
			catch(InputException ex)
			{
				String message = "The input information was not complete!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			catch(ExitException ee)
			{
				String message = "The strategy doesn't exit!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if (e.getSource() == Reset_Button1)
		{
			No_text1.setText(null);
			Name_text1.setText(null);
			Type_text1.setText(null);
			Book_text1.setText(null);
			Discount_text1.setText(null);
		}
		
		if (e.getSource() == Reset_Button2)
		{
			No_text2.setText(null);
		}
		
		if (e.getSource() == Reset_Button3)
		{
			No_text3.setText(null);
			Name_text3.setText(null);
			Type_text3.setText(null);
			Book_text3.setText(null);
			Discount_text3.setText(null);
		}
	}
}
