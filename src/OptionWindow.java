import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import javax.swing.JButton;

public class OptionWindow {

	JFrame frame;
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	JTextField textField_3;
	JTextField textField_4;	
	JComboBox<String> comboBox;
	JTextArea textArea = new JTextArea();
	JButton btnCompute;

	/**
	 * Create the application.
	 */
	public OptionWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 458);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblStockPrice = new JLabel("Stock Price:");
		lblStockPrice.setBounds(13, 25, 89, 14);
		frame.getContentPane().add(lblStockPrice);
		
		JLabel lblStrikePrice = new JLabel("Strike Price:");
		lblStrikePrice.setBounds(13, 50, 89, 14);
		frame.getContentPane().add(lblStrikePrice);
		
		JLabel lblVolatility = new JLabel("Volatility:");
		lblVolatility.setBounds(13, 75, 89, 14);
		frame.getContentPane().add(lblVolatility);
		
		JLabel lblRiskfreeInterestRate = new JLabel("Risk-free Interest Rate:");
		lblRiskfreeInterestRate.setBounds(222, 25, 165, 14);
		frame.getContentPane().add(lblRiskfreeInterestRate);
		
		JLabel lblTimeToExpiration = new JLabel("Time to Expiration (yrs):");
		lblTimeToExpiration.setBounds(222, 50, 135, 14);
		frame.getContentPane().add(lblTimeToExpiration);
		
		textArea.setBounds(13, 103, 441, 266);
		textArea.setEditable(false);
		frame.getContentPane().add(textArea);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Black Scholes", "Monte Carlo", "Binomial", "Trinomial"}));
		comboBox.setBounds(327, 75, 127, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(87, 22, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(87, 47, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(87, 72, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(368, 22, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(368, 47, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		btnCompute = new JButton("Compute");
		btnCompute.setBounds(186, 380, 89, 23);
		frame.getContentPane().add(btnCompute);
	}

	public void updateText(String out) {
		textArea.setText(out);
		frame.repaint();
		
	}
}
