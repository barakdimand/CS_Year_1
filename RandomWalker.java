/*
Assignment number	:	2.4
File Name			:	RandomWalker.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/

public class RandomWalker {
	public static void main(String args[]) {
	int N = Integer.parseInt(args[0]);
	int x = 0; 
	int y = 0;
	int rand;
	for (int i = 0; i < N; i++) { 
		rand = (int)(Math.random() * 4); // 25% chance for each case
		 switch (rand) {
			case 0:
				y++;
			break;	
			case 1:
				x++;
			break;
			case 2:
				y--;
			break;
			default:
				x--;
		 }
		 System.out.println("(" + x + ", " + y + ")");
	}
	System.out.println("squared distance = " + ((x * x) + (y * y)));
	}
}













// public class RandomWalker {
// 	public static void main(String args[]) {
// 
// 	int N = Integer.parseInt(args [0]);
// 	int x = 0;
// 	int y = 0;
// 	double z = Math.random();
// 	String s;
// 	double dist = 0;
// 	
// 	for (int i = 0; i < N; i++) {
// 		z = Math.random();
// 			if (z < .25) {
// 			y = y + 1;
// 			s = ("(" + x + ", " + y + ")");
// 			System.out.println(s);
// 			 } else if (z < .50) {
// 			x = x + 1;
// 			s = ("(" + x + ", " + y + ")");
// 			System.out.println(s);
// 			 } else if (z < .75) {
// 			y = y - 1;
// 			s = ("(" + x + ", " + y + ")");
// 			System.out.println(s);
// 			 } else {
// 			x = x - 1;
// 			s = ("(" + x + ", " + y + ")");
// 			System.out.println(s);
// 			 }
// 		if (i == (N - 1)) {
// 		dist = (x * x) + (y * y);
// 		 }
// 		}
// 	System.out.println("squared distance is = " + dist);
// 	}
// }
// 









