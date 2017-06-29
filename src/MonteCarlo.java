
public class MonteCarlo implements OptionPricing{

	double s, x, r, sigma, t;
	
    public double MonteCarloCall() {
        int n = 10000;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double eps = StdRandom.gaussian();
            double price = s * Math.exp(r*t - 0.5*sigma*sigma*t + sigma*eps*Math.sqrt(t));
  	        double value = Math.max(price - x, 0);
            sum += value;
        }
        double mean  = sum / n ;  
        return Math.exp(-r*t) * mean;
    }

    public  double MonteCarloPut() {
        int n = 10000;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double eps = StdRandom.gaussian();
            double price = s * Math.exp(r*t - 0.5*sigma*sigma*t + sigma*eps*Math.sqrt(t));
  	        double value = Math.max(x - price, 0);
            sum += value;
        }
        double mean = sum / n;   
        return Math.exp(-r*t) * mean;
    }

	@Override
	public String calculate(double s, double x, double r, double sigma, double t) {
		this.s = s;
		this.x = x;
		this.r = r;
		this.sigma = sigma;
		this.t = t;	

		return "\nMonte Carlo Call:" + MonteCarloCall() + "\n\nMonte Carlo Put:" + MonteCarloPut();
	}
}
