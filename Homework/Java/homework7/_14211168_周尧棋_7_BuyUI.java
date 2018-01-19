package homework7;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class _14211168_周尧棋_7_BuyUI extends JFrame implements ActionListener
{
	private JTable show;	//所有图书显示
	
	protected JLabel title = new JLabel("Buy", SwingUtilities.CENTER);
	protected JLabel isbn = new JLabel("ISBN:",SwingUtilities.RIGHT);
	protected JLabel numbers = new JLabel("Numbers:",SwingUtilities.RIGHT);
	protected JTextField isbn_text = new JTextField(10);
	protected JTextField num_text = new JTextField(5);
	
	protected JPanel jp_south = new JPanel();
	protected JPanel jp_up = new JPanel(new BorderLayout());
	protected JPanel jp_buy = new JPanel();
	protected JButton Buy_button = new JButton("Buy");
	protected JButton Reset_button = new JButton("Reset");
	
	_14211168_周尧棋_7_Controller controller = null;
	_14211168_周尧棋_7_ShoppingCarUI shopping;
	
	private void initTable()
	{
		String[] tableHead = {"ISBN", "Book Name", "Price", "Book Type"};
		String[][] content = new String[30][2];
		final DefaultTableModel model = new DefaultTableModel(content,tableHead);
		show = new JTable(model)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		for (int i = 0; i < controller.bookCatalog.books.size(); i ++)
		{
			_14211168_周尧棋_7_BookSpecification aBook = controller.bookCatalog.books.get(i);
			model.setValueAt(aBook.isbn, i, 0);
			model.setValueAt(aBook.title, i, 1);
			model.setValueAt(Double.toString(aBook.price), i, 2);
			model.setValueAt(Integer.toString(aBook.type), i, 3);
		}
		
		TableColumn column = null;
		int columns = show.getColumnCount();
		for (int i = 0; i < columns; i ++)
		{
			column = show.getColumnModel().getColumn(i);
			column.setPreferredWidth(150);
		}
		show.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(show);
		scroll.setSize(300,150);
		jp_up.add(scroll,BorderLayout.CENTER);
	}
	
	_14211168_周尧棋_7_BuyUI(_14211168_周尧棋_7_Controller Ctrl)
	{
		this.controller = Ctrl;
		shopping = new _14211168_周尧棋_7_ShoppingCarUI(controller);
		jp_buy.setLayout(new BoxLayout(jp_buy,BoxLayout.Y_AXIS));
		
		Font font = new Font("Calibri",Font.BOLD,24);
		title.setFont(font);
		title.setBorder(BorderFactory.createEtchedBorder());
		jp_up.add(title,BorderLayout.NORTH);
		
		initTable();
		
		jp_buy.add(jp_up);
		jp_buy.add(Box.createVerticalStrut(10));
		jp_south.add(Box.createHorizontalStrut(50));
		jp_south.add(isbn);
		jp_south.add(Box.createHorizontalStrut(5));
		jp_south.add(isbn_text);
		jp_south.add(Box.createHorizontalStrut(20));
		jp_south.add(numbers);
		jp_south.add(Box.createHorizontalStrut(5));
		jp_south.add(num_text);
		jp_south.add(Box.createHorizontalStrut(50));
		jp_south.add(Buy_button);
		jp_south.add(Box.createHorizontalStrut(5));
		jp_south.add(Reset_button);
		jp_south.add(Box.createHorizontalStrut(50));
		jp_south.setLayout(new BoxLayout(jp_south,BoxLayout.X_AXIS));
		jp_south.setPreferredSize(new java.awt.Dimension(400,25));
		jp_buy.add(jp_south);
		jp_buy.add(Box.createVerticalStrut(10));
		
		getContentPane().add(jp_buy);
		
		setTitle("Buy"); 
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600,300);
		
		Buy_button.addActionListener(this);
		Reset_button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == Buy_button)
		{
			try
			{
				if (isbn_text.getText().equals("") || num_text.getText().equals(""))
				{
					throw new InputException();
				}
				else
				{
					String isbn = isbn_text.getText();
					int numbers = Integer.parseInt(num_text.getText());
					if (controller.getBookCatalog().getBookSpecification(isbn) == null)
					{
						throw new ExitException("The book doesn't exit!");
					}
					else
					{
						controller.buyBook(isbn,numbers);
						controller.getSale().registerObserver(shopping);
						controller.getSale().notifyObservers();
						shopping.setVisible(true);
					}
				}
			}
			
			catch (InputException ex)
			{
				String message = "The input information was not complete!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			catch (ExitException ee)
			{
				String message = "The book doesn't exit!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource() == Reset_button)
		{
			isbn_text.setText(null);
			num_text.setText(null);
		}
	}
}
