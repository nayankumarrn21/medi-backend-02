package com.neudesic.MediAssistsPolicyService.exceptions;

public class ExceptionResponse {
    private Integer errorCode;
    private String errorMessage;
    private String requestedURI;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Integer errorCode, String errorMessage, String requestedURI) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.requestedURI = requestedURI;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void setRequestedURI(String requestedURI) {
        this.requestedURI = requestedURI;
    }
}
