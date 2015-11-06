package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadFromJSON {
	
	JSONObject jsonObject = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	
	@SuppressWarnings("unchecked")
	public void printJSONArray(File file){
		// JSONParser is used to parse the data
		JSONParser parser = new JSONParser();
		
		try {
			// Object to hold the parsed json file
			Object obj = parser.parse(new FileReader(file));
			// Create a JSONArray from the JSONObject
			jsonArray = (JSONArray) obj;
			setJSONArray(jsonArray);
			
			System.out.printf("%-3s %-13s %s\n", "#", "mineral name", "count");
			System.out.println("--  ------------  -----");
			
			// Iterator to loop through the json array
			int i = 1;
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while(iterator.hasNext()){
				JSONObject innerObject = (JSONObject) iterator.next();
				String name = (String) innerObject.get("name");
				Long count = (Long) innerObject.get("count");
				System.out.printf("%-3d %-13s %d\n", i, name, count);
				i++;
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
	
	public JSONArray getJSONArray() {		
		return jsonArray;
	}
	
	public void setJSONArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
}