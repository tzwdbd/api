package com.oversea.api.exception;

import com.oversea.common.exception.ProcessStatusCode;


public class RequestProcessInterruptException extends Exception {
    private ProcessStatusCode code;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1665955705320762549L;
    
    public RequestProcessInterruptException(ProcessStatusCode code) {
        this.setCode(code);
    }

    public RequestProcessInterruptException(ProcessStatusCode apiException, Exception e) {
        super(e);
        this.setCode(apiException);
    }

    public RequestProcessInterruptException(ProcessStatusCode apiException, String msg) {
        super(msg);
        this.setCode(apiException);
    }

    public void setCode(ProcessStatusCode code) {
        this.code = code;
    }

    public ProcessStatusCode getCode() {
        return code;
    }
    
    @Override
    public String getMessage() {
        if(code != null)
            return code.getCode();
        else
            return super.getMessage();
    }
}