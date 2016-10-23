package assign1;

import java.io.IOException;

public class Ladder {
	

	public static void main(String[] args) throws IOException{
		
		String json = Operations.readJsonFile();
		Operations.parse(json);
				
	}


}


