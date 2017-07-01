package com.oversea.api.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;

/**
 *  自动建立3个索引，因为请求表每月一张自动建立，所以需要制定索引
 * @author fj
 */
@Document(collection="REQUEST_STATISTICS_RECORD")
public class RequestStatisticsRecord  implements Serializable{

    private static final long serialVersionUID = -1558130197372878496L;
    private String method;
	
	private Date startTime;
	
	private long executionTime;
	
	private String resultStatus;
	
	private RequestBaseParams requestBaseParams;
	
	private ResponseBaseParams responseBaseParams;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public RequestBaseParams getRequestBaseParams() {
		return requestBaseParams;
	}

	public void setRequestBaseParams(RequestBaseParams requestBaseParams) {
		this.requestBaseParams = requestBaseParams;
	}

	public ResponseBaseParams getResponseBaseParams() {
		return responseBaseParams;
	}

	public void setResponseBaseParams(ResponseBaseParams responseBaseParams) {
		this.responseBaseParams = responseBaseParams;
	}
}
