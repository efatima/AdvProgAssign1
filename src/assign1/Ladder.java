package assign1;

import java.io.IOException;
import java.util.Scanner;

public class Ladder {	

	public static void main(String[] args) throws IOException{
		
		String json = Operations.readJsonFile(); //Reads JSON file and saves it in the form of a string
		Operations.parse(json); //converts data from String version JSON to objects of DictData
		
		boolean sameLength = Operations.takeInput();
		if(sameLength == false){
			System.out.println("Error! Both words need to be of same Length!");
		}
		
		System.out.println("Enter 'y' to calculate path for all Nodes, create Graph and to print Frequency Distribution Table: ");
		Scanner scan = new Scanner(System.in);
		String option = scan.nextLine();
		
		if(option.compareTo("y")==0){
		    Operations.printHashMap();
		    
		}
		else{
			System.out.println("Exited..");
		}
						
       return;
				
	}


	

}


