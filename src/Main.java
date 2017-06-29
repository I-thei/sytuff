import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static double s = 0, x = 0, r = 0, sigma = 0, t = 0, lookback = 0;
	static OptionWindow ow; static VolatilityPricing vp;
	static String out = ""; static String out2 = "";
	
	public static void main(String[] args){
	
	ow = new OptionWindow(); 
	vp = new VolatilityPricing();
	
	ow.btnCompute.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				getValues();
			} catch(Exception ex) {
				out = "Error in inputs";
				ow.frame.repaint();
			};
			
		    String name = ow.comboBox.getSelectedItem().toString();
		    switch(name) {
		    	case "Black Scholes":
		    		BlackScholes bs = new BlackScholes();
		            out = bs.calculate(s, x, r, sigma, t);
		            break;
		    	case "Monte Carlo":
		    		MonteCarlo mc = new MonteCarlo();
		            out = mc.calculate(s, x, r, sigma, t);
		            break;
		    	case "Binomial":
		    		BinomialOption b = new BinomialOption();
		            out = b.calculate(s, x, r, sigma, t);
		            break;
		            
		    	case "Trinomial":
		    		TrinomialOption to = new TrinomialOption();
		            out = to.calculate(s, x, r, sigma, t);
		            break;		    		
		    }
		    	ow.updateText(out);
			}
	});
	
	vp.btnCompute.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				getValues2();
			} catch(Exception ex) {
				out2 = "Error in inputs";
				vp.frame.repaint();
			};
		
			 
		    
			 String name = vp.comboBox.getSelectedItem().toString();
			 try {
			 switch(name) {
		    	case "Historical Volatility": 
		    		Process p = Runtime.getRuntime().exec("python27 HV.py "+(vp.textField.getText()));
					BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
					out2 = "Standard Deviation:\n";
					out2 += in.readLine();
					break;
		    	case "EWMA":
		    		Process p2 = Runtime.getRuntime().exec("python27 EWMA.py "+(vp.textField.getText()));
					BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
					out2 = "Assuming lambda = 0.94:\n";
					out2 += in2.readLine();
		    		break;
		    	case "Heston":
		    		break;
		    	case "SABR":
		    		break;
		    	case "GARCH":
		    		Process p3 = Runtime.getRuntime().exec("python27 GARCH.py "+(vp.textField.getText()));
					BufferedReader in3 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
					out2 = "Volatility for each day:\n";
					String text = in3.readLine();
					while(text != null){
						if(in3 != null){
							out2 += text + "\n";
							System.out.println(out2);
							text = in3.readLine();
						}
					}
		    		break;
		    }
			} catch (Exception e1) {
					e1.printStackTrace();
			}
		    	vp.updateText(out2);
			}
	});
	
}

	private static void getValues() {
		s = Double.parseDouble(ow.textField.getText());
	    x = Double.parseDouble(ow.textField_1.getText());
	    r = Double.parseDouble(ow.textField_3.getText());
	    sigma = Double.parseDouble(ow.textField_2.getText());
	    t = Double.parseDouble(ow.textField_4.getText());
	}
	
	private static void getValues2() {
		lookback = Double.parseDouble(vp.textField.getText());
	}
}
