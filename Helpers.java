import java.io.*;
import java.util.*;

public class Helpers{

	/*
	* read in file and return arraylist containing each line in file
	*/
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

	/*
	* Creating a hashmap using the element abbreviation as the key, and the element as the value
	*/
	public static HashMap<String, String> createHashMap(ArrayList<String> array){
		HashMap<String, String> hashmap = new HashMap<String, String>();

		for (int i = 0; i < array.size(); i++){
			String line = array.get(i);
			String [] split = line.split(":");
			
			hashmap.put(split[0].toLowerCase(), split[1]);
		}

		return hashmap;

	}

	public static ArrayList<String> checkElements(String line, HashMap<String, String> elementsDict){

		if (line.length() == 0 || line.equals(""))
			return null;

		StringBuilder sb = new StringBuilder(line.toLowerCase());
		int begin = 0;
		int end = 2;
		ArrayList<String> elements = new ArrayList<String>();

		while (end < sb.length()){
			String twoLetters = sb.substring(begin, end);
			String oneLetter = sb.substring(begin, end - 1);
			

			// check for abbreviations with two characterss
			if (elementsDict.get(twoLetters) != null){
				elements.add(twoLetters);
				begin += 2;
				end += 2;
			}
			// check for abbreviations with one character
			else if (elementsDict.get(oneLetter) != null){
				elements.add(oneLetter);
				begin += 1;
				end += 1;
			}
			// if neither one letter or two letter element is found, return null
			else{
				return null;
			}
			
		}

		return elements;
	}

	public static String removeNonCharacters(String line){
		String validString = "";

		for (int i = 0; i < line.length(); i++){
			if ((line.charAt(i) < 123 && line.charAt(i) > 96) || (line.charAt(i) < 91 && line.charAt(i) > 64))
				validString += line.charAt(i);
		}

		return validString;
	}
}