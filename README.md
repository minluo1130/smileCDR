# HAPI FHIR Playground: Basic

This project is a skeleton project for using [HAPI FHIR](https://hapifhir.io) to access a public [FHIR](http://hl7.org/fhir/) server hosted at [http://hapi.fhir.org/baseR4](http://hapi.fhir.org/baseR4).

### Getting Started:

* Run SampleClient, it reads "familyNames.csv" file and query the family name in this file and print running average time.

### Basic Tasks:

* [ ] Modify `SampleClient` so that it prints the first and last name, and birth date of each Patient to the screen

* [ ] Sort the output so that the results are ordered by the patient's first name

* [ ] Commit your work

### Intermediate Tasks:

* [ checked] Create a text file containing 20 different last names

* [ checked] Modify 'SampleClient' so that instead of searching for patients with last name 'SMITH',
      it reads in the contents of this file and for each last name queries for patients with that last name

* [ checked] Print the average response time for these 20 searches by implementing an IClientInterceptor that uses
      the requestStopWatch to determine the response time of each request.

* [ checked] Run this loop three times, printing the average response time for each loop.  The first two times the loop should
      run as described above.  The third time the loop of 20 searches is run, the searches should be performed with
      caching disabled.

* [ checked] If there is enough time between runs, you should expect to see loop 2 with a shorter average response time than loop 1 and 3.
	Loop 2 average time is about half of the loop 1 and 3.

* [ checked] Please include unit tests for your work
	added two test file there
	
* [ checked] Commit your work

### Optimization

* instead of using one family name for each query, we can use multiple names. so change code to use "values(string[])", only one query can finish this task
			  
			  	client.search()
				.forResource("Patient")
				.cacheControl(cache)
				.where(Patient.FAMILY.matches()
				.values(searchStrings))
				.returnBundle(Bundle.class)
				.execute();

				