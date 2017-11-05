import java.io.*;
import java.util.*;

public class Helpers{
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

	public static HashMap<String, String> createHashMap(ArrayList<String> array){
		HashMap<String, String> hashmap = new HashMap<String, String>();

		for (int i = 0; i < array.size(); i++){
			String line = array.get(i);
			String [] split = line.split(":");
			
			
		}

		return hashmap;

	}
}