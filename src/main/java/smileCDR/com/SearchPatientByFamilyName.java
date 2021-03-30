package smileCDR.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Property;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

public class SearchPatientByFamilyName {
	private static final String PATIENT_NAME ="name";
	private static final String FIRST_NAME ="given";
	private static final String LAST_NAME ="family";
	private static final String BASE_URL = "http://hapi.fhir.org/baseR4";

	private IGenericClient client;
	private TimeMonitorInterceptor timeMonitor=null;
	
	public SearchPatientByFamilyName() {
		client = FhirContext.forR4().newRestfulGenericClient(BASE_URL);
		client.registerInterceptor(new LoggingInterceptor(false));
	}
	
	public void setTimeMonitorInterceptor(TimeMonitorInterceptor monitor) {
		this.timeMonitor =monitor;
		client.registerInterceptor(timeMonitor);
	}
	

	public Bundle getSearchFamilyNamesResult(String searchStr,CacheControlDirective cache)  {
		Bundle result = null;

		result = client.search()
				.forResource("Patient")
				.cacheControl(cache)
				.where(Patient.FAMILY.matches()
				.value(searchStr))
				.returnBundle(Bundle.class)
				.execute();

		//System.out.println(String.format("average search time in millisecond is:%f", averageTimeInMills));

		return result;
	}


	public List<String> getNames(Bundle result) {
		List<String> nameList = new ArrayList<>();
		if(result==null) return nameList;
		
		List<BundleEntryComponent> list = result.getEntry();
		for (BundleEntryComponent component : list) {
			Property property = component.getResource().getChildByName(PATIENT_NAME);
			if(property==null) continue;
			for (Base base : property.getValues()) {
				String firstName =(base.getChildByName(FIRST_NAME).getValues().isEmpty()) ?" ": base.getChildByName(FIRST_NAME).getValues().get(0).toString(); 
				String lastName =(base.getChildByName(LAST_NAME).getValues().isEmpty()) ?" ": base.getChildByName(LAST_NAME).getValues().get(0).toString();
			
				nameList.add(firstName + " " + lastName);
			}
		}

		return nameList;
	}

	public void printWithOrder(List<String> names) {
		Collections.sort(names);

		names.forEach(name -> System.out.println(name));
	}

}
