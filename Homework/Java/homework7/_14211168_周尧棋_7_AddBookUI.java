package homework7;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class _14211168_÷‹“¢∆Â_7_AddBookUI extends JFrame implements ActionListener
{
	protected JButton Add_Button = new JButton("Add");
	protected JButton Reset_Button = new JButton("Reset");
	
	protected JLabel title = new JLabel("Add Book", SwingUtilities.CENTER);
	protected JLabel ISBN = new JLabel("ISBN: ",SwingUtilities.CENTER);
	protected JLabel Title = new JLabel("Book Title: ",SwingUtilities.CENTER);
	protected JLabel Price = new JLabel("Price: ",SwingUtilities.CENTER);
	protected JLabel Type = new JLabel("Type: ",SwingUtilities.CENTER);
	
	protected JTextField ISBN_text = new JTextField(15);
	protected JTextField Title_text = new JTextField(15);
	protected JTextField Price_text = new JTextField(15);
	protected JTextField Type_text = new JTextField(15);
	
	protected JPanel jp_center_left = new JPanel(new GridLayout(4,1,10,10));
	protected JPanel jp_center_right = new JPanel(new GridLayout(4,1,10,10));
	protected JPanel jp_center = new JPanel(new GridLayout(1,2,5,5));
	protected JPanel jp_south = new JPanel();
	protected JPanel jp_add = new JPanel(new BorderLayout());
	
	_14211168_÷‹“¢∆Â_7_Controller controller = null;
	
	_14211168_÷‹“¢∆Â_7_AddBookUI(_14211168_÷‹“¢∆Â_7_Controller Ctrl)
	{
		this.controller = Ctrl;
		
		Font font = new Font("Calibri",Font.BOLD,24);
		title.setFont(font);
		title.setBorder(BorderFactory.createEtchedBorder());
		getContentPane().add(title,BorderLayout.NORTH);
		
		font = new Font("Calibri",Font.BOLD,15);
		ISBN.setFont(font);
		Title.setFont(font);
		Price.setFont(font);
		Type.setFont(font);
		
		jp_center_left.add(ISBN);
		jp_center_left.add(Title);
		jp_center_left.add(Price);
		jp_center_left.add(Type);
		jp_center_right.add(ISBN_text);
		jp_center_right.add(Title_text);
		jp_center_right.add(Price_text);
		jp_center_right.add(Type_text);
		jp_center.add(jp_center_left);
		jp_center.add(jp_center_right);
		Border b1 = BorderFactory.createEmptyBorder(5,5,5,5);
		Border b2 = BorderFactory.createTitledBorder("Book Information:");
		jp_center.setBorder(BorderFactory.createCompoundBorder(b1, b2));
		getContentPane().add(jp_center);
		
		Add_Button.setBorder(BorderFactory.createRaisedBevelBorder());
		Reset_Button.setBorder(BorderFactory.createRaisedBevelBorder());
		jp_south.setLayout(new BoxLayout(jp_south,BoxLayout.X_AXIS));
		Add_Button.setPreferredSize(new Dimension(120,90));
		Reset_Button.setPreferredSize(new Dimension(120,90));
		jp_south.add(Box.createHorizontalStrut(70));
		jp_south.add(Add_Button);
		jp_south.add(Box.createHorizontalStrut(70));
		jp_south.add(Reset_Button);
		jp_south.add(Box.createHorizontalStrut(70));
		
		jp_add.add(jp_center,BorderLayout.NORTH);
		jp_add.add(jp_south,BorderLayout.CENTER);
		getContentPane().add(jp_add);
		
		this.setTitle("Book"); 
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(436,270);
		
		Add_Button.addActionListener(this);
		Reset_Button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == Add_Button)
		{
			try
			{
				if (ISBN_text.getText().equals("") || Title_text.getText().equals("") || Price_text.getText().equals("") 
						|| Type_text.getText().equals(""))
				{
					throw new InputException();
				}
				else
				{
					String isbn = ISBN_text.getText();
					String name = Title_text.getText();
					if (controller.getBookCatalog().getBookSpecification(isbn) == null)
					{
						double price = Integer.parseInt(Price_text.getText());
						int type = Integer.parseInt(Type_text.getText());
						controller.addBook(isbn,name,price,type);
					}
					else
					{
						throw new ExitException("The book already exits!");
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
				String message = "The book already exits!";
				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource() == Reset_Button)
		{
			ISBN_text.setText(null);
			Title_text.setText(null);
			Price_text.setText(null);
			Type_text.setText(null);
		}
	}
}