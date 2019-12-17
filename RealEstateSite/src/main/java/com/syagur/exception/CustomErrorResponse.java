package com.syagur.exception;

import java.time.LocalDateTime;

public class CustomErrorResponse {

    private String message;
    private LocalDateTime time;
    private int status;

    public CustomErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.time = LocalDateTime.now();
    }

    public CustomErrorResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
