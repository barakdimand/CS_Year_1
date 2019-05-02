/*
Assignment number	:	1.1
File Name			:	BillCalc.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/

public class BillCalc {
	public static void main(String[] args){
	
		int billTotal = Integer.parseInt(args [0]);
		int billWithTip = billTotal + (int) (billTotal * .15);
		int numberOfPeople = Integer.parseInt(args [1]);
	
		System.out.println("Each guest should pay " + 
			(billWithTip / numberOfPeople) + " Shekels.");

	}
}