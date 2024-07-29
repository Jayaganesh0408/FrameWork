package SelfLearning.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getDatafromJSON() throws IOException{
		// Converting json to string using FileUtils(commons.io dependency)
		//"readFileToString" method got deprecated and to use new method we need to pass Char encoding "StandardCharsets.UTF_8"
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\SelfLearning\\Data\\TestData.json"), StandardCharsets.UTF_8);
		
		//converting String to Hasmap lists using "Jackon databind" dependency and "ObjectMapper" method
	
		ObjectMapper mapper = new ObjectMapper(); // create object of class to use methods of that class
		List<HashMap<String,String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
		//This data has Hashmaps in format like { {map}, {map2}, {map3}, . . . }
	
	}

}
