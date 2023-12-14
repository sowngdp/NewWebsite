package com.news.QLLTC.common;

public class HttpStatusException  extends RuntimeException{
    private final int httpStatus;

    public HttpStatusException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
