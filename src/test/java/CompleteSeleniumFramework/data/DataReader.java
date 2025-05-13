package CompleteSeleniumFramework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		// For fileutils you have to add the follwoing dependency in your pom.xml file
		// https://mvnrepository.com/artifact/commons-io/commons-io
		//
		// read json to string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\CompleteSeleniumFramework\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);

		// String to HashMap Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});//result={map,map1}
		
		return data;

	}

}
