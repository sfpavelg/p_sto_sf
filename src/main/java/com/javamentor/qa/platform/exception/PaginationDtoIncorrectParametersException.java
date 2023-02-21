package com.javamentor.qa.platform.exception;

public class PaginationDtoIncorrectParametersException extends RuntimeException {

    private static final long serialVersionUID = 4057277886349217925L;

    public PaginationDtoIncorrectParametersException(String message) {
        super(message);
    }
}
