package assign1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner; 

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Operations {
	
	private static ArrayList<DictData> wordNode = new ArrayList<DictData>();
//	private final static HashMap<StringBuilder, ArrayList<DictData>> dict = new HashMap<StringBuilder, ArrayList<DictData>>();
	private final static HashMap<String, ArrayList<String>> dict = new HashMap<String, ArrayList<String>>();

//    private final List<DictData> nodeQ= new LinkedList<>();

	public static void takeInput()  {
		String start ="hell";
		String end = "bell";
//		System.out.println("Enter the Start word: ");
//		Scanner scan = new Scanner(System.in);
//		start = scan.next();
//		System.out.println(start);
//		System.out.println("Enter the End word: ");
//		end = scan.next();
//		System.out.println(end);

		Ladder wordLadder = new Ladder(start, end); //saves start and end word in Ladder object

		//check if both Strings are of equal length + if both exist in dictionary
		if (start.length()== end.length()){
			System.out.println("same length:valid");
		}
		
	}
	
	
	public synchronized static void addToList(String word, String wordNode) {
		System.out.println("my key: "+word);
//		String key = dict.get(word);

//	    ArrayList<DictData> List = dict.get(word);
	    ArrayList<String> List = dict.get(word);

//		System.out.println(wordNodeList);
	    
	    
	    
	    if (dict.containsKey(word)){
	 			System.out.println("KEY FOUND.. exists");
//
     			if(List == null) {
	 				System.out.println("Key exists but List is empty");
//	 	            List = new ArrayList<String>();
//	 	            wordNodeList.add(wordNode);
//	 	 	         dict.put(word, wordNodeList);
//	 	 			System.out.println("KEY Added");
////	 	 			System.out.println(dict.get(word));
//	 	 			
//	 		    }
//	 			
//	 			if(!wordNodeList.contains(wordNode)){
//		        	wordNodeList.add(wordNode);
//					System.out.println("Second if");
//
		        }
     			
     			else{
	 				System.out.println("Key exists+ data exists too :D");
	 				List.add(wordNode);

     			}
			}
	    else{
	    	//key doesn't exist
	    	if(List == null) {
 				System.out.println("Else.. first if");
 	            List = new ArrayList<String>();
 	            List.add(wordNode); 			
	 	        dict.put(word, List);
	 	 		System.out.println("KEY Added");


	    	}

	    }    	
//	    	}
//
//
// 	 	         dict.put(word, wordNodeList);
// 	 			System.out.println("KEY Added");
//// 	 			System.out.println(dict.get(word));
 	 			
// 		    }
 			
// 			if(!wordNodeList.contains(wordNode)){
//	        	wordNodeList.add(wordNode);
//				System.out.println("Else.....Second if");
//
//	        }

	   
	
//}



	    // if list does not exist create it
//	    if(wordNodeList == null) {
//			System.out.println("Key doesn't exist");
//            wordNodeList = new ArrayList<DictData>();
//            wordNodeList.add(wordNode);
// 	         dict.put(word, wordNodeList);
// 			System.out.println("KEY Added");
// 			System.out.println(dict.get(word));
// 			
//	    } else {
//			System.out.println("Key exists!");
//
//	        // add if wordNode isn't already in list --avoiding duplicates
//	        if(!wordNodeList.contains(wordNode)){
////	        	wordNodeList.add(wordNode);
//	        }
//	    }
//	    
//		System.out.println(dict.entrySet());
		


//	}
	
	}
	
	
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
//	       while (fieldsIterator.hasNext()) {
//	       String [] test ={"hello", "test", "happy", "nappy", "mappy", "yello", "best", "nest", "jest", "neighbour", "vest"};
	       String [] test ={"ha", "ho"};

//	       int m = 0;
	       for(int j=0; j<test.length; j++){

//	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
//               System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
//    	       DictData newNode = new DictData(field.getKey(), field.getValue());
	           DictData newNode = new DictData(test[j], "blah blah");

        //     wordNode.add(newNode);
    	       
    	       String currentWord = test[j];

//    	       String currentWord = newNode.getWord();
//    	       char[] word =   currentWord.toCharArray();
//    	       System.out.println(currentWord.length());
    	       
    	       int length = currentWord.length();

    	       for (int i = 0; i < length; i++){
//        	       StringBuilder word = new StringBuilder(currentWord);
//        	        word.setCharAt(i, '_');
//        	        System.out.println(word);
    	    	   char[] word =   currentWord.toCharArray();
    	    	   word[i] = '_';
        	       
//        	        addToList(word, newNode);
    	    	   
//        	       System.out.println(String.valueOf(word));

        	        addToList(String.valueOf(word), test[j]);

        	        
        			        	        
        	        //check if key exists in map
        	            //if true push actual word in that array list against key
        	           
        	            //else if false create key and then push word 
        	        
        	        
        	        //continue loop for until word length is complete;

    	    	   
    	       }
    	       
    	       for (Entry<String, ArrayList<String>> entry  : dict.entrySet()) {
   		        System.out.print(entry.getKey()+" -----> ");
   		        for(String fruitNo : entry.getValue()){
//   		            System.out.print(fruitNo+" ");
   		            System.out.print(fruitNo+" ");
//   		            System.out.print(fruitNo.getMeaning()+" ");

   		        }
		            System.out.print(" \n");

//   		        System.out.println();
   		    }

    	       
    	       
    	       
	       }  
    	       
    	       
//    	       dict.add(field.getKey());//adding words to HashSet
//	           System.out.println("Key: " + newNode.getWord() + "\tValue:" + newNode.getMeaning());


	       }
//	}
	
	
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
