package homework7;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class _14211168_周尧棋_7_ShoppingCarUI extends JFrame implements _14211168_周尧棋_7_Observer
{
	private JTable show;	//购物结果显示
	String[] tableHead = {"Book Name", "Copies"};
	String[][] content = new String[30][2];
	final DefaultTableModel model = new DefaultTableModel(content,tableHead);
	
	protected JLabel title = new JLabel("Goods List", SwingUtilities.CENTER);
	private JLabel total = new JLabel("Total: $");
	private JTextField money = new JTextField(10);//价格显示
	private JPanel jp_south = new JPanel();
	private JPanel jp_up = new JPanel(new BorderLayout());
	private JPanel jp_shop = new JPanel();
	
	_14211168_周尧棋_7_Controller controller;
	
	private void initTable()
	{
		show = new JTable(model)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		TableColumn column = null;
		int columns = show.getColumnCount();
		for (int i = 0; i < columns; i ++)
		{
			column = show.getColumnModel().getColumn(i);
			column.setPreferredWidth(270);
		}
		show.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(show);
		jp_shop.add(scroll, BorderLayout.CENTER);
	}
	
	_14211168_周尧棋_7_ShoppingCarUI(_14211168_周尧棋_7_Controller Ctrl)
	{
		this.controller = Ctrl;
		
		Font font = new Font("Calibri",Font.BOLD,20);
		title.setFont(font);
		title.setBorder(BorderFactory.createEtchedBorder());
		jp_shop.add(title, BorderLayout.NORTH);
		initTable();
		
		jp_south.setLayout(new BoxLayout(jp_south,BoxLayout.X_AXIS));
		jp_south.add(jp_up);
		jp_south.add(Box.createVerticalStrut(10));
		jp_south.add(total);
		jp_south.add(Box.createHorizontalStrut(10));
		jp_south.add(money);
		jp_south.add(Box.createVerticalStrut(10));
		jp_shop.add(jp_south, BorderLayout.SOUTH);
		getContentPane().add(jp_shop);
		
		this.setTitle("Shopping Car");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550,550);
		money.setEditable(false);

	}
	
	public void update(_14211168_周尧棋_7_Sale sale)
	{
		for (int i = 0; i < sale.items.size(); i ++)
		{
			model.setValueAt(sale.items.get(i).bookSpec.isbn, i, 0);
			model.setValueAt(Integer.toString(sale.items.get(i).copies), i, 1);
		}
		money.setText(Double.toString(sale.getTotal()));
	}
}
