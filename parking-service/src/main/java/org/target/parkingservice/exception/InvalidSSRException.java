package org.target.parkingservice.exception;

public class InvalidSSRException extends Exception {
    public InvalidSSRException(String ssr) {
        super(ssr+" SSR does not exists");
    }
}
