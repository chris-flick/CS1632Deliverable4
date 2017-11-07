import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Deliverable4{
	public static void main(String args []){
		long readFileTime = 0;
		long hashMapTime = 0;
		long checkElementsTime = 0;
		long generateElementAbbrTime = 0;
		long generateElementNameTime = 0;

		if (args.length != 1){
			System.out.println("Error: Enter only one argument, the file to read.");
			System.exit(1);
		}

		
		File inputFile = new File(args[0]);
		File elementsFile = new File("elements.txt");
		


		long startTime = System.nanoTime();
		ArrayList<String> elements = Helpers.readFile(elementsFile);
		ArrayList<String> input = Helpers.readFile(inputFile);
		long endTime = System.nanoTime();
		readFileTime = (endTime - startTime);

		// if either are null, then there was a FileNotFoundException or IOException, so exit
		if (elements == null || input == null)
			System.exit(1);

		startTime = System.nanoTime();
		HashMap<String, String> elementsDict = Helpers.createHashMap(elements);
		endTime = System.nanoTime();
		hashMapTime = (endTime - startTime);

		for (int i = 0; i < input.size(); i++){
			String line = input.get(i);

			if (line.equals(""))
				continue;

			startTime = System.nanoTime();
			ArrayList<String> result = Helpers.checkElements(line, elementsDict);
			endTime = System.nanoTime();
			checkElementsTime += (endTime - startTime);

			startTime = System.nanoTime();
			String elementAbbr = Helpers.generateElementAbbr(result);
			endTime = System.nanoTime();
			generateElementAbbrTime += (endTime - startTime);

			startTime = System.nanoTime();
			String elementNames = Helpers.generateElementName(result, elementsDict);
			endTime = System.nanoTime();
			generateElementNameTime += (endTime - startTime);

			if (elementAbbr != null)
				System.out.println(elementAbbr);
			else
				System.out.println("Could not create name \"" + line + "\" out of elements.");

			if (elementNames != null)
				System.out.println(elementNames);

			System.out.println();
		}

		System.out.println("readFile(): " + readFileTime);
		System.out.println("createHashMap(): " + hashMapTime);
		System.out.println("checkElements(): " + checkElementsTime);
		System.out.println("generateElementAbbr(): " + generateElementAbbrTime);
		System.out.println("generateElementNameTime(): " + generateElementNameTime);

	}




}
