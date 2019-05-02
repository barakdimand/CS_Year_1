/*
Assignment number	:	2.2
File Name			:	CalcPi.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/


public class CalcPi {
	public static void main(String args[]) {
	int N = Integer.parseInt(args[0]);
	double d = 1.0;
	double p = 0.0;
	for (int i = 0; i < N; i++) {
		if (i % 2 == 0) { 
		p = p + (1 / d);
	} else { 
		p = p - (1 / d);
	}
	d = d + 2;
	}
	System.out.println(4 * p);
   	}
}




