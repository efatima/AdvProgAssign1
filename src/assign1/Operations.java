package assign1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

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
	static UndirectedGraph<DictData, DefaultEdge> graph = new SimpleGraph<DictData, DefaultEdge>(DefaultEdge.class);
	static Integer longestChain =0;
	static List<Integer>  pathLengths = new LinkedList<Integer>();


	
	public static boolean takeInput()  {
		String start =" ";
		String end = " ";
		System.out.println("\nWelcome to Program Word Ladder!");
		System.out.println("\nYou can enter start and end words of your choice from the above list.");
		System.out.println("Please Enter the Start word: ");
		Scanner scan = new Scanner(System.in);
		start = scan.next();
		System.out.println("Please Enter the End word: ");
		end = scan.next();
		System.out.println("Your start word is: "+start+" -->");
		System.out.println("Your end word is: --> "+end);

		//check if both Strings are of equal length 
		if (start.length()== end.length()){
			 BFS(start, end);		
			 return true;
		}
		
		return false;
		
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
		 			    graph.addEdge(index, wordNode);//for every new node added in the List, make edges between that node and all existing ones under the same key 
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
	       
	       System.out.println("\nParsing JSON...");
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
    	       DictData newNode = new DictData(field.getKey(), field.getValue());
    	       
    	       String currentWord = newNode.getWord();
    	       graph.addVertex(newNode);

    	       
    	       
    	       int length = currentWord.length();

    	       for (int i = 0; i < length; i++){
    	    	   char[] word =   currentWord.toCharArray();
    	    	   word[i] = '_';
        	       
        	        addToList(String.valueOf(word), newNode); 	    	   
    	       }
    	         	           	           
    	       
	           System.out.println("Key: " + newNode.getWord() + "\tValue:" + newNode.getMeaning());               
	     }
	       
	 }
	
	
	
	
	public static void printHashMap(){
		  System.out.print("\nPrinting HashMap...\n");

	       for (Entry<String, ArrayList<DictData>> entry  : dict.entrySet()) {
 		        System.out.print(entry.getKey()+" -----> ");
 		        for(DictData index : entry.getValue()){
 		            System.out.print(index.getWord()+" ");

 		        }
		            System.out.print(" \n");

 		    }
		   try {
			displayGraph();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	       calculateAllMinChains();
	       findNoChainWords();
	       try {
			calculateFrequencyDistribution();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void displayGraph() throws InterruptedException{
	    System.out.println("\nDisplaying Graph in Background...");

		JFrame frame = new JFrame();
		frame.setSize(400, 400);
	    JGraph jgraph = new JGraph( new JGraphModelAdapter<DictData, DefaultEdge>( graph) );
	    frame.getContentPane().add(jgraph);
	    frame.setVisible(true);
//	    System.out.println("graph: " + graph.toString());
//	    System.out.println("edges of MABBLE: " + graph.edgesOf("RABBLE"));

	}
	
	
	public static void calculateAllMinChains(){ //calculates minimum chains for all solvable words
	    for(DictData index : graph.vertexSet()){
		    for(DictData node : graph.vertexSet()){
		    	//if both nodes aren't same && both have same length
	    	    if (!(index.getWord().compareTo(node.getWord()) == 0) && index.getWord().length() == node.getWord().length()){
	    	    	//Apply Breadth First Search to calculate minimum Chain
	    	    	   BFS(index.getWord(), node.getWord());	    	    	 
	    	     }
    	}
	  }

		
	}
	
	public static void findNoChainWords(){ //finds all words that don't have a chain i.e a graph edge connected to them
	    for(DictData index : graph.vertexSet()){
	    	    Set<DefaultEdge> edge = graph.edgesOf(index);
	    	    if(edge.size()<1){
		    	    System.out.println("\n'"+index.getWord()+"' has NO Chain");
	    	    }   	
	    }
	}
	
	
	public static void calculateFrequencyDistribution() throws IOException{ //calculate Frequency Distribution of Chain lengths
		   Map<Integer, Integer> freqTable = new HashMap<Integer, Integer>();

         for (int i=0; i< pathLengths.size(); i++){
        	 
        	 if (freqTable.containsKey(pathLengths.get(i))){
           	  freqTable.put(pathLengths.get(i), freqTable.get(pathLengths.get(i))+1);
        		 
        	 }
        	 else{
              	  freqTable.put(pathLengths.get(i), 1);

        	 }
 	    	        	 
         }
         
 		System.out.println("\nMaximum Number of Steps Found: "+longestChain);

 	    System.out.println("\nFrequency Distribution Table for All Chain Lengths: ");
        printAndWriteToFile(freqTable);//prints frequency Table and writes to file

         

	}
	
	
	public static boolean BFS(String startNode, String endNode){
	    System.out.println("In Breadth-First-Search: ");
		Map<String, String> prev = new HashMap<String, String>();
	    Map<DictData, Boolean> visited = new HashMap<DictData, Boolean>();
	
		List<String> directions = new LinkedList<String>();	
		Queue q = new LinkedList<String>();
		
		DictData current = null;
		DictData destination = null;

	    for(DictData index : graph.vertexSet()){
    	    if (index.getWord().compareTo(startNode) == 0){
    	    	current = index;
    	    }
    	    if (index.getWord().compareTo(endNode) == 0){
    	    	destination = index;
    	    }
    	    	    
    	}
	    
	    
	    if(current == null || destination== null){
	    	System.out.println("Invalid word/words! Non-Existant in Dictionary Used");
	    	return false;
	    }
	    else{
	    	System.out.println("Start Node '"+ current.getWord()+"' found in Dictionary");
	    	System.out.println("End Node '"+ destination.getWord()+"' found in Dictionary");
	    }
	    
	    q.add(current);
	    
	    visited.put(current, true);
	    
	    while(!q.isEmpty()){
	        current = (DictData) q.remove();
	        if (current.getWord().compareTo(endNode) == 0){
	            break;
	        }else{
	        	DictData nextNode = null;
	        	for(DefaultEdge e : graph.edgesOf(current)){
	        		if (graph.getEdgeTarget(e) == current) {
	        			nextNode = graph.getEdgeSource(e);
	        		}
	        		else {
	        			nextNode = graph.getEdgeTarget(e);
	        		}
	            	if(!visited.containsKey(nextNode)){
	                    q.add(nextNode);
	                    visited.put(nextNode, true);
	                    prev.put(nextNode.getWord(), current.getWord());
	                }
	            }
	        }
	    }
	    if (current.getWord().compareTo(endNode)!= 0){
	        System.out.println("Destination can't be reached since Start and End nodes aren't Connected");
	        return false;
	    }
	    for(String node = endNode; node != null; node = prev.get(node)) {
	        directions.add(node);
	    }
	    System.out.println("\n"+directions.toString());
	    System.out.println("Steps: "+(directions.size()-1));//path steps
	    Integer tempChain= directions.size()-1;
	    pathLengths.add((directions.size()-1)); //keeps track of all path Lengths for frequency distribution	

	    if(longestChain < tempChain){//keeps updating the longest chain variable as chains are being found and printed
	    	longestChain = tempChain;
		    System.out.println("\nLongest Chain Updated To: "+longestChain+"\n");

	    }
	    
	    return true;
	    
	}	
	
	


	public static String readJsonFile() throws JsonParseException, JsonMappingException, IOException{//Reads a JSON file, saves the data in the form of a string and returns it
	   String line = null;
//	   String jsonFileName = "Dictionary.json";
	   String jsonFileName = "testDictionary.json";
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
	
	
	
	public static void printAndWriteToFile(Map<Integer, Integer> freqTable) throws IOException {
		File fout = new File("FrequencyTable.txt");
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		 bw.write("PathLength Frequency");
	     bw.newLine();
	 
		for (Map.Entry entry : freqTable.entrySet()) {
     	    System.out.println(entry.getKey() + " : " + entry.getValue());
		    bw.write(entry.getKey()+" "+entry.getValue()+"\n");
			bw.newLine();
      }	 
		bw.close();
	}
	

}
