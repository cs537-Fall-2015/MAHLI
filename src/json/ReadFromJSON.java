package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadFromJSON {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// The location of the data file to read from
		String myFilePath = "C:\\Users\\hensan\\Documents\\workspace2\\MAHLI\\data2.json";
		
		// JSONParser is used to parse the data
		JSONParser parser = new JSONParser();
		
		try {
			// Object to hold the parsed json file
			Object obj = parser.parse(new FileReader(myFilePath));
			// Create a JSONArray from the JSONObject
			JSONArray jsonArray = (JSONArray) obj;
			
			System.out.printf("%-14s %s\n", "mineral name", "count");
			System.out.println("------------   -----");
			
			// Iterator to loop through the json array
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while(iterator.hasNext()){
				JSONObject innerObject = (JSONObject) iterator.next();
				String name = (String) innerObject.get("name");
				Long count = (Long) innerObject.get("count");
				System.out.printf("%-14s %d\n", name, count);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No file found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O exception found.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Parse exception found.");
			e.printStackTrace();
		}
	}
}