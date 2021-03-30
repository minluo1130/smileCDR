package smileCDR.com;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class GetSearchCriteriaFromFile {
	private static final String DELIMITER =",";
	private String filePath;
	
	public GetSearchCriteriaFromFile(String file) {
		this.filePath =file;
	}
	
	public String[] getSearchStringFromFile() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
   	 
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
             
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8).split(DELIMITER);
	}
	
}
