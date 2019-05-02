/*
Assignment number	:	3.1
File Name			:	CalcExp.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/


public class CalcExp {
	public static void main(String args[]) {
	      // Tests the exp function.
		  double x = Double.parseDouble(args[0]);  // use other values, if you want
		  int N = Integer.parseInt(args[1]);    // experiment with other values, to see how it effects the compuatation's accuracy.
		  //System.out.println("e raised to the power of " + x + " according to Java: "+ Math.exp(x));
		 // System.out.println("Same value, using my exp function with " + N + " steps: " + exp(x,N));
		System.out.println(exp(x,N));
	}
	
	// Returns an approximation of the value of the constant e raised to the power of the given x.
	// The approximation's accuracy is determined by the given N. The higher N, the higher the accuracy.
	public static double exp(double x, int N) {
		  double term = 1;
		  double sum = 1;
		  double fact = 1;
				for (int i = 1; i <= N; i++) {
					term = term * x;
					fact = fact * i;
					sum = sum + (term / fact);
				}
				return sum;
	}
}
		  	
	


