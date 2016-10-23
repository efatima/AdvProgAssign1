package assign1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Operations {
	
	private static ArrayList<DictData> wordNode = new ArrayList<DictData>();

	
	public static void parse(String json)  {
         JsonFactory factory = new JsonFactory();

         ObjectMapper mapper = new ObjectMapper(factory);
	       JsonNode rootNode = null;
	       
	       System.out.println("in json parsing");
		try {
			rootNode = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	       Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
	       while (fieldsIterator.hasNext()) {

	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
//               System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
    	       DictData newNode = new DictData(field.getKey(), field.getValue());
    	       wordNode.add(newNode);
	           System.out.println("Key: " + newNode.getWord() + "\tValue:" + newNode.getMeaning());


	       }
	}
	
	
	public static String readJsonFile() throws JsonParseException, JsonMappingException, IOException{//Reads a JSON file, saves the data in the form of a string and returns it
	   String line = null;
	   String jsonFileName = "Dictionary.json";
	   String jsonFileData ="";
	   InputStream input = new FileInputStream(jsonFileName);
	   InputStreamReader reader = new InputStreamReader(input);
	   BufferedReader buffer = new BufferedReader(reader);
	
	   try{
		    while((line=buffer.readLine())!=null){
		           jsonFileData += line;		
		     }
    	  }
	   catch (IOException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	      }
	
	return jsonFileData;
   }

}
