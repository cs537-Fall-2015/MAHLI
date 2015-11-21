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
	int size;
	Long[] data;
	String[] dataName;
	
	@SuppressWarnings("unchecked")
	public void printJSONArray(){
		System.out.println("*** START OF DATA ANALYSIS ***");
		System.out.printf("%-3s %-13s %s\n", "#", "mineral name", "count");
		System.out.println("--  ------------  -----");
		
		// Iterator to loop through the json array
		int i = 0;
		Iterator<JSONObject> iterator = getJSONArray().iterator();
		while(iterator.hasNext()){
			JSONObject innerObject = (JSONObject) iterator.next();
			dataName[i] = (String) innerObject.get("name");
			data[i] = (Long) innerObject.get("count");
			System.out.printf("%-3d %-13s %d\n", (i + 1), dataName[i], data[i]);
			i++;
		}
		System.out.println("*** END OF DATA ANALYSIS ***");
	}
	
	public JSONArray getJSONArray() {
		return jsonArray;
	}
	
	public void setJSONArray(File file){
		// JSONParser is used to parse the data
		JSONParser parser = new JSONParser();
		
		try {
			// Object to hold the parsed json file
			Object obj = parser.parse(new FileReader(file));
			// Create a JSONArray from the JSONObject
			jsonArray = (JSONArray) obj;
			size = jsonArray.size();
			data =  new Long[size];
			dataName = new String[size];
			
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
	
	public String[] getDataName() {		
		return dataName;
	}
	
	public Long[] getData() {		
		return data;
	}
	
	public int getSize() {		
		return size;
	}
}