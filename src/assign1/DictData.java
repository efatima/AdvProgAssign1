package assign1;


import com.fasterxml.jackson.databind.JsonNode;

public class DictData {
	
   private String word;
   private JsonNode meaning;
   
   
   DictData(String word, JsonNode meaning){
	   this.word = word;
	   this.meaning = meaning;
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
