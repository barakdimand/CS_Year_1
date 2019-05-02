/*
Assignment number	:	2.1
File Name			:	TimeCalc.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/

public class TimeCalc {
	public static void main(String[] args) {
		String s = args[0];
		int hours = Integer.parseInt("" + s.charAt(0) + s.charAt(1));
		int minutes = Integer.parseInt("" + s.charAt(3) + s.charAt(4));
		int add = Integer.parseInt(args[1]);
		if (minutes > 59 || hours > 23 || hours < 0 || minutes < 0 || add < 0) {
			System.out.println("Invalid Input");
			return;
		}
		hours = hours + (add / 60); // add 1 hour for every 60 minutes
		minutes = minutes + (add % 60); // increase minutes by remaining minutes
		while (minutes >= 60) { // if minutes > 59, make it <= 59
			minutes = minutes - 60;
			hours++;
		}			
		 
		while (hours > 23) { // make hours < 24
			hours = hours - 24;
		}
		if (hours < 12 && minutes < 10) {
			System.out.println(hours + ":0" + minutes + " am");
		}
		if (hours < 12 && minutes >= 10) {
			System.out.println(hours + ":" + minutes + " am");
		}
		if (hours > 12 && minutes < 10) {
			System.out.println((hours - 12) + ":0" + minutes + " pm");
		}
		if (hours > 12 && minutes >= 10) {
			System.out.println((hours - 12) + ":" + minutes + " pm");
		}
		if (hours == 12 && minutes < 10) {
			System.out.println(hours + ":0" + minutes + " pm");
		}
		if (hours == 12 && minutes >= 10) {
			System.out.println(hours + ":" + minutes + " pm");
		}
	}
}

