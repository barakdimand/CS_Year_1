/*
Assignment number	:	8.1
File Name			:	RecursionHW8.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/



import std.*;

public class RecursionHW8 {
	public static void main (String[] args){
		
		
		// tests the recursive add() function for integers >= 0
		//test1();
		// tests the recursive int2Bin() function 
		//test2();
		// tests the parseInt() function
		//test3(); 
		// tests palindrome function
		//test4(); 
		// tests sierpinskis triangle function for n = 3
		//test5a();
		// tests sierpinskis triangle function for n = 6
		//test5b();
		
		
	}
	
	public static void test1() {
		int a = 5;
		int b = 8;
		int c = 0;
		int d = 45;
		System.out.println(add(a,b));
		System.out.println(add(b,a));
		System.out.println(add(c,d));
		System.out.println(add(a,c));
		System.out.println(add(b,d));
	}
	
	public static void test2() {
		System.out.println(int2Bin(3));
		System.out.println(int2Bin(8));
		System.out.println(int2Bin(26));
	}
	
	public static void test3() {
		System.out.println(parseInt("10"));
		System.out.println(parseInt("546"));
		System.out.println(parseInt("03657"));
		
	}
	
	public static void test4() {
		System.out.println(isPalindrome("a"));
		System.out.println(isPalindrome("aba"));
		System.out.println(isPalindrome("ggaataagg"));
		System.out.println(isPalindrome("abcdeddcba"));
		System.out.println(isPalindrome("acda"));
	}
	
	public static void test5a() {
		sierpinski(3);
	}
	
	public static void test5b() {
		sierpinski(6);
	}
	
	/** 
	 * returns the sum of an a and b using a recursive method
	 * @param integer a
	 * @param integer b
	 * @return the sum of an a and b as an int.
	 */
	public static int add(int a, int b) {
		if (b == 0) {
			return a;
		}
		return add(a + 1, b -1);
	}
	
	/** 
	 *  returns a string binary representation of an integer
	 * @param integer n
	 * @return a string binary representation
	 */
	public static String int2Bin(int n) {
		
		if (n == 0) {
			return " ";
		} 
		if (n % 2 == 0) {
			return int2Bin(n / 2) + "0";
		} else {
			return int2Bin(n / 2) + "1";
		}
	}
	
	/** 
	 * returns the int value of the given string of digits
	 * @param String str
	 * @return int value of the givin digits in str.
	 */
	public static int parseInt(String str) {
	
		if (str.length() == 0) {
			return 0;
		}
		return parseInt(str.substring(1)) + (int)Math.pow(10, str.length() - 1) * (str.charAt(0) - '0');
	}
	
	/**
	 * returns True or False depending on if a given string is a palindrome.
	 * @param String str
	 * @return True if the string is a palindrome. False otherwise
	 */
	public static boolean isPalindrome(String str) {
		if (str.length() <= 1) {
			return true;
		}
		if (str.charAt(0) == str.charAt(str.length() -1)) {
			return isPalindrome(str.substring(1, str.length() - 1));
		} else {
			return false;
		}
	}
	
	/**
	 *  Draws sierpinskis triangles with depth of integer n
	 * @param integer n
	 */
	public static void sierpinski(int n) {
		sierpinskiExtended(n,0,0.25,0.5,0,0.4333,0);
	}
	
	
	public static void sierpinskiExtended(int n , double x1, double x2, double x3 , double y1 ,double y2 , double y3) {
	
		if (n == 1) { 
			StdDraw.line(0,0,0.25,0.4333);
			StdDraw.line(0,0,0.5,0);
			StdDraw.line(0.25,0.4333,0.5,0);
			StdDraw.show();
			return;
		
		} else {
		
			double xPoint5 = (.5 * x1 + .5 * x3);
			double yPoint5 = (.5 * y3 + .5 * y3);
			double xPoint4 = (.5 * x2 + .5 * x3);
			double yPoint4 = (.5 * y2 + .5 * y3);
			double xPoint3 = (.5 * x1 + .5 * x2);
			double yPoint3 = (.5 * y1 + .5 * y2);
			
			StdDraw.line(xPoint3, yPoint3, xPoint4, yPoint4);
			StdDraw.line(xPoint4, yPoint4, xPoint5, yPoint5);
			StdDraw.line(xPoint3, yPoint3, xPoint5, yPoint5);
			
			sierpinskiExtended(n - 1, x1, xPoint3, xPoint5, y1, yPoint3, yPoint5);
			sierpinskiExtended(n - 1, xPoint5, xPoint4, x3, yPoint5, yPoint4, y3);
			sierpinskiExtended(n - 1, xPoint3, x2, xPoint4, yPoint3, y2, yPoint4);
		}
	}
}


