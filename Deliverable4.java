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
		

		ArrayList<String> elements = readFile(elementsFile);
		ArrayList<String> input = readFile(inputFile);

	}

	public static ArrayList<String> readFile(File file){
		BufferedReader reader = null;
		ArrayList<String> lines = new ArrayList<String>();

		try{
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null){
				lines.add(text);
			}
		} catch (FileNotFoundException e){
			System.out.println(file.getName() + " file could not be found");
			System.exit(1);
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (reader != null){ 
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return lines;
	}
}