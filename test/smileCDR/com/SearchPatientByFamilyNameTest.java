package smileCDR.com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.junit.Before;
import org.junit.Test;

import ca.uhn.fhir.rest.api.CacheControlDirective;

public class SearchPatientByFamilyNameTest {
	  private SearchPatientByFamilyName searchEngine;
	   
	   @Before
	   public void setup() throws IOException {
		   searchEngine = new SearchPatientByFamilyName();
	   }
	   
	   @Test
	   public void shouldReturnEmptyListForNoExistFamilyName() {
		   String noExistFamilyName ="aaaabbbccc";
		   Bundle bundle =searchEngine.getSearchFamilyNamesResult(noExistFamilyName, new CacheControlDirective());
		   assertEquals(0,bundle.getEntry().size());
	   }
	   
	   @Test
	   public void shouldReturnCorrectFamilyName() {
		   String familyName ="Smith";
		   Bundle bundle =searchEngine.getSearchFamilyNamesResult(familyName, new CacheControlDirective());
		   List<String> names =searchEngine.getNames(bundle);
		   String name=names.get(0);
		   assertTrue(name.contains(familyName));
	   }
}
