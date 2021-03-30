package smileCDR.com;
import java.io.IOException;


import ca.uhn.fhir.rest.api.CacheControlDirective;

public class SampleClient {

    public static void main(String[] theArgs) throws IOException {
    	SearchPatientByFamilyName searchEngine = new SearchPatientByFamilyName();
    	TimeMonitorInterceptor timeMonitor = new TimeMonitorInterceptor();
    	searchEngine.setTimeMonitorInterceptor(timeMonitor);
    	
    	GetSearchCriteriaFromFile searchFromFile = new GetSearchCriteriaFromFile("familyNames.csv");
    	
    	String[] searchNames = searchFromFile.getSearchStringFromFile();
    	
    	CacheControlDirective cacheControl = new CacheControlDirective();
    	cacheControl.setNoCache(false);
    	cacheControl.setNoStore(false);
    	
    	System.out.print("First time searching..");
    	for(String curSearch:searchNames) {
    		searchEngine.getSearchFamilyNamesResult(curSearch,cacheControl);
//    		Bundle bundle=searchEngine.getSearchFamilyNamesResult(curSearch,cacheControl);
//    		searchEngine.printWithOrder(searchEngine.getNames(bundle));
    	}
    	
    	System.out.println("average search time:" + timeMonitor.getAverage());
    	
    	
    	System.out.print("Second time searching with cache..");
    	timeMonitor.resetTime();
    	
    	for(String curSearch:searchNames) {
    		searchEngine.getSearchFamilyNamesResult(curSearch,cacheControl);
    	}
    	
    	System.out.println("Average search time:" +timeMonitor.getAverage());
    	
    	
    	cacheControl.setNoCache(true);
    	cacheControl.setNoStore(true);
    	
    	System.out.print("Thire time searching without cache..");
    	timeMonitor.resetTime();
    	
    	for(String curSearch:searchNames) {
    		searchEngine.getSearchFamilyNamesResult(curSearch,cacheControl);
    	}
    	
    	System.out.println("Average search time:" +timeMonitor.getAverage());
    	
	    System.out.println("finished");

    }
}
