/*
Assignment number	:	2.3
File Name			:	DamkaBoard.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/


public class DamkaBoard {
	public static void main(String args[]){
	int N = Integer.parseInt(args[0]);
	int row = 0;
	while (row < N) {
		if (row % 2 == 0) {
			String x = "* ";
			for (int i = 0; i < N; i++) {
				System.out.print(x);
			}
			System.out.println();
			row++;
		} else {
			String y = " *";
			for (int i = 0; i < N; i++) {
				System.out.print(y);
			}
			System.out.println();
			row++;
		}
	}
	}
}