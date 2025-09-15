package com.Code81.books.error;

public class BookApiException extends RuntimeException {
    public BookApiException(String message) {
        super(message);
    }


    public BookApiException() {
        super();
    }

}
