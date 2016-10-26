package assign1;

//Testing commit
import com.fasterxml.jackson.databind.JsonNode;

public class DictData {
	
   private String word;
   private JsonNode meaning;
   private String mean;
   
   
//   DictData(String word, JsonNode meaning){
//	   this.word = word;
//	   this.meaning = meaning;
//   }

public DictData(String word, String word2) {
	// TODO Auto-generated constructor stub
	this.word = word;
	this.mean = word2;

}

public String getWord() {
	return word;
}

public void setWord(String word) {
	this.word = word;
}

public JsonNode getMeaning() {
	return meaning;
}

public void setMeaning(JsonNode meaning) {
	this.meaning = meaning;
}

}


