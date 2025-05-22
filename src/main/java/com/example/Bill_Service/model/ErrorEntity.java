package com.example.Bill_Service.model;

import jakarta.persistence.Transient;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public class ErrorEntity {

    private LocalDateTime localDateTime;
    private String message;

    @Transient
    private String errorAuthor; // @Transient peut être ajouté si nécessaire
    private int httpStatus;

    public ErrorEntity(LocalDateTime localDateTime, int httpStatus, String errorAuthor, String message) {
        this.localDateTime = localDateTime;
        this.httpStatus = httpStatus;
        this.errorAuthor = errorAuthor;
        this.message = message;
    }

    public ErrorEntity() {
    }

    public ErrorEntity(LocalDateTime now, String message, Object errorAuthor, int value) {
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorAuthor() {
        return errorAuthor;
    }

    public void setErrorAuthor(String errorAuthor) {
        this.errorAuthor = errorAuthor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

