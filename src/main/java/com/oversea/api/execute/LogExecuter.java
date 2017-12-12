package com.oversea.api.execute;

import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;

public interface LogExecuter {
	
	public void init();
	
	public void log(String from, RequestBaseParams requestParams, ResponseBaseParams responseParams);
	
	public void close();
}
