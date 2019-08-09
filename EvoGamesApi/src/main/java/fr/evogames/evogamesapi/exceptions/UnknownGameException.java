package fr.evogames.evogamesapi.exceptions;

public class UnknownGameException extends Exception {

    private String message;

    public UnknownGameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
