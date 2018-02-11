package hangman;
/**
 * The Array implementation of the GameModel interface.
 */
public class ArrayGameModel implements GameModel {
	/** The number of characters (lower/upper). */
	private static final int ALPHABET_COUNT = 26*2;
	
	/** hung state */
	private int	state;
	
	/**correct word */
	private String guessword;
	private String[] wordarray;
	private String[] result;
	
	/**previous guessed letters*/
	private String guessedletters = "";
	
	
	
	/**
	 * Creates a new ArrayGameModel object.
	 * 
	 *  guessWord the word to guess
	 */
	public ArrayGameModel(String guessWord) {
		// TODO (1)
		state    = STARTING_STATE;
		guessword = guessWord;
		wordarray = new String[guessword.length()];
		result = new String[guessword.length()];
		for (int i=0;i<guessword.length(); i++){
			result[i] = "_";
			wordarray[i] = String.valueOf(guessword.charAt(i));
		}
	}
		
	public boolean isPriorGuess(char guess) {
		// TODO (2)
		return guessedletters.contains(String.valueOf(guess));
	}
	
	public int numberOfGuesses() {
		// TODO (3)
		return guessedletters.length();
	}
	
	public boolean isCorrectGuess(char guess) {
		String g = String.valueOf(guess);
		// TODO (4)
		if(guessword.contains(g) &  guessedletters.contains(g) == false){
			return true;
		}
		return false;
	}
	
	public boolean doMove(char guess) {
		// TODO (5)
		String g = String.valueOf(guess);
		if (guessword.contains(g) & guessedletters.contains(g) == false){
			for(int i=0;i<guessword.length();i++){
				if(wordarray[i].equals(g)){
					result[i] = wordarray[i];
				}
			}
			guessedletters = guessedletters + g;
			return true;
		}
		if (guessword.contains(g) == false & guessedletters.contains(g)){
			return false;			
		} else {
			state++;
			guessedletters = guessedletters + g;
		}		
		return false;
	}

	public boolean inWinningState() {
		// TODO (6)
		String temp = "";
		for(int i =0;i<guessword.length();i++){
			temp = temp + result[i];
		}
		return temp.equals(guessword);
	}

	public boolean inLosingState() {
		// TODO(7)
		if (result.equals(wordarray) != true & state == 10){
			return true;
		}
		return false;
	}
	
	public String toString() {
		// TODO (8)	
		String s = "";
		for(int i=0;i<guessword.length()-1;i++){
			s = s + result[i] + " ";
		}
		s = s + result[guessword.length()-1];	
		return s;
	}

	public String previousGuessString() {
		String s = ""; 
		// TODO (9)
		if (guessedletters.equals("")){
			return "[]";			
		}		
		for(int i=0;i<guessedletters.length()-1;i++){
			s =s + String.valueOf(guessedletters.charAt(i)) + ", ";			
		}
		s = "[" + s + String.valueOf(guessedletters.charAt(guessedletters.length()-1)) + "]";
		return s;
	}
	
	public int getState() {
		return state;
	}

	public String getWord() {
		// TODO (10)
		return guessword;
	}
}