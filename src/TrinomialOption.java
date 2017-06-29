
public class TrinomialOption implements OptionPricing {

	double s, x, r, sigma, t, dt, pu, pm, pd;
	int n;
	double[][] p;
	
    public void initialize(double S, double x, double r, double sigma, double T, int n) {
    //    '        T... expiration time
    //    '        S... stock price
    //    '        K... strike price
    //    '        q... dividend yield
    //    '        n... height of the binomial tree

        
        this.dt = T / n;
        double up = Math.exp(sigma * Math.sqrt(dt));
        double down = 1./up;
        this.pu = Math.pow((Math.exp(r * dt/2) - (Math.exp(-sigma * Math.sqrt(dt/2)))),2) /Math.pow((Math.exp(sigma*Math.sqrt(dt/2)) - Math.exp(-sigma*Math.sqrt(dt/2))), 2);
        this.pd = Math.pow(Math.exp(sigma * Math.sqrt(dt/2)) - (Math.exp(r * dt/2)),2) /Math.pow((Math.exp(sigma*Math.sqrt(dt/2)) - Math.exp(-sigma*Math.sqrt(dt/2))), 2);
        this.pm = 1 - pu -pd;
        System.out.println();
     
        this.p = new double[2*n+1][n+1];
        p[n+1][0] = S;
        
        for (int j = 2; j < n; j++) {
        	//p[j][0] = p[j-1][0]*up;  
            for(int i = n-j+2; i < n+j-1; i++) {
                p[i][j] = Math.pow(s*up, n+1-i);
            }
        }
    }
    
    public double put() {
    	
    	double d1 = (Math.log(s/x) + (r + sigma * sigma/2) * t) / (sigma * Math.sqrt(t));
		double d2 = d1 - sigma * Math.sqrt(t);
    	double price = x * Math.exp(-r*t) * Gaussian.cdf(-d2) - s * Gaussian.cdf(-d1);
    	   
    			
        double[][] p2 = new double[2*n+1][n+1];
        for (int j = 0; j < n; j++) {
        	p2[2*n][j] = Math.max(0, x - this.p[2*n][j]);
        }
        for (int i = n-1; i >  0; i--) {
        	 for (int j = n-i+2; j < n+i; j++) {
        		 p2[j][i] = Math.max(x - this.p[j][i], Math.exp(-r*dt)*(pu*p2[j-1][i+1] + pm*p2[j][i+1] + pd*p2[j+1][i+1]));
        	 }
        }
        return p2[n+1][2] - price;
        
    }
    
    public double call() {
    	double d1 = (Math.log(s/x) + (r + sigma * sigma/2) * t) / (sigma * Math.sqrt(t));
		double d2 = d1 - sigma * Math.sqrt(t);
    	return s * Gaussian.cdf(d1) - x * Math.exp(-r*t) * Gaussian.cdf(d2);
    }

	@Override
	public String calculate(double s, double x, double r, double sigma, double t) {
		this.s = s;
		this.x = x;
		this.r = r;
		this.sigma = sigma;
		this.t = t;
        this.n = 1000;
        
        initialize(s,x,r,sigma,t,this.n);
		
		return "\nCall: " + call() + "\n\nPut: " + put(); 
		

		
	}

}
