/*
Assignment number	:	9.2
File Name			:	LanguageModel.java
Name (First Last)	:	Barak Dimand
Student ID			:	329951131
Email				:	barakdimand@aol.com
*/

package languagemodel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import std.StdIn;

public class LanguageModel {

	// The length of the moving window
	private int windowLength; 
	// The map where we manage the (window, LinkedList) mappings 
	private HashMap<String, LinkedList<CharProb>> probabilities;

	// Random number generator:
	// Used by the getRandomChar method, initialized by the class constructors. 
	Random randomGenerator;
	
	/**
	 * Creates a new language model, using the given window length
	 * and a given (fixed) number generator seed.
	 * @param windowLength
	 * @param seed
	 */
	public LanguageModel(int windowLength, int seed) {
		this.randomGenerator = new Random(seed);
		this.windowLength = windowLength;
		probabilities = new HashMap<String, LinkedList<CharProb>>();
	}	
		
	/**
	 * Creates a new language model, using the given window length.
	 * @param windowLength
	 */
	public LanguageModel(int windowLength) {
		this.randomGenerator = new Random();
		this.windowLength = windowLength;
		probabilities = new HashMap<String, LinkedList<CharProb>>();
	}

	/**
	 * Builds a language model from the text in standard input (the corpus).
	 */
	public void train() {
	    String window = "";
		char c;
		
		for (int i = 0; i < windowLength; i++) {
			window += StdIn.readChar();
		}
		
		while (StdIn.isEmpty() == false) {
			c = StdIn.readChar();
			if (probabilities.get(window) == null) {
				LinkedList<CharProb> probs = new LinkedList< >();
				probs.add(new CharProb(c));
				probabilities.put(window, probs);
			} else {
				LinkedList<CharProb> probs = probabilities.get(window);
				calculateCounts(probs, c);
			}
			String str = window.substring(1);
			window = str + c;
		}
		for (LinkedList<CharProb> probs : probabilities.values()) {
			calculateProbabilities(probs);
		}
	}
		
	// If the given character is found in the given list, increments its count;
    // Otherwise, constructs a new CharProb object and adds it to the given list.
	private void calculateCounts(LinkedList<CharProb> probs, char c) {
		int index = Tools.indexOf(probs, c);
		if (index != -1) {
			probs.get(index).count++;
		} else {
			probs.add(new CharProb(c));
		}
	}
	
	// Calculates and sets the probabilities (p and cp fields) of all the
	// characters in the given list.
	private void calculateProbabilities(LinkedList<CharProb> probs) {				
		
		int size = probs.size();
		double cp = 0;
		double totalCount = 0;
		for (int i = 0; i < size; i++) {
			totalCount += probs.get(i).count;
		}
		for (int i = 0; i < size; i++) {
			probs.get(i).p = probs.get(i).count/totalCount;
			cp = cp + probs.get(i).p;
			probs.get(i).cp = cp;
		}
	}	

	/**
	 * Returns a string representing the probabilities map.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : probabilities.keySet()) {
			LinkedList<CharProb> keyProbs = probabilities.get(key);
			str.append(key + ": ").append(Tools.toString(keyProbs)).append("\n");
		}
		return str.toString();
	}		
	
	/**
	 * Generates a random text, based on the probabilities that were learned during training. 
	 * @param initialText - text to start. 
	 * @param textLength - the size of text to generate
	 * @return the generated text
	 */
	public String generate(String initialText, int textLength) {
		String tempWindow = initialText;
		String result = initialText;
		for (int i = 0; i < textLength; i++) {
			char c = getRandomChar(probabilities.get(tempWindow));
			result = result + c;
			tempWindow = tempWindow.substring(1) + c;
		}
		return result;
	}

	// Returns a random character from the given probabilities list.
	public char getRandomChar(LinkedList<CharProb> probs) {
		// Replace the following statement with your code
		double rand = randomGenerator.nextDouble();
		for (int i = 0; i < probs.size(); i++) {
			if (probs.get(i).cp > rand) {
				return probs.get(i).chr;
			}
		}
		return 'X';
	}
	
	/**
	 * A Test of the LanguageModel class.
	 * Learns a given corpus (text file) and generates text from it.
	 */
	public static void main(String []args) {		
		int windowLength = Integer.parseInt(args[0]);  // window length
		String initialText = args[1];			      // initial text
		int textLength = Integer.parseInt(args[2]);	  // size of generated text
		boolean random = (args[3].equals("random") ? true : false);  // random / fixed seed
		if (initialText.length() < windowLength) {
			System.out.println(initialText);
			return;
		}
		LanguageModel lm;

		// Creates a language model with the given window length and random/fixed seed
		if (random) {
			// the generate method will use a random seed
			lm = new LanguageModel(windowLength);      
		} else {
			// the generate method will use a fixed seed = 20 (for testing purposes)
			lm = new LanguageModel(windowLength, 20); 
		}
		
		// Trains the model, creating the map.
		lm.train();
		
		// Generates text, and prints it.
		System.out.println(lm.generate(initialText,textLength));
	}
}
