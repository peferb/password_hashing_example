package se.peferb.security.repository;

public class HashingException extends RuntimeException {
    public HashingException(String message, Exception e) {
        super(message, e);
    }
}
