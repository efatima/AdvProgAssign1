package assign1;

import java.io.IOException;

public class Ladder {
	
	private String startWord;
	private String endWord;
	   
	   
	Ladder(String startWord, String endWord){
		this.setStartWord(startWord);
		this.setEndWord(endWord);
	}
	
	public String getStartWord() {
		return startWord;
	}


	public void setStartWord(String startWord) {
		this.startWord = startWord;
	}


	public String getEndWord() {
		return endWord;
	}


	public void setEndWord(String endWord) {
		this.endWord = endWord;
	}

	

	public static void main(String[] args) throws IOException{
		
		String json = Operations.readJsonFile(); //Reads JSON file and saves it in the form of a string
		Operations.parse(json); //converts data from String version JSON to objects of DictData
		
		Operations.takeInput();

				
	}


	

}


