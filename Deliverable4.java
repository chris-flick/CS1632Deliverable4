import java.io.*;
import java.util.*;

public class Deliverable4{
	public static void main(String args []){
		if (args.length != 1){
			System.out.println("Incorrect arguments. Run command 'java Deliverable4 <filename>'");
			System.exit(1);
		}

		File inputFile = new File(args[0]);
		File elementsFile = new File("elements.txt");


		ArrayList<String> elements = Helpers.readFile(elementsFile);
		ArrayList<String> input = Helpers.readFile(inputFile);

		// if either are null, then there was a FileNotFoundException or IOException, so exit
		if (elements == null || input == null)
			System.exit(1);

		HashMap<String, String> elementsDict = Helpers.createHashMap(elements);

		for (int i = 0; i < input.size(); i++){
			String line = input.get(i);
			ArrayList<String> result = Helpers.checkElements(line, elementsDict);
			if(result != null){
			for(int j = 0; j<result.size(); j++){
				if(j == result.size()-1) System.out.println(result.get(j));
				else System.out.print(result.get(j) + " - ");
			}

			for(int j = 0; j<result.size(); j++){
				if(j == result.size()-1) System.out.println(elementsDict.get(result.get(j)));
				else System.out.print(elementsDict.get(result.get(j)) + " - ");
			}
		}else{
			System.out.println("Could not create name \"" + line + "\" out of elements.");
		}
		System.out.println();
	}





	}




}
