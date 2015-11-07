package json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteToJSON {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// This is the object that will be stored in JSON
		//MyClassHere myObject = new MyClassHere(5);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonMainObject = new JSONObject();
		JSONObject jsonArrayObject = new JSONObject();
		
		// You need to define this filepath yourself
		// This is where the file will be written to
		// In this example it is written to my desktop
		// If Example.json doesn't exist it will be created
		String myFilePath = "C:\\Users\\hensan\\Documents\\workspace2\\MAHLI\\data2.json";
		
		jsonArrayObject.put("count", 1234);
		jsonArrayObject.put("name", "GOLD");
		jsonArray.add(jsonArrayObject);
		jsonArrayObject = new JSONObject();
		jsonArrayObject.put("count", 4321);
		jsonArrayObject.put("name", "SILVER");
		jsonArray.add(jsonArrayObject);

		// Instantiate the writer since we're writing to a JSON file.
		FileWriter writer = null;
		try {
			writer = new FileWriter(myFilePath);
			// Write the file
			writer.write(jsonArray.toJSONString());
			// Flush and close the Writer
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
