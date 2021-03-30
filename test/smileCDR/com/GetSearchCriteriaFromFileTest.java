package smileCDR.com;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import smileCDR.com.GetSearchCriteriaFromFile;


public class GetSearchCriteriaFromFileTest {
	   private GetSearchCriteriaFromFile searchFromFile;
	   
	   @Before
	   public void setup() throws IOException {
		   searchFromFile = new GetSearchCriteriaFromFile("familyNames.csv");
	   }
	   
	   @Test
	   public void shouldGetCorrectSearchNames() throws IOException {
	     // Create a temporary file.
		 String[] actual =searchFromFile.getSearchStringFromFile();
	     
		 assertEquals(20,actual.length);
	     //Note: File is guaranteed to be deleted after the test finishes.
	   }
}