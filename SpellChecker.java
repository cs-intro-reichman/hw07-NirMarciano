
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		// System.out.println(levenshtein("uuuuuu", "word2"));
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		
		if(word1.isEmpty()) return word2.length();
		else if(word2.isEmpty()) return word1.length();
		else if(word1.charAt(0) == word2.charAt(0))	
			return levenshtein(tail(word1), tail(word2));
		else 
			return (1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))), levenshtein(tail(word1), tail(word2))));

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i =0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {

		int min = levenshtein(dictionary[0], word);
		int index = 0;

		for(int i = 1; i < dictionary.length; i++) {
			if(min > levenshtein(dictionary[i], word)) {
				min = levenshtein(dictionary[i], word);
				index = i;
			}
		}

		if(min <= threshold) 
			return dictionary[index];
		else return null;
	}

}
