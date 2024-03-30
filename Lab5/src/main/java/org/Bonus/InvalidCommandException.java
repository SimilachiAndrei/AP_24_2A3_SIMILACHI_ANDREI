package org.Bonus;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(Exception exception) {
        super("Invalid command.", exception);
    }
}
