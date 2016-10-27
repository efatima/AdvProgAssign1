package assign1;

//Testing commit
import com.fasterxml.jackson.databind.JsonNode;

public class DictData {
	
   private String word;
   private JsonNode meaning;
   private String mean;
   private DictData parent;
   private int distance;
   
   
   DictData(String word, JsonNode meaning){
	   this.word = word;
	   this.meaning = meaning;
   }
   
   DictData(String word, DictData parent, String mean){
	   this.word = word;
	   this.parent = parent;
   }
   
   
//
//   DictData(String word, JsonNode meaning, DictData parent){
//	   this.word = word;
//	   this.meaning = meaning;
//	   this.parent = parent;
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

public DictData getParent() {
	return parent;
}

public void setParent(DictData parent) {
	this.parent = parent;
}

public void setDistance(int dist) {
	// TODO Auto-generated method stub
	this.distance = dist;

}

public int getDistance() {
	return distance;
}

}


