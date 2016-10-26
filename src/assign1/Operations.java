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

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Operations {
	
	private final static HashMap<String, ArrayList<DictData>> dict = new HashMap<String, ArrayList<DictData>>();
	static UndirectedGraph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

	
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
	
	
	public synchronized static void addToList(String word, DictData wordNode) {
		System.out.println("my key: "+word);
	    ArrayList<DictData> List = dict.get(word);	    
	    
	    if (dict.containsKey(word)){
//	 			System.out.println("KEY FOUND.. exists");

     			if(List == null) {
	 				System.out.println("Key exists but List is empty"); //which won't be the case for us
		        }
     			
     			else if(!List.contains(wordNode)) {//if the node doesn't already exist then push
//	 				System.out.println("Key and Data EXISTS! Appending...");				
	 				List.add(wordNode);
	 				
	 			   for(DictData index : List){
	 				    if(index!=wordNode){
		 			    graph.addEdge(index.getWord(), wordNode.getWord());//for every new node added in the List, make edges between that node and all existing ones under the same key 
	 				    }
	 		        }



     			}
			}
	    else{
	    	//key doesn't exist
	    	if(List == null) {
 	            List = new ArrayList<DictData>();
 	            List.add(wordNode); 			
	 	        dict.put(word, List);
//	 	 		System.out.println("New KEY Added");
	    	}

	    }    	
	
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
	       while (fieldsIterator.hasNext()) {
//	       String [] test ={"hell", "test", "happy", "yes", "set", "let", "best", "nest", "bet", "neighbour", "vest"};
//	       for(int j=0; j<test.length; j++){

	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
//             System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
    	       DictData newNode = new DictData(field.getKey(), field.getValue());
//	           DictData newNode = new DictData(test[j], "blah blah");
    	       
//    	       String currentWord = test[j];
    	       String currentWord = newNode.getWord();
    	       graph.addVertex(newNode.getWord());

    	       
    	       int length = currentWord.length();

    	       for (int i = 0; i < length; i++){
    	    	   char[] word =   currentWord.toCharArray();
    	    	   word[i] = '_';
        	       
        	        addToList(String.valueOf(word), newNode);
//        	        addToList(String.valueOf(word), test[j]);
    	    	   
    	       }
    	       
    	     
    	       
    	       
    	       
	       }  
    	     
         
    	       
//	           System.out.println("Key: " + newNode.getWord() + "\tValue:" + newNode.getMeaning());
               printHashMap();
               try {
				displayGraph();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	       }
//	}
	
	
	public static void printHashMap(){
		  System.out.print("Result...");

	       for (Entry<String, ArrayList<DictData>> entry  : dict.entrySet()) {
 		        System.out.print(entry.getKey()+" -----> ");
 		        for(DictData index : entry.getValue()){
 		            System.out.print(index.getWord()+" ");

 		        }
		            System.out.print(" \n");

 		    }

	}
	
	public static void displayGraph() throws InterruptedException{
	    System.out.println("In display graph");

		JFrame frame = new JFrame();
		frame.setSize(400, 400);
	    JGraph jgraph = new JGraph( new JGraphModelAdapter<String, DefaultEdge>( graph) );
	    frame.getContentPane().add(jgraph);
	    frame.setVisible(true);
	    System.out.println("graph: " + graph.toString());
//	    System.out.println("edges of MABBLE: " + graph.edgesOf("RABBLE"));



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
