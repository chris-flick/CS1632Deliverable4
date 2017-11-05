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

		HashMap<String, String> elementsDict = Helpers.createHashMap(elements);


	}




}