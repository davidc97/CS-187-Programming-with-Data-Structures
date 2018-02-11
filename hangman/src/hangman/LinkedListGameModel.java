package hangman;

public class LinkedListGameModel implements GameModel {
	private int state;
	private String guessword;
	private String guessedletters ="";
	private LLCharacterNode head = new LLCharacterNode('_');

	public LinkedListGameModel(String guessWord){
		guessword = guessWord;
		state = STARTING_STATE;
		head.setInfo('_');
		LLCharacterNode cur = head;
		for(int i=0;i<guessword.length();i++){
			cur.setLink(new LLCharacterNode('_'));
			cur=cur.getLink();
		}
	}
	public boolean isPriorGuess(char guess) {
		 return guessedletters.contains(String.valueOf(guess));
	}

	public int numberOfGuesses() {
		return guessedletters.length();
	}

	public boolean isCorrectGuess(char guess) {
		String g = String.valueOf(guess);
		if(guessword.contains(g) & guessedletters.contains(g)==false){
			return true;
		}
		return false;
	}

	public boolean doMove(char guess) {
		String g = String.valueOf(guess);
		if(guessword.contains(g) & guessedletters.contains(g) ==false){
			for(int i = 0;i<guessword.length();i++){
				if(String.valueOf(guessword.charAt(i)).equals(g)){
					LLCharacterNode cur = head;
					for(int j =0; j<i; j++){
					cur = cur.getLink();
			}
					cur.setInfo(guess);
			}
			}
			if(guessword.charAt(guessword.length()-1) == guess){
				LLCharacterNode cur = head;
				while(cur.getLink() != null){
					cur=cur.getLink();
				}
				cur.setInfo(guess);
			}
			guessedletters = guessedletters + g;
			return true;
		}
		if(guessword.contains(g) == false & guessedletters.contains(g)){
			return false;
		} else{
			state++;
			guessedletters = guessedletters + g;
		}
		return false;
	}

	public boolean inWinningState() {
		String temp = "";
		LLCharacterNode cur = head;
		while (cur.getLink() != null){
			temp = temp + cur.getInfo();
			cur = cur.getLink();
		}
		return temp.equals(guessword);
	}

	public boolean inLosingState() {
		if(this.inWinningState() ==false & state == 10){
		return true;
		}
		return false;
	}
	public String toString() {
		String s = "";
		String last= "";
		LLCharacterNode cur = head;
		for(int i = 0;i <guessword.length()-1;i++ ){
			s = s + cur.getInfo() + " ";
			cur = cur.getLink();
		}
		LLCharacterNode cur2 = head;
		for(int i = 0;i< guessword.length();i++){
			cur2=cur2.getLink();
			if(cur2.getLink() ==null){
				s = s + cur2.getInfo();
			}
		}
		
		return s;
	}

	public int getState() {
		return state;
	}

	public String previousGuessString() {
		String s = "";
		if(guessedletters.equals("")){
			return "[]";
		}
		for(int i=0;i<guessedletters.length()-1;i++){
			s =s + String.valueOf(guessedletters.charAt(i)) + ", ";			
		}
		s = "[" + s + String.valueOf(guessedletters.charAt(guessedletters.length()-1)) + "]";
		return s;
	}

	public String getWord() {
		return guessword;
	}

}
