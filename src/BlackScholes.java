public class BlackScholes implements OptionPricing {

	double d1, d2, s, x, r, sigma, t;
	    
    public double callPrice() {
        return s * Gaussian.cdf(d1) - x * Math.exp(-r*t) * Gaussian.cdf(d2);
    }

    public double putPrice() {
        return x * Math.exp(-r*t) * Gaussian.cdf(-d2) - s * Gaussian.cdf(-d1);
    }

    public double gamma() {
    	return Gaussian.pdf(d1)/(s * sigma * Math.sqrt(t));
    }

    public double vega() {
    	return Gaussian.pdf(d1) * s * Math.sqrt(t);
    }

    public double callDelta() {
    	return Gaussian.cdf(d1);
    }

    public double callTheta() {
    	return -(s * Gaussian.pdf(d1) * sigma) / (2 * Math.sqrt(t)) - r * x * Math.exp(-r * t) * Gaussian.cdf(d2) ;
    }

    public double callRho() {
    	return  x * t * Math.exp(-r * t) * Gaussian.cdf(d2);
    }

    public double putDelta() {
    	return Gaussian.cdf(d1) - 1;
    }

    public double putTheta() {
    	return -(s * Gaussian.pdf(d1) * sigma) / (2 * Math.sqrt(t)) + r * x * Math.exp(-r * t) * Gaussian.cdf(-d2) ;
    }

    public double putRho() {
    	return -x * t * Math.exp(-r * t) * Gaussian.cdf(-d2);

    }

	@Override
	public String calculate(double s, double x, double r, double sigma, double t) {
		this.s = s;
		this.x = x;
		this.r = r;
		this.sigma = sigma;
		this.t = t;
		
		this.d1 = (Math.log(s/x) + (r + sigma * sigma/2) * t) / (sigma * Math.sqrt(t));
		this.d2 = this.d1 - sigma * Math.sqrt(t);
		
		return "Call: " + callPrice() +
				"\nCall Delta: " + callDelta() + 
				"\nCall Gamma: " + gamma() + 
				"\nCall Vega: " + vega() +
				"\nCall Theta: " + callTheta() +
				"\nCall Rho: " + callRho() + "\n" +
				"\nPut: " + putPrice() + 
				"\nPut Delta: " + putDelta() + 
				"\nPut Gamma: " + gamma() +
				"\nPut Vega: " + vega() +
				"\nPut Theta: " + putTheta() +
				"\nPut Rho: " + putRho();
	}
}
