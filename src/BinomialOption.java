public class BinomialOption implements OptionPricing{
	double s, x, r, sigma, t, dt, p1;
	int n;
	double[][] p;
	
    public void initialize(double S, double x, double r, double sigma, double T, int n) {
    //    '        T... expiration time
    //    '        S... stock price
    //    '        K... strike price
    //    '        q... dividend yield
    //    '        n... height of the binomial tree

        this.p = new double[n+1][n+1];
        this.dt = T / n;
        double up = Math.exp(sigma * Math.sqrt(dt));
        double down = 1./up;
        this.p1 = (Math.exp(r * dt) - down)/(up - down);
        
     
        p[0][0] = S;
        for (int j = 1; j < n; j++) {
        	p[j][0] = p[j-1][0]*up;  
            for(int i = 1; i < j; i++) {
                p[j][i] = p[j-1][i-1]*down;
            }
        }
    }
    
    public double put() {
    	  double[] p = new double[n];
          double deltaT = t / n;
          double up = Math.exp(sigma * Math.sqrt(deltaT));
          double p0 = (up - Math.exp(-r * deltaT)) / (up * up - 1);
          double p1 = Math.exp(-r * deltaT) - p0;
          
      //initial values at time T
          for (int i = 0; i < n; i++) {
              p[i] = x - s * Math.pow(up, 2*i - n);
              if (p[i] < 0) p[i] = 0;
          }
          
      //move to earlier times
          for (int j = n-1; j > 0; j--) {
              for(int i = 0; i < j; i++) {
                  p[i] = p0 * p[i+1] + p1 * p[i];    // binomial value
                  double exercise = x - s * Math.pow(up, 2*i - j);  // exercise value
                  if (p[i] < exercise) p[i] = exercise;
              }
          }
          return p[0];
    }
    
    public double call() {
    	double[][] p2 = new double[n+1][n+1];
    	 for (int j = 0; j < n; j++) {
    		 p2[n][j] = Math.max(0, this.p[n][j] - x);
        }
    	 
         for (int j = n-1; j > 0; j--) {
        	 for (int i = 0; i < j; i++) {
        		  p2[j][i] = Math.max(this.p[j][i] - x, Math.exp(-r*dt)*(p1*p2[j+1][i]+(1-p1)*p2[j+1][i+1]));
        	 }
        }
    	return p2[1][0];
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