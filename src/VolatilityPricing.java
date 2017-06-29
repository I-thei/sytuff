import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import javax.swing.JButton;

public class VolatilityPricing {

	JFrame frame;
	JTextField textField;
	JComboBox<String> comboBox;
	JTextArea textArea = new JTextArea();
	JButton btnCompute;

	/**
	 * Create the application.
	 */
	public VolatilityPricing() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 412);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblStockPrice = new JLabel("No. of Days for Lookback:");
		lblStockPrice.setBounds(13, 25, 152, 14);
		frame.getContentPane().add(lblStockPrice);
		
		textArea.setBounds(13, 67, 441, 266);
		textArea.setEditable(false);
		frame.getContentPane().add(textArea);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Historical Volatility", "EWMA", "Heston", "SABR", "GARCH"}));
		comboBox.setBounds(327, 22, 127, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(170, 22, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnCompute = new JButton("Compute");
		btnCompute.setBounds(193, 344, 89, 23);
		frame.getContentPane().add(btnCompute);
	}

	public void updateText(String out) {
		textArea.setText(out);
		frame.repaint();
		
	}
}

