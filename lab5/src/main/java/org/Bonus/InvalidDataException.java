package org.Bonus;

public class InvalidDataException extends Exception {
    public InvalidDataException(Exception exception) {
        super("Invalid data.", exception);
    }

}
