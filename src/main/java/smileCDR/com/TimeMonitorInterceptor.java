package smileCDR.com;
import java.io.IOException;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;

@Interceptor
public class TimeMonitorInterceptor implements IClientInterceptor {
	private int  count;
	private long totalTimeInMillis;
	private static final int CAPACITY =1000;

	public TimeMonitorInterceptor() {
		super();
		count=0;
		totalTimeInMillis =0L;
	}

	@Override
	@Hook(Pointcut.CLIENT_REQUEST)
	public void interceptRequest(IHttpRequest theRequest) {
		// TODO Auto-generated method stub
		if(count>CAPACITY) {
			resetTime();
		}
		
		count++;
	}
	
	public void resetTime() {
		this.count =0;
		this.totalTimeInMillis=0L;
	}

	@Override
	@Hook(Pointcut.CLIENT_RESPONSE)
	public void interceptResponse(IHttpResponse theResponse) throws IOException {
		// TODO Auto-generated method stub
		long curTime =theResponse.getRequestStopWatch().getMillis();
		
		totalTimeInMillis+=curTime;
	}
	
	public double getAverage() {
		if(count!=0) return totalTimeInMillis/count;
		
		return 0.0;
	}
}
