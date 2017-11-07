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
			System.out.println("Error: File \"" + file.getName() + "\" does not exist.");
			return null;
		} catch (IOException e){
			System.out.println("There has been an IO error. Please try again");
			return null;
		} finally {
			try {
				if (reader != null){
					reader.close();
				}
			} catch (IOException e) {
				System.out.println("There has been an IO error. Please try again");
				return null;
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
			// elements.txt has been reformatted so that it can be easily split off of ":" character
			String line = array.get(i);
			String [] split = line.split(":");

			// make abbreviation lowercase in order to simplify the process of matching by getting rid of lettercase
			hashmap.put(split[0].toLowerCase(), split[1]);
		}

		return hashmap;

	}

	public static ArrayList<String> checkElements(String line, HashMap<String, String> elementsDict){

		if (line.length() == 0 || line.equals(""))
			return null;

		// make string lowercase and call removeNonCharacters() method to remove anything that isn't in the alphabet
		StringBuilder sb = new StringBuilder(removeNonCharacters(line.toLowerCase()));
		if(sb.toString().equals("")) return null;
		else sb.append(" ");

		int check = 0;
		int begin = 0;
		int end = 2;
		ArrayList<String> elements = new ArrayList<String>();

		while (end <= sb.length()){
			// get two letter substring in order to try to match to a Two letter element
			String twoLetters = sb.substring(begin, end);
			// get one letter substring in order to try to match to a One letter element
			String oneLetter = sb.substring(begin, end - 1);


			// check for abbreviations with two characterss
			if (elementsDict.get(twoLetters) != null){
				elements.add(twoLetters);
				begin += 2;
				end +=2;
			}
			// check for abbreviations with one character
			else if (elementsDict.get(oneLetter) != null){
				elements.add(oneLetter);
				begin += 1;
				end +=1;
			}
			// if neither one letter or two letter element is found, return null
			else{
				check = 1;
				break;
			}

		}

		// if above while loop doesn't work, need to run again but this time search
		// for 1 letter abbreviations in order to account for all possible combinations
		if (check == 1){
			begin = 0;
			end = 2;
			elements = new ArrayList<String>();

			while (end <= sb.length()){
				// get two letter substring in order to try to match to a Two letter element
				String twoLetters = sb.substring(begin, end);
				// get one letter substring in order to try to match to a One letter element
				String oneLetter = sb.substring(begin, end - 1);



				// check for abbreviations with one character
				if (elementsDict.get(oneLetter) != null){
					elements.add(oneLetter);
					begin += 1;
					end +=1;
				}
				// check for abbreviations with two characterss
				else if (elementsDict.get(twoLetters) != null){
					elements.add(twoLetters);
					begin += 2;
					end +=2;
				}
				// if neither one letter or two letter element is found, return null
				else{
					return null;
				}

			}
		}
		return elements;
	}

	// strip the string of any characters that aren't A-Z or a-z
	public static String removeNonCharacters(String line){
		String validString = "";

		for (int i = 0; i < line.length(); i++){
			// use ASCII range to filter out characters that aren't a-z or A-Z
			if ((line.charAt(i) < 123 && line.charAt(i) > 96) || (line.charAt(i) < 91 && line.charAt(i) > 64))
				validString += line.charAt(i);
		}

		return validString;
	}

	/*
	*	takes arraylist of elements and produces an I/O friendly string consisting of the element abbreviations to print
	*/
	public static String generateElementAbbr(ArrayList<String> elements){
		StringBuilder result = new StringBuilder("");

		if(elements != null){
			for(int j = 0; j < elements.size(); j++){
				if(j == elements.size() - 1)
					result.append(capitalizeFirstLetter(elements.get(j)));
				else
					result.append(capitalizeFirstLetter(elements.get(j)) + " - ");
			}
		}
		else{
			return null;
		}
		return result.toString();
	}

	/*
	* takes arraylist of elements and dictionary and produces an I/O friendly string consisting of the element names for printing
	*/
	public static String generateElementName(ArrayList<String> elements, HashMap<String, String> elementsDict){
		StringBuilder result = new StringBuilder("");

		if(elements != null){

			for(int j = 0; j < elements.size(); j++){
				if(j == elements.size() - 1)
					result.append(elementsDict.get(elements.get(j)));
					//System.out.println(elementsDict.get(elements.get(j)));
				else
					result.append(elementsDict.get(elements.get(j)) + " - ");
					//System.out.print(elementsDict.get(elements.get(j)) + " - ");
			}
		}
		else{
			return null;
			//System.out.println("Could not create name \"" + line + "\" out of elements.");
		}
		return result.toString();
	}

	/*
	*	Created capitalize method to take care of Element abbreviations that are all lowercase
	*/
	public static String capitalizeFirstLetter(String word){
		if (word == null || word.length() == 0) {
					return word;
			}
			return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
}
