package homework7;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class InputException extends Exception
{
	public InputException()
	{
		System.out.println("Input information error!");
	}
}

class ExitException extends Exception
{
	public ExitException(String ms)
	{
		System.out.println(ms);
	}
}

public class _14211168_÷‹“¢∆Â_7_mainUI extends JFrame implements ActionListener
{
	protected JLabel title = new JLabel("BUPT Shopping System", SwingUtilities.CENTER);
	protected JButton AddBook = new JButton("AddBook");
	protected JButton Strategies = new JButton("Strategies");
	protected JButton Buy = new JButton("Buy");
	private JPanel jp_main = new JPanel(new BorderLayout());
	private JPanel jp_button = new JPanel();
	
	_14211168_÷‹“¢∆Â_7_Controller controller = new _14211168_÷‹“¢∆Â_7_Controller();
	
	_14211168_÷‹“¢∆Â_7_mainUI()
	{		
		Font font = new Font("Calibri",Font.BOLD,24);
		title.setFont(font);
		title.setBorder(BorderFactory.createEtchedBorder());
		jp_main.add(title,BorderLayout.NORTH);
		
		jp_button.setLayout(new BoxLayout(jp_button,BoxLayout.X_AXIS));
		jp_button.add(Box.createHorizontalStrut(55));
		jp_button.add(AddBook);
		jp_button.add(Box.createHorizontalStrut(10));
		jp_button.add(Strategies);
		jp_button.add(Box.createHorizontalStrut(10));
		jp_button.add(Buy);
		jp_button.add(Box.createHorizontalStrut(55));
		jp_main.add(jp_button, BorderLayout.CENTER);
		getContentPane().add(jp_main);

		// choose settings for the frame and make it visible
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(400,200);
	    setLocation(400,300);
	    setVisible(true);
	    AddBook.addActionListener(this);
	    Strategies.addActionListener(this);
	    Buy.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == AddBook)
		{
			_14211168_÷‹“¢∆Â_7_AddBookUI addbook = new _14211168_÷‹“¢∆Â_7_AddBookUI(this.controller);
			addbook.setVisible(true);
		}
		
		if (e.getSource() == Strategies)
		{
			_14211168_÷‹“¢∆Â_7_StrategiesUI strategies = new _14211168_÷‹“¢∆Â_7_StrategiesUI(this.controller);
			strategies.setVisible(true);
		}
		
		if (e.getSource() == Buy)
		{
			_14211168_÷‹“¢∆Â_7_BuyUI buy = new _14211168_÷‹“¢∆Â_7_BuyUI(this.controller);
			buy.setVisible(true);
		}
	}
	
	public static void main(String arg[])
	{
		_14211168_÷‹“¢∆Â_7_mainUI Main = new _14211168_÷‹“¢∆Â_7_mainUI();
		//set the title of the frame
		Main.setTitle("Shopping System");
	}
}
