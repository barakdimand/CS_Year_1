/*
Assignment number	:	3.3
File Name			:	Anagram.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/

public class Anagram {
	// A collection of methods for handling anagrams.
	public static void main(String args[]) {
		// Tests the isAnagram function.
		// System.out.println(isAnagram("silent", "listen")); // true
		// System.out.println(isAnagram("William Shakespeare", "I am a weakishspeller")); // true
		// System.out.println(isAnagram("Madam Curie", "Radium came")); // true
		// Tests the ramdomAnagram function.
		System.out.println(randomAnagram("sil"));

	}

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {

		String one = preProcess(str1);
		String two = preProcess(str2);
		int length1 = one.length();
		int length2 = two.length();
		if (length1 != length2) {
			return false;
		}
		char[] array1 = new char[length1];
		char[] array2 = new char[length2];
		for (int i = 0; i < one.length(); i++) {
			array1[i] = one.charAt(i);
			array2[i] = two.charAt(i);
		}

		for (int i = 0; i < array1.length; i++) {

			Boolean isEqual = false;

			for (int j = 0; j < array2.length; j++) {

				if (array1[i] == array2[j]) {
					array2[j] = ' ';
					isEqual = true;
					break;
				}
			}

			if (isEqual == false) {
				return false;
			}
		}
		return true;
	}

	// Returns a preprocessed version of the given string: all the letter characters
	// are converted to lower-case, and all the other characters are deleted. For
	// example,
	// the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {

		String input = str;
		String newString = "";

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if ((c > 64) && (c < 91)) {
				c = (char) (c + 32);
				newString = newString + c;
			} else if ((c < 65) || (c == 96)) {
				c = '.';
				newString = newString;
			} else {
				newString = newString + c;
			}
		}
		return newString;
	}

	private static Object charAt(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	// Returns a random anagram of the given string. The random anagram consists of
	// the same
	// letter characters as the given string, arranged in a random order. The random
	// anagram
	// is not required to form a word in the English language.
	public static String randomAnagram(String str) {
		String s = preProcess(str);
		char[] Input = new char[s.length()];
		char[] Result = new char[s.length()];
		boolean[] Boolean = new boolean[s.length()];
		// create input array
		for (int i = 0; i < s.length(); i++) {
			Input[i] = s.charAt(i);
		}
		// loop to fill out slots with random letters
		int n = 0;
		for (int i = 0; i < s.length(); i++) {	
			while (n < s.length()) {
				int randomNumber = (int) (Math.random() * (s.length()));
				char letter = Input[n];
				if (!Boolean[randomNumber]) {
					Result[randomNumber] = letter;
					Boolean[randomNumber] = true;
					n++;
				} 
			}
		}
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			result = result + Result[i];
		}
		return result;
	}

	// Returns true if the given character is an English letter ('a' to 'z' or 'A'
	// to 'Z'), false otherwise.
	private static boolean isLetter(char c) {
		if ((c >= 'A') && (c != 96) && (c <= 'z')) {
			return true;
		}
		return false;
	}
}
